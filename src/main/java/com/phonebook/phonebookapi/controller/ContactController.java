package com.phonebook.phonebookapi.controller;

import com.phonebook.phonebookapi.dto.ContactDto;
import com.phonebook.phonebookapi.model.Contact;
import com.phonebook.phonebookapi.repository.ContactRepository;
import com.phonebook.phonebookapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contacts/")
@CrossOrigin
public class ContactController {

    private final ContactService contactService;
    private final ContactRepository contactRepository;

    @Autowired
    public ContactController(ContactService contactService, ContactRepository contactRepository) {
        this.contactService = contactService;
        this.contactRepository = contactRepository;
    }

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public Contact addContact(@RequestBody @Valid ContactDto contactDto){

        return contactService.addContact(contactDto);
    }

    @GetMapping("getAllContacts")
    public Page<Contact> getAll(Pageable pageable){

        return contactService.getAllContacts(pageable);
    }

    @GetMapping("getById/{id}")
    public Contact getById(@PathVariable("id") Long id) throws Exception {

        return contactService.getContact(id);
    }

    @PutMapping("updateContact/{id}")
    public ResponseEntity<Contact> update(@PathVariable("id") Long id, @RequestBody @Valid ContactDto contactDto) throws Exception {

        Contact contact = contactService.updateContact(id,contactDto);

        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @DeleteMapping("deleteContact{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void delete(@PathVariable("id") Long id){

        contactService.deleteContact(id);
    }
    @GetMapping("S")
    public Contact getS(){
        return contactRepository.findByFirstNameChar();
    }

}
