package com.kinotel.allochrome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class MyPreferenceActivity extends PreferenceActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this); 
        prefs.registerOnSharedPreferenceChangeListener(spChanged);  
        
	}
	
	public static class MyPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);      
        }
    }
	
	SharedPreferences.OnSharedPreferenceChangeListener spChanged = new SharedPreferences.OnSharedPreferenceChangeListener() {

		@Override
		public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
			String url = prefs.getString("pref_Urlkey", "file:///android_asset/www/two1.html");
			Log.d("MyPreferenceActivity", "Selected the URL:" + url);
			Intent testIntent = new Intent(getApplicationContext(), MainActivity.class);
	        startActivity(testIntent);
		}
	};
	


}
