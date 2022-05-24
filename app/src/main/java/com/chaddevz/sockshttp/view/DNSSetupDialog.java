package com.chaddevz.sockshttp.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.chadx.sockshttp.R;
import com.chaddevz.service.config.Settings;

public class DNSSetupDialog
{
    private SharedPreferences  sp;
	private Context context;
	private Preference p;

	public DNSSetupDialog(Context c, final Preference p) {
		context = c;
        sp = new Settings(c).getPrefsPrivate();
		this.p = p;
		showDialog();
    }

	void showDialog() {
		MaterialAlertDialogBuilder adb = new MaterialAlertDialogBuilder(context );
        LayoutInflater i = LayoutInflater.from(context);
		View view = i.inflate(R.layout.dialog_dns,null);
		final TextInputEditText etDNS1 = (TextInputEditText) view.findViewById(R.id.etPrimaryDNS);
		final TextInputEditText etDNS2 = (TextInputEditText) view.findViewById(R.id.etSecondaryDNS);
		etDNS1.setText(sp.getString("primary_dns","8.8.4.4"));
		etDNS2.setText(sp.getString("secondary_dns","8.8.8.8"));
        adb.setCancelable(false);
        adb.setTitle("Set DNS");
        adb.setView(view);
        adb.setPositiveButton("SAVE", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface p1, int p2)
                {
					sp.edit().putString("primary_dns", etDNS1.getText().toString()).commit();
                    sp.edit().putString("secondary_dns", etDNS2.getText().toString()).commit();
					String s = String.format("%s, %s", etDNS1.getText().toString(), etDNS2.getText().toString());
					p.setSummary(s);
                }
            });
        adb.setNegativeButton("Cancel", null);
        adb.setNeutralButton("Reset", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface p1, int p2)
                {
					sp.edit().putString("primary_dns", "8.8.4.4").commit();
                    sp.edit().putString("secondary_dns", "8.8.8.8").commit();
					String s = String.format("%s:%s", "8.8.4.4", "8.8.8.8");
					p.setSummary(s);
                }
            });
		adb.create().show();
	}

}
