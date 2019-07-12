package com.example.hitam_toastmasters;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Entry extends AppCompatActivity {

    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    // For Date Picker
    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);

    private Spinner titleSpinner, newMeetRoleplayedSpinner, extMeetRolePlayedSpinner;
    private EditText levelEntry, newMeetName, newMeetDetailsName, extMeetDetailsName;
    private Button levelBtn, newMeetRadioSubmitBtn, newMeetSubmitBtn, extMeetBtn, attendDate, newMeetDate, extMeetDate;
    private RadioGroup attendedRadioGroup, ribbonGroup, extRibbonGroup;
    private RadioButton attendedRadioBtn, ribbonBtn, extRibbonBtn;
    private RelativeLayout levelView, newMeetAttendedView, newMeetDetailsView, extMeetView;

    private String parentNode, clubMeetingName, rolePlayed, ribbonEarned;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);

        parentNode = getIntent().getStringExtra("parentNode");
        dbRef = dbRef.child("hitamToastmaster").child("memberProfiles").child(parentNode);

        levelView = findViewById(R.id.levelView);
        levelEntry = findViewById(R.id.levelEntry);
        levelBtn = findViewById(R.id.levelBtn);
        newMeetAttendedView = findViewById(R.id.newMeetAttendedView);
        attendedRadioGroup = findViewById(R.id.newMeetRadioGroup);
        newMeetName = findViewById(R.id.newMeetName);
        newMeetSubmitBtn = findViewById(R.id.newMeetSubmitBtn);
        newMeetRadioSubmitBtn = findViewById(R.id.newMeetRadioSubmitBtn);
        newMeetDetailsView = findViewById(R.id.newMeetDetailsView);
        newMeetDetailsName = findViewById(R.id.newMeetDetailsName);
        ribbonGroup = findViewById(R.id.ribbonGroup);
        extMeetView = findViewById(R.id.extMeetView);
        extMeetDetailsName = findViewById(R.id.extMeetDetailsName);
        extRibbonGroup = findViewById(R.id.extRibbonGroup);
        extMeetBtn = findViewById(R.id.extMeetBtn);

        levelView.setVisibility(View.GONE);
        newMeetAttendedView.setVisibility(View.GONE);
        newMeetDetailsView.setVisibility(View.GONE);
        extMeetView.setVisibility(View.GONE);

        attendDate = findViewById(R.id.attendDate);
        newMeetDate = findViewById(R.id.newMeetDate);
        extMeetDate = findViewById(R.id.extMeetDate);


        // For choosing Date
        attendDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Entry.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String datex = day+"/"+month+"/"+year;
                        attendDate.setText(datex);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        newMeetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Entry.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String datex = day+"/"+month+"/"+year;
                        newMeetDate.setText(datex);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        extMeetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Entry.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String datex = day+"/"+month+"/"+year;
                        extMeetDate.setText(datex);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


        // This is for title spinner
        titleSpinner = findViewById(R.id.titleSpinner);
        String[] titles = new String[]{
                "Level",
                "Club Meeting",
                "External Meeting" };
        // Initializing an ArrayAdapter for Spinner
        ArrayAdapter<String> spinnerArrayAdapter =
                new ArrayAdapter<>(this, R.layout.spinner_item, titles);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        titleSpinner.setAdapter(spinnerArrayAdapter);
        titleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                if (i==0) {
                    levelView.setVisibility(View.VISIBLE);
                    newMeetAttendedView.setVisibility(View.GONE);
                    newMeetDetailsView.setVisibility(View.GONE);
                    extMeetView.setVisibility(View.GONE);
                } else if (i==1) {
                    levelView.setVisibility(View.GONE);
                    newMeetAttendedView.setVisibility(View.VISIBLE);
                    newMeetDetailsView.setVisibility(View.GONE);
                    extMeetView.setVisibility(View.GONE);

                } else if (i==2) {
                    levelView.setVisibility(View.GONE);
                    newMeetAttendedView.setVisibility(View.GONE);
                    newMeetDetailsView.setVisibility(View.GONE);
                    extMeetView.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(Entry.this, "Hey", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        // For Level Title
        levelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if (levelEntry.getText().length() > 0) {
                dbRef.child("level").setValue(levelEntry.getText().toString());
                Toast.makeText(Entry.this, "Level has been updated!", Toast.LENGTH_SHORT).show();
                levelEntry.setText("");
                finish();
            } else {
                Toast.makeText(Entry.this, "The field is empty!", Toast.LENGTH_SHORT).show();
            }
            }
        });


        // This is for rolePlayed spinner
        newMeetRoleplayedSpinner = findViewById(R.id.newMeetRolePlayed);
        final String[] roles = new String[]{
                "SAA",
                "TMOD",
                "General Evaluator",
                "Speaker",
                "Speech Evaluator",
                "Timer",
                "Ah Counter",
                "Grammarian",
                "TTM",
                "Vote Counter",
                "Audience",
                "Listener" };
        // Initializing an ArrayAdapter for Spinner
        ArrayAdapter<String> clubRoleSpinnerArrayAdapter =
                new ArrayAdapter<>(this, R.layout.spinner_item, roles);
        clubRoleSpinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        newMeetRoleplayedSpinner.setAdapter(clubRoleSpinnerArrayAdapter);
        newMeetRoleplayedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                rolePlayed = roles[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        // For New Meeting Title
        newMeetRadioSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attendedRadioBtn = findViewById(attendedRadioGroup.getCheckedRadioButtonId());
                if (attendedRadioBtn != null) {
                    if (attendedRadioBtn.getId() == (R.id.attended)) {
                        clubMeetingName = newMeetName.getText().toString().trim();
                        newMeetAttendedView.setVisibility(View.GONE);
                        newMeetDetailsView.setVisibility(View.VISIBLE);
                    } else if (attendedRadioBtn.getId() == (R.id.notAttended)) {
                        if (newMeetName.getText().toString().length() > 0 && attendDate.getText().toString().length() > 4) {
                            clubMeetingName = newMeetName.getText().toString().trim();
                            rolePlayed = "Not Attended";
                            ribbonEarned = "No";
                            dbRef.child("meetings").push().setValue(new MeetingDetails(clubMeetingName, rolePlayed, ribbonEarned, attendDate.getText().toString()));
                            dbRef.child("clubAbsence").runTransaction(new Transaction.Handler() {
                                @NonNull
                                @Override
                                public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                    int value;
                                    value = mutableData.getValue(Integer.class);
                                    value = value + 1;
                                    mutableData.setValue(value);
                                    return Transaction.success(mutableData);
                                }
                                @Override
                                public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {}
                            });
                            finish();
                        } else {
                            Toast.makeText(Entry.this, "Fill in the fields!", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(Entry.this, "Please fill the fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // For New Meeting Details
        newMeetSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ribbonBtn = findViewById(ribbonGroup.getCheckedRadioButtonId());
                if (newMeetDetailsName.getText().length() > 0 && ribbonBtn != null && newMeetDate.getText().length() > 4) {

                    clubMeetingName = newMeetDetailsName.getText().toString().trim();

                    if (ribbonBtn.getId() == R.id.ribbonEarned) {
                        ribbonEarned = "Yes";

                        // Incrementing Ribbon Count
                        dbRef.child("ribbonsEarned").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                int value;
                                value = mutableData.getValue(Integer.class);
                                value = value + 1;
                                mutableData.setValue(value);
                                return Transaction.success(mutableData);
                            }
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {}
                        });
                    } else if (ribbonBtn.getId() == R.id.ribbonNotEarned) {
                        ribbonEarned = "No";
                    }

                    if (rolePlayed.equals("Speaker")) {

                        // Incrementing Ribbon Count
                        dbRef.child("speechesGiven").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                int value;
                                value = mutableData.getValue(Integer.class);
                                value = value + 1;
                                mutableData.setValue(value);
                                return Transaction.success(mutableData);
                            }
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {}
                        });
                    }

                    // Pushing to Database
                    dbRef.child("meetings").push().setValue(new MeetingDetails(clubMeetingName, rolePlayed, ribbonEarned, newMeetDate.getText().toString()));
                    finish();

                    // Incrementing Attendance
                    dbRef.child("clubAttendance").runTransaction(new Transaction.Handler() {
                        @NonNull
                        @Override
                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                            int value;
                            value = mutableData.getValue(Integer.class);
                            value = value + 1;
                            mutableData.setValue(value);
                            return Transaction.success(mutableData);
                        }
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {}
                    });

                    newMeetDetailsName.setText("");
                    ribbonGroup.clearCheck();
                    newMeetDate.setText("");
                    newMeetDate.setHint("DATE");
                } else {
                    Toast.makeText(Entry.this, "Please fill the fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // This is for externalRolePlayed spinner
        extMeetRolePlayedSpinner = findViewById(R.id.extMeetRolePlayed);
        final String[] extRoles = new String[]{
                "SAA",
                "TMOD",
                "General Evaluator",
                "Speaker",
                "Speech Evaluator",
                "Timer",
                "Ah Counter",
                "Grammarian",
                "TTM",
                "Vote Counter",
                "Audience",
                "Listener" };
        // Initializing an ArrayAdapter for Spinner
        ArrayAdapter<String> extRoleSpinnerArrayAdapter =
                new ArrayAdapter<>(this, R.layout.spinner_item, roles);
        extRoleSpinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        extMeetRolePlayedSpinner.setAdapter(extRoleSpinnerArrayAdapter);
        extMeetRolePlayedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                rolePlayed = extRoles[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        // For External Meetings
        extMeetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                extRibbonBtn = findViewById(extRibbonGroup.getCheckedRadioButtonId());
                if (extMeetDetailsName.getText().length() > 0 && extRibbonBtn != null && extMeetDate.getText().length() > 4) {

                    clubMeetingName = extMeetDetailsName.getText().toString().trim();

                    if (extRibbonBtn.getId() == R.id.extRibbonEarned) {
                        ribbonEarned = "Yes";

                        // Incrementing Ribbon Count
                        dbRef.child("ribbonsEarned").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                int value;
                                value = mutableData.getValue(Integer.class);
                                value = value + 1;
                                mutableData.setValue(value);
                                return Transaction.success(mutableData);
                            }
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {}
                        });
                    } else if (extRibbonBtn.getId() == R.id.extRibbonNotEarned) {
                        ribbonEarned = "No";
                    }

                    if (rolePlayed.equals("Speaker")) {

                        // Incrementing Ribbon Count
                        dbRef.child("speechesGiven").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                int value;
                                value = mutableData.getValue(Integer.class);
                                value = value + 1;
                                mutableData.setValue(value);
                                return Transaction.success(mutableData);
                            }
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {}
                        });
                    }

                    //Pushing to DB
                    dbRef.child("meetings").push().setValue(new MeetingDetails(clubMeetingName, rolePlayed, ribbonEarned, extMeetDate.getText().toString()));
                    finish();

                    // Incrementing Attendance
                    dbRef.child("externalAttendance").runTransaction(new Transaction.Handler() {
                        @NonNull
                        @Override
                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                            int value;
                            value = mutableData.getValue(Integer.class);
                            value = value + 1;
                            mutableData.setValue(value);
                            return Transaction.success(mutableData);
                        }
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {}
                    });

                    newMeetDetailsName.setText("");
                    ribbonGroup.clearCheck();
                    extMeetDate.setHint("DATE");
                } else {
                    Toast.makeText(Entry.this, "Please fill the fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goToMembers(View view) {
        startActivity(new Intent(Entry.this, MainActivity.class));
    }
}