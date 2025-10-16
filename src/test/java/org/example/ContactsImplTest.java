package org.example;

import org.junit.jupiter.api.*;
import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactsImplTest {

    private File tempFile;
    private ContactsImpl contacts;

    @BeforeEach
    void setUp() {
        contacts = new ContactsImpl();
    }

    @Test
    void testAddContact() {
        Contact contact = new Person("Alice", "Brown");
        contacts.addContact(contact);
        assertEquals(1, contacts.getCount());
        assertEquals("Brown", contacts.getContact(0).getField("surname"));
    }

    @Test
    void testDeleteContact() {
        Contact contact = new Person("Alice", "Brown");
        contacts.addContact(contact);
        contacts.deleteContact(contact);
        assertEquals(0, contacts.getCount());
    }

    @Test
    void testEditContact() {
        Contact contact = new Person("Alice", "Brown");
        contacts.addContact(contact);
        contacts.editContact(contact, "gender", "F");

        Contact updated = contacts.getContact(0);
        assertEquals("F", updated.getField("gender"));
    }

    @Test
    void testGetContact() {
        Contact contact = new Person("Alice", "Brown");
        contacts.addContact(contact);
        assertEquals("Alice", contacts.getContact(0).getField("name"));
    }

    @Test
    void testGetContactInvalid() {
        assertNull(contacts.getContact(5));
    }

    @Test
    void testListContacts() {
        Contact contact1 = new Person("Alice", "Brown");
        Contact contact2 = new Organization("Central Bank", "Wall str");
        contacts.addContact(contact1);
        contacts.addContact(contact2);

        List<Contact> list = contacts.listContacts();
        assertEquals(2, list.size());
        assertTrue(list.contains(contact1));
        assertTrue(list.contains(contact2));
    }

    @Test
    void testSearchContacts(){
        Contact contact1 = new Person("Alice", "Brown");
        Contact contact2 = new Organization("Central Bank", "Wall str");
        contacts.addContact(contact1);
        contacts.addContact(contact2);

        List<Contact> result = contacts.searchContacts("bANk");
        assertEquals(1, result.size());
        assertTrue(result.contains(contact2));
        assertFalse(result.contains(contact1));
    }
}
