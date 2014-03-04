package de.cpoepke.demos.resources;

import com.yammer.metrics.annotation.Timed;
import de.cpoepke.demos.model.Person;
import de.cpoepke.demos.repository.PersonRepository;
import lombok.Setter;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("person")
@Produces(MediaType.APPLICATION_JSON)
@Setter
public class PersonResource {

    private PersonRepository repo = PersonRepository.getInstance();

    @GET
    @Timed
    @Path("{id}")
    public Person get(@PathParam("id") UUID id) {
        return repo.get(id);
    }

    @POST
    @Path("put")
    public Person put(@Valid Person person) {
        person.setId(UUID.randomUUID());
        return repo.put(person);
    }

    @GET
    @Timed
    @Path("{id}/delete")
    public boolean delete(@PathParam("id") UUID id) {
        return repo.delete(id);
    }

    @GET
    @Timed
    @Path("size")
    public long size() {
        return repo.size();
    }
}
