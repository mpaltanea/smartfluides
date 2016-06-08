package fr.osb.smartf.agent.dispatcher.service;

import fr.osb.smartf.agent.dispatcher.exception.types.TechnicalException;
import fr.osb.smartf.agent.dispatcher.util.AgentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by szagoret on 06.06.2016.
 */
@Service
public class FindActiveAgentServiceImpl implements FindActiveAgentService {
    private static final Logger LOG = LoggerFactory.getLogger(FindActiveAgentServiceImpl.class);

    private static final String NO_ACTIVE_AGENT_MSG = "No active agent found";
    private static final String NO_INSTANCES_MSG = "No instances found";
    private static final String AGENT_FOUND_MSG = "Found active agent";

    @Autowired
    private DiscoveryClient discovery;

    @Primary
    @Bean
    RestTemplate getLoadBalancedRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate nonLoadBalancedRestTemplate;

    @Bean
    @LoadBalanced
    RestTemplate getNonLoadBalancedRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    @LoadBalanced
    RestTemplate loadBalancedRestTemplate;


    /**
     * Find active agent
     *
     * @return host:port of found agent
     */
    @Override
    public String getActiveAgent() {

        int nrInstances = this.discovery.getInstances(AgentUtil.READ_WRITE_TYPE).size();
        if (nrInstances == 0) {
            LOG.warn(NO_INSTANCES_MSG);
            throw new TechnicalException(NO_INSTANCES_MSG);
        }

        // use the load balanced rest template to select an instance;
        // the instance may still be registered in zookeeper, although its enclosed agent might be stopped
        // retry selecting instance as many times as detected instances and until enclosed agent responds to ping

        boolean foundActiveAgent = false;
        int instanceCounter = 0;

        String hostPort = "";
        do {
            try {
                hostPort = this.loadBalancedRestTemplate.getForObject("http://worker/ping", String.class);
                foundActiveAgent = true;
            } catch (Exception ex) {
                instanceCounter++;
                LOG.error(ex.getMessage());
            }
        } while (!foundActiveAgent && (instanceCounter < nrInstances));
        if (foundActiveAgent) {
            LOG.info(AGENT_FOUND_MSG);
            return hostPort;
        } else {
            LOG.warn(NO_ACTIVE_AGENT_MSG);
            throw new TechnicalException(NO_ACTIVE_AGENT_MSG);
        }
    }
}
