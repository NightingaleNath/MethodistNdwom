package ideanity.oceans.methodistndwom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ideanity.oceans.methodistndwom.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIMER = 3000;
    RelativeLayout imageBack;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //Calling New Activity after SPLASH_SCREEN seconds 1s = 1000
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          SplashScreenActivity.this.startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                                          SplashScreenActivity.this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                                          SplashScreenActivity.this.finish();

                                      }
                                  }, //Pass time here
                SPLASH_TIMER);

    }

}
