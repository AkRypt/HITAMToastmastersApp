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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MeetingsList extends AppCompatActivity {

    private ListView meetingsList;
    private ProgressBar meetingsLoading;
    final List<String[]> meetingListItem = new ArrayList<>();
    private List<String> meetingNumberList = new ArrayList<>();

    private DatabaseReference meetingsRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meetings_list);

        meetingsLoading = findViewById(R.id.meetingsLoading);
        meetingsLoading.setVisibility(View.VISIBLE);

        meetingsList = findViewById(R.id.meetingsListView);
//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, meetingListItem);
//        meetingsList.setAdapter(arrayAdapter);    THIS DOESNT WORK

        meetingsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                for (DataSnapshot ds : dataSnapshot.child("momeetings").getChildren()) {
                    String meetingNum = ds.child("meetingNumber").getValue(String.class);
                    String meetingDate = ds.child("meetingDate").getValue(String.class);

                    meetingNumberList.add(0, meetingNum);
                    meetingListItem.add(0, new String[]{"Meeting #"+meetingNum, meetingDate});
                }
                meetingsLoading.setVisibility(View.GONE);
                ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(MeetingsList.this, R.layout.simple_list_item_2_vt, R.id.t1, meetingListItem) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        TextView text1 = view.findViewById(R.id.t1);
                        TextView text2 = view.findViewById(R.id.t2);

                        String[] entry = meetingListItem.get(position);
                        text1.setText(entry[0]);
                        text2.setText(entry[1]);
                        return view;
                    }
                };
                meetingsList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        meetingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final String numSelected = meetingNumberList.get(i);

                meetingsRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        for (DataSnapshot ds : dataSnapshot.child("momeetings").getChildren()) {
                            if (ds.child("meetingNumber").getValue() == numSelected) {

                                String meetingNode = ds.getKey();

                                Intent intent =  new Intent(MeetingsList.this, MeetingMinutesPoster.class);
                                intent.putExtra("meetingNode", meetingNode);
                                startActivity(intent);
                            }
                        }
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
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_meeting_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(MeetingsList.this, PasswordNewMeeting.class));
        return super.onOptionsItemSelected(item);
    }
}
