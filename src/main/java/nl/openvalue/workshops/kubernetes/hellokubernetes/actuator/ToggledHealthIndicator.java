package nl.openvalue.workshops.kubernetes.hellokubernetes.actuator;

import nl.openvalue.workshops.kubernetes.hellokubernetes.search.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ToggledHealthIndicator implements ReactiveHealthIndicator {
    @Autowired
    private HealthService healthService;

    @Override
    public Mono<Health> health() {
        return checkDownstreamServiceHealth().onErrorResume(
                ex -> Mono.just(new Health.Builder().down(ex).build())
        );
    }

    private Mono<Health> checkDownstreamServiceHealth() {
        if (healthService.isHealthy()) {
            return Mono.just(new Health.Builder().up().build());
        } else {
            return Mono.just(new Health.Builder().down().build());
        }
    }
}