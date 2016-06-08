package fr.osb.smartf.agent.dispatcher.controller;

import fr.osb.smartf.agent.dispatcher.controller.dto.ImportFileParams;
import fr.osb.smartf.agent.dispatcher.controller.dto.ImportType;
import fr.osb.smartf.agent.dispatcher.controller.dto.ImportWsParams;
import fr.osb.smartf.agent.dispatcher.service.FindActiveAgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mpaltanea on 11.04.2016.
 */

@RestController
public class DispatcherController /*implements DependencyWatcherListener*/ {

    private static final Logger LOG = LoggerFactory.getLogger(DispatcherController.class);

    private final OAuth2RestOperations oauth2RestTemplate;
    private final FindActiveAgentService findActiveAgentService;

    @Autowired
    public DispatcherController(OAuth2RestOperations oauth2RestTemplate,
                                FindActiveAgentService findActiveAgentService) {
        this.oauth2RestTemplate = oauth2RestTemplate;
        this.findActiveAgentService = findActiveAgentService;
    }

    @RequestMapping("/delegateImportFile")
    public String delegateImportFile(
            @RequestBody ImportFileParams param) {

        ImportFileFormat fileExtension = ImportFileFormat.getExtensionType(param.getFileName());
        return delegate(param.getImportType() + "/" + fileExtension.getMethodName(), param, MediaType.APPLICATION_JSON);
    }


    @RequestMapping("/delegateImportRawDataFromWs")
    public String delegateImportRawDataFromWs(
            @RequestBody ImportWsParams param) {

        return delegate(param.getImportType().name() + "/importRawDataFromWs", param, MediaType.APPLICATION_JSON);
    }

    @RequestMapping(value = "/delegateImportRawDataFromJson/{importType}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public String delegateImportRawDataFromJson(@RequestBody String rawData,
                                                @PathVariable("importType") ImportType importType) {
        return delegate(importType.name() + "/importJson", rawData, MediaType.APPLICATION_JSON);
    }

    @RequestMapping(value = "/delegateImportRawDataFromXml/{importType}",
            produces = MediaType.APPLICATION_XML_VALUE,
            consumes = MediaType.APPLICATION_XML_VALUE
    )
    public String delegateImportRawDataFromXml(
            @RequestBody String rawData,
            @PathVariable("importType") ImportType importType) {
        return delegate(importType.name() + "/importXml", rawData, MediaType.APPLICATION_XML);
    }

    private String delegate(String workerMethod, Object param, MediaType mediaType) {

        String hostPort = findActiveAgentService.getActiveAgent();

        /**
         * set explicitly MediaType to make possible call JSON and XML services
         */
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);

        HttpEntity<Object> httpEntity = new HttpEntity<>(param, httpHeaders);
        return oauth2RestTemplate.postForObject("http://" + hostPort + "/" + workerMethod, httpEntity, String.class);
    }

}
