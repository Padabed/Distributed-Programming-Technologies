package eu.glowacki.jaxws.implementation.compositePerson;

import eu.glowacki.jaxws.api.compositePerson.IPerson;
import eu.glowacki.jaxws.api.compositePerson.Person;
import eu.glowacki.jaxws.api.compositePerson.PersonRequest;
import eu.glowacki.jaxws.api.compositePerson.PersonResponse;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@WebService(
        name = "IPerson",
        targetNamespace = "http://glowacki.eu/composite-people"
)
public class PersonImpl implements IPerson {
    private static final Logger LOGGER = Logger.getAnonymousLogger();
    private static final List<Person> people = new LinkedList<>();
    private static final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    static {
        try {
            populate();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Endpoint.publish(IPerson.URI, new PersonImpl());
        LOGGER.info("SERVICE STARTED");
    }

    public static void populate() throws ParseException {
        people.add(new Person("Nikita", "Padabed", formatter.parse("2002-01-24")));
        people.add(new Person("Andrew", "Kasalski", formatter.parse("2002-01-24")));
        people.add(new Person("Kris", "Vinchi", formatter.parse("2001-01-24")));
        people.add(new Person("Arnold", "Lewandowki", formatter.parse("2000-01-24")));
        people.add(new Person("Ronaldo", "Kostilev", formatter.parse("2004-01-24")));
    }

    @Override
    public PersonResponse filter(PersonRequest req) {
        return new PersonResponse(people.stream().filter(x -> x.birthDate.equals(req.birthDate) && x.lastName.equals(req.lastName))
                .collect(Collectors.toList()));
    }
}
