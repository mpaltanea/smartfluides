package fr.osb.smartf.agent.worker.mongo.repository;

import fr.osb.smartf.agent.worker.mongo.model.Album;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mpaltanea on 22.04.2016.
 */
@Repository("albumRepository")
public interface AlbumRepository extends MongoRepository<Album, String> {
}
