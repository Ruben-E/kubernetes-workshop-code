package nl.openvalue.workshops.kubernetes.hellokubernetes.search;

import org.springframework.stereotype.Component;

@Component
public class HealthService {
    private boolean healthy = true;

    public void toggleUnhealthy() {
        this.healthy = false;
    }

    public void toggleHealthy() {
        this.healthy = true;
    }

    public boolean isHealthy() {
        return healthy;
    }
}
