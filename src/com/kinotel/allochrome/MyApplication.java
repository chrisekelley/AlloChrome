package com.kinotel.allochrome;

import us.costan.chrome.ChromeView;
import android.app.Application;

public class MyApplication extends Application {
	@Override
    public void onCreate() {
        super.onCreate();
        ChromeView.initialize(this);
    }
}
