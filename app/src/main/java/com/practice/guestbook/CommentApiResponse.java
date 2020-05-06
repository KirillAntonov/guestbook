package com.practice.guestbook;

import java.util.List;

public class CommentApiResponse {
    List<Data> data;
    Links links;
    Meta meta;
}

class Data {
    long id;
    long user_id;
    User user;
    String title;
    String message;
    String created_at;
    String updated_at;
}

class Links {
    String first;
    String last;
    String prev;
    String next;
}

class Meta {
    int current_page;
    int from;
    int last_page;
    String path;
    int per_page;
    int to;
    long total;
}
