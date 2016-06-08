package fr.osb.smartf.agent.worker.mongo.repository;

import fr.osb.smartf.agent.worker.mongo.model.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by szagoret on 01.06.2016.
 */
@Repository("organizationRepository")
public interface OrganizationRepository extends MongoRepository<Organization, String> {
}
