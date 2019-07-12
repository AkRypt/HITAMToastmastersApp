package com.example.hitam_toastmasters;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    private EditText ahcSpeakerName, ahCount, umCount, shortCount, longCount, remarks;
    private Button ahAdd, umAdd, shortAdd, longAdd, ahcSubmit;
    private ListView ahcList;
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
        remarks = findViewById(R.id.remarksAhc);

        ahAdd = findViewById(R.id.ahAddBtn);
        umAdd = findViewById(R.id.umAddBtn);
        shortAdd = findViewById(R.id.shortAddBtn);
        longAdd = findViewById(R.id.longAddBtn);
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

        ahcSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ahcSpeakerName.getText().toString().trim().length() > 0) {

                    ahcListItem.add(new AhCounterSingleItem(ahcSpeakerName.getText().toString().trim(),
                            ahCount.getText().toString(), umCount.getText().toString(),
                            shortCount.getText().toString(), longCount.getText().toString(),
                            remarks.getText().toString()));
                    ahCounterResultsAdapter.notifyDataSetChanged();

                    ahcSpeakerName.setText("");
                    ahCount.setText("0");
                    umCount.setText("0");
                    shortCount.setText("0");
                    longCount.setText("0");
                    remarks.setText("");
                    Toast.makeText(AhCounterPage.this, "Successfully recorded!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AhCounterPage.this, "Please enter Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
