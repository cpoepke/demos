package de.cpoepke.demos.model;

import com.google.common.collect.Lists;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

// Lombok annotations
@Data
// Uncomment for link header support
//@ToString(exclude = {"partner", "mother", "father"})
//@EqualsAndHashCode(exclude = {"partner", "mother", "father"})
//// Annotation creating link headers as an alternative to links in representations
//@Links(
//        {
//        @Link(value = @Ref(
//                value = "{id}",
//                resource = PersonResource.class,
//                method = "get",
//                condition = "${not empty(instance.father)}",
//                bindings = @Binding(name = "id", value = "${instance.father.id}")),
//            rel = "father"),
//
//        @Link(value = @Ref(
//                value = "{id}",
//                resource = PersonResource.class,
//                method = "get",
//                condition = "${not empty(instance.mother)}",
//                bindings = @Binding(name = "id", value = "${instance.mother.id}")),
//            rel = "mother"),
//
//        @Link(value = @Ref(
//                value = "{id}",
//                resource = PersonResource.class,
//                method = "get",
//                condition = "${not empty(instance.partner)}",
//                bindings = @Binding(name = "id", value = "${instance.partner.id}")),
//            rel = "partner")
//        }
//)
public class Person implements Serializable {

    private UUID id;

    @NotNull
    private String name;

    private PersonLink father;

    private PersonLink mother;

    private PersonLink partner;

    private List<PersonLink> children = Lists.newArrayList();
}
