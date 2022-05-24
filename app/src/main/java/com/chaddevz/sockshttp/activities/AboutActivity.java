package com.chaddevz.sockshttp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.chadx.sockshttp.R;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.TextView;
import com.chaddevz.sockshttp.util.Utils;
import android.text.Html;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.ads.AdView;
import com.chaddevz.service.tunnel.TunnelUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.chaddevz.sockshttp.SocksHttpApp;
import com.chadx.sockshttp.BuildConfig;

public class AboutActivity extends BaseActivity
{
	private AdView adsBannerView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);

		// toolbar
		Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		
		adsBannerView = (AdView) findViewById(R.id.adBannerSecondView);
		if (!BuildConfig.DEBUG) {
			//adsBannerView.setAdUnitId(SocksHttpApp.ADS_UNITID_BANNER_SOBRE);
		}

		// carrega anúncio
		if (TunnelUtils.isNetworkOnline(this)) {

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
	}

	@Override
	public boolean onSupportNavigateUp()
	{
		onBackPressed();
		return true;
	}

	

	@Override
	protected void onResume()
	{
		// TODO: Implement this method
		super.onResume();

		if (adsBannerView != null) {
			adsBannerView.resume();
		}
	}

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();

		if (adsBannerView != null) {
			adsBannerView.pause();
		}
	}

	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		super.onDestroy();

		if (adsBannerView != null) {
			adsBannerView.destroy();
		}
	}

}

