package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources("classpath:endpoints.properties")
public interface EndpointsConfig extends WebConfig{

    EndpointsConfig ENDPOINTS_CONFIG = ConfigFactory.create(EndpointsConfig.class);

    @Key("allUsersEndpoint")
    String getAllUsersEndpoint();

    @Key("userByIdEndpoint")
    String getUserByIdEndpoint();
}
