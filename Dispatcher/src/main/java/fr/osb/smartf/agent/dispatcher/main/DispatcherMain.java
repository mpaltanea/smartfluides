package fr.osb.smartf.agent.dispatcher.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by mpaltanea on 06.04.2016.
 */

@ComponentScan(basePackages = "fr.osb.smartf")
@EnableDiscoveryClient
@EnableAutoConfiguration(
        exclude = {org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
public class DispatcherMain {

    public static void main(String[] args) {
        SpringApplication.run(DispatcherMain.class, args);
    }

}