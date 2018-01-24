package io.github.biezhi.quartz.job;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;

public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        JobKey    key       = jobDetail.getKey();
        System.out.println("当前 Job Key: " + key + ", 当前时间: " + LocalDateTime.now().toString());
    }

} 