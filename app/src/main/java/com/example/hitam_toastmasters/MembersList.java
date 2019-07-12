package com.example.hitam_toastmasters;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class MembersList extends AppCompatActivity {


    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
//    private StorageReference photoRef = FirebaseStorage.getInstance().getReference().child("hitam_tm_members");

    private MembersAdapter membersAdapter;
    private List<SingleMemberItem> singleMemberItem = new ArrayList<>();
    private List<String> memberNamesList = new ArrayList<>();
    private ProgressBar loading;

    private ListView membersList;

//    private DatabaseReference rootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://hitamtoastmasters.firebaseio.com/hitamToastmaster/memberProfiles");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.members_list);

        loading = findViewById(R.id.loading);
        loading.setVisibility(View.VISIBLE);

        membersList = findViewById(R.id.membersList);
        membersAdapter = new MembersAdapter(this, R.layout.pick_profile_list, singleMemberItem);
        membersList.setAdapter(membersAdapter);

        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                for (DataSnapshot ds : dataSnapshot.child("memberProfiles").getChildren()) {
                    String name = ds.child("name").getValue().toString();
                    String role = ds.child("role").getValue().toString();
                    String level = ds.child("level").getValue().toString();
                    String photoName = ds.child("photo").getValue().toString();

                    singleMemberItem.add(new SingleMemberItem(name, role, level, photoName));
                    memberNamesList.add(name);
                    membersAdapter.notifyDataSetChanged();
                }
                loading.setVisibility(View.GONE);
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

        membersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final String nameSelected = memberNamesList.get(i);
                final DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

                mRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {
                        for (DataSnapshot ds : dataSnapshot.child("memberProfiles").getChildren()) {
                            if (ds.child("name").getValue() == nameSelected) {
                                Intent intent =  new Intent(MembersList.this, Profile.class);

                                String name = ds.child("name").getValue().toString();
                                String role = ds.child("role").getValue().toString();
                                String level = ds.child("level").getValue().toString();
                                String photoName = ds.child("photo").getValue().toString();

                                intent.putExtra("name", name);
                                intent.putExtra("role", role);
                                intent.putExtra("level", level);
                                intent.putExtra("photo", photoName);
                                intent.putExtra("parentNode", ds.getKey());
                                startActivity(intent);
                            }
                        }
                    }
                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {}
                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {}
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                });
            }
        });

    }


    // Action Bar Search View
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        final MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                MembersAdapter membersAdapterx = (MembersAdapter) membersList.getAdapter();
                Filter filter = membersAdapterx.getFilter();
                filter.filter(s);
                return true;
            }
        });
        return true;
    }
}

//    // For Authentication
//    private static final int RC_SIGN_IN = 1;
//    // For Shared Preferences
//    public static final String MY_PREFS_NAME = "MyPrefsFile";
//
//    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
//    private FirebaseAuth.AuthStateListener mAuthStateListener;
//    private String user;

//    // For Authentication - Checks if user is authenticated or not
//    mAuthStateListener = new FirebaseAuth.AuthStateListener() {
//        @Override
//        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//            FirebaseUser userx = firebaseAuth.getCurrentUser();
//
//            if (userx != null) {
//                Toast.makeText(MainActivity.this, "You're now logged in!", Toast.LENGTH_SHORT).show();
//                user = mAuth.getUid();
//                // Sending user uid to all Activities
//                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//                editor.putString("user", user);
//                editor.apply();
//            } else {
//                startActivityForResult(
//                        AuthUI.getInstance()
//                                .createSignInIntentBuilder()
//                                .setIsSmartLockEnabled(false)
//                                .setAvailableProviders(Arrays.asList(
//                                        new AuthUI.IdpConfig.EmailBuilder().build()))
//                                .setTheme(R.style.LogInTheme)
//                                .build(),
//                        RC_SIGN_IN);
//            }
//        }
//    };
//    @Override
//    public void onResume() {
//        super.onResume();
//        mAuth.addAuthStateListener(mAuthStateListener);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mAuth.removeAuthStateListener(mAuthStateListener);
//    }

