package fr.osb.smartf.agent.worker.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mpaltanea on 22.04.2016.
 */
@Document(collection = "album")
public class Album extends MongoObject {

    @Id
    private Integer id;

    private Integer userId;
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void importLine(String line) {
    }

    @Override
    public void importMap(Map map) {
        this.id = (Integer) map.get("id");
        this.userId = (Integer) map.get("userId");
        this.title = (String) map.get("title");
    }
}
