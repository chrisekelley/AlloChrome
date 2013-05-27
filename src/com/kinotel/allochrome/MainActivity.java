package com.kinotel.allochrome;

import us.costan.chrome.ChromeView;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	SharedPreferences preferences;
	ChromeView chromeView;
	OnSharedPreferenceChangeListener myPrefListner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chromeview);
		chromeView = (ChromeView)findViewById(R.id.chrome_view);
		chromeView.getSettings().setJavaScriptEnabled(true);
		preferences = PreferenceManager.getDefaultSharedPreferences(this); 
		preferences.registerOnSharedPreferenceChangeListener(spChangedMain);  
		String url = preferences.getString("pref_Urlkey", "file:///android_asset/www/index.html");
		Toast.makeText(MainActivity.this, "URL to display: " + url, Toast.LENGTH_SHORT).show();
		chromeView.loadUrl(url);
	}
	
	SharedPreferences.OnSharedPreferenceChangeListener spChangedMain = new SharedPreferences.OnSharedPreferenceChangeListener() {

		@Override
		public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
			String url = prefs.getString("pref_Urlkey", "file:///android_asset/www/two1.html");
			Log.d("MainActivity", "Selected the URL:" + url);
			chromeView.loadUrl(url);
		}
		// your stuff here
	};
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// This method is called once the menu is selected
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// We have only one menu option
		case R.id.action_settings:
			// Launch Preference activity
			Intent i = new Intent(MainActivity.this, MyPreferenceActivity.class);
			startActivity(i);
			break;
		}
		return true;
	} 

	@Override     
	protected void onResume() {
		super.onResume();          
		preferences.registerOnSharedPreferenceChangeListener(myPrefListner);     
	}



	@Override     
	protected void onPause() {         
		super.onPause();          
		preferences.unregisterOnSharedPreferenceChangeListener(myPrefListner);

	}
	@Override     
	protected void onDestroy() {         
		super.onPause();          
		preferences.unregisterOnSharedPreferenceChangeListener(myPrefListner);

	}

}
