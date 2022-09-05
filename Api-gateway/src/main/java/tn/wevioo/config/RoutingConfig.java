package tn.wevioo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@ComponentScan
@Configuration
public class RoutingConfig {
    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r->r.path("/api/auth/**").uri("lb://AUTH-SERVICE")) //dynamic routing to auth-service
                .route("auth-service", r->r.path("/api/test/**").uri("lb://AUTH-SERVICE")) //dynamic routing to auth-service
                .route("doc-service", r->r.path("/api/**").uri("lb://DOC-SERVICE")) //dynamic routing to doc-service
                .build();
    }

}
