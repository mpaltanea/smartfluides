package fr.osb.smartf.agent.worker.mongo.util;

import fr.osb.smartf.agent.worker.mongo.model.*;

/**
 * Created by mpaltanea on 14.04.2016.
 */
public class MongoFactory {

    /**
     * TODO Refactoring this class to have a single class/enum with
     * import type and relevant constants
     */
    public static final String SITE = "site";
    public static final String ORGANIZATION = "organization";
    public static final String INDEX = "index";
    public static final String ALBUM = "album";

    public MongoObject createObject(String objectType) {
        switch (objectType) {
            case SITE: return new Site();
//            case ORGANIZATION: return new Organization();
            case INDEX: return new Index();
            case ALBUM: return new Album();
            default: throw new IllegalArgumentException("Invalid objectType: " + objectType);
        }
    }
}