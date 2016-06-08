package fr.osb.smartf.agent.worker.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mpaltanea on 13.04.2016.
 */
@Document(collection = "index")
public class Index extends MongoObject {

    @Id
    private String id;
    private String code;
    private String value;

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void importLine(String line) {
        String[] tokens = line.split(";");
        this.code = tokens[0];
        this.value = tokens[1];
    }

    @Override
    public void importMap(Map map) {
    }
}
