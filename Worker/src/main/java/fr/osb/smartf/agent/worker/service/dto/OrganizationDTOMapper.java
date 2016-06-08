package fr.osb.smartf.agent.worker.service.dto;

import fr.osb.smartf.agent.worker.mongo.model.Organization;

/**
 * Created by szagoret on 03.06.2016.
 */
public class OrganizationDTOMapper {



    /**
     * Mapping OrganizationDTO to Organization entity
     *
     * @param organizationDTO
     * @return Organization entity
     */
    public static Organization toOrganization(OrganizationDTO organizationDTO) {

        Organization organization = new Organization();
        organization.setId(organizationDTO.getId());
        organization.setName(organizationDTO.getName());
        organization.setGuId(organizationDTO.getGuId());
        organization.setSegment(organizationDTO.getSegment());
        organization.setType(organizationDTO.getType());
        organization.setDescription(organizationDTO.getDescription());
        organization.setEmail(organizationDTO.getEmail());
        organization.setAddress(organizationDTO.getAddress());
        organization.setZipCode(organizationDTO.getZipCode());
        organization.setCountry(organizationDTO.getCountry());
        organization.setTown(organizationDTO.getTown());
        organization.setLogo(organizationDTO.getLogo());

        return organization;
    }
}
