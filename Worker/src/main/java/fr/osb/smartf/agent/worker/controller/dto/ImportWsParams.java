package fr.osb.smartf.agent.worker.controller.dto;

/**
 * Created by mpaltanea on 03.05.2016.
 */
public class ImportWsParams {
    private String importType;
    private String url;
    private String username;
    private String password;

    public String getImportType() {
        return importType;
    }

    public void setImportType(String importType) {
        this.importType = importType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
