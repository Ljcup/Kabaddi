package com.myapp.kabaddi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;
import com.google.protobuf.StringValue;

import java.util.HashMap;
import java.util.Map;

public class scoring extends AppCompatActivity {

    private TextView Team1,Team2,Team1score,Team2score;
    private Button Scoreteam1,Scoreteam2,Undoteam1,Undoteam2, finishbtn, Score2team1,Score3team1,Score2team2,Score3team2,Undo2team1,Undo3team1,Undo2team2,Undo3team2;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Score");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Team1 = findViewById(R.id.team1name);
        Team2 = findViewById(R.id.team2name);
        Team1score = findViewById(R.id.team1score);
        Team2score = findViewById(R.id.team2score);

        Scoreteam1 = findViewById(R.id.team1point);
        Scoreteam2 = findViewById(R.id.team2point);
        Score2team1 = findViewById(R.id.team1point2);
        Score3team1 = findViewById(R.id.team1point3);
        Score2team2 = findViewById(R.id.team2point2);
        Score3team2 = findViewById(R.id.team2point3);
        Undoteam1 = findViewById(R.id.undoteam1);
        Undoteam2 = findViewById(R.id.undoteam2);
        Undo2team1 = findViewById(R.id.undo2team1);
        Undo3team1 = findViewById(R.id.undo3team1);
        Undo2team2 = findViewById(R.id.undo2team2);
        Undo3team2 = findViewById(R.id.undo3team2);

        finishbtn = findViewById(R.id.finishcurrentmatch);

        String matchID = getIntent().getStringExtra("matchid");


        db.collection("matches").document(matchID)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value,
                                        @Nullable FirebaseFirestoreException error) {

                        if(error != null){
                            Log.d("Error", String.valueOf(error));
                        }
                        if(value != null && value.exists())
                        {
                            Team1.setText(value.getString("Team1"));
                            Team2.setText(value.getString("Team2"));
                            Team1score.setText(value.getLong("Team1score")+"");
                            Team2score.setText(value.getLong("Team2score")+"");
                        }
                    }
                });

        final int counter[] = {0,0};

        Scoreteam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter[0]++;
                Map<String,Object> score1= new HashMap<>();
                score1.put("Team1score",counter[0]);
                db.collection("matches").document(matchID).update(score1);
            }
        });

        Score2team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter[0] = counter[0] + 2;
                Map<String,Object> score1= new HashMap<>();
                score1.put("Team1score",counter[0]);
                db.collection("matches").document(matchID).update(score1);
            }
        });

        Score3team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter[0] = counter[0] + 3;
                Map<String,Object> score1= new HashMap<>();
                score1.put("Team1score",counter[0]);
                db.collection("matches").document(matchID).update(score1);
            }
        });

        Scoreteam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter[1]++;
                Map<String,Object>score2 = new HashMap<>();
                score2.put("Team2score",counter[1]);
                db.collection("matches").document(matchID).update(score2);
            }
        });

        Score2team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter[1] = counter[1] + 2;
                Map<String,Object>score2 = new HashMap<>();
                score2.put("Team2score",counter[1]);
                db.collection("matches").document(matchID).update(score2);
            }
        });

        Score3team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter[1] = counter[1] +3;
                Map<String,Object>score2 = new HashMap<>();
                score2.put("Team2score",counter[1]);
                db.collection("matches").document(matchID).update(score2);
            }
        });


        Undoteam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter[0] == 0){

                }else{
                    counter[0]--;
                    Map<String,Object> score1= new HashMap<>();
                    score1.put("Team1score",counter[0]);
                    db.collection("matches").document(matchID).update(score1);
                }

            }
        });

        Undo2team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter[0] == 0){

                }else{
                    counter[0] = counter[0] - 2;
                    Map<String,Object> score1= new HashMap<>();
                    score1.put("Team1score",counter[0]);
                    db.collection("matches").document(matchID).update(score1);
                }

            }
        });

        Undo3team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter[0] == 0){

                }else{
                    counter[0] = counter[0] - 3;
                    Map<String,Object> score1= new HashMap<>();
                    score1.put("Team1score",counter[0]);
                    db.collection("matches").document(matchID).update(score1);
                }

            }
        });

        Undoteam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter[1] == 0){

                }
                else {
                    counter[1]--;
                    Map<String,Object>score2 = new HashMap<>();
                    score2.put("Team2score",counter[1]);
                    db.collection("matches").document(matchID).update(score2);
                }

            }
        });

        Undo2team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter[1] == 0){

                }
                else {
                    counter[1] = counter[1] - 2;
                    Map<String,Object>score2 = new HashMap<>();
                    score2.put("Team2score",counter[1]);
                    db.collection("matches").document(matchID).update(score2);
                }

            }
        });

        Undo3team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter[1] == 0){

                }
                else {
                    counter[1] = counter[1] -3;
                    Map<String,Object>score2 = new HashMap<>();
                    score2.put("Team2score",counter[1]);
                    db.collection("matches").document(matchID).update(score2);
                }

            }
        });



        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(scoring.this);

                builder.setMessage("Are you sure want to finish match?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                scoring.super.onBackPressed();
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure want to finish match?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scoring.super.onBackPressed();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


}