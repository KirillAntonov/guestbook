package com.practice.guestbook.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientApi {
    static final String BASE_URL = "http://pusher.cpl.by/";
    private static Retrofit retrofit;
    private static ServerApi serverApi;

    public static ServerApi getApi () {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            serverApi = retrofit.create(ServerApi.class);
        }
        return serverApi;
    }
}
