package com.example.moviement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Point;
import android.text.Editable;
import android.text.TextWatcher;
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

		eTMainActivity_1.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
	}

	private class GetJSON extends AsyncTask<String, Void, String>{
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			DefaultHttpClient httpClient = new DefaultHttpClient(new BasicHttpParams());
			HttpPost httpPost = new HttpPost("http://www.myapifilms.com/imdb/top");
			httpPost.setHeader("Content/type", "application/json");

			InputStream inputStream = null;
			String result = null;

			HttpResponse response;
			try {
				response = httpClient.execute(httpPost);
				HttpEntity entity = response.getEntity();
				
				inputStream = entity.getContent();
			    // json is UTF-8 by default
			    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
			    StringBuilder sb = new StringBuilder();
			    String line = null;
			    while ((line = reader.readLine()) != null)
			    {
			        sb.append(line + "\n");
			    }
			    result = sb.toString();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
			}    

			try {
				JSONObject jObject = new JSONObject(result);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			return null;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}


	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
