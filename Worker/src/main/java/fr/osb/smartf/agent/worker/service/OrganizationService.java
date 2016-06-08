package fr.osb.smartf.agent.worker.service;

import fr.osb.smartf.agent.worker.service.dto.OrganizationDTO;

import java.util.List;

/**
 * Created by szagoret on 01.06.2016.
 */
public interface OrganizationService {

    void save(List<OrganizationDTO> organizationDTOList);
}
