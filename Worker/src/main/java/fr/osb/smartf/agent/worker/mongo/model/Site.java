package fr.osb.smartf.agent.worker.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mpaltanea on 13.04.2016.
 */
@Document(collection = "site")
public class Site extends MongoObject {

    public Site createDTO() {
        return new Site();
    }

    @Id
    private String id;
    private String name;
    private String code;
    private String town;
    private String address;
    private String internalGuid;
    private String externalGuid;
    private Double latitude;
    private Double longitude;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInternalGuid() {
        return internalGuid;
    }

    public void setInternalGuid(String internalGuid) {
        this.internalGuid = internalGuid;
    }

    public String getExternalGuid() {
        return externalGuid;
    }

    public void setExternalGuid(String externalGuid) {
        this.externalGuid = externalGuid;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public void importLine(String line) {
        String[] tokens = line.split(";");
        this.name = tokens[0];
        this.code = tokens[1];
    }

    @Override
    public void importMap(Map map) {
    }
}
