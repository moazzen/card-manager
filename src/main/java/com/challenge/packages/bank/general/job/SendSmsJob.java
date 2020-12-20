package com.challenge.packages.bank.general.job;


import com.challenge.packages.bank.general.services.RabbitmqService;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class SendSmsJob {

    @Autowired
    private JobScheduler jobScheduler;

    @Autowired
    RabbitmqService rabbitmqService;

    @Bean
    void smsJob () {
        jobScheduler.scheduleRecurrently(() -> rabbitmqService.consumeSmsQueueData(), Cron.minutely());
    }
}
