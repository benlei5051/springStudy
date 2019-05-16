     PowerMock有两个重要的注解：
 
       –@RunWith(PowerMockRunner.class)
 
       –@PrepareForTest( { YourClassWithEgStaticMethod.class })
 
       如果你的测试用例里没有使用注解@PrepareForTest，那么可以不用加注解@RunWith(PowerMockRunner.class)，反之亦然。当你需要使用PowerMock强大功能（Mock静态、final、私有方法等）的时候，就需要加注解@PrepareForTest。
       
       
 六 、无所不能的PowerMock

       (1) 验证静态方法：

       PowerMockito.verifyStatic();
       Static.firstStaticMethod(param);

       (2) 扩展验证:

       PowerMockito.verifyStatic(Mockito.times(2)); //  被调用2次                                Static.thirdStaticMethod(Mockito.anyInt()); // 以任何整数值被调用

       (3) 更多的Mock方法

       http://code.google.com/p/powermock/wiki/MockitoUsage13

      七、PowerMock简单实现原理

       •  当某个测试方法被注解@PrepareForTest标注以后，在运行测试用例时，会创建一个新的org.powermock.core.classloader.MockClassLoader实例，然后加载该测试用例使用到的类（系统类除外）。

       •   PowerMock会根据你的mock要求，去修改写在注解@PrepareForTest里的class文件（当前测试类会自动加入注解中），以满足特殊的mock需求。例如：去除final方法的final标识，在静态方法的最前面加入自己的虚拟实现等。

       •   如果需要mock的是系统类的final方法和静态方法，PowerMock不会直接修改系统类的class文件，而是修改调用系统类的class文件，以满足mock需求。