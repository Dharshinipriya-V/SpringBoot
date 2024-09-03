package com.example.demo.controller;
 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Person;
import com.example.demo.repository.PersonRepository;
 
@RestController
@RequestMapping("/persons")  // Base path for all person-related endpoints
public class PersonController {
 
    @Autowired
    private PersonRepository personRepository;
 
    // GET all persons
    @GetMapping
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
 
    // GET person by ID
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable long id) {
        Person person = personRepository.findById(id).orElse(null);
        return person;
    }
 
    // POST to create a new person
    @PostMapping
    public void createPerson(@RequestBody Person person) {
        personRepository.save(person);
    }
 
    // PUT to update an existing person
    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        return personRepository.findById(id)
                .map(person -> {
                    // Update the fields as necessary
                    return personRepository.save(new Person(id, updatedPerson.p_name(), updatedPerson.p_email()));
                })
                .orElseThrow(() -> new RuntimeException("Person not found"));
    }
    // DELETE to remove a person by ID
    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }
}
 