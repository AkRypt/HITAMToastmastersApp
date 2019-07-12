package com.example.hitam_toastmasters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Grammarian extends AppCompatActivity {

    private EditText gramSpeakerName, remarksGram;
    private CheckBox wod, iod;
    private Button gramSubmit;
    private ListView gramList;
    private List<GramSingleItem> gramSingleItems = new ArrayList<>();
    private GrammarianAdapter grammarianAdapter;

    private String wodx, iodx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grammarian);

        gramSpeakerName = findViewById(R.id.gramSpeakerName);
        remarksGram = findViewById(R.id.remarksGram);

        wod = findViewById(R.id.wodCheckbox);
        iod = findViewById(R.id.iodCheckbox);
        gramSubmit = findViewById(R.id.gramSubmit);

        gramList = findViewById(R.id.gramList);
        grammarianAdapter = new GrammarianAdapter(this, R.layout.gram_results, gramSingleItems);
        gramList.setAdapter(grammarianAdapter);

        gramSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gramSpeakerName.getText().toString().trim().length() > 0
                        && remarksGram.getText().toString().trim().length() > 0) {

                    if (wod.isChecked()) { wodx = "yes"; }
                    else { wodx = "no"; }
                    if (iod.isChecked()) { iodx = "yes"; }
                    else { iodx = "no"; }

                    gramSingleItems.add(new GramSingleItem(gramSpeakerName.getText().toString().trim(),
                            wodx, iodx, remarksGram.getText().toString().trim()));
                    grammarianAdapter.notifyDataSetChanged();

                    gramSpeakerName.setText("");
                    wod.setChecked(false);
                    iod.setChecked(false);
                    remarksGram.setText("");
                    Toast.makeText(Grammarian.this, "Successfully recorded!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Grammarian.this, "Please fill in the fields!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
