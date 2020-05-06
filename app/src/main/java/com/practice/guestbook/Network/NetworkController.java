package com.practice.guestbook.Network;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.Toast;

import com.practice.guestbook.IdentificationActivity;
import com.practice.guestbook.MainActivity;
import com.practice.guestbook.PathUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.practice.guestbook.IdentificationActivity.user;
import static com.practice.guestbook.Network.ClientApi.BASE_URL;

public class NetworkController {

    ProgressDialog pDialog;
    Context context;

    public NetworkController(Context context) {
        this.context = context;
        pDialog = new ProgressDialog(context);
    }

    public void loginUser(String email, String password) {
        login(email, password);
    }

    public void registerUser(String name, String email, String password, Uri selectedUri) {
        register(name, email, password, selectedUri);
    }

    private void register(String name, String email, String password, Uri selectedUri) {
        pDialog.setMessage("Registration ...");
        pDialog.show();

        File file = new File(PathUtils.getPath(context, selectedUri));

        RequestBody nameRequest = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody emailRequest = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody passwordRequest = RequestBody.create(MediaType.parse("text/plain"), password);
        RequestBody passwordConfirmationRequest = RequestBody.create(MediaType.parse("text/plain"), password);

        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("avatar", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

        ServerApi serverApi = ClientApi.getApi();
        Call<UserResponse> call = serverApi.register(nameRequest, emailRequest, passwordRequest, passwordConfirmationRequest, multipartBody);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if (response.body() != null) {

                    user.setUser(response.body().getUser());
                    user.setToken(response.body().getToken());

                    SharedPreferences.Editor editor = IdentificationActivity.sp.edit();
                    editor.putInt("id", user.getUser().getId());
                    editor.putString("name", user.getUser().getName());
                    editor.putString("email", user.getUser().getEmail());
                    editor.putString("avatar", BASE_URL + user.getUser().getAvatar());
                    editor.putInt("is_admin", user.getUser().getIs_admin());
                    editor.putString("created_at", user.getUser().getCreated_at());
                    editor.putString("updated_at", user.getUser().getUpdated_at());

                    editor.putString("token", user.getToken().getToken());
                    editor.putString("expires_at", user.getToken().getExpires_at());
                    editor.apply();

                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);

                    Toast.makeText(context, "Welcome!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Incorrect data.", Toast.LENGTH_LONG).show();
                }

                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                pDialog.dismiss();
            }
        });
    }

    private void login(String email, String password) {

        pDialog.setMessage("Authorization ...");
        pDialog.show();

        ServerApi serverApi = ClientApi.getApi();
        Call<UserResponse> call = serverApi.login(email, password);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {


                if (response.body() != null) {

                    user.setUser(response.body().getUser());
                    user.setToken(response.body().getToken());

                    SharedPreferences.Editor editor = IdentificationActivity.sp.edit();
                    editor.putInt("id", user.getUser().getId());
                    editor.putString("name", user.getUser().getName());
                    editor.putString("email", user.getUser().getEmail());
                    editor.putString("avatar", BASE_URL + user.getUser().getAvatar());
                    editor.putInt("is_admin", user.getUser().getIs_admin());
                    editor.putString("created_at", user.getUser().getCreated_at());
                    editor.putString("updated_at", user.getUser().getUpdated_at());

                    editor.putString("token", user.getToken().getToken());
                    editor.putString("expires_at", user.getToken().getExpires_at());
                    editor.apply();

                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);

                    Toast.makeText(context, "Welcome!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Incorrect email or password.", Toast.LENGTH_LONG).show();
                }

                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void addNewComment(final Context context, String title, String message) {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Adding new comment ...");
        pDialog.show();

        ServerApi serverApi = ClientApi.getApi();
        serverApi.addNewComment(title, message, "Bearer " + IdentificationActivity.user.getToken().getToken()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                pDialog.dismiss();
                Toast.makeText(context, "Comment is added.", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void deleteComment(final Context context, final long comment_id) {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Deleting comment ...");
        pDialog.show();

        ServerApi serverApi = ClientApi.getApi();
        serverApi.deleteComment(comment_id, "Bearer " + IdentificationActivity.user.getToken().getToken()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                pDialog.dismiss();

                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);

                Toast.makeText(context, "Comment "+ comment_id +" is deleted.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


}


