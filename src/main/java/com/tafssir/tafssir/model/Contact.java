package com.tafssir.tafssir.model;

import jakarta.persistence.*;


@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String name;
    private String email;
    private String message;

    public String getName() {
        return name;
    }

    public Contact(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
