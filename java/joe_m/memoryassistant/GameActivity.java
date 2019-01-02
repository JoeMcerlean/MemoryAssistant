package joe_m.memoryassistant;

/**
 * Created by Joe_M on 24/11/2017.
 */


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;


public class GameActivity extends Activity {

    //declaring gameview
    private GameView gameView;
    boolean cancelled = false;
    int clickCount = 0;
    int amountOfFlowers = 0;
    int score = 0;
    int[] firstHighScores = new int[4];
    ArrayList<Integer> shuffled = new ArrayList<Integer>();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        sharedPreferences = this.getSharedPreferences("SHAR_PREF_NAME", Context.MODE_PRIVATE);

        firstHighScores[0] = sharedPreferences.getInt("score1",0);
        firstHighScores[1] = sharedPreferences.getInt("score2",0);
        firstHighScores[2] = sharedPreferences.getInt("score3",0);
        firstHighScores[3] = sharedPreferences.getInt("score4",0);

        //Getting display object
        Display display = getWindowManager().getDefaultDisplay();

        //Getting the screen resolution into point object
        Point size = new Point();
        display.getSize(size);

        final int[] btns = {R.drawable.empty_box, R.drawable.empty_box, R.drawable.empty_box, R.drawable.empty_box, R.drawable.flower_box, R.drawable.flower_box};

        for(int j = 0; j<6; j++) {
            shuffled.add(j);
        }
        Collections.shuffle(shuffled);

        //Initializing game view object
        //this time we are also passing the screen size to the GameView constructor
        gameView = new GameView(this, size.x, size.y);


        final ImageButton btn = findViewById(R.id.buttonBox);
        final ImageButton btn1 = findViewById(R.id.buttonBox2);
        final ImageButton btn2 = findViewById(R.id.buttonBox3);
        final ImageButton btn3 = findViewById(R.id.buttonBox4);
        final ImageButton btn4 = findViewById(R.id.buttonBox5);
        final ImageButton btn5 = findViewById(R.id.buttonBox6);

        btn.setClickable(false);
        btn1.setClickable(false);
        btn2.setClickable(false);
        btn3.setClickable(false);
        btn4.setClickable(false);
        btn5.setClickable(false);

        btn.setBackgroundResource(R.drawable.closed_box);
        btn1.setBackgroundResource(R.drawable.closed_box);
        btn2.setBackgroundResource(R.drawable.closed_box);
        btn3.setBackgroundResource(R.drawable.closed_box);
        btn4.setBackgroundResource(R.drawable.closed_box);
        btn5.setBackgroundResource(R.drawable.closed_box);


        AlertDialog.Builder builder1 = new AlertDialog.Builder(GameActivity.this);
        builder1.setMessage("For this game wait for the boxes to reveal their contents and once resealed find the boxes containing the flowers")
                .setCancelable(false)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        cancelled = true;
                    }
                })
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    btn.setBackgroundResource(btns[shuffled.get(0)]);
                                }
                            }, 2000);
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    btn1.setBackgroundResource(btns[shuffled.get(1)]);
                                }
                            }, 4000);
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    btn2.setBackgroundResource(btns[shuffled.get(2)]);
                                }
                            }, 6000);
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    btn3.setBackgroundResource(btns[shuffled.get(3)]);
                                }
                            }, 8000);
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    btn4.setBackgroundResource(btns[shuffled.get(4)]);
                                }
                            }, 10000);
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    btn5.setBackgroundResource(btns[shuffled.get(5)]);
                                }
                            }, 12000);

                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    btn.setBackgroundResource(R.drawable.closed_box);
                                    btn1.setBackgroundResource(R.drawable.closed_box);
                                    btn2.setBackgroundResource(R.drawable.closed_box);
                                    btn3.setBackgroundResource(R.drawable.closed_box);
                                    btn4.setBackgroundResource(R.drawable.closed_box);
                                    btn5.setBackgroundResource(R.drawable.closed_box);
                                    btn.setClickable(true);
                                    btn1.setClickable(true);
                                    btn2.setClickable(true);
                                    btn3.setClickable(true);
                                    btn4.setClickable(true);
                                    btn5.setClickable(true);

                                    btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (amountOfFlowers < 2) {
                                                btn.setBackgroundResource(btns[shuffled.get(0)]);
                                                clickCount++;
                                                btn.setClickable(false);
                                                if (btn.getBackground().getConstantState() == getResources().getDrawable(R.drawable.flower_box).getConstantState()) {
                                                    score++;
                                                    amountOfFlowers++;
                                                } else {
                                                    score--;
                                                }
                                                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
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
                                                Intent myIntent = new Intent(GameActivity.this, SecondGameActivityLevelOne.class);
                                                startActivity(myIntent);
                                            }
                                        }
                                    });

                                    btn1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (amountOfFlowers < 2) {
                                                btn1.setBackgroundResource(btns[shuffled.get(1)]);
                                                clickCount++;
                                                btn1.setClickable(false);
                                                if (btn1.getBackground().getConstantState() == getResources().getDrawable(R.drawable.flower_box).getConstantState()) {
                                                    score++;
                                                    amountOfFlowers++;
                                                } else {
                                                    score--;
                                                }
                                                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
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
                                                Intent myIntent = new Intent(GameActivity.this, SecondGameActivityLevelOne.class);
                                                startActivity(myIntent);
                                            }
                                        }
                                    });

                                    btn2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (amountOfFlowers < 2) {
                                                btn2.setBackgroundResource(btns[shuffled.get(2)]);
                                                clickCount++;
                                                btn2.setClickable(false);
                                                if (btn2.getBackground().getConstantState() == getResources().getDrawable(R.drawable.flower_box).getConstantState()) {
                                                    score++;
                                                    amountOfFlowers++;
                                                } else {
                                                    score--;
                                                }
                                                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
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
                                                Intent myIntent = new Intent(GameActivity.this, SecondGameActivityLevelOne.class);
                                                startActivity(myIntent);
                                            }
                                        }
                                    });

                                    btn3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (amountOfFlowers < 2) {
                                                btn3.setBackgroundResource(btns[shuffled.get(3)]);
                                                clickCount++;
                                                btn3.setClickable(false);
                                                if (btn3.getBackground().getConstantState() == getResources().getDrawable(R.drawable.flower_box).getConstantState()) {
                                                    score++;
                                                    amountOfFlowers++;
                                                } else {
                                                    score--;
                                                }
                                                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
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
                                                Intent myIntent = new Intent(GameActivity.this, SecondGameActivityLevelOne.class);
                                                startActivity(myIntent);
                                            }
                                        }
                                    });

                                    btn4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (amountOfFlowers < 2) {
                                                btn4.setBackgroundResource(btns[shuffled.get(4)]);
                                                clickCount++;
                                                btn4.setClickable(false);
                                                if (btn4.getBackground().getConstantState() == getResources().getDrawable(R.drawable.flower_box).getConstantState()) {
                                                    score++;
                                                    amountOfFlowers++;
                                                } else {
                                                    score--;
                                                }
                                                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
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
                                                Intent myIntent = new Intent(GameActivity.this, SecondGameActivityLevelOne.class);
                                                startActivity(myIntent);
                                            }
                                        }
                                    });

                                    btn5.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (amountOfFlowers < 2) {
                                                btn5.setBackgroundResource(btns[shuffled.get(5)]);
                                                clickCount++;
                                                btn5.setClickable(false);
                                                if (btn5.getBackground().getConstantState() == getResources().getDrawable(R.drawable.flower_box).getConstantState()) {
                                                    score++;
                                                    amountOfFlowers++;
                                                } else {
                                                    score--;
                                                }
                                                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
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

                                                Intent myIntent = new Intent(GameActivity.this, SecondGameActivityLevelOne.class);
                                                startActivity(myIntent);
                                            }
                                        }
                                    });

                                }
                            }, 14000);
                        }

                });
        AlertDialog alert = builder1.create();
        alert.show();

        }


    //pausing the game when activity is paused
/*    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    //running the game when activity is resumed
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

*/

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