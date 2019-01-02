package joe_m.memoryassistant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


/**
 * Created by Joe_M on 20/02/2018.
 */

public class SecondGameActivityLevelThree extends Activity {


    int clickCount = 0;
    int score = 0;
    int i = 0;
    int b = 0;
    int rnd;
    ArrayList<Integer> shuffled = new ArrayList<Integer>();
    Random rand = new Random();
    SharedPreferences sharedPreferences;
    int[] firstHighScores = new int[4];
    int[] clips = {R.raw.gown,R.raw.rise,R.raw.tempt,R.raw.sport,R.raw.bulletin,R.raw.week,R.raw.lifestyle,R.raw.skip,R.raw.chimpanzee,R.raw.sum,R.raw.sector,R.raw.suburb,R.raw.ban,R.raw.restaurant,R.raw.neck, R.raw.celebration,R.raw.injury,R.raw.teeth,R.raw.gasp,R.raw.timetable,R.raw.interference,R.raw.admire,R.raw.injection,R.raw.closed,R.raw.nest,R.raw.smoke,R.raw.retain,R.raw.import_s,R.raw.stop,R.raw.nuclear,R.raw.cancer,R.raw.native_s,R.raw.grow,R.raw.unique,R.raw.bowel,R.raw.vet,R.raw.provision,R.raw.sulphur,R.raw.artificial,R.raw.measure,R.raw.trace,R.raw.qualification,R.raw.confidence,R.raw.impulse,R.raw.lease,R.raw.clarify,R.raw.revolutionary,R.raw.girlfriend,R.raw.allow,R.raw.rare,R.raw.turn,R.raw.compete,R.raw.social,R.raw.cooperation,R.raw.heel,R.raw.demand,R.raw.ghost,R.raw.private_s,R.raw.dare,R.raw.recession};
    int[] songs = new int[10];
    int[] testSongs = new int[20];
    static MediaPlayer audio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondgame);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        for(int j = 0; j<20; j++) {
            shuffled.add(j);
        }
        Collections.shuffle(shuffled);

        sharedPreferences = this.getSharedPreferences("SHAR_PREF_NAME", Context.MODE_PRIVATE);

        firstHighScores[0] = sharedPreferences.getInt("score1",0);
        firstHighScores[1] = sharedPreferences.getInt("score2",0);
        firstHighScores[2] = sharedPreferences.getInt("score3",0);
        firstHighScores[3] = sharedPreferences.getInt("score4",0);

        final int[] initialWordRandoms = new int[10];
        final int[] beforeTestRandoms = new int[20];
        final int[] testNumberRandoms = new int[20];
        final int[] testWordRandoms = new int[20];

        String[] fullWordsList = {"gown","rise","tempt","sport","bulletin","week","lifestyle","skip","chimpanzee","sum","sector","suburb","ban","restaurant","neck","celebration","injury","teeth","gasp","timetable","interference","admire","injection","closed","nest","smoke","retain","import","stop","nuclear","cancer","native","grow","unique","bowel","vet","provision","sulphur","artificial","measure","trace","qualification","confidence","impulse","lease","clarify","revolutionary","girlfriend","allow","rare","turn","compete","social","cooperation","heel","demand","ghost","private","dare","recession"};
        final String[] words = new String[10];
        final String[] testWords = new String[20];

        final ImageButton yesButton = findViewById(R.id.yesBox);
        final ImageButton noButton = findViewById(R.id.noBox);
        final TextView wordsView = findViewById(R.id.editText);

        yesButton.setClickable(false);
        noButton.setClickable(false);

        // CREATES RANDOM NUMBERS FOR RANDOM LIST OF WORDS
        while (i < 10) {
            rnd = rand.nextInt(60);
            boolean repeat = false;
            do {
                for (int j = 0; j < 10; j++) {
                    if (rnd == initialWordRandoms[j]) {
                        repeat = true;
                        break;
                    } else if (j == i) {
                        initialWordRandoms[i] = rnd;
                        i++;
                        repeat = true;
                        break;
                    }
                }
            } while (!repeat);

        }

        // SETS LIST OF WORDS AND AUDIO
        for (int y = 0; y < 10; y++) {
            words[y] = fullWordsList[initialWordRandoms[y]];
            songs[y] = clips[initialWordRandoms[y]];
        }

        // SETS ARRAY OF WORDS TO A LIST
        final List valid = Arrays.asList(words);

        // SETS FIRST HALF OF RANDOM WORDS
        for (int x = 0; x < 10; x++) {
            beforeTestRandoms[x] = initialWordRandoms[x];
        }

        // SETS RANDOM NUMBERS FOR SECOND HALF
        while (i < 20) {
            rnd = rand.nextInt(60);
            boolean repeat = false;
            do {
                for (int j = 0; j < 20; j++) {
                    if (rnd == beforeTestRandoms[j]) {
                        repeat = true;
                        break;
                    } else if (j == i) {
                        beforeTestRandoms[i] = rnd;
                        i++;
                        repeat = true;
                        break;
                    }
                }
            } while (!repeat);
        }

        // SET NUMBER ORDER FOR THE TEST
        for (i = 0; i < 20; i++) {
            testWordRandoms[i] = beforeTestRandoms[shuffled.get(i)];
        }


        // SET WORDS FOR THE TEST
        for (i = 0; i < 20; i++) {
            testWords[i] = fullWordsList[testWordRandoms[i]];
            testSongs[i] = clips[testWordRandoms[i]];
        }

        AlertDialog.Builder builder1 = new AlertDialog.Builder(SecondGameActivityLevelThree.this);
        builder1.setMessage("For this game remember the first 10 words that appear.")
                .setCancelable(false)
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();


                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                wordsView.setText(words[0]);
                                audio = MediaPlayer.create(SecondGameActivityLevelThree.this,songs[0]);
                                audio.start();
                            }
                        }, 4000);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                wordsView.setText(words[1]);
                                audio.stop();
                                audio.release();
                                audio = MediaPlayer.create(SecondGameActivityLevelThree.this,songs[1]);
                                audio.start();
                            }
                        }, 8000);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                wordsView.setText(words[2]);
                                audio.stop();
                                audio.release();
                                audio = MediaPlayer.create(SecondGameActivityLevelThree.this,songs[2]);
                                audio.start();
                            }
                        }, 12000);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                wordsView.setText(words[3]);
                                audio.stop();
                                audio.release();
                                audio = MediaPlayer.create(SecondGameActivityLevelThree.this,songs[3]);
                                audio.start();
                            }
                        }, 16000);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                wordsView.setText(words[4]);
                                audio.stop();
                                audio.release();
                                audio = MediaPlayer.create(SecondGameActivityLevelThree.this,songs[4]);
                                audio.start();
                            }
                        }, 20000);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                wordsView.setText(words[5]);
                                audio.stop();
                                audio.release();
                                audio = MediaPlayer.create(SecondGameActivityLevelThree.this,songs[5]);
                                audio.start();
                            }
                        }, 24000);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                wordsView.setText(words[6]);
                                audio.stop();
                                audio.release();
                                audio = MediaPlayer.create(SecondGameActivityLevelThree.this,songs[6]);
                                audio.start();
                            }
                        }, 28000);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                wordsView.setText(words[7]);
                                audio.stop();
                                audio.release();
                                audio = MediaPlayer.create(SecondGameActivityLevelThree.this,songs[7]);
                                audio.start();
                            }
                        }, 32000);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                wordsView.setText(words[8]);
                                audio.stop();
                                audio.release();
                                audio = MediaPlayer.create(SecondGameActivityLevelThree.this,songs[8]);
                                audio.start();
                            }
                        }, 36000);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                wordsView.setText(words[9]);
                                audio.stop();
                                audio.release();
                                audio = MediaPlayer.create(SecondGameActivityLevelThree.this,songs[9]);
                                audio.start();
                            }
                        }, 40000);
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(SecondGameActivityLevelThree.this);
                                builder2.setMessage("Select yes or no for the next 20 words whether the word displayed was in the original 10 words.")
                                        .setCancelable(false)
                                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();

                                                wordsView.setText(testWords[clickCount]);
                                                audio.stop();
                                                audio = MediaPlayer.create(SecondGameActivityLevelThree.this,testSongs[clickCount]);
                                                audio.start();
                                            }
                                        });
                                AlertDialog alert2 = builder2.create();
                                alert2.show();

                                yesButton.setClickable(true);
                                noButton.setClickable(true);

                                yesButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (clickCount < 19) {
                                            if (valid.contains(testWords[clickCount])) {
                                                score++;
                                            } else {
                                                score--;
                                            }
                                            clickCount++;
                                            wordsView.setText(testWords[clickCount]);
                                            audio.stop();
                                            audio.release();
                                            audio = MediaPlayer.create(SecondGameActivityLevelThree.this,testSongs[clickCount]);
                                            audio.start();
                                            AlertDialog.Builder builder = new AlertDialog.Builder(SecondGameActivityLevelThree.this);
                                            builder.setMessage("Your Score is: " + score)
                                                    .setCancelable(false)
                                                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            dialog.cancel();
                                                        }
                                                    });
                                            AlertDialog alert = builder.create();
                                            alert.show();
                                        } else {

                                            //Assigning the scores to the highscore integer array
                                            for(int i=0;i<4; i++){
                                                if(firstHighScores[i] < score){
                                                    final int finalI = i;
                                                    firstHighScores[i] = score;
                                                    break;
                                                }
                                            }

                                            //storing the scores through shared Preferences
                                            SharedPreferences.Editor e = sharedPreferences.edit();
                                            for(int i=0;i<4;i++){
                                                int j = i+1;
                                                e.putInt("score"+j,firstHighScores[i]);
                                            }
                                            e.apply();
                                            Intent myIntent = new Intent(SecondGameActivityLevelThree.this, MainActivity.class);
                                            startActivity(myIntent);
                                        }
                                    }
                                });

                                noButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (clickCount < 19) {
                                            if (!valid.contains(testWords[clickCount])) {
                                                score++;
                                            } else {
                                                score--;
                                            }
                                            clickCount++;
                                            wordsView.setText(testWords[clickCount]);
                                            audio.stop();
                                            audio.release();
                                            audio = MediaPlayer.create(SecondGameActivityLevelThree.this,testSongs[clickCount]);
                                            audio.start();
                                            AlertDialog.Builder builder = new AlertDialog.Builder(SecondGameActivityLevelThree.this);
                                            builder.setMessage("Your Score is: " + score)
                                                    .setCancelable(false)
                                                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            dialog.cancel();
                                                        }
                                                    });
                                            AlertDialog alert = builder.create();
                                            alert.show();
                                        } else {

                                            //Assigning the scores to the highscore integer array
                                            for(int i=0;i<4; i++){
                                                if(firstHighScores[i] < score){
                                                    final int finalI = i;
                                                    firstHighScores[i] = score;
                                                    break;
                                                }
                                            }

                                            //storing the scores through shared Preferences
                                            SharedPreferences.Editor e = sharedPreferences.edit();
                                            for(int i=0;i<4;i++){
                                                int j = i+1;
                                                e.putInt("score"+j,firstHighScores[i]);
                                            }
                                            e.apply();
                                            Intent myIntent = new Intent(SecondGameActivityLevelThree.this, MainActivity.class);
                                            startActivity(myIntent);
                                        }
                                    }
                                });
                            }

                        }, 44000);
                    }
                });

        AlertDialog alert = builder1.create();
        alert.show();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        GameView.stopMusic();
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

}
