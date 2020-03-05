package com.practice.guestbook.Identification;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.practice.guestbook.IdentificationActivity;
import com.practice.guestbook.R;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    ImageView userImage;
    EditText userNameText;
    EditText userEmailText;
    EditText userPasswordText;
    Button registerButton;

    public static Uri selectedImageUri;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userImage = view.findViewById(R.id.userImage);
        userNameText = view.findViewById(R.id.userNameText);
        userEmailText = view.findViewById(R.id.userEmailText);
        userPasswordText = view.findViewById(R.id.userPasswordText);
        registerButton = view.findViewById(R.id.registerButton);


        //userImage.setImageDrawable();
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select image"), 1);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IdentificationActivity.networkController.registerUser(userNameText.getText().toString(), userEmailText.getText().toString(), userPasswordText.getText().toString(), new File(getRealPathFromURI(getContext(), selectedImageUri)));
            }
        });
    }

    public static String getRealPathFromURI(Context context, Uri contentURI) {
        String result = null;
        Cursor cursor = context.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            if(idx >= 0) {
                result = cursor.getString(idx);
            }
            cursor.close();
        }
        return result;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && data != null) {
            selectedImageUri = data.getData();
            userImage.setImageURI(selectedImageUri);
        }
    }
}
