package nbg.spring.smdb.controller;

import lombok.RequiredArgsConstructor;
import nbg.spring.smdb.domain.Person;
import nbg.spring.smdb.service.BaseService;
import nbg.spring.smdb.service.PersonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController extends AbstractController<Person> {
    private final PersonService personService;

    @Override
    public BaseService<Person, Long> getBaseService() { return personService; }
}
