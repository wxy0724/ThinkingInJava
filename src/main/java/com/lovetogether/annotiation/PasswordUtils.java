package com.lovetogether.annotiation;

import java.util.List;

public class PasswordUtils {
    @UseCase(id = 47, description = "Password must contain at least one numermic")
    public boolean validatePassword(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }

    @UseCase(id = 48)
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id = 49, description = "New password can`t equal previously used ones")
    public boolean checkForNewPassword(List<String> prevPasswords, String password) {
        return !prevPasswords.contains(password);
    }

    public static void main(String[] args) {
        PasswordUtils passwordUtils = new PasswordUtils();
        System.out.println(passwordUtils.validatePassword("qwerty"));
    }
}
