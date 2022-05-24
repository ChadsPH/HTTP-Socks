package com.chaddevz.sockshttp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.chadx.sockshttp.R;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import androidx.fragment.app.FragmentManager;
import android.widget.Toast;
import android.view.View;
import android.content.Context;
import androidx.appcompat.widget.SwitchCompat;
import android.widget.CompoundButton;
import com.chaddevz.sockshttp.util.Utils;
import android.util.Log;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputEditText;
import androidx.drawerlayout.widget.DrawerLayout;
import android.net.Uri;
import android.widget.Button;
import com.chaddevz.sockshttp.SocksHttpApp;
import android.widget.CheckBox;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import com.chaddevz.sockshttp.activities.ConfigGeralActivity;
import android.view.LayoutInflater;
import android.content.pm.PackageManager;
import android.text.Html;
import androidx.appcompat.app.AlertDialog;
import android.content.pm.PackageInfo;
import com.chaddevz.service.util.SkProtect;
import com.chaddevz.service.logger.SkStatus;
import android.content.ServiceConnection;
import android.content.ComponentName;
import android.os.IBinder;
import android.widget.LinearLayout;
import android.annotation.TargetApi;
import android.os.Build;
import android.net.VpnService;
import android.content.ActivityNotFoundException;
import android.app.Activity;
import com.chaddevz.service.logger.ConnectionStatus;
import android.os.Handler;
import androidx.core.content.ContextCompat;
import com.chaddevz.service.config.Settings;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.os.PersistableBundle;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import android.widget.RadioGroup;
import com.chaddevz.service.config.ConfigParser;
import androidx.core.app.ActivityCompat;
import android.content.DialogInterface;
import com.chaddevz.service.tunnel.TunnelManagerHelper;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.chaddevz.sockshttp.model.ViewFragment;
import android.text.InputType;
import android.widget.ImageButton;
import java.io.IOException;
import com.google.android.material.navigation.NavigationView;
import android.util.AttributeSet;
import com.chaddevz.sockshttp.util.GoogleFeedbackUtils;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdListener;
import com.chaddevz.sockshttp.activities.BaseActivity;
import com.chaddevz.service.tunnel.TunnelUtils;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import com.chaddevz.sockshttp.util.ConfigUtil;
import com.chaddevz.sockshttp.adapter.SpinnerAdapter;
import android.app.PendingIntent;
import android.app.AlarmManager;
import com.chaddevz.sockshttp.util.ConfigUpdate;
import com.chaddevz.sockshttp.util.AESCrypt;
import com.chaddevz.sockshttp.adapter.PromoAdapter;
import org.json.JSONArray;
import com.github.mikephil.charting.charts.LineChart;
import com.chaddevz.sockshttp.Graph.GraphHelper;
import com.chaddevz.sockshttp.Graph.StoredData;
import android.graphics.Color;
import com.chaddevz.sockshttp.Graph.RetrieveData;
import com.chaddevz.sockshttp.Graph.DataTransferGraph2;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import android.view.View.OnClickListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.chaddevz.sockshttp.adapter.LogsAdapter;
import com.chaddevz.sockshttp.activities.AboutActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import android.widget.AdapterView;
import com.chaddevz.sockshttp.view.SettingsActivity;
import com.chaddevz.sockshttp.StatisticGraphData.DataTransferStats;
import android.os.AsyncTask;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.AdError;

public class MainActivity extends BaseActivity
implements NavigationView.OnNavigationItemSelectedListener,
			View.OnClickListener, RadioGroup.OnCheckedChangeListener,
				CompoundButton.OnCheckedChangeListener, SkStatus.StateListener
{

	private TextView connectionStatus;

	@Override
	public boolean onNavigationItemSelected(MenuItem item)
	{
		switch (item.getItemId()) {
			
			case R.id.miPhoneConfg:
				//	startActivity(new Intent(getBaseContext(),hRecycler.class));
				String str3 = "android.intent.action.MAIN";
				if(Build.VERSION.SDK_INT<30)
				{
					Intent in = new Intent(str3);
					in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					in.setClassName("com.android.settings", "com.android.settings.RadioInfo");
					startActivity(in);
				} else {
					Intent in2 = new Intent(str3);
					in2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					in2.setClassName("com.android.phone", "com.android.phone.settings.RadioInfo");
					startActivity(in2);
				}
				break;
                
                case R.id.rate:
               startActivity(new Intent("android.intent.action.VIEW", 
										 Uri.parse("https://play.google.com/store/apps/details?id=hiro.vpn.socks")));
                break;
				
			case R.id.facebook:
				startActivity(new Intent("android.intent.action.VIEW", 
										 Uri.parse("https://m.facebook.com/adik016")));
                break;
				
			case R.id.github:
				startActivity(new Intent("android.intent.action.VIEW", 
										 Uri.parse("https://github.com/ChadDevz")));
                break;
				
			case R.id.about:
				Intent about = new Intent(MainActivity.this, AboutActivity.class);
				about.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				MainActivity.this.startActivity(about);
				break;
				
			case R.id.miSettings:
				Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				MainActivity.this.startActivity(intent);

				break;
			
			}

		return false;
	}

	private NavigationView navi;
	private DrawerLayout drawer;
	private Toolbar toolbar_main;
	private DrawerPanelMain mDrawerPanel;
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String UPDATE_VIEWS = "MainUpdate";
	public static final String OPEN_LOGS = "com.chaddevz.sockshttp:openLogs";
	
	//private DrawerLog mDrawer;
	private Settings mConfig;
	//private Toolbar toolbar_main;
	private Handler mHandler;
	private LinearLayout mainLayout;
	private LinearLayout loginLayout;
	private LinearLayout proxyInputLayout;
	private TextView proxyText;
	private RadioGroup metodoConexaoRadio;
	private LinearLayout payloadLayout;
	private TextInputEditText payloadEdit;
	private SwitchCompat customPayloadSwitch;
	private Button starterButton;
	private ImageButton inputPwShowPass;
	private TextInputEditText inputPwUser;
	private TextInputEditText inputPwPass;
	
	private LinearLayout configMsgLayout;
	private TextView configMsgText;
	private AdView adsBannerView;
	private ConfigUtil config;

	private Spinner serverSpinner;
	private Spinner payloadSpinner;
	private SpinnerAdapter serverAdapter;
	private PromoAdapter payloadAdapter;

	private ArrayList<JSONObject> serverList;
	private ArrayList<JSONObject> payloadList;
	
	private LineChart mChart;
	private GraphHelper graph;
	private Thread dataUpdate;
	private Handler fHandler = new Handler();
	private Thread dataThread;
	
	private RecyclerView logList;
	private ImageView iv1;
	private BottomSheetBehavior<View> bottomSheetBehavior;
	private View bshl;
	public static LogsAdapter mAdapter;
	private static final int START_VPN_PROFILE = 2002;
	public static int PICK_FILE = 1;
	private SharedPreferences sp;
	private TextView bytesIn;
	private TextView bytesOut;
	private TextView version;
	private static final String AD_UNIT_ID = "ca-app-pub-7494485212856544/9047190120";
	private InterstitialAd interstitialAd;
	
	@Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

		mHandler = new Handler();
		mConfig = new Settings(this);
		mDrawerPanel = new DrawerPanelMain(this);
		doLayout();
		// verifica se existe algum problema
		SkProtect.CharlieProtect();
		sp = new Settings(this).getPrefsPrivate();
		boolean showFirstTime = sp.getBoolean("connect_first_time", true);
		if (showFirstTime)
        {
            SharedPreferences.Editor pEdit = sp.edit();
            pEdit.putBoolean("connect_first_time", false);
            pEdit.apply();
			Settings.setDefaultConfig(this);
			//showWelcome();
        }
		
	/*	bytesIn = (TextView) findViewById(R.id.bytesIn);
        bytesOut = (TextView) findViewById(R.id.bytesOut);
		*/
		IntentFilter filter = new IntentFilter();
		filter.addAction(UPDATE_VIEWS);
		filter.addAction(OPEN_LOGS);

		LocalBroadcastManager.getInstance(this)
			.registerReceiver(mActivityReceiver, filter);
			
		doUpdateLayout();
	}


	/**
	 * Layout
	 */
	
	 
	private void updateHeaderCallback() {
		DataTransferStats dataTransferStats = StatisticGraphData.getStatisticData().getDataTransferStats();
		bytesIn.setText(Utils.byteCountToDisplaySize(dataTransferStats.getTotalBytesReceived(), false));
		bytesOut.setText(Utils.byteCountToDisplaySize(dataTransferStats.getTotalBytesSent(), false));
	}
	
	private void loadAd() {
		AdRequest adRequest = new AdRequest.Builder().build();
		InterstitialAd.load(
			this,
			AD_UNIT_ID,
			adRequest,
			new InterstitialAdLoadCallback() {
				@Override
				public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
					MainActivity.this.interstitialAd = interstitialAd;
					interstitialAd.setFullScreenContentCallback(
						new FullScreenContentCallback() {
							@Override
							public void onAdDismissedFullScreenContent() {
								MainActivity.this.interstitialAd = null;

							}

							@Override
							public void onAdFailedToShowFullScreenContent(AdError adError) {
								MainActivity.this.interstitialAd = null;

							}

							@Override
							public void onAdShowedFullScreenContent() {

							}
						});
				}

				@Override
				public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
					interstitialAd = null;


				}
			});
	}

	private void showInterstitial() {
		// Show the ad if it's ready. Otherwise toast and restart the game.
		if (interstitialAd != null) {
			interstitialAd.show(this);

		}else{
			loadAd();
		}


    }
	
	 
	private void doLayout() {
		setContentView(R.layout.activity_main_drawer);
		mChart = (LineChart) findViewById(R.id.chart1);
		mChart.setNoDataText("");
		mChart.invalidate();
		graph = GraphHelper.getHelper().with(this).color(Color.parseColor(getString(R.color.white))).chart(mChart);
		if (!StoredData.isSetData)
		{
			StoredData.setZero();
		}
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
	//	mDrawer.setDrawer(this);	
		liveData();
		// set ADS
		adsBannerView = (AdView) findViewById(R.id.adBannerMainView);
		
		drawer = (DrawerLayout)findViewById(R.id.drawer);
		navi = (NavigationView)findViewById(R.id.navigation);
		toolbar_main = (Toolbar) findViewById(R.id.toolbar_main);
		setSupportActionBar(toolbar_main);
		navi.setNavigationItemSelectedListener(this);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar_main,R.string.bobo,R.string.bobo);
		toolbar_main.setTitle("");
		toggle.syncState();
		drawer.setDrawerListener(toggle);
		
		if (TunnelUtils.isNetworkOnline(MainActivity.this)) {
			adsBannerView.setAdListener(new AdListener() {
				@Override
				public void onAdLoaded() {
					if (adsBannerView != null) {
						adsBannerView.setVisibility(View.VISIBLE);
					}
				}
			});
			adsBannerView.loadAd(new AdRequest.Builder()
				.build());
		}
		
		final SharedPreferences prefs = mConfig.getPrefsPrivate();
        SharedPreferences.Editor edit = prefs.edit();
		SharedPreferences sPrefs = mConfig.getPrefsPrivate();
		
		mainLayout = (LinearLayout) findViewById(R.id.activity_mainLinearLayout);
		loginLayout = (LinearLayout) findViewById(R.id.activity_mainInputPasswordLayout);
		starterButton = (Button) findViewById(R.id.activity_starterButtonMain);

		inputPwUser = (TextInputEditText) findViewById(R.id.activity_mainInputPasswordUserEdit);
		inputPwPass = (TextInputEditText) findViewById(R.id.activity_mainInputPasswordPassEdit);

		inputPwShowPass = (ImageButton) findViewById(R.id.activity_mainInputShowPassImageButton);

		((TextView) findViewById(R.id.activity_mainAutorText))
			.setOnClickListener(this);

		proxyInputLayout = (LinearLayout) findViewById(R.id.activity_mainInputProxyLayout);
		proxyText = (TextView) findViewById(R.id.activity_mainProxyText);
		
        sPrefs.edit().putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, false).apply();
		sPrefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY).apply();
        config = new ConfigUtil(this);
		serverSpinner = (Spinner) findViewById(R.id.serverSpinner);
		payloadSpinner = (Spinner) findViewById(R.id.payloadSpinner);
       connectionStatus = (TextView) findViewById(R.id.connection_status);
		serverList = new ArrayList<>();
		payloadList = new ArrayList<>();

		serverAdapter = new SpinnerAdapter(this, R.id.serverSpinner, serverList);
		payloadAdapter = new PromoAdapter(this, R.id.payloadSpinner, payloadList);

		serverSpinner.setAdapter(serverAdapter);
		payloadSpinner.setAdapter(payloadAdapter);

		loadServer();
		loadNetworks();
		updateConfig(true);

		serverSpinner.setSelection(prefs.getInt("LastSelectedServer", 0));
        serverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                @Override
                public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4) {
                    SharedPreferences prefs = mConfig.getPrefsPrivate();
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putInt("LastSelectedServer", p3).apply();
                }

                @Override
                public void onNothingSelected(AdapterView<?> p1) {
                }
            });
		payloadSpinner.setSelection(prefs.getInt("LastSelectedPayload", 0));
        payloadSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                @Override
                public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4) {
                    SharedPreferences prefs = mConfig.getPrefsPrivate();
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putInt("LastSelectedPayload", p3).apply();
                }

                @Override
                public void onNothingSelected(AdapterView<?> p1) {
                }
            });
		
		bytesIn = (TextView) findViewById(R.id.bytesIn);
        bytesOut = (TextView) findViewById(R.id.bytesOut);
		
		version = (TextView) findViewById(R.id.ConfigVersion);
		version.setText(config.getVersion());
		
		metodoConexaoRadio = (RadioGroup) findViewById(R.id.activity_mainMetodoConexaoRadio);
		customPayloadSwitch = (SwitchCompat) findViewById(R.id.activity_mainCustomPayloadSwitch);

		starterButton.setOnClickListener(this);
		proxyInputLayout.setOnClickListener(this);

		payloadLayout = (LinearLayout) findViewById(R.id.activity_mainInputPayloadLinearLayout);
		payloadEdit = (TextInputEditText) findViewById(R.id.activity_mainInputPayloadEditText);

		configMsgLayout = (LinearLayout) findViewById(R.id.activity_mainMensagemConfigLinearLayout);
		configMsgText = (TextView) findViewById(R.id.activity_mainMensagemConfigTextView);

		// fix bugs
		if (mConfig.getPrefsPrivate().getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
			if (mConfig.getPrefsPrivate().getBoolean(Settings.CONFIG_INPUT_PASSWORD_KEY, false)) {
				inputPwUser.setText(mConfig.getPrivString(Settings.USUARIO_KEY));
				inputPwPass.setText(mConfig.getPrivString(Settings.SENHA_KEY));
			}
		}
		else {
			payloadEdit.setText(mConfig.getPrivString(Settings.CUSTOM_PAYLOAD_KEY));
		}
		customPayloadSwitch.setChecked(true);

        edit.putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, !true);
        setPayloadSwitch(prefs.getInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT), true);
		metodoConexaoRadio.setOnCheckedChangeListener(this);
		customPayloadSwitch.setOnCheckedChangeListener(this);
		inputPwShowPass.setOnClickListener(this);
		
		
		View bottomSheet = findViewById(R.id.bottom_sheet); 
		this.bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
		this.bshl = findViewById(R.id.bshl);
		this.iv1 = (ImageView)findViewById(R.id.ivLogsDown);
		bshl.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
						bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
						iv1.animate().setDuration(500).rotation(180);
					} 
					if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
						bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
						iv1.animate().setDuration(500).rotation(0);
					}
				}
			});

		bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() { 
				@Override 
				public void onStateChanged(@NonNull View view, int i) { 
					switch (i){ 
						case BottomSheetBehavior.STATE_COLLAPSED: 
							bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
							iv1.animate().setDuration(500).rotation(0);
							break; 
						case BottomSheetBehavior.STATE_EXPANDED: 
							bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
							iv1.animate().setDuration(300).rotation(180);
							break; 
						case BottomSheetBehavior.STATE_HIDDEN: 
							bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
							if (iv1.getRotation() == 0) {
								iv1.animate().setDuration(500).rotation(180);
							} else {
								iv1.animate().setDuration(500).rotation(0);
							}
							break;
					} 
				} 
				@Override 
				public void onSlide(@NonNull View view, float v) { 

				} 
			});
	
	LinearLayoutManager layoutManager = new LinearLayoutManager(this);
	mAdapter = new LogsAdapter(layoutManager, this);
	logList = (RecyclerView) findViewById(R.id.recyclerLog);
	logList.setAdapter(mAdapter);
	logList.setLayoutManager(layoutManager);
	mAdapter.scrollToLastPosition();
	
	boolean isRunning = SkStatus.isTunnelActive();
	if (isRunning) {
		serverSpinner.setEnabled(false);
		payloadSpinner.setEnabled(false);
		//SettingsActivity.setEnabled(false);
	} else {
		serverSpinner.setEnabled(true);
		payloadSpinner.setEnabled(true);
	}
	}
	
	
	private void doUpdateLayout() {
		SharedPreferences prefs = mConfig.getPrefsPrivate();

		boolean isRunning = SkStatus.isTunnelActive();
		int tunnelType = prefs.getInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT);
		
		setStarterButton(starterButton, this);
		setPayloadSwitch(tunnelType, !prefs.getBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, true));

		String proxyStr = getText(R.string.no_value).toString();

		if (prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
			proxyStr = "*******";
			proxyInputLayout.setEnabled(false);
		}
		else {
			String proxy = mConfig.getPrivString(Settings.PROXY_IP_KEY);

			if (proxy != null && !proxy.isEmpty())
				proxyStr = String.format("%s:%s", proxy, mConfig.getPrivString(Settings.PROXY_PORTA_KEY));
			proxyInputLayout.setEnabled(!isRunning);
		} 

		proxyText.setText(proxyStr);


		switch (tunnelType) {
			case Settings.bTUNNEL_TYPE_SSH_DIRECT:
				((AppCompatRadioButton) findViewById(R.id.activity_mainSSHDirectRadioButton))
					.setChecked(true);
				break;

			case Settings.bTUNNEL_TYPE_SSH_PROXY:
				((AppCompatRadioButton) findViewById(R.id.activity_mainSSHProxyRadioButton))
					.setChecked(true);
				break;
			
			case Settings.bTUNNEL_TYPE_SSH_SSLTUNNEL:
				((AppCompatRadioButton) findViewById(R.id.activity_mainTLSSSLRadioButton))
					.setChecked(true);
		//		toolbar_main.setTitle("SSL Tunnel");
				break;
		}

		int msgVisibility = View.GONE;
		int loginVisibility = View.GONE;
		String msgText = "";
		boolean enabled_radio = !isRunning;

		if (prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
			
			if (prefs.getBoolean(Settings.CONFIG_INPUT_PASSWORD_KEY, false)) {
				loginVisibility = View.VISIBLE;
				
				inputPwUser.setText(mConfig.getPrivString(Settings.USUARIO_KEY));
				inputPwPass.setText(mConfig.getPrivString(Settings.SENHA_KEY));
				
				inputPwUser.setEnabled(!isRunning);
				inputPwPass.setEnabled(!isRunning);
				inputPwShowPass.setEnabled(!isRunning);
				
				//inputPwPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			}
			
			String msg = mConfig.getPrivString(Settings.CONFIG_MENSAGEM_KEY);
			if (!msg.isEmpty()) {
				msgText = msg.replace("\n", "<br/>");
				msgVisibility = View.VISIBLE;
			}
			
			if (mConfig.getPrivString(Settings.PROXY_IP_KEY).isEmpty() ||
					mConfig.getPrivString(Settings.PROXY_PORTA_KEY).isEmpty()) {
				enabled_radio = false;
			}
		}

		loginLayout.setVisibility(loginVisibility);
		configMsgText.setText(msgText.isEmpty() ? "" : Html.fromHtml(msgText));
		configMsgLayout.setVisibility(msgVisibility);
		
		// desativa/ativa radio group
		for (int i = 0; i < metodoConexaoRadio.getChildCount(); i++) {
			metodoConexaoRadio.getChildAt(i).setEnabled(enabled_radio);
		}
	}
	
	private void liveData()
	{
        dataUpdate = new Thread(new Runnable() {
				@Override
				public void run()
				{

					while (!dataUpdate.getName().equals("stopped"))
					{

						fHandler.post(new Runnable() {

								//private static final long xup = 0;

								@Override
								public void run()
								{
									if(toString().equals("Connected")){
										graph.start();
									}
								}
							});

						try
						{
							Thread.sleep(1000);
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
						//  progressStatus--;
					}

				}
			});

        dataUpdate.setName("started");
        dataUpdate.start();
    }
	final class MyThreadClass implements Runnable{
        @Override
        public void run(){
            int i = 0;
            synchronized (this)
			{
                while (dataThread.getName() == "showDataGraph")
				{
                    //  Log.e("insidebroadcast", Integer.toString(service_id) + " " + Integer.toString(i));
                    getData2();
                    try
					{
                        wait(1000);
                        i++;
                    }
					catch (InterruptedException e)
					{
						// sshMsg(e.getMessage());
                    }

                }
				// stopSelf(service_id);
            }

        }
    }


	public void getData2(){
        List<Long> allData;
        allData = RetrieveData.findData();
		long mDownload = DataTransferGraph2.download;
		long mUpload = DataTransferGraph2.upload;
		mDownload = allData.get(0);
        mUpload = allData.get(1);
		storedData2(mUpload,mDownload);
    }

	public void storedData2(Long mUpload,Long mDownload){
        StoredData.downloadSpeed = mDownload;
        StoredData.uploadSpeed = mUpload;
        if (StoredData.isSetData){
            StoredData.downloadList.remove(0);
            StoredData.uploadList.remove(0);
            StoredData.downloadList.add(mDownload);
            StoredData.uploadList.add(mUpload);
        }
    }
	
	
	private synchronized void doSaveData() {
		try {
			SharedPreferences prefs = mConfig.getPrefsPrivate();
			SharedPreferences.Editor edit = prefs.edit();

			if (mainLayout != null && !isFinishing())
				mainLayout.requestFocus();

			if (!prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
				if (payloadEdit != null && !prefs.getBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, true)) {
					int pos = payloadSpinner.getSelectedItemPosition();
                    // int modeType = prefs.getInt("TunnelMode",modeGroup.getCheckedRadioButtonId());


					boolean directModeType = config.getNetworksArray().getJSONObject(pos).getBoolean("isSSL");


					if (directModeType) {
						prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_SSLTUNNEL).apply();
						String sni = config.getNetworksArray().getJSONObject(pos).getString("SNI");
						edit.putString(Settings.CUSTOM_PAYLOAD_KEY, sni);
					} else {
						prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY).apply();
						String payload = config.getNetworksArray().getJSONObject(pos).getString("Payload");
						edit.putString(Settings.CUSTOM_PAYLOAD_KEY, payload);
					}

				}
			}
			else {
				if (prefs.getBoolean(Settings.CONFIG_INPUT_PASSWORD_KEY, false)) {
					edit.putString(Settings.USUARIO_KEY, inputPwUser.getEditableText().toString());
					edit.putString(Settings.SENHA_KEY, inputPwPass.getEditableText().toString());
				}
			}

			edit.apply();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadServerData() {
		try {
			SharedPreferences prefs = mConfig.getPrefsPrivate();
			SharedPreferences.Editor edit = prefs.edit();
			//    int modeType = prefs.getInt("TunnelMode",modeGroup.getCheckedRadioButtonId());
            int pos1 = serverSpinner.getSelectedItemPosition();
            int pos2 = payloadSpinner.getSelectedItemPosition();
            boolean directModeType = config.getNetworksArray().getJSONObject(pos2).getBoolean("isSSL");
            if (directModeType) {
                String ssl_port = config.getServersArray().getJSONObject(pos1).getString("SSLPort");
                edit.putString(Settings.SERVIDOR_PORTA_KEY, ssl_port);
            } else {
                String ssh_port = config.getServersArray().getJSONObject(pos1).getString("ServerPort");
                edit.putString(Settings.SERVIDOR_PORTA_KEY, ssh_port);
            }



			String ssh_server = config.getServersArray().getJSONObject(pos1).getString("ServerIP");
			String remote_proxy = config.getServersArray().getJSONObject(pos1).getString("ProxyIP");
			String proxy_port = config.getServersArray().getJSONObject(pos1).getString("ProxyPort");
            String ssh_user = config.getServersArray().getJSONObject(pos1).getString("ServerUser");
			String ssh_password = config.getServersArray().getJSONObject(pos1).getString("ServerPass");
			edit.putString(Settings.USUARIO_KEY, ssh_user);
			edit.putString(Settings.SENHA_KEY, ssh_password);
			edit.putString(Settings.SERVIDOR_KEY, ssh_server);
			edit.putString(Settings.PROXY_IP_KEY, remote_proxy);
			edit.putString(Settings.PROXY_PORTA_KEY, proxy_port);

			edit.apply();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadServer() {
		try {
			if (serverList.size() > 0) {
				serverList.clear();
				serverAdapter.notifyDataSetChanged();
			}
			for (int i = 0; i < config.getServersArray().length(); i++) {
				JSONObject obj = config.getServersArray().getJSONObject(i);
				serverList.add(obj);
				serverAdapter.notifyDataSetChanged();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadNetworks1() {
		try {
			if (payloadList.size() > 0) {
				payloadList.clear();
				payloadAdapter.clear();
			}
			JSONObject obj = getJSONConfig2(this);
			JSONArray networkPayload = obj.getJSONArray("Networks");
			for (int i = 0; i < networkPayload.length(); i++) {
				payloadList.add(networkPayload.getJSONObject(i));
			}
			//Collections.sort(listNetwork, NetworkNameComparator());
			payloadAdapter.notifyDataSetChanged();
		} catch (Exception e) {
			Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

	private void loadNetworks() {
		try {
			if (payloadList.size() > 0) {
				payloadList.clear();
				payloadAdapter.notifyDataSetChanged();
			}
			for (int i = 0; i < config.getNetworksArray().length(); i++) {
				JSONObject obj = config.getNetworksArray().getJSONObject(i);
				payloadList.add(obj);
				payloadAdapter.notifyDataSetChanged();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateConfig(final boolean isOnCreate) {
		new ConfigUpdate(this, new ConfigUpdate.OnUpdateListener() {
				@Override
				public void onUpdateListener(String result) {
					try {
						if (!result.contains("Error on getting data")) {
							String json_data = AESCrypt.decrypt(config.PASSWORD, result);
							if (isNewVersion(json_data)) {
								newUpdateDialog(result);
							} else {
								if (!isOnCreate) {
									noUpdateDialog();
								}
							}
						} else if(result.contains("Error on getting data") && !isOnCreate){
							errorUpdateDialog(result);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start(isOnCreate);
	}

	private boolean isNewVersion(String result) {
		try {
			String current = config.getVersion();
			String update = new JSONObject(result).getString("Version");
			return config.versionCompare(update, current);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void newUpdateDialog(final String result) {
		new AlertDialog.Builder(MainActivity.this)
			.setTitle("New Update Available")
			.setMessage("There is a new config update found. Would you like to update your config?")
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialogInterface, int i) {
					try {
						File file = new File(getFilesDir(), "Config.json");
						OutputStream out = new FileOutputStream(file);
						out.write(result.getBytes());
						out.flush();
						out.close();
						restart_app();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			})
			.setNegativeButton("No", null)
			.create().show();
	}

	private void noUpdateDialog() {
		new AlertDialog.Builder(MainActivity.this)
			.setTitle("No Update Available")
			.setMessage("There is a no new update found.")
			.setPositiveButton("Ok",null)
			.create().show();
	}

	private void errorUpdateDialog(String error) {
		new AlertDialog.Builder(MainActivity.this)
			.setTitle("Error on update")
			.setMessage("There is an error occurred when checking for update. Please contact Developer.\n" +
						"Error:" + error)
			.setPositiveButton("Ok", null)
			.create().show();
	}

	private void restart_app() {
		Intent intent = new Intent(this, MainActivity.class);
		int i = 123456;
		PendingIntent pendingIntent = PendingIntent.getActivity(this, i, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + ((long) 1000), pendingIntent);
		finish();
	}
	/**
	 * Tunnel SSH
	 */

	public void startOrStopTunnel(Activity activity) {
		if (SkStatus.isTunnelActive()) {
			TunnelManagerHelper.stopSocksHttp(activity);
			serverSpinner.setEnabled(true);
			payloadSpinner.setEnabled(true);
		}
		else {
			// oculta teclado se vísivel, tá com bug, tela verde
			//Utils.hideKeyboard(activity);
			serverSpinner.setEnabled(false);
			payloadSpinner.setEnabled(false);
			Settings config = new Settings(activity);
			
			
			
			if (config.getPrefsPrivate()
					.getBoolean(Settings.CONFIG_INPUT_PASSWORD_KEY, false)) {
				if (inputPwUser.getText().toString().isEmpty() || 
						inputPwPass.getText().toString().isEmpty()) {
					Toast.makeText(this, R.string.error_userpass_empty, Toast.LENGTH_SHORT)
						.show();
					return;
				}
			}
			
			launchVPN();
			/*Intent intent = new Intent(activity, LaunchVpn.class);
			intent.setAction(Intent.ACTION_MAIN);
			
			if (config.getHideLog()) {
				intent.putExtra(LaunchVpn.EXTRA_HIDELOG, true);
			}
			
			activity.startActivity(intent);*/
		}
	}

	private void launchVPN() {
		Intent intent = VpnService.prepare(this);

        if (intent != null) {
            SkStatus.updateStateString("USER_VPN_PERMISSION", "", R.string.state_user_vpn_permission,
									   ConnectionStatus.LEVEL_WAITING_FOR_USER_INPUT);
            // Start the query
            try {
                startActivityForResult(intent, START_VPN_PROFILE);
            } catch (ActivityNotFoundException ane) {
                SkStatus.logError(R.string.no_vpn_support_image);
            }
        } else {
            onActivityResult(START_VPN_PROFILE, Activity.RESULT_OK, null);
        }
    }
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == PICK_FILE)
		{
			if (resultCode == RESULT_OK) {
				try {
					Uri uri = data.getData();
					String intentData = importer(uri);
					//String cipter = AESCrypt.decrypt(ConfigUtil.PASSWORD, intentData);
					File file = new File(getFilesDir(), "Config.json");
					OutputStream out = new FileOutputStream(file);
					out.write(intentData.getBytes());
					out.flush();
					out.close();
					loadServer();
					loadNetworks();
					restart_app();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (requestCode == START_VPN_PROFILE) {
            if (resultCode == Activity.RESULT_OK) {

				if (!TunnelUtils.isNetworkOnline(this)) {
					Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT);
				} else
					TunnelManagerHelper.startSocksHttp(this);
			}
		}}
	
	private String importer(Uri uri)
	{
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try
		{
			reader = new BufferedReader(new InputStreamReader(getContentResolver().openInputStream(uri)));

			String line = "";
			while ((line = reader.readLine()) != null)
			{
				builder.append(line);
			}
			reader.close();
		}
		catch (IOException e) {e.printStackTrace();}
		return builder.toString();
	} 
	
	private void setPayloadSwitch(int tunnelType, boolean isCustomPayload) {
		SharedPreferences prefs = mConfig.getPrefsPrivate();

		boolean isRunning = SkStatus.isTunnelActive();

		customPayloadSwitch.setChecked(isCustomPayload);

		if (prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
			payloadEdit.setEnabled(false);
			
			if (mConfig.getPrivString(Settings.CUSTOM_PAYLOAD_KEY).isEmpty()) {
				customPayloadSwitch.setEnabled(false);
			}
			else {
				customPayloadSwitch.setEnabled(!isRunning);
			}
			
			if (!isCustomPayload && tunnelType == Settings.bTUNNEL_TYPE_SSH_PROXY)
				payloadEdit.setText(Settings.PAYLOAD_DEFAULT);
			else
				payloadEdit.setText("*******");
		}
		else {
			customPayloadSwitch.setEnabled(!isRunning);

			if (isCustomPayload) {
				payloadEdit.setText(mConfig.getPrivString(Settings.CUSTOM_PAYLOAD_KEY));
				payloadEdit.setEnabled(!isRunning);
			}
			else if (tunnelType == Settings.bTUNNEL_TYPE_SSH_PROXY) {
				payloadEdit.setText(Settings.PAYLOAD_DEFAULT);
				payloadEdit.setEnabled(false);
			}
		}

		if (isCustomPayload || tunnelType == Settings.bTUNNEL_TYPE_SSH_PROXY) {
			payloadLayout.setVisibility(View.VISIBLE);
		}
		else {
			payloadLayout.setVisibility(View.GONE);
		}
	}

	public void setStarterButton(Button starterButton, Activity activity) {
		String state = SkStatus.getLastState();
		boolean isRunning = SkStatus.isTunnelActive();

		if (starterButton != null) {
			int resId;		
			 if (SkStatus.SSH_INICIANDO.equals(state)) {
				resId = R.string.stop;
				dataThread = new Thread(new MyThreadClass());
				dataThread.setName("showDataGraph");			
				dataThread.start();			
				graph.start();
			    showInterstitial();
				starterButton.setEnabled(false);
			}
			else if (SkStatus.SSH_PARANDO.equals(state)) {
				resId = R.string.state_stopping;
				dataThread = new Thread(new MyThreadClass());
				graph.stop();
				
				starterButton.setEnabled(false);
			}
			else {
				resId = isRunning ? R.string.stop : R.string.start;
				
				starterButton.setEnabled(true);
			}

			starterButton.setText(resId);
		}
	}
	
	private boolean isMostrarSenha = false;
	
	@Override
	public void onClick(View p1)
	{
		//SharedPreferences prefs = mConfig.getPrefsPrivate();

		switch (p1.getId()) {
			case R.id.activity_starterButtonMain:
				doSaveData();
				startOrStopTunnel(this);
				loadServerData();
				break;


			case R.id.activity_mainAutorText:
				String url = "http://t.me/SlipkProjects";
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(Intent.createChooser(intent, getText(R.string.open_with)));
				break;
				
			case R.id.activity_mainInputShowPassImageButton:
				isMostrarSenha = !isMostrarSenha;
				if (isMostrarSenha) {
					inputPwPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					inputPwShowPass.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.devz));
				}
				else {
					inputPwPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
					inputPwShowPass.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.devz));
				}
			break;
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup p1, int p2)
	{
		SharedPreferences.Editor edit = mConfig.getPrefsPrivate().edit();

		switch (p1.getCheckedRadioButtonId()) {
			case R.id.activity_mainSSHDirectRadioButton:
				edit.putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT);
				proxyInputLayout.setVisibility(View.GONE);
		//		toolbar_main.setTitle("DIRECT Tunnel");
				break;

			case R.id.activity_mainSSHProxyRadioButton:
				edit.putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY);
				proxyInputLayout.setVisibility(View.VISIBLE);
		//		toolbar_main.setTitle("SSH Tunnel");
				break;
			case R.id.activity_mainTLSSSLRadioButton:
				edit.putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_SSLTUNNEL);
				proxyInputLayout.setVisibility(View.GONE);
		//		toolbar_main.setTitle("SSL Tunnel");
				break;
		}

		edit.apply();

		doSaveData();
		doUpdateLayout();
	}

	@Override
	public void onCheckedChanged(CompoundButton p1, boolean p2)
	{
		SharedPreferences prefs = mConfig.getPrefsPrivate();
		SharedPreferences.Editor edit = prefs.edit();

		switch (p1.getId()) {
			case R.id.activity_mainCustomPayloadSwitch:
				edit.putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, !p2);
				setPayloadSwitch(prefs.getInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT), p2);
				break;
		}

		edit.apply();

		doSaveData();
	}
	
	protected void showBoasVindas() {
		new AlertDialog.Builder(this)
            . setTitle(R.string.attention)
            . setMessage(R.string.first_start_msg)
			. setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface di, int p) {
					// ok
				}
			})
			. setCancelable(false)
            . show();
	}
	
	@Override
	public void updateState(final String state, String msg, int localizedResId, final ConnectionStatus level, Intent intent)
	{
		mHandler.post(new Runnable() {
				@Override
				public void run() {
					doUpdateLayout();
					if(SkStatus.isTunnelActive()){  
						if(level.equals(ConnectionStatus.LEVEL_CONNECTED)) {
							connectionStatus.setText(R.string.state_connected);
                            showInterstitial();
							//timer_layout.setVisibility(View.VISIBLE);
							
						}
						if(level.equals(ConnectionStatus.LEVEL_NOTCONNECTED)){

							connectionStatus.setText(R.string.state_disconnected);
							
						}
						if(level.equals(ConnectionStatus.LEVEL_CONNECTING_SERVER_REPLIED)){
							connectionStatus.setText( R.string.state_auth);
						}
						if(level.equals(ConnectionStatus.LEVEL_CONNECTING_NO_SERVER_REPLY_YET)){
							connectionStatus.setText(R.string.state_connecting);
						}
						if(level.equals(ConnectionStatus.UNKNOWN_LEVEL)){
							connectionStatus.setText(R.string.state_disconnected);
						}
						//if(level.equals(ConnectionStatus.LEVEL_RECONNECTANDO)){
						//status.setText(R.string.state_reconnecting);
					}
					if(level.equals(ConnectionStatus.LEVEL_NONETWORK)){
						connectionStatus.setText(R.string.state_nonetwork);
					}
					if(level.equals(ConnectionStatus.LEVEL_AUTH_FAILED)){
						connectionStatus.setText(R.string.state_auth_failed);
					}

				}
			});
		
		switch (state) {
			case SkStatus.SSH_CONECTADO:
				
			break;
		}
	}

	
	@Override
	public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
		super.onPostCreate(savedInstanceState, persistentState);
		if (mDrawerPanel.getToogle() != null)
			mDrawerPanel.getToogle().syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (mDrawerPanel.getToogle() != null)
			mDrawerPanel.getToogle().onConfigurationChanged(newConfig);
	}
	
	

	/**
	 * Recebe locais Broadcast
	 */

	private BroadcastReceiver mActivityReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null)
                return;

            if (action.equals(UPDATE_VIEWS) && !isFinishing()) {
				doUpdateLayout();
			}
        }
    };


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerPanel.getToogle() != null && mDrawerPanel.getToogle().onOptionsItemSelected(item)) {
            return true;
        }
		
		switch (item.getItemId()) {

			
			case R.id.miExit:
				showExitDialog();
				break;
		
			
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {

			showExitDialog();
	}

	@Override
	public void onResume() {
		super.onResume();
		SharedPreferences prefs = mConfig.getPrefsPrivate();
        SharedPreferences.Editor edit = prefs.edit();
        int server = prefs.getInt("LastSelectedServer", 0);
        int payload = prefs.getInt("LastSelectedPayload", 0);
		serverSpinner.setSelection(server);
        payloadSpinner.setSelection(payload);
		edit.apply();

		SkStatus.addStateListener(this);
		new Timer().schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					runOnUiThread(new Runnable()
						{
							@Override
							public void run()
							{
								updateHeaderCallback();
								// TODO: Implement this method
							}
						});
					// TODO: Implement this method
				}
			}, 0,1000);
		if (adsBannerView != null) {
			adsBannerView.resume();
		}
    }
	
	/*private void saveSpinner() {
        edit = mConfig.getPrefsPrivate().edit();
        int selectedItemPosition = this.serverSpinner.getSelectedItemPosition();
		int selectedItemPosition1 = this.payloadSpinner.getSelectedItemPosition();
        putInt = edit.putInt("LastSelectedServer", selectedItemPosition);
		putInt = edit.putInt("LastSelectedPayload", selectedItemPosition1);
        edit.apply();
    }
*/

	@Override
	protected void onPause()
	{
		super.onPause();
		
		doSaveData();
		
		SkStatus.removeStateListener(this);
		
		if (adsBannerView != null) {
			adsBannerView.pause();
		}
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		
		SharedPreferences prefs = mConfig.getPrefsPrivate();
        SharedPreferences.Editor edit = prefs.edit();
        int server = serverSpinner.getSelectedItemPosition();
        int payload = payloadSpinner.getSelectedItemPosition();
        edit.putInt("LastSelectedServer", server);
        edit.putInt("LastSelectedPayload", payload);
        edit.apply();
		
		SkStatus.removeLogListener(mAdapter);
		LocalBroadcastManager.getInstance(this)
			.unregisterReceiver(mActivityReceiver);
			
		if (adsBannerView != null) {
			adsBannerView.destroy();
		}
	}


	/**
	 * DrawerLayout Listener
	 */

	
	
	/**
	 * Utils
	 */

	public static void updateMainViews(Context context) {
		Intent updateView = new Intent(UPDATE_VIEWS);
		LocalBroadcastManager.getInstance(context)
			.sendBroadcast(updateView);
	}
	
	private void showExitDialog() {
		MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
		alertDialogBuilder.setMessage("Are you sure you want to exit?");
		alertDialogBuilder.setNegativeButton("Minimize", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					finish();
				}
			});
		alertDialogBuilder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					android.os.Process.killProcess(android.os.Process.myPid());
					System.exit(0);
					if (TunnelUtils.isActiveVpn(MainActivity.this)) {
						Utils.exitAll(MainActivity.this);
					}
				}
			});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
}














