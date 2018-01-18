package io.github.biezhi.quartz;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import io.github.biezhi.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 简单触发器例子
 */
public class SimpleTriggerExample {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // 创建 JOB
        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("myjob1", "mygroup1").build();
        System.out.println("启动时间: " + LocalDateTime.now().toString());

        // 五秒后启动一次
//        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("mytrigger1", "mygroup1")
//                .startAt(new Date(Calendar.getInstance().getTimeInMillis() + 5000)).build();

        // 每5秒调用一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("mytrigger2", "mygroup2")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
        // 30 秒后关闭
        Thread.sleep(30_000);
        scheduler.shutdown(true);
    }
}