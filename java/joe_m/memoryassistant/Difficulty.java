package joe_m.memoryassistant;

/**
 * Created by Joe_M on 24/11/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;


public class Difficulty extends Activity {


    RadioButton radiobutton1;
    ImageButton buttonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);
        radiobutton1 =(RadioButton)findViewById(R.id.radioButton);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)
                    // level 1
                    buttonPlay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(Difficulty.this, GameActivity.class);
                            startActivity(myIntent);
                        }
                    });
                    break;
            case R.id.radioButton2:
                if (checked)
                    // level 2
                    buttonPlay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(Difficulty.this, GameActivityTwo.class);
                            startActivity(myIntent);
                        }
                    });
                    break;
            case R.id.radioButton3:
                if (checked)
                    buttonPlay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(Difficulty.this, GameActivityThree.class);
                            startActivity(myIntent);
                        }
                    });
                    // level 3
                    break;
            case R.id.radioButton4:
                if (checked)
                    // level 4
                    buttonPlay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(Difficulty.this, GameActivityFour.class);
                            startActivity(myIntent);
                        }
                    });
                    break;
        }
    }



}