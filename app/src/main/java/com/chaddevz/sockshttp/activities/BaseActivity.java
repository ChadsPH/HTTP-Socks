package com.chaddevz.sockshttp.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.chaddevz.service.config.Settings;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.Context;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import static android.content.pm.PackageManager.GET_META_DATA;
import org.json.JSONObject;
import com.chaddevz.sockshttp.util.Utils;
import java.io.File;
import com.chaddevz.sockshttp.util.AESCrypt;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Pankaj on 03-11-2017.
 */
public abstract class BaseActivity extends AppCompatActivity
{
	public static int mTheme = 0;
    public static final String PASSWORD = new String(new byte[]{-23,-69,-110,-27,-83,-112,-29,-125,-68,-29,-127,-113,-29,-126,-109,});
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//setTheme(ThemeUtil.getThemeId(mTheme));
		
		//setModoNoturnoLocal();
			
		resetTitles();
	}
	

	protected JSONObject getJSONConfig2(Context context) throws Exception {
		String json = null;
        File file = new File(context.getFilesDir(), "Config.json");
		if (file.exists()) {
			String json_file = Utils.readStream(new FileInputStream(file));
			json = AESCrypt.decrypt(PASSWORD, json_file);
			// return new JSONObject(json);
		} else {
			InputStream inputStream = context.getAssets().open("config/config.json");
			json = AESCrypt.decrypt(PASSWORD, Utils.readStream(inputStream));
			// return new JSONObject(json);
		}
        return new JSONObject(json);
    }
	protected void resetTitles() {
		try {
			ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), GET_META_DATA);
			if (info.labelRes != 0) {
				setTitle(info.labelRes);
			}
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
	}
}

