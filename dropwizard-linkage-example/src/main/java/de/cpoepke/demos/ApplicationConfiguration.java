package de.cpoepke.demos;

import com.yammer.dropwizard.config.Configuration;
import de.cpoepke.demos.model.Person;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ApplicationConfiguration extends Configuration {
    @NotNull
    private Person adam;

    @NotNull
    private Person eva;

    @NotNull
    private Person kain;

    @NotNull
    private Person abel;
}
