package com.challenge.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("app-conf")
public class AppConf {

    String rabbitHost;
    String transferMessageText;

    public String getRabbitHost() {
        return rabbitHost;
    }

    public void setRabbitHost(String rabbitHost) {
        this.rabbitHost = rabbitHost;
    }

    public String getTransferMessageText() {
        return transferMessageText;
    }

    public void setTransferMessageText(String transferMessageText) {
        this.transferMessageText = transferMessageText;
    }
}
