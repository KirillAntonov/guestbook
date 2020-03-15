package com.practice.guestbook;

import androidx.annotation.Nullable;

import java.util.List;

public class CommentApiResponse {
    int current_page;
    List<Data> data;
    int from;
    int last_page;
    String next_page_url;
    String path;
    int per_page;
    String prev_page_url;
    int to;
    long total;
}

class Data {
    long comment_id;
    User user;;
    String title;
    String message;
    String created_at;
}
