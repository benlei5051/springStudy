#离线文档只能在本地运行
- 1、首先通过开发工具执行所有的测试类，不要用maven执行，如mvn clean test
- 2、然后用maven命令打包，mvn package -Dmaven.test.skip=true,最终会在pom文件配置的输出html路径下产生api文档


> @Api：修饰整个类，描述Controller的作用

> @ApiOperation：描述一个类的一个方法，或者说一个接口

> @ApiParam：单个参数描述

> @ApiModel：用对象来接收参数

> @ApiProperty：用对象接收参数时，描述对象的一个字段

> @ApiResponse：HTTP响应其中1个描述

> @ApiResponses：HTTP响应整体描述

> @ApiIgnore：使用该注解忽略这个API

> @ApiError ：发生错误返回的信息

> @ApiParamImplicitL：一个请求参数

> @ApiParamsImplicit 多个请求参数