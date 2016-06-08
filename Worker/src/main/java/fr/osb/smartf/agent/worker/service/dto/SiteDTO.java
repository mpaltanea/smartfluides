package fr.osb.smartf.agent.worker.service.dto;

import fr.osb.smartf.agent.worker.mongo.model.Site;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * Created by mpaltanea on 23.05.2016.
 */
public class SiteDTO {

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

    public void setId(String id) {
        this.id = id;
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Site toMongoObject() {
        Site site = new Site();
        site.setName(this.name);
        site.setTown(this.town);
        site.setAddress(this.address);
        return site;
    }

    public SiteDTO() {
    }

    public SiteDTO(String name, String address, String town) {
        this.name = name;
        this.address = address;
        this.town = town;
    }

    public SiteDTO(Site site) {
        this.name = site.getName();
        this.address = site.getAddress();
        this.town = site.getTown();
    }

    public SiteDTO(String line) {
        // TODO validate line{
        String[] tokens = line.split(";");
        this.name = tokens[0];
        this.town = tokens[1];
        this.address = tokens[2];
    }

    public SiteDTO(Map map) {
        // TODO validate map
        this.name = (String) map.get("name");
        this.town = (String) map.get("town");
        this.address = (String) map.get("address");
    }

}
