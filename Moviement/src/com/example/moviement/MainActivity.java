package com.example.moviement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
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
import android.widget.Toast;

public class MainActivity extends Activity {
	LinearLayout lLMainActivity_1;
	LinearLayout lLMainActivity_2;
	ImageView iVMainActivity_1;

	EditText eTMainActivity_1;

	JSONArray allMoviesJson = new JSONArray();
	
	ArrayList<String> allMoviesList= new ArrayList<String>(); 
	
	private static final String IDIMDB = "IDIMDB";
	private static final String RANKING = "RANKING";
	private static final String RATING = "RATING";
	private static final String TITLE = "TITLE";
	private static final String URLPOSTER = "URLPOSTER";
	private static final String YEAR = "YEAR";
	
	
	
	
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
		
		new GetJSON().execute();

	}

	private class GetJSON extends AsyncTask<String, Void, String>{
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			HttpPost httpPost = new HttpPost("http://www.myapifilms.com/imdb/top");		
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = null;
			try {
				httpResponse = httpClient.execute(httpPost);
				String jsonString = EntityUtils.toString(httpResponse.getEntity(),HTTP.UTF_8);
				JSONObject jsonObj = new JSONObject(jsonString);
				allMoviesJson = jsonObj.getJSONArray(jsonString);

				for (int i = 0; i < allMoviesJson.length(); i++) {
					JSONObject jsonObj2 = allMoviesJson.getJSONObject(i);
					// poszczególne pola
					String idIMDB = jsonObj2.getString(IDIMDB);
					String ranking = jsonObj2.getString(RANKING);
					String rating = jsonObj2.getString(RATING);
					String title = jsonObj2.getString(TITLE);
					String urlPoster = jsonObj2.getString(URLPOSTER);
					String year = jsonObj2.getString(YEAR);
					
					Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
					//dodanie do tablicy
					allMoviesList.add(idIMDB);
					allMoviesList.add(ranking);
					allMoviesList.add(rating);
					allMoviesList.add(title);
					allMoviesList.add(urlPoster);
					allMoviesList.add(year);
					
					//sasasas
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
