package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**记录方法调用开始时间*/
    ThreadLocal<Long> startTime = new ThreadLocal<>();

 /*   1.execution (*com.wul.spring.aop.impl.AtithmeticCalculator.*(..))
    匹配 ArithmeticCalculator 中声明的所有方法,
    第一个 * 代表任意修饰符及任意返回值. 第二个 * 代表任意方法. .. 匹配任意数量的参数.
            若目标类与接口与该切面在同一个包中, 可以省略包名.

2.execution (public * ArithmeticCalculator.*(..))
    匹配 ArithmeticCalculator 接口的所有公有方法.

3.execution(* * , *()..)
    第一个 *代表匹配任意修饰符及任意返回值, 第二个 * 代表任意类的对象,第三个 *代表任意方法,
    参数列表中的.. 匹配任意数量的参数.

4.在 AspectJ 中, 切入点表达式可以通过操作符 &&, ||, ! 结合起来.

5.可以在通知方法中声明一个类型为 JoinPoint 的参数. 然后就能访问链接细节. 如方法名称和参数值.
    public void beforeMethod(org.aspectj.lang.JoinPoint joinPoint)
    方法名：String methodName = joinPoint.getSignature().getName();
    参数值：List<Object> args = Arrays.asList(joinPoint.getArgs());*/




    // 缓存线程池
    private ExecutorService pool = Executors.newFixedThreadPool(10);
    /*切入点表达式的格式：execution([可见性] 返回类型 [声明类型].方法名(参数) [异常])

     其中【】中的为可选，其他的还支持通配符的使用:

     *  ： 匹配所有字符
     .. ：一般用于匹配多个包，多个参数
     +  ：表示类及其子类

　　运算符有：&&、||、!*/

//    @Pointcut("execution(* com.pateo.qingcloud.authority.controller..*.add*(..))")
    @Pointcut(value = "execution(* org.andy.hibernateValidator.controller..*.register(..))")
    public void controllerMethodPointcut(){ }

    @Pointcut("@annotation(util.AccountLog)")
    public void annotationPointcut() { }


    /**
     * 环绕通知：
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */

//    @Around("controllerMethodPointcut() && annotationPointcut()")
    public Object recordLog(ProceedingJoinPoint point) throws Throwable {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

	       /* String url = request.getRequestURL().toString();
	        String method = request.getMethod();
	        String uri = request.getRequestURI();
	        String queryString = request.getQueryString();*/
        //logger.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);

        String simpleTargetName = point.getTarget().getClass().getSimpleName();
        String methodName = point.getSignature().getName();

        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();  //获取参数名称

        Object[] method_param = point.getArgs(); // 获取方法参数
        StringBuilder operatingcontent = new StringBuilder();
        operatingcontent.append("Invoke Method : " + simpleTargetName + "." + methodName);
        // 将API 入参读取并进行JSON化输出
        operatingcontent.append("  Request params :");
        if(parameterNames != null){
            for (int i = 0; i < method_param.length; i++) {
                operatingcontent.append("" + parameterNames[i] + " = ");
                if (method_param[i] != null &&
                        !(method_param[i] instanceof HttpServletRequest) &&
                        !(method_param[i] instanceof HttpServletResponse)) {
                    try {
                        operatingcontent.append(new ObjectMapper().writeValueAsString(method_param[i]) + " ");
                    } catch (JsonProcessingException e) {
                        // 某些Spring 辅助参数如 BindingResult无法JSON
                        // 忽略异常
                        continue;
                    }
                } else {
                    operatingcontent.append("null\n");
                }
            }
        }
        logger.info(operatingcontent.toString());
        // result的值就是被拦截方法的返回值
        Object result = point.proceed();
        try {
            logger.info("Response:" + new ObjectMapper().writeValueAsString(result));
        } catch (JsonProcessingException e) {
            // 如果返回的而是 不能 Json序列化的对象，
            // 例如文件下载时的 Resource对象
            // 则 捕获之
        }
        return  result;



    }
   /* @After("controllerMethodPointcut() && annotationPointcut()")
    public void doAfterAdvice(JoinPoint joinPoint){
        logger.info("后置最终通知执行了!!!!");
    }*/
    /**
     * 后置异常通知
     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     *  throwing:限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     *            对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     * @param joinPoint
     * @param exception
     */
   /* @AfterThrowing(value = "controllerMethodPointcut() && annotationPointcut()",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
        //目标方法名：
        logger.info(joinPoint.getSignature().getName());
        logger.info("发生了异常!!!!!   " + exception.getMessage() );
    }*/


//   注意：这里用到了JoinPoint和RequestContextHolder。
//   1）、通过JoinPoint可以获得通知的签名信息，如目标方法名、目标方法参数信息等。
//   2）、通过RequestContextHolder来获取请求信息，Session信息。

    /**
     * 后置返回通知
     * 这里需要注意的是:
     *      如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     *      如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning：限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，
     *            对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     * @param joinPoint
     * @param keys
     */

/*    @AfterReturning(value = "controllerMethodPointcut() && annotationPointcut()",returning = "keys")
    public void doAfterReturningAdvice1(JoinPoint joinPoint,Object keys){
        logger.info("第一个后置返回通知的返回值："+keys);
    }*/


    @AfterReturning(value = "controllerMethodPointcut() && annotationPointcut()",returning = "param",argNames = "param")
//    @AfterReturning(value = "controllerMethodPointcut() && annotationPointcut()",returning = "keys")
    public void doAfterReturningAdvice2(Object param){
        logger.info("第二个后置返回通知的返回值："+param);
    }



//    当我们对web层做多个切面时，会有一个问题：究竟先去切入谁？这里引入一个优先级的问题。处理方法非常简单，
//    我们定义切面时，使用一个注解@Order(i),这个i决定着优先级的高低。
//
//    比如，现在定义了两个切面，一个@Order(3)，一个@Order(8)，那么执行时：
//
//    在切入点前的操作，按order的值由小到大执行，即：先@Order(3)，后@Order(8)；
//
//    在切入点后的操作，按order的值由大到小执行，即：先@Order(8)，后@Order(3)；


    @Before("controllerMethodPointcut()")
    public void doBefore(JoinPoint joinPoint){
        //记录调用开始时间
        startTime.set(System.currentTimeMillis());

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //记录请求日志
        logger.info("======>url；"+request.getRequestURL().toString());
        logger.info("======>httpMethod:"+request.getMethod());
        logger.info("======>ip:"+ request.getRemoteAddr());
        logger.info("======>classMethod : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("======>args："+ Arrays.toString(joinPoint.getArgs()));

    }



}


