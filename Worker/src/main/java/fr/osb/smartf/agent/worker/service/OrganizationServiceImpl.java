package fr.osb.smartf.agent.worker.service;

import fr.osb.smartf.agent.worker.mongo.model.Organization;
import fr.osb.smartf.agent.worker.mongo.repository.OrganizationRepository;
import fr.osb.smartf.agent.worker.service.dto.OrganizationDTO;
import fr.osb.smartf.agent.worker.service.dto.OrganizationDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by szagoret on 01.06.2016.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private static final Logger LOG = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public void save(List<OrganizationDTO> organizationDTOList) {

        if (organizationDTOList.size() != 0) {
            long startTime = System.currentTimeMillis();
            List<Organization> organizationList = organizationDTOList.stream().map(OrganizationDTOMapper::toOrganization).collect(toList());
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            LOG.info("Execution time: " + totalTime + " ms");

            startTime = System.currentTimeMillis();
            organizationRepository.save(organizationList);
            endTime = System.currentTimeMillis();
            totalTime = endTime - startTime;
            LOG.info("Execution insert time: " + totalTime + " ms");

        }
    }
}
