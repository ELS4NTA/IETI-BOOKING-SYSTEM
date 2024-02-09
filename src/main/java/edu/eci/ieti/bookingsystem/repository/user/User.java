package edu.eci.ieti.bookingsystem.repository.user;

import java.time.LocalDate;
import java.util.UUID;

public class User {

    private final String id;
    private final LocalDate createdAt;
    private String name;
    private String lastName;
    private String email;

    public User(String id, String name, String lastName, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.createdAt = LocalDate.now();
    }

    public User(UserDto userDto) {
        this.id = UUID.randomUUID().toString();
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.createdAt = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void update(UserDto userDto) {
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
    }

}
