package com.practice.guestbook.Network;

import com.google.gson.annotations.SerializedName;
import com.practice.guestbook.Token;
import com.practice.guestbook.User;

public class UserResponse {
    @SerializedName("user")
    private User user;
    @SerializedName("token")
    private Token token;

    public UserResponse() {

    }

    public UserResponse(User user, Token token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}

