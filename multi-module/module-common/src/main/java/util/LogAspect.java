/*
package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Aspect
@Component
public class LogAspect {




    // 缓存线程池
    private ExecutorService pool = Executors.newFixedThreadPool(10);


    @Pointcut("execution(* com.pateo.qingcloud.authority.controller..*.add*(..))")
    public void addMethod(){ }

    @Pointcut("execution(* com.pateo.qingcloud.authority.controller..*.edit*(..))")
    public void editMethod(){ }

    @Pointcut("execution(* com.pateo.qingcloud.authority.controller..*.delete*(..))")
    public void deleteMethod(){ }

    @Pointcut("execution(* com.pateo.qingcloud.authority.controller.system.AccountController.*(..))")
    public void accountController(){ }

    @Around("!accountController() && ( addMethod() || editMethod() || deleteMethod())")
    public Object recordLog(ProceedingJoinPoint point) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder
                .getRequestAttributes())
                .getRequest();

        List<Object> argList = new ArrayList<>();
        String optParams = "";
        String optType = "1";
        String optException  = "";
        String optResult = "";
        Object result = null;
        try {
            // 1. 获得调用方法的参数
            Object[] arguments = point.getArgs();
            // 2. 执行被调用方法
            result = point.proceed(arguments);
            // 3. 处理参数信息
            if (arguments != null) {
                for (Object arg : arguments) {
                    if (arg == null){
                        continue;
                    }
                    Class c = arg.getClass();
                    String simpleName = c.getSimpleName();
                    // 处理被代理的request对象
                    if (simpleName.equals("Servlet3SecurityContextHolderAwareRequestWrapper")
                            || simpleName.equals("RequestFacade")) {
                        continue;
                    }
                    argList.add(arg);
                }
                try {
                    optParams = JSON.toJSONString(argList);
                } catch (Exception e) {
                    // 当参数中有其他toJSONString的对象
                    int argSize = argList.size();
                    argList.remove(argSize - 1);
                    optParams = JSON.toJSONString(argList);
                }
            }
            // 4. 处理返回值
            if (result != null) {
                JSONObject json = (JSONObject)JSON.toJSON(result);
                if (json.containsKey("code")) {
                    String code = json.getString("code");
                    if (!code.equals("0") && !code.equals("200")) {
                        // 操作失败
                        optType = "2";
                    }
                }

                if (json.containsKey("statusCode")) {
                    String statusCode = json.getString("statusCode");
                    if (!statusCode.equals("0") && !statusCode.equals("200")) {
                        // 操作失败
                        optType = "2";
                    }
                }
                optResult = JSON.toJSONString(result);
            }
        } catch (Throwable throwable) {
            // 4. 执行异常
            optType = "3";
            optException = throwable.getMessage();
            throw  throwable;
        } finally {
            MethodSignature signature = (MethodSignature)point.getSignature();
            //获取被拦截的方法
            Method method = signature.getMethod();
            //获取被拦截的方法名
            String methodName = method.getName();
            //获取被截取的类
            Class<?> targetClass = point.getTarget().getClass();
            // 获取用户名
            String createBy = tokenUtil.getUserName(request);

            Log recordLog = new Log();
            recordLog.setOptType(optType);
            recordLog.setOptException(optException);
            recordLog.setOptResult(optResult);
            recordLog.setOptParams(optParams);
            recordLog.setCreateBy(createBy);
            recordLog.setOptMethod(methodName);
            recordLog.setOptModule(targetClass.getSimpleName());

            LogThread logThread = new LogThread();
            logThread.setLogRepository(logRepository);
            logThread.setRecordLog(recordLog);
            pool.execute(logThread);
        }

        return result;
    }
}


*/
