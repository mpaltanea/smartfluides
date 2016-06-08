package fr.osb.smartf.agent.worker.service;

import fr.osb.smartf.agent.worker.service.dto.SiteDTO;

import java.util.List;

/**
 * Created by mpaltanea on 23.05.2016.
 */
interface SiteService {

    void saveSite(SiteDTO siteDTO);
    void saveSiteList(List<SiteDTO> siteDTOList);
}
