package com.practice.guestbook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.practice.guestbook.Network.ClientApi;
import com.practice.guestbook.Network.ServerApi;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddCommentFragment extends Fragment {

    private Button addCommentButton;
    EditText titleEditText;
    EditText messageEditText;

    public AddCommentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_comment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addCommentButton = view.findViewById(R.id.addCommentButton);
        titleEditText = view.findViewById(R.id.titleEditText);
        messageEditText = view.findViewById(R.id.messageEditText);

        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IdentificationActivity.networkController.addNewComment(
                        getContext(),
                        titleEditText.getText().toString(),
                        messageEditText.getText().toString()
                );
            }
        });
    }
}
