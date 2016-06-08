package fr.osb.smartf.agent.dispatcher.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * Created by mpaltanea on 11.05.2016.
 */

@Configuration
@EnableOAuth2Client
public class OAuthClientConfiguration {

    private static final String CLIENT_APP = "worker";
    private static final String SECRET = "ftrams1605";

    @Value("${auth.server.token_url}")
    private String authServerUrl;

    @Bean
    public OAuth2RestOperations oauth2RestTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(resource(),
                new DefaultOAuth2ClientContext(atr));
    }

    @Bean
    protected OAuth2ProtectedResourceDetails resource() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri(authServerUrl);
        resourceDetails.setClientId(CLIENT_APP);
        resourceDetails.setClientSecret(SECRET);
        return resourceDetails;
    }
}