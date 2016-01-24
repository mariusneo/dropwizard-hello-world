package eu.test.dropwizard.resource;


import com.codahale.metrics.annotation.Timed;
import eu.test.dropwizard.api.Saying;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    private final String template;

    private final String defaultName;

    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") String name){
        final String value = String.format(template, StringUtils.isEmpty(name) ? defaultName : name);
        return new Saying(counter.incrementAndGet(), value);
    }
}
