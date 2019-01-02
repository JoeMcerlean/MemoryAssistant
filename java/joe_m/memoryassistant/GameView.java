package joe_m.memoryassistant;

/**
 * Created by Joe_M on 24/11/2017.
*/
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.view.SurfaceView;


public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    //context to be used in onTouchEvent to cause the activity transition from GameAvtivity to MainActivity.
    Context context;

    //the score holder
    int score;

    //the high Scores Holder
    int highScore[] = new int[4];

    //Shared Prefernces to store the High Scores
    SharedPreferences sharedPreferences;

    //the mediaplayer objects to configure the background music
    static  MediaPlayer gameOnsound;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        //initializing context
        this.context = context;

        score = 0;

        //initializing shared Preferences
        sharedPreferences = context.getSharedPreferences("SHAR_PREF_NAME",Context.MODE_PRIVATE);

        //initializing the array high scores with the previous values
        highScore[0] = sharedPreferences.getInt("score1",0);
        highScore[1] = sharedPreferences.getInt("score2",0);
        highScore[2] = sharedPreferences.getInt("score3",0);
        highScore[3] = sharedPreferences.getInt("score4",0);


        //initializing the media players for the game sounds
        gameOnsound = MediaPlayer.create(context, R.raw.gameon);
        //killedEnemysound = MediaPlayer.create(context,R.raw.killedenemy);
        //gameOversound = MediaPlayer.create(context,R.raw.gameover);

        //starting the music to be played across the game
        //gameOnsound.start();

    }





    @Override
    public void run() {

        while (playing) {

            update();
//            draw();
            control();

        }


    }


    private void update() {

    }

    private void control() {
 /*       try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } */
    }

    public void pause() {
        playing = false;
/*        try {
            //gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public void resume() {
        playing = true;
        //gameThread = new Thread(this);
        //gameThread.start();
    }

    //stop the music on exit
    public static void stopMusic(){

        gameOnsound.stop();
    }

}

