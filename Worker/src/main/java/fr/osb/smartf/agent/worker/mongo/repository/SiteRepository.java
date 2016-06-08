package fr.osb.smartf.agent.worker.mongo.repository;

import fr.osb.smartf.agent.worker.mongo.model.Site;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mpaltanea on 13.04.2016.
 */
@Repository("siteRepository")
public interface SiteRepository extends MongoRepository<Site, String> {
}
