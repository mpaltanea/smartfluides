package fr.osb.smartf.agent.worker.io.ws;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by mpaltanea on 20.04.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {

    private Long userId;
    private Long id;
    private String title;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
