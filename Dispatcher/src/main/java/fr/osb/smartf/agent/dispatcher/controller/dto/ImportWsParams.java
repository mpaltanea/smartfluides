package fr.osb.smartf.agent.dispatcher.controller.dto;

/**
 * Created by mpaltanea on 03.05.2016.
 */
public class ImportWsParams extends ImportParam {

    private ImportType importType;
    private String url;
    private String username;
    private String password;

    public ImportType getImportType() {
        return importType;
    }

    public void setImportType(ImportType importType) {
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
