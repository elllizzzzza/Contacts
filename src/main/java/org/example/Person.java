package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Person extends Contact{
    private String name;
    private String surname;
    private String birthDate;
    private String gender;

    public void setBirthDate(String birthDate) {
        if (birthDate.isEmpty()) {
            this.birthDate = "[no data]";
            System.out.println("Invalid birthdate.");
        }
        this.birthDate = birthDate;
        updateLastUpdated();
    }

    public void setGender(String gender) {
        if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F")){
            this.gender = gender;
        } else {
            this.gender = "[no data]";
            System.out.println("Invalid gender");
        }
        updateLastUpdated();
    }

    public void setName(String name) {
        this.name = name;
        updateLastUpdated();
    }

    public void setSurname(String surname) {
        this.surname = surname;
        updateLastUpdated();
    }

    @Override
    public String[] getEditableFields() {
        return new String[] {"name", "surname", "birth", "gender", "number"};
    }

    @Override
    public void setField(String fieldName, String value) {
        switch (fieldName.toLowerCase()) {
            case "name":
                setName(value);
                break;
            case "surname":
                setSurname(value);
                break;
            case "birth":
                setBirthDate(value);
                break;
            case "gender":
                setGender(value);
                break;
            case "number":
                setPhoneNumber(value);
                break;
            default:
                System.out.println("Field not found");
        }
    }

    @Override
    public String getField(String fieldName) {
        switch (fieldName.toLowerCase()) {
            case "name":
                return name;
            case "surname":
                return surname;
            case "birth":
                return birthDate;
            case "gender":
                return gender;
            case "number":
                return getPhoneNumber();
            default:
                return "";
        }
    }

    @Override
    public String getInfo() {
        return "Person{" +
                "Name = " + name +
                ", surname = " + surname +
                ", Birth date = " + birthDate +
                ", gender = " + gender +
                ", number = " + getPhoneNumber() +
                ", time created = " + getTimeCreated() +
                ", last edited = " + getLastUpdated() +
                '}';
    }
}
