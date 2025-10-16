package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person("John", "Doe");
    }

    @Test
    void testGettersAndSetters() {
        person.setName("Alice");
        assertEquals("Alice", person.getName());

        person.setSurname("Smith");
        assertEquals("Smith", person.getSurname());

        person.setBirthDate("1990-01-01");
        assertEquals("1990-01-01", person.getBirthDate());

        person.setGender("F");
        assertEquals("F", person.getGender());
    }

    @Test
    void testInvalidBirthDateAndGender() {
        person.setBirthDate("");
        assertEquals("", person.getBirthDate());

        person.setGender("aaa");
        assertEquals("[no data]", person.getGender());
    }

    @Test
    void testPhoneNumberValidation() {
        person.setPhoneNumber("+1 234-567");
        assertEquals("+1 234-567", person.getPhoneNumber());

        person.setPhoneNumber("a--aa");
        assertEquals("[no data]", person.getPhoneNumber());
    }

    @Test
    void testHasNumber() {
        person.setPhoneNumber("+1 234-567");
        assertTrue(person.hasNumber());
    }

    @Test
    void testGetInfo() {
        String expected =  "Person{Name = John, surname = Doe, Birth date = null, gender = null, number = null, time created = null, last edited = null}";
        assertEquals(expected, person.getInfo());
    }

    @Test
    void testSetField(){
        person.setField("name", "Helen");
        assertEquals("Helen", person.getName());

        person.setField("surname", "Brown");
        assertEquals("Brown", person.getSurname());

        person.setField("birth", "22/10/2000");
        assertEquals("22/10/2000", person.getBirthDate());

        person.setField("gender", "M");
        assertEquals("M", person.getGender());

        person.setField("number", "1234-56");
        assertEquals("1234-56", person.getPhoneNumber());
    }

    @Test
    void testGetField(){
        String name = person.getField("name");
        assertEquals("John", name);

        String surname = person.getField("surname");
        assertEquals("Doe", surname);

        person.setBirthDate("10.03.2000");
        String birth = person.getField("birth");
        assertEquals("10.03.2000", birth);

        String gender = person.getField("gender");
        assertNull(gender);
    }

    @Test
    void getEditableFields() {
        String[] expected = {"name", "surname", "birth", "gender", "number"};
        assertEquals(Arrays.toString(expected), Arrays.toString(person.getEditableFields()));
    }
}
