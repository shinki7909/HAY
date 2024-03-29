package com.example.hay;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class LogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_logo);

        Handler handler = new Handler();
        handler.postDelayed(new splashHandler(),2000);
    }
    private class splashHandler implements Runnable{

        @Override
        public void run() {
            startActivity(new Intent(LogoActivity.this,MainActivity.class));
            LogoActivity.this.finish();
        }
    }
}
