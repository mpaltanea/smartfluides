package fr.osb.smartf.agent.worker.controller.organization;

import fr.osb.smartf.agent.worker.controller.dto.ImportFileParams;
import fr.osb.smartf.agent.worker.service.FileProcessService;
import fr.osb.smartf.agent.worker.service.OrganizationService;
import fr.osb.smartf.agent.worker.service.dto.OrganizationDTO;
import fr.osb.smartf.agent.worker.service.dto.OrganizationsDTOList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by szagoret on 01.06.2016.
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private static final Logger LOG = LoggerFactory.getLogger(OrganizationController.class);

    private final OrganizationService organizationService;
    private final FileProcessService fileProcessService;
    private final JobLauncher jobLauncher;

    @Qualifier("importOrganizationJob")
    @Autowired
    private Job job;


    @Value("${import.basedir}")
    private String importDir;

    @Autowired
    public OrganizationController(OrganizationService organizationService,
                                  FileProcessService fileProcessService,
                                  JobLauncher jobLauncher) {
        this.organizationService = organizationService;
        this.fileProcessService = fileProcessService;
        this.jobLauncher = jobLauncher;
    }

    /**
     * Import organizations with json content in request body
     *
     * @param list of OrganizationDTO
     * @return HTTP response
     */
    @RequestMapping(value = "/importJson",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON},
            consumes = {MediaType.APPLICATION_JSON}
    )
    public Response importJson(@RequestBody List<OrganizationDTO> list) {
        LOG.info("Called importOrganizationFromJson");
        organizationService.save(list);
        LOG.info("OrganizationDTO@List saved in DB");
        return Response.ok().build();
    }

    /**
     * Import organizations with xml content in request body
     *
     * @param organizationsListWrapper of OrganizationDTO
     * @return HTTP response
     */
    @RequestMapping(value = "/importXml",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_XML},
            consumes = {MediaType.APPLICATION_XML}
    )
    public Response importXml(@RequestBody OrganizationsDTOList organizationsListWrapper) {
        LOG.info("Called importOrganizationFromJson");
        organizationService.save(organizationsListWrapper.getOrganizations());
        LOG.info("OrganizationDTO@List saved in DB");
        return Response.ok().build();
    }

    /**
     * Import organizations with csv content in request body
     *
     * @return HTTP response
     */
    @RequestMapping(value = "/importCsv",
            method = RequestMethod.POST,
            produces = {MediaType.TEXT_PLAIN},
            consumes = {MediaType.TEXT_PLAIN}
    )
    public Response importCsv(@RequestBody String csv) {
        LOG.info("Called importCsv");
        return Response.ok().build();
    }

    /**
     * Import organizations with json file
     *
     * @param importFileParams{importType, fileName}
     * @return HTTP response
     */
    @RequestMapping(value = "/importJsonFile",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON},
            consumes = {MediaType.APPLICATION_JSON}
    )
    public Response importJsonFile(@RequestBody ImportFileParams importFileParams) {
        try {
            fileProcessService.processFile(importFileParams.getFileName(), importFileParams.getImportType());
        } catch (InvalidPathException e) {
            LOG.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (IOException e) {
            LOG.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (ExecutionException e) {
            LOG.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    /**
     * Import organizations with json file
     *
     * @param importFileParams{importType, fileName}
     * @return HTTP response
     */
    @RequestMapping(value = "/importXmlFile",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON},
            consumes = {MediaType.APPLICATION_JSON}
    )
    public Response importXmlFile(@RequestBody ImportFileParams importFileParams) {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addDate("date", new Date())
                    .addString("pathToFile", importDir + "/" + importFileParams.getImportType() +
                            "/" + importFileParams.getFileName()).toJobParameters();
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().build();
    }


}
