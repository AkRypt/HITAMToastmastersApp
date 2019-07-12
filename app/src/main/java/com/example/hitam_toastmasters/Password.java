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

public class Password extends AppCompatActivity {

    private EditText password;
    private Button submitPassword;
    private TextView wrongPwd;

    private DatabaseReference pRef = FirebaseDatabase.getInstance().getReference().child("hitamToastmaster");

    private String parentNode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);

        password = findViewById(R.id.password);
        submitPassword = findViewById(R.id.submitPassword);
        wrongPwd = findViewById(R.id.wrongPwd);
        wrongPwd.setVisibility(View.GONE);

        parentNode = getIntent().getStringExtra("parentNode");

        submitPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String typedPassword = password.getText().toString().trim();

                pRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (typedPassword.equals(dataSnapshot.child("password").child("password").getValue().toString())) {
                            Intent intent = new Intent(Password.this, Entry.class);
                            intent.putExtra("parentNode", parentNode);
                            startActivity(intent);
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
