package org.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName = args.length > 0 ? args[0] : null;
        ContactsImpl app = new ContactsImpl(fileName);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\n[menu] Enter action (add, list, search, count, exit): > ");
            String action = scanner.nextLine().trim().toLowerCase();

            switch (action) {
                case "add":
                    handleAdd(app, scanner);
                    break;
                case "list":
                    handleList(app, scanner);
                    break;
                case "search":
                    handleSearch(app, scanner);
                    break;
                case "count":
                    System.out.println("The Phone Book has " + app.getCount() + " records.");
                    break;
                case "exit":
                    scanner.close();
                    return;
                default:
                    System.out.println("Unknown action");
            }
            System.out.println();
        }
    }

    private static void handleAdd(ContactsImpl app, Scanner scanner) {
        System.out.print("\nEnter the type (person, organization): ");
        String type = scanner.nextLine().toLowerCase();
        if (type.equals("person")) {
            System.out.print("Enter the name: ");
            String name = scanner.nextLine();
            System.out.print("Enter the surname: ");
            String surname = scanner.nextLine();
            Person person = new Person(name, surname);
            System.out.print("Enter the birth date: ");
            person.setBirthDate(scanner.nextLine());
            System.out.print("Enter the gender (M, F): ");
            person.setGender(scanner.nextLine());
            System.out.print("Enter the number: ");
            person.setPhoneNumber(scanner.nextLine());
            person.setTimeCreated(LocalDateTime.now());
            person.setLastUpdated(LocalDateTime.now());

            app.addContact(person);
            System.out.println("The record is added.");
        } else if (type.equals("organization")) {
            System.out.print("Enter the organization name: ");
            String orgName = scanner.nextLine();
            System.out.print("Enter the address: ");
            String address = scanner.nextLine();
            Organization org = new Organization(orgName, address);
            System.out.print("Enter the number: ");
            org.setPhoneNumber(scanner.nextLine());
            org.setTimeCreated(java.time.LocalDateTime.now());
            org.setLastUpdated(java.time.LocalDateTime.now());

            app.addContact(org);
            System.out.println("The record is added.");
        }
    }

    private static void handleList(ContactsImpl app, Scanner scanner) {
        List<Contact> contacts = app.listContacts();
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i).getField("name"));
        }

        System.out.print("\n[list] Enter action ([number], back): > ");
        String input = scanner.nextLine().trim();
        if (input.equals("back")) return;
        try {
            int index = Integer.parseInt(input);
            Contact contact = app.getContact(index-1);
            if (contact != null) {
                showRecordMenu(app, contact, scanner);
            } else {
                System.out.println("Invalid record number.");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid input. " + e.getMessage());
        }
    }

    private static void handleSearch(ContactsImpl app, Scanner scanner) {
        while (true) {
            System.out.print("\nEnter search query: > ");
            String query = scanner.nextLine();

            List<Contact> results = app.searchContacts(query);
            System.out.println("Found " + results.size() + " results:");
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i).getField("name"));
            }

            System.out.print("\n[search] Enter action ([number], back, again): > ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("back")) return;
            if (input.equals("again")) continue;
            try {
                int index = Integer.parseInt(input) - 1;
                if (index >= 0 && index < results.size()) {
                    showRecordMenu(app, results.get(index), scanner);
                } else {
                    System.out.println("Invalid selection.");
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid input. " + e.getMessage());
            }
            return;
        }
    }

    private static void showRecordMenu(ContactsImpl app, Contact contact, Scanner scanner) {
        System.out.println(contact.getInfo());
        while (true) {
            System.out.print("\n[record] Enter action (edit, delete, menu): > ");
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "edit":
                    String[] fields = contact.getEditableFields();
                    System.out.println("Select a field (");
                    for (String s : fields) {
                        System.out.print(s + ", ");
                    }
                    System.out.println(").");
                    String field = scanner.nextLine().trim();
                    System.out.print("Enter " + field + ": > ");
                    String newValue = scanner.nextLine();

                    app.editContact(contact, field, newValue);
                    System.out.println("Saved");
                    System.out.println(contact.getInfo());
                    break;
                case "delete":
                    app.deleteContact(contact);
                    System.out.println("The record removed!");
                    return;
                case "menu":
                    return;
                default:
                    System.out.println("Unknown action");
            }
        }
    }
}
