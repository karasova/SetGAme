package com.example.setgame;

public class RegisterResponse extends Response{
    int token;

    @Override
    public String toString() {
        return "RegisterResponse{" +
                "status='" + status + '\'' +
                ", token=" + token +
                '}';
    }
}
