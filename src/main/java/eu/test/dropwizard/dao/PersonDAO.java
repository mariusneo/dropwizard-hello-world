package eu.test.dropwizard.dao;

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import eu.test.dropwizard.model.Person;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class PersonDAO extends AbstractDAO<Person> {
    public PersonDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    @Metered
    public Optional<Person> findById(Long id){
        return Optional.fromNullable(get(id));
    }

    @Metered
    public Person create(Person person){
        return persist(person);
    }

    @Metered
    public List<Person> findAll(){
        return list(namedQuery(Person.class.getName() +".findAll"));
    }
}
