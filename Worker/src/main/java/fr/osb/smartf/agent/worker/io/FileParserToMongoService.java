package fr.osb.smartf.agent.worker.io;

//import com.mongodb.MongoClient;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
import fr.osb.smartf.agent.worker.main.config.MongoConfig;
import fr.osb.smartf.agent.worker.mongo.util.MongoFactory;
import fr.osb.smartf.agent.worker.mongo.model.MongoObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

@Service
public class FileParserToMongoService {

    private final static Logger LOG = LoggerFactory.getLogger(FileParserToMongoService.class);

    /*@Autowired
    private SiteRepository repository;*/

    @Autowired private ApplicationContext applicationContext;

    private File file;
    private String importType;

    public void setFile(File file){
        this.file = file;
    }

    public void setImportType(String importType) {
        this.importType = importType;
    }

    public FileParserToMongoService(){

    }

    public FileParserToMongoService(File file) {
        this.file = file;
    }

    public String processPart(long start, long end) throws Exception {
        LOG.info("Start work [" + start + " - " + end + "]");
        try {
            Stream<String> lines = Files.lines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8).skip(start);
            int counter = 1;

            MongoFactory mongoFactory = new MongoFactory();
            MongoRepository repository = (MongoRepository) applicationContext.getBean(importType + MongoConfig.REPO);

            if (repository!=null) {

                work:
                for (String line : (Iterable<String>) lines::iterator) {
                    if (counter >= end) {
                        break work;
                    }
                    counter++;

                    MongoObject mongoObject = mongoFactory.createObject(importType);
                    mongoObject.importLine(line);
                    repository.save(mongoObject);
                }
            }
            lines.close();
        } catch (Exception ex) {
            LOG.error(ex.getMessage());
        }
        LOG.info("End work [" + start + " - " + end + "]");
        return "";
    }

    public Callable<String> processPartTask(final long start, final long end) {
        return new Callable<String>() {
            public String call() throws Exception {
                return processPart(start, end);
            }
        };
    }

    public void processAll(int numberOfThreads, int chunkSize) throws IOException, InterruptedException,
            ExecutionException {
        Stream<String> lines = Files.lines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
        int count = ((int) lines.count()/chunkSize)+1;
        lines.close();
        List<Callable<String>> tasks = new ArrayList<Callable<String>>(count);
        int start = 0;
        for (int i = 0; i < count; i++) {
            tasks.add(processPartTask(start, chunkSize));
            start = start + chunkSize;
        }
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        List<Future<String>> results = executorService.invokeAll(tasks);
        executorService.shutdown();

        for (Future<String> result : results) {
            result.get();
        }
    }
}

