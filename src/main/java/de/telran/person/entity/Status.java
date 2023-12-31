package de.telran.person.entity;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String value;

    Status(String value) {
        this.value = value;
    }

}