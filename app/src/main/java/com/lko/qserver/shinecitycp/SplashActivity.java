package com.lko.qserver.shinecitycp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.lko.qserver.shinecitycp.drawerwithswipetab.DrawerwithSwipeTabActivity;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView image = (ImageView)findViewById(R.id.image_vew);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        image.startAnimation(animation);

        startactivity();
    }
    private void startactivity(){

        Thread thread = new Thread(){
            public void run(){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(getApplicationContext(),DrawerwithSwipeTabActivity.class));
                    finish();
                }
            }

        };thread.start();
    }
}
