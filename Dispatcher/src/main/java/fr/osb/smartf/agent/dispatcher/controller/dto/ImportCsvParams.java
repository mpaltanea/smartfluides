package fr.osb.smartf.agent.dispatcher.controller.dto;

/**
 * Created by mpaltanea on 04.05.2016.
 */
public class ImportCsvParams extends ImportParam {
    private String fileName;
    private String importType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImportType() {
        return importType;
    }

    public void setImportType(String importType) {
        this.importType = importType;
    }

    @Override
    public String toString() {
        return "ImportCsvInput [importType=" + importType + ", fileName=" + fileName + "]";
    }
}
