package com.self.tools.task;

import org.quartz.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@EnableScheduling
@Component
public class MyCronTask {
    @Resource(name = "jobDetail")
    private JobDetail jobDetail;
    @Resource(name = "jobTrigger")
    private CronTrigger cronTrigger;
    @Resource(name = "scheduler")
    private Scheduler scheduler;

    public void scheduleUpdateCronTrigger() throws SchedulerException {
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
        String currentCron = trigger.getCronExpression();
        String searchCron = "0/10 * * * * ？";

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(searchCron);
        trigger.getTriggerBuilder().withIdentity(cronTrigger.getKey()).withSchedule(cronScheduleBuilder).build();
        scheduler.rescheduleJob(cronTrigger.getKey(), trigger);
    }

}
