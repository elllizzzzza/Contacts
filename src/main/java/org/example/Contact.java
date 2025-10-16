package org.example;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Contact implements Serializable {
    private String phoneNumber;
    private LocalDateTime timeCreated;
    private LocalDateTime lastUpdated;

    public Contact() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (isValid(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Invalid phoneNumber");
            this.phoneNumber = "[no data]";
        }
        updateLastUpdated();
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    private boolean isValid(String number){
        String regex = "^\\+?(\\(?[0-9A-Za-z]{1,}\\)?([ -](\\(?[0-9A-Za-z]{2,}\\)?))*$)";

        if(number.chars().filter(ch -> ch == '(').count() > 1 ||
        number.chars().filter(ch -> ch == ')').count() > 1){
            return false;
        }
        return number.matches(regex);
    }

    public boolean hasNumber(){
        return !phoneNumber.isEmpty();
    }

    protected void updateLastUpdated(){
        lastUpdated = LocalDateTime.now();
    }

    public abstract String[] getEditableFields();

    public abstract void setField(String fieldName, String value);

    public abstract String getField(String fieldName);

    public abstract String getInfo();
}
