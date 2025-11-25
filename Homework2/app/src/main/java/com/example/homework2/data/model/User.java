package com.example.homework2.data.model;

public class User {
    private final int id;
    private final String username;
    private final String email;
    private final String password;
    private final String avatar_path;
    private final String signature;
    public User(int id, String username, String email, String password, String avatar_path, String signature) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar_path = avatar_path;
        this.signature = signature;
    }
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getAvatar_path() {
        return avatar_path;
    }
    public String getSignature() {
        return signature;
    }
}
