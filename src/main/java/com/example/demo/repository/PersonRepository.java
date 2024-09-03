package com.example.demo.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.Entity.Person;

public interface PersonRepository extends ListCrudRepository<Person, Long> {

   


}