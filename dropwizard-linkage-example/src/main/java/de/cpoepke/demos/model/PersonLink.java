package de.cpoepke.demos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.jersey.server.linking.Binding;
import com.sun.jersey.server.linking.Ref;
import de.cpoepke.demos.resources.PersonResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.net.URI;

// Lombok annotations
@Data
@ToString(exclude = {"object"})
@EqualsAndHashCode(exclude = {"object"})
public class PersonLink {

    // We ignore this Json property when serializing because we use links but we keep
    // in the model to reference it
    @JsonIgnore
    private final Person object;

    // Annotation creating link in representation
    @Ref(
            value = "{id}",
            resource = PersonResource.class,
            method = "get",
            condition = "${not empty(instance.object)}",
            bindings = @Binding(name = "id", value = "${instance.object.id}"))
    private URI link;
}
