package com.practice.guestbook.Network;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.practice.guestbook.Identification.RegistrationFragment;
import com.practice.guestbook.IdentificationActivity;
import com.practice.guestbook.MainActivity;
import com.practice.guestbook.User;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
    JSONParser jParser;
    Context context;

    public NetworkController(Context context) {
        this.context = context;
        pDialog = new ProgressDialog(context);
        jParser = new JSONParser();
    }

    public void loginUser(String email, String password) {
        login(email, password);
    }

    public void registerUser(String name, String email, String password, File avatar) {
        register(name, email, password, avatar);
    }

    private void register(String name, String email, String password, File avatar) {
        pDialog.setMessage("Registration ...");
        pDialog.show();

        RequestBody nameRequest = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody emailRequest = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody passwordRequest = RequestBody.create(MediaType.parse("text/plain"), password);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), RegistrationFragment.getRealPathFromURI(context, RegistrationFragment.selectedImageUri));
        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("avatar", avatar.getName(), requestFile);

        ServerApi serverApi = ClientApi.getApi();
        Call<User> call = serverApi.register(nameRequest, emailRequest, passwordRequest, multipartBody);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();

                if (user != null) {

                    SharedPreferences.Editor editor = IdentificationActivity.sp.edit();
                    editor.putInt("id", user.getId());
                    editor.putString("name", user.getName());
                    editor.putString("email", user.getEmail());
                    editor.putString("avatar", BASE_URL + user.getAvatar());
                    editor.putInt("is_admin", 0);
                    editor.putString("api_token", user.getApi_token());
                    editor.putString("created_at", user.getCreated_at());
                    editor.putString("updated_at", user.getUpdated_at());
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
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void login(String email, String password) {

        pDialog.setMessage("Authorization ...");
        pDialog.show();

        ServerApi serverApi = ClientApi.getApi();
        Call<User> call = serverApi.login(email, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();

                if (user != null) {

                    SharedPreferences.Editor editor = IdentificationActivity.sp.edit();
                    editor.putInt("id", user.getId());
                    editor.putString("name", user.getName());
                    editor.putString("email", user.getEmail());
                    editor.putString("avatar", user.getAvatar());
                    editor.putInt("is_admin", user.getIs_admin());
                    editor.putString("api_token", user.getApi_token());
                    editor.putString("created_at", user.getCreated_at());
                    editor.putString("updated_at", user.getUpdated_at());
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
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

}


