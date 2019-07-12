package com.example.hitam_toastmasters;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class NewMeetingEntry extends AppCompatActivity {

    private EditText entryMeetingNumber, entryMeetingTheme, entryMeetingWOD,
    entryMeetingIOD, entryMeetingSAA, entryMeetingTMOD, entryMeetingGE, entryMeetingTTM,
    entryMeetingTimer, entryMeetingAhCounter, entryMeetingWMG;
    private EditText entryTTSpeaker1, entryTTSpeaker2, entryTTSpeaker3, entryTTSpeaker4,
    entryTTSpeaker5, entryTTSpeaker6, entryTTSpeaker7, entryTTSpeaker8, entryTTSpeaker9, entryTTSpeaker10;
    private EditText entrySpeaker1Desc, entrySpeaker2Desc, entrySpeaker3Desc,
    entryEvaluator1Desc, entryEvaluator2Desc, entryEvaluator3Desc, entryTMODDesc, entryGEDesc;

    private Spinner meetingSpinner;

    private RelativeLayout meetEntryRL, exCommRL;

    private Button entryMeetingDate, entryMeetingSubmit;

    private String meetingType;

    // For Date Picker
    private Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);

    private DatabaseReference meetRef = FirebaseDatabase.getInstance().getReference().child("hitamToastmaster");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_meeting_entry);

        entryMeetingNumber = findViewById(R.id.entryMeetingNumber);
        entryMeetingDate = findViewById(R.id.entryMeetingDate);
        entryMeetingTheme = findViewById(R.id.entryMeetingTheme);
        entryMeetingWOD = findViewById(R.id.entryMeetingWOD);
        entryMeetingIOD = findViewById(R.id.entryMeetingIOD);
        entryMeetingSAA = findViewById(R.id.entryMeetingSAA);
        entryMeetingTMOD = findViewById(R.id.entryMeetingTMOD);
        entryMeetingGE = findViewById(R.id.entryMeetingGE);
        entryMeetingTTM = findViewById(R.id.entryMeetingTTM);
        entryMeetingTimer = findViewById(R.id.entryMeetingTimer);
        entryMeetingAhCounter = findViewById(R.id.entryMeetingAhCounter);
        entryMeetingWMG = findViewById(R.id.entryMeetingWMG);

        entryTTSpeaker1 = findViewById(R.id.entryTTSpeaker1);
        entryTTSpeaker2 = findViewById(R.id.entryTTSpeaker2);
        entryTTSpeaker3 = findViewById(R.id.entryTTSpeaker3);
        entryTTSpeaker4 = findViewById(R.id.entryTTSpeaker4);
        entryTTSpeaker5 = findViewById(R.id.entryTTSpeaker5);
        entryTTSpeaker6 = findViewById(R.id.entryTTSpeaker6);
        entryTTSpeaker7 = findViewById(R.id.entryTTSpeaker7);
        entryTTSpeaker8 = findViewById(R.id.entryTTSpeaker8);
        entryTTSpeaker9 = findViewById(R.id.entryTTSpeaker9);
        entryTTSpeaker10 = findViewById(R.id.entryTTSpeaker10);

        entrySpeaker1Desc = findViewById(R.id.entrySpeaker1Desc);
        entrySpeaker2Desc = findViewById(R.id.entrySpeaker2Desc);
        entrySpeaker3Desc = findViewById(R.id.entrySpeaker3Desc);
        entryEvaluator1Desc = findViewById(R.id.entryEvaluator1Desc);
        entryEvaluator2Desc = findViewById(R.id.entryEvaluator2Desc);
        entryEvaluator3Desc = findViewById(R.id.entryEvaluator3Desc);
        entryTMODDesc = findViewById(R.id.entryTMODDesc);
        entryGEDesc = findViewById(R.id.entryGEDesc);

        meetEntryRL = findViewById(R.id.meetEntryRL);
        exCommRL = findViewById(R.id.exCommRL);

        entryMeetingSubmit = findViewById(R.id.entryMeetingSubmit);

//        // This is for meeting spinner
//        meetingSpinner = findViewById(R.id.titleSpinner);
//        String[] titles = new String[]{
//                "Club Meeting",
//                "ExComm Meeting"};
//        // Initializing an ArrayAdapter for Spinner
//        ArrayAdapter<String> spinnerArrayAdapter =
//                new ArrayAdapter<>(this, R.layout.spinner_item, titles);
//        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
//        meetingSpinner.setAdapter(spinnerArrayAdapter);
//        meetingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
//                if (i==0) {
//                    meetEntryRL.setVisibility(View.VISIBLE);
//                    exCommRL.setVisibility(View.GONE);
//                    meetingType = "clubMeetings";
//                } else if (i==1) {
//                    meetEntryRL.setVisibility(View.GONE);
//                    exCommRL.setVisibility(View.VISIBLE);
//                    meetingType ="exCommMeetings";
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {}
//        });


        // THIS IS CLUB MEETING ENTRY v

        // For choosing Date
        entryMeetingDate = findViewById(R.id.entryMeetingDate);
        entryMeetingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        NewMeetingEntry.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String datex = day+"/"+month+"/"+year;
                        entryMeetingDate.setText(datex);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        entryMeetingSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (entryMeetingTheme.getText().length() > 0
                        && entryMeetingDate.getText().length() > 4
                        && entryMeetingNumber.getText().length() > 0
                        && entryMeetingWOD.getText().length() > 0
                        && entryMeetingIOD.getText().length() > 0
                        && entryMeetingSAA.getText().length() > 0
                        && entryMeetingTMOD.getText().length() > 0
                        && entryMeetingGE.getText().length() > 0
                        && entryMeetingTTM.getText().length() > 0
                        && entrySpeaker1Desc.getText().length() > 0
                        && entryEvaluator1Desc.getText().length() > 0
                        && entryTTSpeaker1.getText().length() > 0
                        && entryTMODDesc.getText().length() > 0
                        && entryGEDesc.getText().length() > 0) {

                    // can add .child("clubMeetings")

                    meetRef.child("momeetings").push().setValue(new NewMeetingDetails(entryMeetingTheme.getText().toString().trim(),
                            entryMeetingNumber.getText().toString().trim(),
                            entryMeetingDate.getText().toString().trim(),
                            entryMeetingWOD.getText().toString().trim(),
                            entryMeetingIOD.getText().toString().trim(),
                            entryMeetingSAA.getText().toString().trim(),
                            entryMeetingTMOD.getText().toString().trim(),
                            entryMeetingGE.getText().toString().trim(),
                            entryMeetingTTM.getText().toString().trim(),
                            entryMeetingTimer.getText().toString().trim(),
                            entryMeetingAhCounter.getText().toString().trim(),
                            entryMeetingWMG.getText().toString().trim(),
                            entryTTSpeaker1.getText().toString().trim(),
                            entryTTSpeaker2.getText().toString().trim(),
                            entryTTSpeaker3.getText().toString().trim(),
                            entryTTSpeaker4.getText().toString().trim(),
                            entryTTSpeaker5.getText().toString().trim(),
                            entryTTSpeaker6.getText().toString().trim(),
                            entryTTSpeaker7.getText().toString().trim(),
                            entryTTSpeaker8.getText().toString().trim(),
                            entryTTSpeaker9.getText().toString().trim(),
                            entryTTSpeaker10.getText().toString().trim(),
                            entrySpeaker1Desc.getText().toString().trim(),
                            entrySpeaker2Desc.getText().toString().trim(),
                            entrySpeaker3Desc.getText().toString().trim(),
                            entryEvaluator1Desc.getText().toString().trim(),
                            entryEvaluator2Desc.getText().toString().trim(),
                            entryEvaluator3Desc.getText().toString().trim(),
                            entryTMODDesc.getText().toString().trim(),
                            entryGEDesc.getText().toString().trim()));

                    Toast.makeText(NewMeetingEntry.this, "Meeting successfully recorded!", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toast.makeText(NewMeetingEntry.this, "Please fill in the details!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // FOR EXCOMM MEETING POSTER



    }
}
