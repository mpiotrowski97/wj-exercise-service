package pl.michalpiotrowski.wjexerciseservice.infrastructure.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@EnableDiscoveryClient
@Configuration
public class LocalDiscovery {
}
