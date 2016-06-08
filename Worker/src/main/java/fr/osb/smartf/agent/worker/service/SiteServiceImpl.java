package fr.osb.smartf.agent.worker.service;

import fr.osb.smartf.agent.worker.mongo.model.Site;
import fr.osb.smartf.agent.worker.mongo.repository.SiteRepository;
import fr.osb.smartf.agent.worker.service.dto.SiteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpaltanea on 23.05.2016.
 */
@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteRepository siteRepository;

    @Override
    public void saveSite(SiteDTO siteDTO) {
        siteRepository.save(siteDTO.toMongoObject());
    }

    @Override
    public void saveSiteList(List<SiteDTO> siteDTOList) {
        for (SiteDTO siteDTO : siteDTOList) {
            saveSite(siteDTO);
        }
    }

    // TODO remove (used only for tesing "remote" WS)
    public List<SiteDTO> getSites() {
        List<Site> sites = siteRepository.findAll();
        List<SiteDTO> siteDTOList = new ArrayList<>();
        for (Site site : sites) {
            siteDTOList.add(new SiteDTO(site));
        }
        return siteDTOList;
    }
}
