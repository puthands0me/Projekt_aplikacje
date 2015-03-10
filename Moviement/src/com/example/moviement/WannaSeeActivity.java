package com.example.moviement;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

public class WannaSeeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_wanna_see);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wanna_see, menu);
		return true;
	}

}
