package geekbrains.springData.Service;

import geekbrains.springData.Model.Person;
import geekbrains.springData.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // Создание новой записи
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    // Получение всех записей
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // Получение записи по ID
    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    // Обновление записи
    public Person updatePerson(Long id, Person updatedPerson) {
        return personRepository.findById(id)
                .map(person -> {
                    person.setFirstName(updatedPerson.getFirstName());
                    person.setLastName(updatedPerson.getLastName());
                    person.setAge(updatedPerson.getAge());
                    return personRepository.save(person);
                }).orElseThrow(() -> new RuntimeException("Person not found"));
    }

    // Удаление записи
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }
}
