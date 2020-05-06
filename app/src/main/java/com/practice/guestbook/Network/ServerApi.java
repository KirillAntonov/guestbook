package com.practice.guestbook.Network;

import com.practice.guestbook.CommentApiResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServerApi {

    @FormUrlEncoded
    @POST("api/v1/auth/login")
    Call<UserResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @Multipart
    @POST("api/v1/auth/register")
    Call<UserResponse> register(
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("password_confirmation") RequestBody password_confirmation,
            @Part MultipartBody.Part file
    );

    @GET("api/v1/posts")
    Call<CommentApiResponse> getListOfComments(
            @Header("Authorization") String authorization,
            @Query("page") int page
    );

    @FormUrlEncoded
    @POST("api/v1/posts")
    Call<Void> addNewComment (
            @Field("title") String title,
            @Field("message") String message,
            @Header("Authorization") String authorization
    );


    @DELETE("api/v1/posts/{comment}")
    Call<Void> deleteComment(
            @Path("comment") long comment_id,
            @Header("Authorization") String authorization
    );
}
