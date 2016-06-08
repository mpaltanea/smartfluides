package fr.osb.smartf.agent.auth.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.GenericWebApplicationContext;

/**
 * Created by mpaltanea on 16.05.2016.
 */
@ComponentScan(basePackages = "fr.osb.smartf")
@EnableDiscoveryClient
@EnableAutoConfiguration(
        exclude = {org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
public class AuthMain extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AuthMain.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.application().setApplicationContextClass(GenericWebApplicationContext.class);
        return builder.sources(applicationClass);
    }

    private static Class<AuthMain> applicationClass = AuthMain.class;
    }


    @RestController
    class GreetingController {

    @RequestMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        return "Hello, " + name + "!";
    }
}
