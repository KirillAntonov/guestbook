package com.practice.guestbook.Network;

import com.practice.guestbook.CommentApiResponse;
import com.practice.guestbook.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ServerApi {

    @FormUrlEncoded
    @POST("api/v1/auth/login")
    Call<User> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @Multipart
    @POST("api/v1/auth/register")
    Call<User> register(
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part MultipartBody.Part file
    );

    @GET("api/v1/comment")
    Call<CommentApiResponse> getListOfComments(
            @Query("api_token") String api_token,
            @Query("page") int page
    );
}
