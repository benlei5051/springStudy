https://blog.csdn.net/gaojp008/article/details/80583301
https://blog.csdn.net/zhenwei1994/article/details/81460419
https://blog.csdn.net/danielzhou888/article/details/74740817
### hiberante validate 注解的使用

    @Null 只能是null
    @NotNull 不能为null 注意用在基本类型上无效，基本类型有默认初始值
    @AssertFalse 必须为false
    @AssertTrue 必须是true
    
    字符串/数组/集合检查：(字符串本身就是个数组)
    @Pattern(regexp="reg") 验证字符串满足正则
    @Size(max, min) 验证字符串、数组、集合长度范围
    @NotEmpty 验证字符串不为空或者null
    @NotBlank 验证字符串不为null或者trim()后不为空
    
    数值检查：同时能验证一个字符串是否是满足限制的数字的字符串
    @Max 规定值得上限int
    @Min 规定值得下限
    @DecimalMax("10.8") 以传入字符串构建一个BigDecimal，规定值要小于这个值 
    @DecimalMin 可以用来限制浮点数大小
    @Digits(int1, int2) 限制一个小数，整数精度小于int1；小数部分精度小于int2，不能限制为整数
    @Digits 无参数，验证字符串是否合法
    @Range(min=long1,max=long2) 检查数字是否在范围之间
    这些都包括边界值
    日期检查：Date/Calendar
    @Post 限定一个日期，日期必须是过去的日期
    @Future 限定一个日期，日期必须是未来的日期
    
    其他验证：
    @Vaild 递归验证，用于对象、数组和集合，会对对象的元素、数组的元素进行一一校验
    @Email 用于验证一个字符串是否是一个合法的右键地址，空字符串或null算验证通过
    @URL(protocol=,host=,port=,regexp=,flags=) 用于校验一个字符串是否是合法URL
    
 ### 使用
 
       @DecimalMin("0.5")
       @DecimalMAX("3.0")
       @Digits(integer=1,fraction=1)
       @NotBlank(message="radius不能为空")
       private String radius; //半径
       
       
       
 
 
 
 public class User {
 	
 	private String userName;
 	
 	private String password;
 	
 	private String address;
 	
 	private String age;
 	
 	private String sex;
 }      
       
  @GetMapping("/jsonview")// 需求：展示User里面的全部信息
  	public User jsonDetailViewController(){
  		User u = new User();
  		u.setAddress("chongqing");
  		u.setAge("25");
  		u.setPassword("123456");
  		u.setSex("nan");
  		u.setUserName("chhliu");
  		return u;
  	}
  	
  	@GetMapping("/jsonviews")// 需求，只展示User里面的userName字段
  	public User jsonSimpleViewController(){
  		User u = new User();
  		u.setAddress("chongqing");
  		u.setAge("25");
  		u.setPassword("123456");
  		u.setSex("nan");
  		u.setUserName("chhliu");
  		return u;
  	}
  	
  	
public class User {
	
	public interface ServiceGroup1{};// 接口一：用于仅展示userName字段
	
	public interface ServiceGroup2 extends ServiceGroup1 {};// 接口二，继承自接口一，用于展示全部的字段
	
	@JsonView(ServiceGroup1.class)// 通过@JsonView+接口，来决定该字段在哪个业务里面展示
	private String userName;
	
	@JsonView(ServiceGroup2.class)
	private String password;
	
	@JsonView(ServiceGroup2.class)
	private String address;
	
	@JsonView(ServiceGroup2.class)
	private String age;
	
	@JsonView(ServiceGroup2.class)
	private String sex;
}

@GetMapping("/jsonview")
	@JsonView(User.ServiceGroup1.class)// 该controller里面展示的是ServiceGroup1标注的字段
	public User jsonDetailViewController(){
		User u = new User();
		u.setAddress("chongqing");
		u.setAge("25");
		u.setPassword("123456");
		u.setSex("nan");
		u.setUserName("chhliu");
		return u;
	}
	
	@GetMapping("/jsonviews")// 该controller里面展示的是ServiceGroup2标注的字段
	@JsonView(User.ServiceGroup2.class)
	public User jsonSimpleViewController(){
		User u = new User();
		u.setAddress("chongqing");
		u.setAge("25");
		u.setPassword("123456");
		u.setSex("nan");
		u.setUserName("chhliu");
		return u;
	}
测试结果
{"userName":"chhliu"}
{"userName":"chhliu","password":"123456","address":"chongqing","age":"25","sex":"nan"}


       
       
 