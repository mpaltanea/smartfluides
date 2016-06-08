package fr.osb.smartf.agent.dispatcher.security;

/**
 * Created by mpaltanea on 06.05.2016.
 */

/*@Configuration*/
public class OAuth2ServerConfiguration {

    /*private static final String RESOURCE_ID = "restservice";
    private static final String CLIENT_APP = "clientapp";
    private static final String SECRET = "ftrams";

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends
            AuthorizationServerConfigurerAdapter {

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    .inMemory()
                    .withClient(CLIENT_APP)
                    .authorizedGrantTypes("client_credentials", "password")
                    .authorities("ROLE_CLIENT")
                    .scopes("read")
                    .resourceIds(RESOURCE_ID)
                    .secret(SECRET);
        }

        @Bean
        public DefaultAccessTokenConverter defaultAccessTokenConverter() {
            return new DefaultAccessTokenConverter();
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            endpoints
                    .authenticationManager(authenticationManager)
                    .accessTokenConverter(defaultAccessTokenConverter());
        }
    }*/

}
