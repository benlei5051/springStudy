package org.andy.beans.controller;


import org.andy.beans.service.DemoAsyncService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value="/async")
public class DemoAsyncController {

	@Resource
	private DemoAsyncService demoAsyncService;

	/**
	 * 测试异步方法调用顺序
	 */
    @RequestMapping(value = "/getTestDemoAsync", method = RequestMethod.GET)
	public @ResponseBody String getEntityById() throws Exception {

		long start = System.currentTimeMillis();

		Future<String> task1 = demoAsyncService.doTaskOne();
		Future<String> task2 = demoAsyncService.doTaskTwo();
		Future<String> task3 = demoAsyncService.doTaskThree();
		System.out.println("main先执行--------");

		while(true) {
			if(task1.isDone() && task2.isDone() && task3.isDone()) {
				// 三个任务都调用完成，退出循环等待
				break;
			}
			Thread.sleep(1000);
		}

		long end = System.currentTimeMillis();

		System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
		return "成功执行";
	}
}
