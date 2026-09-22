package com.estudo.agenda.shared.VOs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private String email;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public Email(String email) {
        if (isValido(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email inválido: " + email);
        }
        this.email = email;
    }

    public static Boolean isValido(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
    public String getEmail() {
        return email;
    }

    @Override 
    public String toString() {
        return this.email;
    }
}
