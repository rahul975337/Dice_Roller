package com.rt.apps.diceroller;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView dicep;
    Random r=new Random();
    SoundPool dice_sound;
    int sid;
    Handler handler;
    Timer timer =new Timer();
    boolean rolling=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitSound();
        dicep=(ImageView)findViewById(R.id.imageView);
        dicep.setOnClickListener(new HandleClick());

        handler=new Handler(callback);
    }

    private class HandleClick implements View.OnClickListener {
        public void onClick(View arg0) {
            if (!rolling) {
                rolling = true;
                //Show rolling image
                dicep.setImageResource(R.drawable.dice3ddd);

                dice_sound.play(sid, 1.0f, 1.0f, 0, 0, 1.0f);

                timer.schedule(new Roll(), 400);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void InitSound() {
        AudioAttributes aa = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        dice_sound = new SoundPool.Builder().setAudioAttributes(aa).build();

        sid = dice_sound.load(this, R.raw.s, 1);
    }
    class Roll extends TimerTask {
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }
    Handler.Callback callback = new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            //Get roll result
            //Remember nextInt returns 0 to 5 for argument of 6
            //hence + 1

//
//            LayoutInflater inflater = getLayoutInflater();
//            View layout = inflater.inflate(R.layout.custom_toast_layout,
//                    (ViewGroup) findViewById(R.id.toast_layout_root));
//            // get the reference of TextView and ImageVIew from inflated layout
//            TextView toastTextView = (TextView) layout.findViewById(R.id.toastTextView);
//            ImageView toastImageView = (ImageView) layout.findViewById(R.id.toastImageView);
//            // set the text in the TextView
//            toastTextView.setText("Custom Toast In Android");
//            // set the Image in the ImageView
//            toastImageView.setImageResource(R.drawable.ic_launcher);
//            // create a new Toast using context
//            Toast toast = new Toast(getApplicationContext());
//            toast.setDuration(Toast.LENGTH_LONG); // set the duration for the Toast
//            toast.setView(layout); // set the inflated layout
//            toast.show(); // display the custom Toast
            switch(r.nextInt(6)+1) {
                case 1:
                    dicep.setImageResource(R.drawable.dice1);
                    Toast toast=Toast. makeText(getApplicationContext(),"ONE",Toast. LENGTH_SHORT);

                    toast. show();
                    break;
                case 2:
                    dicep.setImageResource(R.drawable.dice2);
                    Toast toast1=Toast. makeText(getApplicationContext(),"TWO",Toast. LENGTH_SHORT);

                    toast1. show();
                    break;
                case 3:
                    dicep.setImageResource(R.drawable.dice3);
                    Toast toast3=Toast. makeText(getApplicationContext(),"THREE",Toast. LENGTH_SHORT);

                    toast3. show();
                    break;
                case 4:
                    dicep.setImageResource(R.drawable.dice4);
                    Toast toast4=Toast. makeText(getApplicationContext(),"FOUR",Toast. LENGTH_SHORT);

                    toast4. show();
                    break;
                case 5:
                    dicep.setImageResource(R.drawable.dice5);
                    Toast toast5=Toast. makeText(getApplicationContext(),"FIVE",Toast. LENGTH_SHORT);

                    toast5. show();
                    break;
                case 6:
                    dicep.setImageResource(R.drawable.dice6);
                    Toast toast6=Toast. makeText(getApplicationContext(),"SIX",Toast. LENGTH_SHORT);
                    toast6. show();
                    break;
                default:
            }
            rolling=false;
            return true;
        }
    };

    //Clean up
    protected void onPause() {
        super.onPause();
        dice_sound.pause(sid);
    }
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}