package io.github.biezhi.quartz;

import io.github.biezhi.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDateTime;

/**
 * cron 表达式触发器例子
 */
public class CronTriggerExample {

    public static void main(String[] args) throws SchedulerException, InterruptedException {

        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("myjob1", "mygroup1").build();
        System.out.println("启动时间: " + LocalDateTime.now().toString());

        // 每5秒调用一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("mytrigger2", "mygroup2")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
        // 30 秒后关闭
        Thread.sleep(30_000);
        scheduler.shutdown(true);
    }
}