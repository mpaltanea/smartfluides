package fr.osb.smartf.agent.worker.batch.organization;

import fr.osb.smartf.agent.worker.mongo.model.Organization;
import fr.osb.smartf.agent.worker.service.dto.OrganizationDTO;
import fr.osb.smartf.agent.worker.service.dto.OrganizationDTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Created by szagoret on 06.06.2016.
 */
@Configuration
public class ImportOrganizationJobConfig {

    Logger LOG = LoggerFactory.getLogger(ImportOrganizationJobConfig.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    ItemReader itemReader;

    @Autowired
    ItemProcessor itemProcessor;

    @Autowired
    ItemWriter itemWriter;

    @Bean
    public static StepScope scope() {
        return new StepScope();
    }

    @Scope("step")
    @Bean
    public StaxEventItemReader<OrganizationDTO> organizationXmlReader(@Value("#{jobParameters[pathToFile]}") String pathToFile) {
        StaxEventItemReader<OrganizationDTO> eventItemReader = new StaxEventItemReader<>();
        eventItemReader.setResource(resourceLoader.getResource("file:" + pathToFile));
        eventItemReader.setFragmentRootElementName("organization");

        Jaxb2Marshaller organizationUnMarshaller = new Jaxb2Marshaller();
        organizationUnMarshaller.setClassesToBeBound(OrganizationDTO.class);
        eventItemReader.setUnmarshaller(organizationUnMarshaller);

        return eventItemReader;
    }


    @Bean
    public ItemWriter<Organization> organizationItemWriter() {
        MongoItemWriter<Organization> writer = new MongoItemWriter<>();
        try {
            writer.setTemplate(mongoTemplate);
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        writer.setCollection("organizations");
        return writer;
    }

    @Bean
    public ItemProcessor<OrganizationDTO, Organization> organizationItemProcessor() {
        return OrganizationDTOMapper::toOrganization;
    }

    @Bean
    public Step importOrganizationXmlStep() {
        return stepBuilderFactory.get("importOrganizationXmlStep")
                .chunk(2)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public Job importOrganizationJob(Step importOrganizationXmlStep) throws Exception {
        return jobBuilderFactory.get("importOrganizationJob")
                .incrementer(new RunIdIncrementer())
                .start(importOrganizationXmlStep)
                .build();
    }


}
