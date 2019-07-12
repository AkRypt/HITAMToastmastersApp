package com.example.hitam_toastmasters;

import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {

    private ProfileMeetingsAdapter profileMeetingsAdapter;
    private List<ProfileSingleMeetingItem> profileSingleMeetingItemList = new ArrayList<>();

    private ImageView memberPic;
    private TextView memberName, memberRole, memberLevel;
    private TextView pathway, ribbons, internalAttendance, externalAttendance, clubAbsence, speechesGiven;
    private ListView profileMeetingsList;
    private ProgressBar profileLoading;

    private String parentNode;

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.prof_action_bg));
//        getWindow().getDecorView()
//                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);

        profileLoading = findViewById(R.id.profileLoading);
        profileLoading.setVisibility(View.VISIBLE);
        memberPic = findViewById(R.id.memberPic);
        memberName = findViewById(R.id.memberName);
        memberRole = findViewById(R.id.memberRole);
        memberLevel = findViewById(R.id.memberLevel);

        pathway = findViewById(R.id.pathway);
        ribbons = findViewById(R.id.ribbons);
        internalAttendance = findViewById(R.id.internalAttendance);
        externalAttendance = findViewById(R.id.externalAttendance);
        clubAbsence = findViewById(R.id.clubAbsence);
        speechesGiven = findViewById(R.id.speechesGiven);

        memberName.setText(getIntent().getStringExtra("name"));
        memberRole.setText(getIntent().getStringExtra("role"));
        memberLevel.setText(getIntent().getStringExtra("level"));
        String photoUrl = "https://firebasestorage.googleapis.com/v0/b/hitamtoastmasters.appspot.com/o/hitam_tm_members%2F"
                +getIntent().getStringExtra("photo")+"?alt=media&token=e62da836-27a7-4b20-a535-79c087100f8f";
        Picasso.get().load(photoUrl).resize(350, 350).into(memberPic);

        profileMeetingsList = findViewById(R.id.profileMeetingsList);
        profileMeetingsAdapter = new ProfileMeetingsAdapter(this, R.layout.profile_meeting_item, profileSingleMeetingItemList);
        profileMeetingsList.setAdapter(profileMeetingsAdapter);

        parentNode = getIntent().getStringExtra("parentNode");

        // Profile Meetings List
        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                pathway.setText("Pathway: "+dataSnapshot.child("memberProfiles")
                        .child(parentNode).child("pathway").getValue().toString());
                ribbons.setText("Ribbons Earned: "+dataSnapshot.child("memberProfiles")
                        .child(parentNode).child("ribbonsEarned").getValue().toString());
                internalAttendance.setText("Club Meetings Attended: "+dataSnapshot.child("memberProfiles")
                                .child(parentNode).child("clubAttendance").getValue().toString());
                externalAttendance.setText("External Meetings Attended: "+dataSnapshot.child("memberProfiles")
                        .child(parentNode).child("externalAttendance").getValue().toString());
                clubAbsence.setText("Club Meetings Not Attended: "+dataSnapshot.child("memberProfiles")
                        .child(parentNode).child("clubAbsence").getValue().toString());
                speechesGiven.setText("Speeches Given: "+dataSnapshot.child("memberProfiles")
                        .child(parentNode).child("speechesGiven").getValue().toString());

                for (DataSnapshot ds : dataSnapshot.child("memberProfiles").child(parentNode).child("meetings").getChildren()) {
                    String name = ds.child("meetingName").getValue().toString();
                    String role = ds.child("rolePlayed").getValue().toString();
                    String date = ds.child("date").getValue().toString();
                    String ribbon = ds.child("ribbonEarned").getValue().toString();

                    profileSingleMeetingItemList.add(0, new ProfileSingleMeetingItem(name, role, date, ribbon));
                    profileMeetingsAdapter.notifyDataSetChanged();
                }
                profileLoading.setVisibility(View.GONE);
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

    }


    // Action Bar Button to edit profile
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_entry, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(Profile.this, Password.class);
        intent.putExtra("parentNode", parentNode);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }
}
