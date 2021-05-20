package nbg.spring.smdb.service;

import nbg.spring.smdb.domain.Person;
import nbg.spring.smdb.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl extends AbstractServiceImpl<Person> implements PersonService {
    private final PersonRepository PersonRepository;

    @Override
    public JpaRepository<Person, Long> getRepository() {
        return PersonRepository;
    }
}
