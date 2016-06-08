package fr.osb.smartf.agent.worker.main.config;

import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Created by mpaltanea on 19.05.2016.
 */

@EnableWs
@Configuration
public class WSConfig extends WsConfigurerAdapter {

    private static final String SITE_SCHEMA_LOCATION = "META-INF/schemas/site.xsd";
    private static final String SITE_PORTTYPE_NAME = "Site";

    @Bean
    public ServletRegistrationBean RsRegistrationBean(ApplicationContext applicationContext) {
        DispatcherServlet servlet = new DispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(servlet, "/*");
    }

    @Bean
    public ServletRegistrationBean dispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(false);
        return new ServletRegistrationBean(servlet, "/services/*");
    }

    @Bean(name = "site")
    public DefaultWsdl11Definition sitesWsdl11Definition(XsdSchema siteSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName(SITE_PORTTYPE_NAME);
        wsdl11Definition.setLocationUri("/site");
        wsdl11Definition.setTargetNamespace("http://fr.osb.smartf.agent/worker/schema/site");
        wsdl11Definition.setSchema(siteSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema siteSchema() {
        return new SimpleXsdSchema(new ClassPathResource(SITE_SCHEMA_LOCATION));
    }

}
