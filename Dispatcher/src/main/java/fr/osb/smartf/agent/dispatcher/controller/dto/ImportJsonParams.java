package fr.osb.smartf.agent.dispatcher.controller.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by mpaltanea on 27.05.2016.
 */
public class ImportJsonParams extends ImportParam {
    private String importType;
    private List<LinkedHashMap> data;

    public String getImportType() {
        return importType;
    }

    public void setImportType(String importType) {
        this.importType = importType;
    }

    public List<LinkedHashMap> getData() {
        if (data == null) {
            data = new ArrayList<>();
        }
        return data;
    }

    public void setData(List<LinkedHashMap> data) {
        this.data = data;
    }
}
