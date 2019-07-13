package com.example.hitam_toastmasters;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AhCounterPage extends AppCompatActivity {

    private EditText ahcSpeakerName, ahCount, umCount, shortCount, longCount, andCount, soCount, word, remarks;
    private Button ahAdd, umAdd, shortAdd, longAdd, andAdd, soAdd, ahcSubmit;
    private NonScrollableListView ahcList;
    final List<AhCounterSingleItem> ahcListItem = new ArrayList<>();
    private AhCounterResultsAdapter ahCounterResultsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ahcounter_page);

        ahcSpeakerName = findViewById(R.id.ahcSpeakerName);
        ahCount = findViewById(R.id.ahCount);
        umCount = findViewById(R.id.umCount);
        shortCount = findViewById(R.id.shortCount);
        longCount = findViewById(R.id.longCount);
        andCount = findViewById(R.id.andCount);
        soCount = findViewById(R.id.soCount);
        word = findViewById(R.id.wordAhc);
        remarks = findViewById(R.id.remarksAhc);

        ahAdd = findViewById(R.id.ahAddBtn);
        umAdd = findViewById(R.id.umAddBtn);
        shortAdd = findViewById(R.id.shortAddBtn);
        longAdd = findViewById(R.id.longAddBtn);
        andAdd = findViewById(R.id.andAddBtn);
        soAdd = findViewById(R.id.soAddBtn);
        ahcSubmit = findViewById(R.id.ahcSubmit);

        ahcList = findViewById(R.id.ahcList);
        ahCounterResultsAdapter = new AhCounterResultsAdapter(this, R.layout.ahcounter_results, ahcListItem);
        ahcList.setAdapter(ahCounterResultsAdapter);

        ahAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ahC = Integer.parseInt(ahCount.getText().toString().trim())+1;
                ahCount.setText(String.valueOf(ahC));
            }
        });

        umAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(umCount.getText().toString().trim())+1;
                umCount.setText(String.valueOf(count));
            }
        });

        shortAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(shortCount.getText().toString().trim())+1;
                shortCount.setText(String.valueOf(count));
            }
        });

        longAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(longCount.getText().toString().trim())+1;
                longCount.setText(String.valueOf(count));
            }
        });

        andAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(andCount.getText().toString().trim())+1;
                andCount.setText(String.valueOf(count));
            }
        });

        soAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(soCount.getText().toString().trim())+1;
                soCount.setText(String.valueOf(count));
            }
        });

        ahcSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ahcSpeakerName.getText().toString().trim().length() > 0) {

                    ahcListItem.add(new AhCounterSingleItem(ahcSpeakerName.getText().toString().trim(),
                            ahCount.getText().toString(), umCount.getText().toString(),
                            shortCount.getText().toString(), longCount.getText().toString(),
                            andCount.getText().toString(), soCount.getText().toString(),
                            word.getText().toString(), remarks.getText().toString()));
                    ahCounterResultsAdapter.notifyDataSetChanged();

                    ahcSpeakerName.setText("");
                    ahCount.setText("0");
                    umCount.setText("0");
                    shortCount.setText("0");
                    longCount.setText("0");
                    andCount.setText("0");
                    soCount.setText("0");
                    word.setText("");
                    remarks.setText("");
                    Toast.makeText(AhCounterPage.this, "Successfully recorded!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AhCounterPage.this, "Please enter Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.warning)
                .setTitle("Exit?")
                .setMessage("Are you sure you want to exit? All the results will be gone. Take a screenshot if you need the results.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
