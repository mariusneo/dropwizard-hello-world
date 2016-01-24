package eu.test.dropwizard.resource;


import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import eu.test.dropwizard.dao.PersonDAO;
import eu.test.dropwizard.model.Person;
import eu.test.dropwizard.view.PersonView;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/people/{personId}")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final PersonDAO personDAO;

    public PersonResource(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    @GET
    @UnitOfWork
    @Timed
    public Person getPerson(@PathParam("personId")LongParam personId){
        return findSafely(personId.get());
    }


    @GET
    @Path("/view_freemaker")
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML)
    public PersonView getPersonViewFreemaker(@PathParam("personId") LongParam personId){
        return new PersonView(PersonView.Template.FREEMAKER, findSafely(personId.get()));
    }

    @GET
    @Path("/view_mustache")
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML)
    public PersonView getPersonViewMustache(@PathParam("personId") LongParam personId){
        return new PersonView(PersonView.Template.MUSTACHE, findSafely(personId.get()));
    }


    private Person findSafely(long personId){
        final Optional<Person> person = personDAO.findById(personId);
        if (!person.isPresent()){
            throw new NotFoundException("No such user.");
        }

        return person.get();
    }
}
