package com.practice.guestbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.practice.guestbook.Identification.AuthorizationFragment;
import com.practice.guestbook.Network.NetworkController;
import com.practice.guestbook.Network.UserResponse;

public class IdentificationActivity extends AppCompatActivity {

    public static UserResponse user;
    public static SharedPreferences sp;
    public static NetworkController networkController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idetification);

        sp = getPreferences(MODE_PRIVATE);

        networkController = new NetworkController(this);


        if (sp.getInt("id", 0) != 0) {
            user = new UserResponse(new User(sp.getInt("id", 0), sp.getString("name", ""), sp.getString("email", ""),
                    sp.getString("avatar", ""), sp.getInt("is_admin", 0),
                    sp.getString("created_at", ""), sp.getString("updated_at", "")), new Token(sp.getString("token", ""), sp.getString("expires_at", "")));

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            user = new UserResponse();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.identificationMainConstraint, new AuthorizationFragment()).commit();
        }

    }
}
