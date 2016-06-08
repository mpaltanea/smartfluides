package fr.osb.smartf.agent.worker.mongo.model;

import java.util.Map;

/**
 * Created by mpaltanea on 14.04.2016.
 */
public abstract class MongoObject {
    public abstract void importLine(String line);
    public abstract void importMap(Map map);
}
