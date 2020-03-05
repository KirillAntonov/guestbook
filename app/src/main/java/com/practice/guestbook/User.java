package com.practice.guestbook;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class User {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("avatar")
    private String avatar;
    @Nullable
    @SerializedName("is_admin")
    private int is_admin = 0;
    @SerializedName("api_token")
    private String api_token;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;

    public User() {

    }

    public User(int id, String name, String email, String avatar, int is_admin, String api_token, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.avatar = avatar;
        this.is_admin = is_admin;
        this.api_token = api_token;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setIs_admin(int is_admin) {
        this.is_admin = is_admin;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
