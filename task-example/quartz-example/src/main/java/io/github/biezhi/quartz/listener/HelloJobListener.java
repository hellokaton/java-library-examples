package io.github.biezhi.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class HelloJobListener implements JobListener {

	public static final String LISTENER_NAME = "dummyJobListenerName";

	@Override
	public String getName() {
		return LISTENER_NAME;
	}

	/**
	 * 当任务即将执行时
	 * @param context
	 */
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		String jobName = context.getJobDetail().getKey().toString();
		System.out.println("任务要开始啦");
		System.out.println("任务 : " + jobName + " 开始...");

	}

	/**
	 * 不知道什么时候运行。。。
	 *
	 * @param context
	 */
	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		System.out.println("jobExecutionVetoed");
	}

	/**
	 * 在执行任务后运行此操作
	 * @param context
	 * @param jobException
	 */
	@Override
	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
		System.out.println("执行任务后运行");

		String jobName = context.getJobDetail().getKey().toString();
		System.out.println("任务 : " + jobName + " 运行结束...");

		if (!jobException.getMessage().equals("")) {
			System.out.println("Exception thrown by: " + jobName + " Exception: " + jobException.getMessage());
		}

	}

}