package fr.osb.smartf.agent.worker.main;

import fr.osb.smartf.agent.worker.main.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan(basePackages = "fr.osb.smartf")
@EnableDiscoveryClient
@EnableAutoConfiguration(
        exclude = {org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
@Import(value = {SwaggerConfig.class})
/**
 * Created by mpaltanea on 01.04.2016.
 */
public class WorkerMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WorkerMain.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WorkerMain.class, args);
    }
}
