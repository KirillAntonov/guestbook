package com.practice.guestbook;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Token {
    @SerializedName("access_token")
    String token;
    @SerializedName("expires_at")
    String expires_at;

    public Token(String token, String expires_at) {
        this.token = token;
        this.expires_at = expires_at;
    }

    public String getToken() {
        return token;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }
}
