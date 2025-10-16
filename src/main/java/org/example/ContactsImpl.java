package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ContactsImpl {
    private List<Contact> contacts = new ArrayList<>();
    private File file = null;

    public ContactsImpl(){

    }

    public ContactsImpl(String fileName) {
        if (fileName != null && !fileName.isEmpty()) {
            file = new File(fileName);
            if (file.exists()) {
                loadContacts();
            } else {
                saveContacts();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void loadContacts() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            contacts = (List<Contact>) in.readObject();
        } catch (Exception e) {
            throw new RuntimeException("Error loading contacts: " + e.getMessage());
        }
    }

    public void saveContacts() {
        if (file == null) return;
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(contacts);
        } catch (IOException e) {
            throw new RuntimeException("Error saving contacts: " + e.getMessage());
        }
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        saveContacts();
    }

    public void deleteContact(Contact contact) {
        contacts.remove(contact);
        saveContacts();
    }

    public int getCount() {
        return contacts.size();
    }

    public Contact getContact(int index) {
        if (index >= 0 && index < contacts.size()) {
            return contacts.get(index);
        }
        return null;
    }

    public void editContact(Contact contact, String fieldName, String newValue) {
        if (contact != null) {
            contact.setField(fieldName, newValue);
            saveContacts();
        }
    }

    public List<Contact> listContacts() {
        return new ArrayList<>(contacts);
    }

    public List<Contact> searchContacts(String query) {
        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        List<Contact> result = new ArrayList<>();

        for (Contact c : contacts) {
            StringBuilder info = new StringBuilder();
            for (String field : c.getEditableFields()) {
                info.append(c.getField(field)).append(" ");
            }

            if (pattern.matcher(info.toString().toLowerCase()).find()) {
                result.add(c);
            }
        }
        return result;
    }
}
