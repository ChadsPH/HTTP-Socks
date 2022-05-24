package com.chaddevz.sockshttp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.chadx.sockshttp.R;
import com.chaddevz.sockshttp.activities.BaseActivity;
import android.os.Handler;

/**
 * @author anuragdhunna
 */
public class LauncherActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		
        new Handler().postDelayed(new Runnable(){

				@Override
				public void run() {
					Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					startActivity(intent);
					onBackPressed();
				}
			}, 5000);
    }
	
}

