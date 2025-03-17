package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:common.properties",
        "classpath:${env}.properties"})
public interface WebConfig extends Config {

    WebConfig WEB_CONFIG = ConfigFactory.create(WebConfig.class,
            System.getenv(),
            System.getProperties());

    @Key("baseURI")
    String getBaseURI();
}
