package com.example.hitam_toastmasters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PasswordNewMeeting extends AppCompatActivity {

    EditText passwordMeeting;
    Button submitPasswordMeeting;
    TextView wrongPwd;

    DatabaseReference pRef = FirebaseDatabase.getInstance().getReference().child("hitamToastmaster").child("password");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_new_meetings);

        passwordMeeting = findViewById(R.id.passwordMeeting);
        submitPasswordMeeting = findViewById(R.id.submitPasswordMeeting);
        wrongPwd = findViewById(R.id.wrongPwdMeeting);
        wrongPwd.setVisibility(View.GONE);

        submitPasswordMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String typedPassword = passwordMeeting.getText().toString().trim();

                pRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (typedPassword.equals(dataSnapshot.child("password").getValue().toString())) {
                            startActivity(new Intent(PasswordNewMeeting.this, NewMeetingEntry.class));
                            wrongPwd.setVisibility(View.GONE);
                            finish();
                        } else {
                            wrongPwd.setVisibility(View.VISIBLE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });


    }
}
