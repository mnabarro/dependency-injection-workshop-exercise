package app.core;

import app.secondary.ContactRepository;

import java.util.List;

public class GetContacts {
    private final ContactRepository contactRepository;

    public GetContacts(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> execute() {
        return contactRepository.getAll();
    }
}
