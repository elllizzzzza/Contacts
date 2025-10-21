package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public abstract class Contact implements Serializable {
    private String phoneNumber;
    private LocalDateTime timeCreated;
    private LocalDateTime lastUpdated;
    private static final String PHONE_NUMBER_REGEX = "^\\+?(\\(?[0-9A-Za-z]+\\)?([ -](\\(?[0-9A-Za-z]{2,}\\)?))*$)";

    public void setPhoneNumber(String phoneNumber) {
        if (isValid(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Invalid phoneNumber");
            this.phoneNumber = "[no data]";
        }
        updateLastUpdated();
    }

    private boolean isValid(String number){
        if(number.chars().filter(ch -> ch == '(').count() > 1 ||
        number.chars().filter(ch -> ch == ')').count() > 1){
            return false;
        }
        return number.matches(PHONE_NUMBER_REGEX);
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
