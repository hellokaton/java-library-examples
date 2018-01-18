package io.github.biezhi.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ExceptionJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("五秒一次的体验...");
        throw new JobExecutionException("异常消息: XXX出错了");
    }

} 