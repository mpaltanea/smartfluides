package fr.osb.smartf.agent.worker.controller.dto;

/**
 * Created by szagoret on 03.06.2016.
 */
public class ImportFileParams {

    String importType;

    String fileName;

    public String getImportType() {
        return importType;
    }

    public void setImportType(String importType) {
        this.importType = importType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}