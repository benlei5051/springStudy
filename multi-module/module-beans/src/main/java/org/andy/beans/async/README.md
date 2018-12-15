https://blog.csdn.net/m0_37595562/article/details/81013909


我们可以直接在Controller中调用这个业务方法，它就是异步执行的，会在默认的线程池中去执行。
需要注意的是一定要在外部的类中去调用这个方法，如果在本类调用是不起作用的，
比如this.saveLog()。 最后在启动类上开启异步任务的执行，添加@EnableAsync即可