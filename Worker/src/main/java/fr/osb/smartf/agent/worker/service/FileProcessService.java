package fr.osb.smartf.agent.worker.service;

/**
 * Created by szagoret on 03.06.2016.
 */
public interface FileProcessService {

    void processFile(String fileName, String importType) throws Exception;
}
