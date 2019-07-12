package com.example.hitam_toastmasters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MeetingMinutesPoster extends AppCompatActivity {

    private TextView meetingTheme, meetingDate, meetingNumber, saaPlayer, tmodPlayer, gePlayer,
    ttmPlayer, timerPlayer, ahcPlayer, gramPlayer, wod, iod, tmodDesc, speaker1Desc, speaker2Desc,
    speaker3Desc, evaluator1Desc, evaluator2Desc, evaluator3Desc, ttSpeaker1, ttSpeaker2,
            ttSpeaker3, ttSpeaker4, ttSpeaker5, ttSpeaker6, ttSpeaker7, ttSpeaker8, ttSpeaker9,
            ttSpeaker10, geDesc;

    private DatabaseReference postRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.meeting_minutes_poster);

        final String meetingNode = getIntent().getStringExtra("meetingNode");

        meetingTheme = findViewById(R.id.meetingTheme);
        meetingDate = findViewById(R.id.meetingDate);
        meetingNumber = findViewById(R.id.meetingNumber);
        saaPlayer = findViewById(R.id.saaPlayer);
        tmodPlayer = findViewById(R.id.tmodPlayer);
        gePlayer = findViewById(R.id.gePlayer);
        ttmPlayer = findViewById(R.id.ttmPlayer);
        timerPlayer = findViewById(R.id.timerPlayer);
        ahcPlayer = findViewById(R.id.ahcPlayer);
        gramPlayer = findViewById(R.id.gramPlayer);
        wod = findViewById(R.id.wod);
        iod = findViewById(R.id.iod);
        tmodDesc = findViewById(R.id.tmodDesc);
        speaker1Desc = findViewById(R.id.speaker1Desc);
        speaker2Desc = findViewById(R.id.speaker2Desc);
        speaker3Desc = findViewById(R.id.speaker3Desc);
        evaluator1Desc = findViewById(R.id.evaluator1Desc);
        evaluator2Desc = findViewById(R.id.evaluator2Desc);
        evaluator3Desc = findViewById(R.id.evaluator3Desc);
        ttSpeaker1 = findViewById(R.id.ttSpeaker1);
        ttSpeaker2 = findViewById(R.id.ttSpeaker2);
        ttSpeaker3 = findViewById(R.id.ttSpeaker3);
        ttSpeaker4 = findViewById(R.id.ttSpeaker4);
        ttSpeaker5 = findViewById(R.id.ttSpeaker5);
        ttSpeaker6 = findViewById(R.id.ttSpeaker6);
        ttSpeaker7 = findViewById(R.id.ttSpeaker7);
        ttSpeaker8 = findViewById(R.id.ttSpeaker8);
        ttSpeaker9 = findViewById(R.id.ttSpeaker9);
        ttSpeaker10 = findViewById(R.id.ttSpeaker10);
        geDesc = findViewById(R.id.geDesc);


        postRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot ds, @Nullable String s) {

                ds = ds.child("momeetings").child(meetingNode);

                meetingTheme.setText(ds.child("meetingTheme").getValue().toString());
                meetingDate.setText(ds.child("meetingDate").getValue().toString());
                meetingNumber.setText("#"+ds.child("meetingNumber").getValue().toString());
                saaPlayer.setText(ds.child("saa").getValue().toString());
                tmodPlayer.setText(ds.child("tmod").getValue().toString());
                gePlayer.setText(ds.child("ge").getValue().toString());
                ttmPlayer.setText(ds.child("ttm").getValue().toString());
                timerPlayer.setText(ds.child("timer").getValue().toString());
                ahcPlayer.setText(ds.child("ahcounter").getValue().toString());
                gramPlayer.setText(ds.child("wmg").getValue().toString());
                wod.append(ds.child("wod").getValue().toString());
                iod.append(ds.child("iod").getValue().toString());
                tmodDesc.setText(ds.child("tmodReview").getValue().toString());
                speaker1Desc.setText(ds.child("speaker1").getValue().toString());
                speaker2Desc.setText(ds.child("speaker2").getValue().toString());
                speaker3Desc.setText(ds.child("speaker3").getValue().toString());
                evaluator1Desc.setText(ds.child("evaluator1").getValue().toString());
                evaluator2Desc.setText(ds.child("evaluator2").getValue().toString());
                evaluator3Desc.setText(ds.child("evaluator3").getValue().toString());
                ttSpeaker1.setText(ds.child("ttSpeaker1").getValue().toString());
                ttSpeaker2.setText(ds.child("ttSpeaker2").getValue().toString());
                ttSpeaker3.setText(ds.child("ttSpeaker3").getValue().toString());
                ttSpeaker4.setText(ds.child("ttSpeaker4").getValue().toString());
                ttSpeaker5.setText(ds.child("ttSpeaker5").getValue().toString());
                ttSpeaker6.setText(ds.child("ttSpeaker6").getValue().toString());
                ttSpeaker7.setText(ds.child("ttSpeaker7").getValue().toString());
                ttSpeaker8.setText(ds.child("ttSpeaker8").getValue().toString());
                ttSpeaker9.setText(ds.child("ttSpeaker9").getValue().toString());
                ttSpeaker10.setText(ds.child("ttSpeaker10").getValue().toString());
                geDesc.setText(ds.child("geReview").getValue().toString());

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
}
