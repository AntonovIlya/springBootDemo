package springbootdemo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springbootdemo.profiles.DevProfile;
import springbootdemo.profiles.ProductionProfile;
import springbootdemo.profiles.SystemProfile;

@Configuration
public class WebConfig {

    @Bean
    @ConditionalOnProperty(prefix = "profile", name = "dev")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "profile", name = "production")
    public SystemProfile productionProfile() {
        return new ProductionProfile();
    }
}
