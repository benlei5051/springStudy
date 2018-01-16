package org.andy.beans.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: andy
 * @Date: 2017/9/25 15:25
 * @Description:
 */
@Component

public class ScheduledTasks {

    private final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * @Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
     * @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
     * @Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
     * @Scheduled(cron=” /5 “) ：通过cron表达式定义规则，什么是cro表达式，自行搜索引擎。
     */
    @Scheduled(fixedRate = 5000)
    public void getTask1() {
        System.out.println("任务1，当前时间：" + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0/5 * *  * * ? ")
    public void getTask2() {
        System.out.println("任务2，当前时间：" + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0/5 * *  * * ? ")
    public void getTaskLog4j() {
        logger.info("Log4j，当前时间：" + dateFormat.format(new Date()));
    }
}
