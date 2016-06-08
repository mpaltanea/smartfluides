package fr.osb.smartf.agent.worker.main.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by mpaltanea on 14.04.2016.
 */

@Configuration
@EnableMongoRepositories({"fr.osb.smartf.agent.worker.mongo.repository"})
public class MongoConfig extends AbstractMongoConfiguration {

    public static final String REPO = "Repository";

    @Value("${mongo.server.host}")
    private String host;

    @Value("${mongo.server.port}")
    private int port;

    @Override
    protected String getDatabaseName() {
        return "smartf";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(host, port);
    }

}
