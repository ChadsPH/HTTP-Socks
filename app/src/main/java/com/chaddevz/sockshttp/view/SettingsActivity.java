package com.chaddevz.sockshttp.view;

import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import com.chaddevz.service.config.SettingsConstants;
import com.chaddevz.service.config.Settings;
import com.chadx.sockshttp.R;
import com.chaddevz.service.HttpService;

public class SettingsActivity extends AppCompatActivity
{

	private Toolbar toolbar;

	public static class SettingsFragment extends PreferenceFragment  implements SharedPreferences.OnSharedPreferenceChangeListener, 
	SettingsConstants
	{

		private CheckBoxPreference forwardUDP;
        private EditTextPreference resolverUDP;
        private CheckBoxPreference forwardDNS;
        private Preference setDNS;
        private EditTextPreference pingSec;
        private SharedPreferences mSecurePrefs;
		

		@Override
		public void onSharedPreferenceChanged(SharedPreferences settings, String key) {    
			switch (key){
				case DNSFORWARD_KEY:
					if (forwardDNS.isChecked()) {
						setDNS.setEnabled(true);
					} else {
						setDNS.setEnabled(false);
					}
					break; 
				case "set_dns":
					String dns1 = mSecurePrefs.getString("primary_dns", "8.8.4.4");
					String dns2 = mSecurePrefs.getString("secondary_dns", "8.8.8.8");
					String s = String.format("%s:%s", dns1, dns2);
					setDNS.setSummary(s);
					break;
				case UDPFORWARD_KEY:
					if (forwardUDP.isChecked()) {
						resolverUDP.setEnabled(true);
					} else {
						resolverUDP.setEnabled(false);
					}
					break; 
				case UDPRESOLVER_KEY:
					if(mSecurePrefs.getString(Settings.UDPRESOLVER_KEY, "").equals("")){
						resolverUDP.setSummary("UDP Resolver");		
					} else {
						resolverUDP.setSummary(mSecurePrefs.getString(Settings.UDPRESOLVER_KEY, "127.0.0.1:7300"));		 
					} 
					break;
				case PINGER_KEY:
					if(mSecurePrefs.getString(Settings.PINGER_KEY, "").equals("")){
						pingSec.setSummary("3");		
					} else {
						pingSec.setSummary(mSecurePrefs.getString(Settings.PINGER_KEY, ""));		 
					} 
					break;
			}

		}  

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.app_settings);
			mSecurePrefs = new Settings(getActivity()).getPrefsPrivate();

			forwardDNS = (CheckBoxPreference) findPreference(DNSFORWARD_KEY);

			setDNS = (Preference) findPreference("set_dns");
			setDNS.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
					@Override
					public boolean onPreferenceClick(Preference p1)
					{
						new DNSSetupDialog(getActivity(), setDNS);
						return false;
					}
				});
			if (forwardDNS.isChecked()) {
				setDNS.setEnabled(true);
			} else {
				setDNS.setEnabled(false);
			}
			String dns1 = mSecurePrefs.getString("primary_dns", "8.8.4.4");
			String dns2 = mSecurePrefs.getString("secondary_dns", "8.8.8.8");
			String s = String.format("%s, %s", dns1, dns2);
			setDNS.setSummary(s);

			forwardUDP = (CheckBoxPreference) findPreference(UDPFORWARD_KEY);

			resolverUDP = (EditTextPreference) findPreference(UDPRESOLVER_KEY);
			if (forwardUDP.isChecked()) {
				resolverUDP.setEnabled(true);
			} else {
				resolverUDP.setEnabled(false);
			}
			resolverUDP.setSummary(mSecurePrefs.getString(Settings.UDPRESOLVER_KEY, ""));

			if(mSecurePrefs.getString(Settings.UDPRESOLVER_KEY, "").equals("")){
				resolverUDP.setSummary("UDP Resolver");		
			} else {
				resolverUDP.setSummary(mSecurePrefs.getString(Settings.UDPRESOLVER_KEY, "127.0.0.1:7300"));		 
			} 

			pingSec = (EditTextPreference) findPreference(PINGER_KEY);
			pingSec.setSummary(mSecurePrefs.getString(Settings.PINGER_KEY, "3"));

			if(mSecurePrefs.getString(Settings.PINGER_KEY, "").equals("")){
				pingSec.setSummary("Seconds of Ping");		
			} else { 
				pingSec.setSummary(mSecurePrefs.getString(Settings.PINGER_KEY, ""));		 
			} 

			if(HttpService.isRunning) {
				forwardDNS.setEnabled(false);
				setDNS.setEnabled(false);
				forwardUDP.setEnabled(false);
				resolverUDP.setEnabled(false);
				pingSec.setEnabled(false);
			//	sshTunnel.setEnabled(false);
			//	dnsTunnel.setEnabled(false);
			}  
		}


	    @Override
        public void onResume() {
            super.onResume();
            mSecurePrefs.registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            mSecurePrefs.unregisterOnSharedPreferenceChangeListener(this);
		}
	}

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.fragment_prefs);
		toolbar =  (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.container, new SettingsFragment());
        beginTransaction.commit();
        setupActionBar();
    }
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
			actionBar.setDisplayHomeAsUpEnabled(true);
			//    actionBar.setHomeAsUpIndicator(R.drawable.abc_ic_clear_material);
        }
	}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
			onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    } 
}
