package fr.osb.smartf.agent.worker.service.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by szagoret on 03.06.2016.
 */
@XmlRootElement(name = "organizations")
public class OrganizationsDTOList {

    @XmlElement(name = "organization")
    List<OrganizationDTO> organizations = new ArrayList<>();

    public List<OrganizationDTO> getOrganizations() {
        return organizations;
    }
}
