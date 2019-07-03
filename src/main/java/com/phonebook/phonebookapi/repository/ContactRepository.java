package com.phonebook.phonebookapi.repository;

import com.phonebook.phonebookapi.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long>, CrudRepository<Contact,Long> {

//@Query("select contact from Contact contact where firstName like 'S%'")
@Query(value = "SELECT * FROM Contact WHERE firstName like 'S%'",nativeQuery = true)
Contact findByFirstNameChar();
}
