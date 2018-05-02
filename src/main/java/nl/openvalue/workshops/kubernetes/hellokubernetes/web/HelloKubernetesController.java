package nl.openvalue.workshops.kubernetes.hellokubernetes.web;

import nl.openvalue.workshops.kubernetes.hellokubernetes.search.HealthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static java.lang.Math.atan;
import static java.lang.Math.cbrt;
import static java.lang.Math.tan;

@RestController
public class HelloKubernetesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloKubernetesController.class);

    @Autowired
    private HealthService healthService;

    @GetMapping("/hello")
    @ResponseBody
    public Mono<String> sayHello() {
        return Mono.just("Hello from " + System.getenv("HOSTNAME"));
    }

    @GetMapping("/calculate")
    @ResponseBody
    public void calculate() {
        LOGGER.info("Calculating...");

        for (int i=0; i<1_000_000; i++) {
            double d = tan(atan(tan(atan(tan(atan(tan(atan(tan(atan(123456789.123456789))))))))));
            cbrt(d);
        }

        LOGGER.info("Done calculating...");
    }

    @GetMapping("/actuator/unhealthy")
    @ResponseBody
    public Mono<String> unhealthy() throws Exception {
        healthService.toggleUnhealthy();
        return Mono.just("Healthy: " + healthService.isHealthy());
    }

    @GetMapping("/actuator/healthy")
    @ResponseBody
    public Mono<String> healthy() throws Exception {
        healthService.toggleHealthy();
        return Mono.just("Healthy: " + healthService.isHealthy());
    }

    @GetMapping("/actuator/toggle")
    @ResponseBody
    public Mono<String> toggle() throws Exception {
        if (healthService.isHealthy()) {
            healthService.toggleUnhealthy();
        } else {
            healthService.toggleHealthy();
        }
        return Mono.just("Healthy: " + healthService.isHealthy());
    }
}
