package com.example.hitam_toastmasters;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TimerPage extends AppCompatActivity {

    private Spinner timerSpinner;
    private EditText speakerName;
    private Chronometer chronometer;
    private Button timerStart, timerStop;
    private RelativeLayout timerBg, colorBtns;

    private int speechType;

    private ListView timerResultsList;

    private ArrayList<String> speakerNamesList = new ArrayList<>();
    private ArrayList<String> speakerTimeList = new ArrayList<>();
    private List<String[]> timerResultItem = new ArrayList<>();

    private ArrayAdapter<String[]> adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.timer_activity);

        timerBg = findViewById(R.id.timerBg);
        colorBtns = findViewById(R.id.colorBtns);
        timerSpinner = findViewById(R.id.timerSpinner);
        speakerName = findViewById(R.id.speakerName);
        chronometer = findViewById(R.id.chronometer);
        timerStart = findViewById(R.id.timerStart);
        timerStart.setVisibility(View.VISIBLE);
        timerStop = findViewById(R.id.timerStop);

        // Results List
        timerResultsList = findViewById(R.id.timerResultsList);
        adapter = new ArrayAdapter<String[]>(TimerPage.this, android.R.layout.simple_list_item_2, android.R.id.text1, timerResultItem) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = view.findViewById(android.R.id.text1);
                TextView text2 = view.findViewById(android.R.id.text2);

                String[] entry = timerResultItem.get(position);
                text1.setText(entry[0]);
                text2.setText(entry[1]);
                return view;
            }
        };
        timerResultsList.setAdapter(adapter);

        // This is for spinner
        timerSpinner = findViewById(R.id.timerSpinner);
        String[] titles = new String[]{
                "Ice Breaker Speech",
                "Prepared Speech",
                "Table Topic",
                "Evaluation"};
        // Initializing an ArrayAdapter for Spinner
        ArrayAdapter<String> spinnerArrayAdapter =
                new ArrayAdapter<>(this, R.layout.spinner_item, titles);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        timerSpinner.setAdapter(spinnerArrayAdapter);
        timerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0) {
                    speechType = 0;
                } else if (i==1){
                    speechType = 1;
                } else if (i==2){
                    speechType = 2;
                } else if (i==3){
                    speechType = 3;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });


        timerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (speakerName.getText().length() > 0) {
                    timerBg.setBackgroundColor(Color.parseColor("#ffffff"));
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                    timerStart.setVisibility(View.INVISIBLE);
                    colorBtns.setVisibility(View.INVISIBLE);
                    timerSpinner.setVisibility(View.INVISIBLE);
                    timerResultsList.setVisibility(View.INVISIBLE);
                    if (speechType == 0) {
                        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                            @Override
                            public void onChronometerTick(Chronometer chronometer) {
                                if (chronometer.getText().equals("04:00")) {
                                    timerBg.setBackgroundColor(Color.parseColor("#00ff04"));
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            timerBg.setBackgroundColor(Color.parseColor("#ffffff"));
                                        }
                                    }, 5000);
                                } else if (chronometer.getText().equals("05:00")) {
                                    timerBg.setBackgroundColor(Color.parseColor("#fff203"));
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            timerBg.setBackgroundColor(Color.parseColor("#ffffff"));
                                        }
                                    }, 5000);
                                } else if (chronometer.getText().equals("06:00")) {
                                    timerBg.setBackgroundColor(Color.parseColor("#ff1e00"));
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            timerBg.setBackgroundColor(Color.parseColor("#ffffff"));
                                        }
                                    }, 5000);
                                } else if (chronometer.getText().equals("15:00")) {
                                    chronometer.stop();
                                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
                                    timerStart.setVisibility(View.VISIBLE);
                                    colorBtns.setVisibility(View.VISIBLE);
                                    timerSpinner.setVisibility(View.VISIBLE);
                                    timerResultsList.setVisibility(View.VISIBLE);
                                }
                            }});
                    } else if (speechType == 1){
                        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                            @Override
                            public void onChronometerTick(Chronometer chronometer) {
                                if (chronometer.getText().equals("05:00")) {
                                    timerBg.setBackgroundColor(Color.parseColor("#00ff04"));
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            timerBg.setBackgroundColor(Color.parseColor("#ffffff"));
                                        }
                                    }, 5000);
                                } else if (chronometer.getText().equals("06:00")) {
                                    timerBg.setBackgroundColor(Color.parseColor("#fff203"));
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            timerBg.setBackgroundColor(Color.parseColor("#ffffff"));
                                        }
                                    }, 5000);
                                } else if (chronometer.getText().equals("07:00")) {
                                    timerBg.setBackgroundColor(Color.parseColor("#ff1e00"));
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            timerBg.setBackgroundColor(Color.parseColor("#ffffff"));
                                        }
                                    }, 5000);
                                } else if (chronometer.getText().equals("15:00")) {
                                    chronometer.stop();
                                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
                                    timerStart.setVisibility(View.VISIBLE);
                                    colorBtns.setVisibility(View.VISIBLE);
                                    timerSpinner.setVisibility(View.VISIBLE);
                                    timerResultsList.setVisibility(View.VISIBLE);
                                }
                            }});
                    } else if (speechType == 2){
                        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                            @Override
                            public void onChronometerTick(Chronometer chronometer) {
                            if (chronometer.getText().equals("01:00")) {
                                timerBg.setBackgroundColor(Color.parseColor("#00ff04"));
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        timerBg.setBackgroundColor(Color.parseColor("#ffffff"));
                                    }
                                }, 5000);
                            } else if (chronometer.getText().equals("01:30")) {
                                timerBg.setBackgroundColor(Color.parseColor("#fff203"));
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        timerBg.setBackgroundColor(Color.parseColor("#ffffff"));
                                    }
                                }, 5000);
                            } else if (chronometer.getText().equals("02:00")) {
                                timerBg.setBackgroundColor(Color.parseColor("#ff1e00"));
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        timerBg.setBackgroundColor(Color.parseColor("#ffffff"));
                                    }
                                }, 5000);
                            } else if (chronometer.getText().equals("5:00")) {
                                chronometer.stop();
                                getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
                                timerStart.setVisibility(View.VISIBLE);
                                colorBtns.setVisibility(View.VISIBLE);
                                timerSpinner.setVisibility(View.VISIBLE);
                                timerResultsList.setVisibility(View.VISIBLE);
                            }
                        }});
                    } else if (speechType == 3){
                        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                            @Override
                            public void onChronometerTick(Chronometer chronometer) {
                                if (chronometer.getText().equals("02:00")) {
                                    timerBg.setBackgroundColor(Color.parseColor("#00ff04"));
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            timerBg.setBackgroundColor(Color.parseColor("#ffffff"));
                                        }
                                    }, 5000);
                                } else if (chronometer.getText().equals("02:30")) {
                                    timerBg.setBackgroundColor(Color.parseColor("#fff203"));
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            timerBg.setBackgroundColor(Color.parseColor("#ffffff"));
                                        }
                                    }, 5000);
                                } else if (chronometer.getText().equals("03:00")) {
                                    timerBg.setBackgroundColor(Color.parseColor("#ff1e00"));
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            timerBg.setBackgroundColor(Color.parseColor("#ffffff"));
                                        }
                                    }, 5000);
                                } else if (chronometer.getText().equals("5:00")) {
                                    chronometer.stop();
                                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
                                    timerStart.setVisibility(View.VISIBLE);
                                    colorBtns.setVisibility(View.VISIBLE);
                                    timerSpinner.setVisibility(View.VISIBLE);
                                    timerResultsList.setVisibility(View.VISIBLE);
                                }
                            }});
                    }
                } else {
                    Toast.makeText(TimerPage.this, "Enter the speaker name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        timerStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (speakerName.getText().toString().length() > 0) {
                    chronometer.stop();
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
                    timerStart.setVisibility(View.VISIBLE);
                    colorBtns.setVisibility(View.VISIBLE);
                    timerSpinner.setVisibility(View.VISIBLE);
                    timerResultsList.setVisibility(View.VISIBLE);
                    String name = speakerName.getText().toString().trim();
                    String time = chronometer.getText().toString();
                    speakerNamesList.add(name);
                    speakerTimeList.add(time);

                    timerResultItem.add(new String[]{name, time});
                    adapter.notifyDataSetChanged();

                    speakerName.setText("");
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.stop();
                    Toast.makeText(TimerPage.this, "Successfully recorded!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TimerPage.this, "Enter Speaker Name", Toast.LENGTH_SHORT).show();
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

    public void colorChange(View view) {

        if (view.getId() == R.id.whiteClr) {
            timerBg.setBackgroundColor(Color.WHITE);
        } else if (view.getId() == R.id.greenClr) {
            timerBg.setBackgroundColor(Color.parseColor("#00ff04"));
        } else if (view.getId() == R.id.yellowClr) {
            timerBg.setBackgroundColor(Color.parseColor("#fff203"));
        } else if (view.getId() == R.id.redClr) {
            timerBg.setBackgroundColor(Color.parseColor("#ff1e00"));
        }
    }
}
