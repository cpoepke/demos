package de.cpoepke.demos.health;

import com.sun.jersey.api.core.InjectParam;
import com.yammer.metrics.core.HealthCheck;
import de.cpoepke.demos.repository.PersonRepository;
import lombok.Setter;

@Setter
public class PersonHealthCheck extends HealthCheck {

    @InjectParam
    private PersonRepository repo;

    public PersonHealthCheck() {
        super("person");
    }

    @Override
    protected Result check() throws Exception {
        if (repo.size() > 0) {
            return Result.unhealthy("There exists nobody!");
        }
        return Result.healthy();
    }
}
