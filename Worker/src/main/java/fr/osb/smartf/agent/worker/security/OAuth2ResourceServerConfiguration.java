package fr.osb.smartf.agent.worker.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.*;
/**
 * Created by mpaltanea on 06.05.2016.
 */

@Configuration
public class OAuth2ResourceServerConfiguration {

    private static final String RESOURCE_ID = "restservice";
    private static final String CLIENT_APP = "worker";
    private static final String SECRET = "ftrams1605";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServer extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .anonymous().and()
                    .authorizeRequests()
                    .antMatchers("/hi").permitAll()
                    .antMatchers("/ping").permitAll()
                    .antMatchers("/import**").authenticated()
            ;
            // @formatter:on
        }

        @Override
        public void configure(ResourceServerSecurityConfigurer resources)
                throws Exception {
            resources.resourceId(RESOURCE_ID);
        }

        @Bean
        public AccessTokenConverter accessTokenConverter() {
            return new DefaultAccessTokenConverter();
        }

        @Bean
        public RemoteTokenServices remoteTokenServices(
                @Value("${auth.server.check_token_url}") final String checkTokenUrl,
                @Value(CLIENT_APP) final String clientId,
                @Value(SECRET) final String clientSecret) {

            final RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
            remoteTokenServices.setCheckTokenEndpointUrl(checkTokenUrl);
            remoteTokenServices.setClientId(clientId);
            remoteTokenServices.setClientSecret(clientSecret);
            remoteTokenServices.setAccessTokenConverter(accessTokenConverter());
            return remoteTokenServices;
        }
    }
}
