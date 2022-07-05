package eu.glowacki.jaxws.api.compositePerson;

import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(
        name = "PersonResponseMessage",
        namespace = "http://glowacki.eu/composite-people"
)
public final class PersonResponse {
    public List<Person> people;

    public PersonResponse(List<Person> people) {
        this.people = people;
    }

    public PersonResponse() {
    }
}
