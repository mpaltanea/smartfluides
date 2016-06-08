package fr.osb.smartf.agent.dispatcher.controller.dto;

/**
 * Created by szagoret on 03.06.2016.
 */
public class ImportFileParams {

    ImportType importType;

    String fileName;

    public ImportType getImportType() {
        return importType;
    }

    public void setImportType(ImportType importType) {
        this.importType = importType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}


