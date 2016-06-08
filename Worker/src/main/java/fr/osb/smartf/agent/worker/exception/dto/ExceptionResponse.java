package fr.osb.smartf.agent.worker.exception.dto;

/**
 * Created by mpaltanea on 07.04.2016.
 */
public class ExceptionResponse {
    private String response;

    public ExceptionResponse() {

    }

    public ExceptionResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

