package com.phonebook.phonebookapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phonebook.phonebookapi.dto.ContactDto;
import com.phonebook.phonebookapi.model.Contact;
import com.phonebook.phonebookapi.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private static final Logger LOGGER= LoggerFactory.getLogger(ContactService.class);

    private final ContactRepository contactRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public ContactService(ContactRepository contactRepository, ObjectMapper objectMapper) {
        this.contactRepository = contactRepository;
        this.objectMapper = objectMapper;
    }

    public Contact addContact(ContactDto contactDto){
        LOGGER.info("Add contact {}",contactDto);

        Contact contact = objectMapper.convertValue(contactDto,Contact.class);

        return contactRepository.save(contact);
    }

    public Page<Contact> getAllContacts(Pageable pageable){

        return contactRepository.findAll(pageable);
    }

    public Contact getContact(Long id) throws Exception {

        return contactRepository.findById(id).orElseThrow(()->new Exception("Contact "+id+" not found"));
    }

    public Contact updateContact(Long id,ContactDto contactDto) throws Exception {
        Contact contact = getContact(id);

        BeanUtils.copyProperties(contact,contactDto);

        return contactRepository.save(contact);
    }

    public void deleteContact(Long id){

        contactRepository.deleteById(id);
    }

    public Contact getS(){

        return contactRepository.findByFirstNameChar();
    }
}
