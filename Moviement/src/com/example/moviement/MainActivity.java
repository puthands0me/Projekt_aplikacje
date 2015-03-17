package com.example.moviement;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.view.Menu;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	LinearLayout lLMainActivity_1;
	LinearLayout lLMainActivity_2;
	ImageView iVMainActivity_1;
	
	EditText eTMainActivity_1;
	
	int szer_ekranu;
	int wys_ekranu;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		szer_ekranu = size.x;
		wys_ekranu = size.y;
		
        lLMainActivity_1 = (LinearLayout) findViewById(R.id.lLMainActivity_1);
        lLMainActivity_2 = (LinearLayout) findViewById(R.id.lLMainActivity_2);
        iVMainActivity_1 = (ImageView) findViewById(R.id.iVMainActivity_1);
        eTMainActivity_1 = (EditText) findViewById(R.id.eTMainActivity_1);
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
