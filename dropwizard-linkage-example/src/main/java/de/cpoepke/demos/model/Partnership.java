package de.cpoepke.demos.model;


import com.sun.jersey.server.linking.Binding;
import com.sun.jersey.server.linking.Link;
import com.sun.jersey.server.linking.Ref;

@Link(
        value = @Ref(
                resource = Person.class,
                style = Ref.Style.ABSOLUTE,
                bindings = @Binding(name = "id", value = "${partner.id}")))
public class Partnership {
}
