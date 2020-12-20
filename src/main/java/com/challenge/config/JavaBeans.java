package com.challenge.config;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Configuration
public class JavaBeans {

    @Autowired
    AppConf appConf;

    @Bean
    RestTemplate restTemplate () {
        return new RestTemplate();
    }

    @Bean
    Connection rabbitMqConnection () throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(appConf.getRabbitHost());
        return factory.newConnection();
    }

    @Bean
    Channel rabbitMqChannel(Connection connection) throws IOException {
        Channel channel = connection.createChannel();
        channel.queueDeclare(Constants.SMS_QUEUE_1, false, false, false, null);
        //create other queues ...
        return channel;
    }

    @Bean
    public StorageProvider storageProvider(JobMapper jobMapper) {
        InMemoryStorageProvider storageProvider = new InMemoryStorageProvider();
        storageProvider.setJobMapper(jobMapper);
        return storageProvider;
    }

}
