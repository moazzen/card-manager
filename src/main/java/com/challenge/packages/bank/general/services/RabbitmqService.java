package com.challenge.packages.bank.general.services;

import com.challenge.config.Constants;
import com.challenge.packages.bank.card.dao.CustomerDao;
import com.challenge.packages.bank.card.dto.Customer;
import com.challenge.packages.bank.card.dto.Notification;
import com.challenge.packages.bank.card.dto.TransferHistoryRequest;
import com.challenge.packages.bank.remote_services.services.SmsSenderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rabbitmq.client.*;
import org.springframework.util.SerializationUtils;

import java.io.IOException;

@Service
public class RabbitmqService {

    @Autowired
    Channel channel;

    @Autowired
    SmsSenderInterface smsProvider;

    @Autowired
    CustomerDao customerDao;

    public void pushInQueue (String queueName, Object object) throws IOException {
        channel.basicPublish("", queueName, null, SerializationUtils.serialize(object));
    }

    public void consumeSmsQueueData () throws IOException {
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(
                    String consumerTag,
                    Envelope envelope,
                    AMQP.BasicProperties properties,
                    byte[] body) throws IOException {

                Notification notification = (Notification) SerializationUtils.deserialize(body);

                //process data ...
                Customer customer = customerDao.getCustomerById(notification.getCustomerId());
                notification.setRecipient(customer.getMobile());
                smsProvider.send(notification);
            }
        };
        channel.basicConsume(Constants.SMS_QUEUE_1, true, consumer);
    }

}
