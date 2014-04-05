package com.fromthebasement.service;

import com.fromthebasement.model.Person;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * Created by jeffginn on 4/5/14.
 */
@Path("/people")
public interface PersonManager extends GenericManager<Person, Long> {
    @GET
    @Path("{lastname}")
    List<Person> findByLastName(@PathParam("lastname") String lastName);

    @GET
    List<Person> getPeople();
}
