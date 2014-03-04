package de.cpoepke.demos;

import com.google.common.collect.Lists;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.server.linking.LinkFilter;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import de.cpoepke.demos.health.PersonHealthCheck;
import de.cpoepke.demos.repository.PersonRepository;
import de.cpoepke.demos.resources.PersonResource;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
public class ApplicationService extends Service<ApplicationConfiguration> {

    private PersonRepository repo = PersonRepository.getInstance();

    public static void main(String[] args) throws Exception {
        new ApplicationService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {
        bootstrap.setName("application");
    }

    @Override
    public void run(ApplicationConfiguration configuration,
                    Environment environment) {

        // Register Jersey LinkFilter
        environment.setJerseyProperty(ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS, LinkFilter.class);

        // Create the family
        configuration.getAdam().setPartner(configuration.getEva());
//        configuration.getAdam().getChildren().add(configuration.getKain());
//        configuration.getAdam().getChildren().add(configuration.getAbel());
        repo.put(configuration.getAdam());

        configuration.getEva().setPartner(configuration.getAdam());
//        configuration.getEva().getChildren().add(configuration.getKain());
//        configuration.getEva().getChildren().add(configuration.getAbel());
        repo.put(configuration.getEva());

        configuration.getKain().setFather(configuration.getAdam());
        configuration.getKain().setMother(configuration.getEva());
        repo.put(configuration.getKain());

        configuration.getAbel().setFather(configuration.getAdam());
        configuration.getAbel().setMother(configuration.getEva());
        repo.put(configuration.getAbel());

        // Initialize Dropwizard Resource and Health Check
        environment.addResource(new PersonResource());
        environment.addHealthCheck(new PersonHealthCheck());
    }

}
