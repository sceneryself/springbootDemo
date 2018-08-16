package com.self.tools;

import com.self.tools.task.MyScheduleTask;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 *          <dependency>
 *             <groupId>org.springframework</groupId>
 *             <artifactId>spring-context-support</artifactId>
 *         </dependency>
 */
@Configuration
public class QuartzConfiguration {

    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactoryBean(Trigger cronJobTrigger){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(cronJobTrigger);
        bean.setStartupDelay(5);
        return bean;
    }

    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean(MethodInvokingJobDetailFactoryBean detailFactoryBean){
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(detailFactoryBean.getObject());
        bean.setCronExpression("0/5 * * * * ?");
        bean.setName("myTrigger");
        return bean;
    }

    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(MyScheduleTask task){
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetObject(task);
        bean.setTargetMethod("scheduleTest");
        bean.setGroup("test_group");
        bean.setName("test");
        bean.setConcurrent(true);
        return bean;
    }

}
