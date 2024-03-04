package edu.eci.ieti.bookingsystem.controller.auth;

import java.util.Date;

public class TokenDto {

    private String token;
    private Date expirationDate;

    public TokenDto(String token, Date expirationDate) {
        this.token = token;
        this.expirationDate = expirationDate;
    }

    public TokenDto() {
    }

    public String getToken() {
        return token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

}
