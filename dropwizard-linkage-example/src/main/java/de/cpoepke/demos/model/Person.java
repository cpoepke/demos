package de.cpoepke.demos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.jersey.server.linking.Binding;
import com.sun.jersey.server.linking.Link;
import com.sun.jersey.server.linking.Links;
import com.sun.jersey.server.linking.Ref;
import de.cpoepke.demos.resources.PersonResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.net.URI;
import java.util.UUID;

// Lombok annotations
@Data
@ToString(exclude = {"partner", "mother", "father"})
@EqualsAndHashCode(exclude = {"partner", "mother", "father"})
// Annotation creating link headers as an alternative to links in representations
@Links(
        {
        @Link(value = @Ref(
                value = "{id}",
                resource = PersonResource.class,
                method = "get",
                condition = "${not empty(instance.father)}",
                bindings = @Binding(name = "id", value = "${instance.father.id}")),
            rel = "father"),

        @Link(value = @Ref(
                value = "{id}",
                resource = PersonResource.class,
                method = "get",
                condition = "${not empty(instance.mother)}",
                bindings = @Binding(name = "id", value = "${instance.mother.id}")),
            rel = "mother"),

        @Link(value = @Ref(
                value = "{id}",
                resource = PersonResource.class,
                method = "get",
                condition = "${not empty(instance.partner)}",
                bindings = @Binding(name = "id", value = "${instance.partner.id}")),
            rel = "partner")
        }
)
public class Person implements Serializable {

    private UUID id;

    @NotNull
    private String name;

    // We ignore this Json property when serializing because we use links but we keep
    // it in the model to reference ist
    @JsonIgnore
    private Person father;

    // Annotation creating link in representation
    @Ref(
            value = "{id}",
            resource = PersonResource.class,
            method = "get",
            condition = "${not empty(instance.father)}",
            bindings = @Binding(name = "id", value = "${instance.father.id}"))
    private URI fatherLink;

    @JsonIgnore
    private Person mother;

    @Ref(
            value = "{id}",
            resource = PersonResource.class,
            method = "get",
            condition = "${not empty(instance.mother)}",
            bindings = @Binding(name = "id", value = "${instance.mother.id}"))
    private URI motherLink;

    @JsonIgnore
    private Person partner;

    @Ref(
            value = "{id}",
            resource = PersonResource.class,
            method = "get",
            condition = "${not empty(instance.partner)}",
            bindings = @Binding(name = "id", value = "${instance.partner.id}"))
    private URI partnerLink;
}
