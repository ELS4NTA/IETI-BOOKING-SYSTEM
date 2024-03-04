package edu.eci.ieti.bookingsystem.repository.user;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Document(collection = "users_collection")
public class UserDocument {

    @Id
    private String id;
    private LocalDate createdAt;
    private String name;
    private String lastName;
    private String email;
    private String passwordHash;
    private List<RoleEnum> roles;

    public UserDocument() {
    }

    public UserDocument(String id, String name, String lastName, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.createdAt = LocalDate.now();
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public UserDocument(UserDto userDto) {
        this.id = null;
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.createdAt = LocalDate.now();
        this.passwordHash = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
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

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void update(UserDto userDto) {
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void addRole(RoleEnum role) {
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }

}
