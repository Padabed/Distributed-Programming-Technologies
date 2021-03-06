package eu.glowacki.jaxws.api.compositePerson;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(
        name = "IPerson",
        targetNamespace = "http://glowacki.eu/composite-people"
)
public interface IPerson {
    public static final String URI = "http://localhost:8080/composite-people";

    @WebMethod(action = "http://glowacki.eu/composite-people/filter")
    public PersonResponse filter(PersonRequest req);
}
