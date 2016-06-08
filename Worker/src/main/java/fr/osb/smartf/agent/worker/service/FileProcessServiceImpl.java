package fr.osb.smartf.agent.worker.service;

import fr.osb.smartf.agent.worker.io.FileParserToMongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by szagoret on 03.06.2016.
 */
@Service
public class FileProcessServiceImpl implements FileProcessService {

    private static final Logger LOG = LoggerFactory.getLogger(FileProcessServiceImpl.class);

    private static final int DEFAULT_NR_THREADS = 20;
    private static final int DEFAULT_CHUNK_SIZE = 10000;

    @Value("${import.basedir}")
    private String importDir;

    @Autowired
    private FileParserToMongoService fileParserServiceMongo;

    @Override
    public void processFile(String fileName, String importType) throws Exception {
        LOG.info("Called processFile(" + fileName + ") service.");

        fileParserServiceMongo.setFile(new File(importDir + "/" + importType + "/" + fileName));
        fileParserServiceMongo.setImportType(importType);

        fileParserServiceMongo.processAll(DEFAULT_NR_THREADS, DEFAULT_CHUNK_SIZE);

        LOG.info("End processing file: " + fileName);
    }
}
