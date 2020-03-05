package com.practice.guestbook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.practice.guestbook.Identification.AuthorizationFragment;
import com.practice.guestbook.Network.NetworkController;

public class IdentificationActivity extends AppCompatActivity {

    public static User user;
    public static SharedPreferences sp;
    public static NetworkController networkController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idetification);

        sp = getPreferences(MODE_PRIVATE);

        networkController = new NetworkController(this);


        if (sp.getInt("id", 0) != 0) {
            user = new User(sp.getInt("id", 0), sp.getString("name", ""), sp.getString("email", ""),
                    sp.getString("avatar", ""), sp.getInt("is_admin", 0), sp.getString("api_token", ""),
                    sp.getString("created_at", ""), sp.getString("updated_at", ""));

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.identificationMainConstraint, new AuthorizationFragment()).commit();
        }

    }
}
