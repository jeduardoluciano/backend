package br.com.appdosamba.backend.infra.scheduler;

import javax.enterprise.context.ApplicationScoped;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import br.com.appdosamba.backend.infra.scheduler.jobs.SQSJob;

@ApplicationScoped
public class StartJobs {

	public void initialize() {
		try {
			JobDetail job = JobBuilder.newJob(SQSJob.class).withIdentity("dummyJobName", "group1").build();

			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 * * * ?")).build();

			Scheduler scheduler = new StdSchedulerFactory().getScheduler();

			scheduler.start();
			scheduler.scheduleJob(job, trigger);

		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
