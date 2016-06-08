package fr.osb.smartf.agent.worker.mongo.repository;

import fr.osb.smartf.agent.worker.mongo.model.Index;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mpaltanea on 15.04.2016.
 */
@Repository("indexRepository")
public interface IndexRepository extends MongoRepository<Index, String> {
}
