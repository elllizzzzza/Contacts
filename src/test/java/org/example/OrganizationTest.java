package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationTest {
    Organization organization;

    @BeforeEach
    void setUp() {
        organization = new Organization("Car shop", "Wall St. 3");
    }

    @Test
    void testGettersAndSetters() {
        organization.setOrgName("Central Bank");
        assertEquals("Central Bank", organization.getOrgName());

        organization.setAddress("Wall St. 4");
        assertEquals("Wall St. 4", organization.getAddress());
    }

    @Test
    void getEditableFields() {
        String[] expected = {"name", "address", "number"};
        assertEquals(Arrays.toString(expected), Arrays.toString(organization.getEditableFields()));
    }

    @Test
    void testSetField() {
        organization.setField("name", "AUA");
        assertEquals("AUA", organization.getOrgName());

        organization.setField("address", "Baghramyan");
        assertEquals("Baghramyan", organization.getAddress());

        organization.setField("number", "(122)((123))");
        assertEquals("[no data]", organization.getPhoneNumber());
    }

    @Test
    void testGetField() {
        String name = organization.getField("name");
        assertEquals("Car shop", name);

        String address = organization.getField("address");
        assertEquals("Wall St. 3", address);

        String number = organization.getField("number");
        assertNull(number);

        String def = organization.getField("aa");
        assertEquals("", def);
    }

    @Test
    void getInfo() {
        String info = organization.getInfo();
        assertEquals("Organization{name=Car shop, address = Wall St. 3, number = null, time created = null, last edited = null}", info);
    }
}