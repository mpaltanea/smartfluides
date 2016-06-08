package fr.osb.smartf.agent.worker.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by mpaltanea on 20.04.2016.
 */

@Service
public class RestConsumerToMongoService {

    @Autowired
    private ApplicationContext applicationContext;

    private String serviceName;
    private String importType;

    // TODO externalize available WSs
    public static final Map<String, String> WS_MAP;
    static {
        Map<String, String> map = new HashMap<String, String>();
        map.put("site", "");
        map.put("album", "http://jsonplaceholder.typicode.com/albums");
        WS_MAP = Collections.unmodifiableMap(map);
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getImportType() {
        return importType;
    }

    public void setImportType(String importType) {
        this.importType = importType;
    }


    // TODO authentication
    public List<LinkedHashMap> makeRequest(String wsUrl) {
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap> list = restTemplate.getForObject(wsUrl, List.class);
        return list;
    }

    /*protected <T> ResponseEntity<T> get(String url, ParameterizedTypeReference<T> responseType, Object... uriVariables) {
        RestTemplate template = new RestTemplate();
        return template.exchange(url, HttpMethod.GET, null, responseType, uriVariables);
    }*/

    /*protected <T> ResponseEntity<T> get(String url, Class <T> tClass, Object... uriVariables) {
        RestTemplate template = new RestTemplate();
        return template.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<T>() {}, uriVariables);
    }*/

}
