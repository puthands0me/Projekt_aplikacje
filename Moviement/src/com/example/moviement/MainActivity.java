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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	LinearLayout lLMainActivity_1;
	LinearLayout lLMainActivity_2;
	LinearLayout lLMainActivity_3;
	ImageView iVMainActivity_1;

	EditText eTMainActivity_1;

	JSONArray allMoviesJson = new JSONArray();
	
	ArrayList<Film> allMoviesList= new ArrayList<Film>(); 
	
	private static final String IDIMDB = "idIMDB";
	private static final String RANKING = "ranking";
	private static final String RATING = "rating";
	private static final String TITLE = "title";
	private static final String URLPOSTER = "urlPoster";
	private static final String YEAR = "year";
	
	private String filmTitle;
	
	
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
		
		//ttt

		lLMainActivity_1 = (LinearLayout) findViewById(R.id.lLMainActivity_1);
		lLMainActivity_2 = (LinearLayout) findViewById(R.id.lLMainActivity_2);
		lLMainActivity_3 = (LinearLayout) findViewById(R.id.lLMainActivity_3);
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
				//Log.d("#WA", eTMainActivity_1.getText()+"");
				lLMainActivity_3.removeAllViews();
				filmTitle = eTMainActivity_1.getText()+"";
				new GetJSON().execute();
			}
		});
		
		

	}

	private class GetJSON extends AsyncTask<String, Void, String>{

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			HttpGet httpGet = new HttpGet("http://www.myapifilms.com/imdb?title="+filmTitle+"&format=JSON&filter=N&exactFilter=0&limit=10&lang=en-us&exactFilter=0");
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = null;
			
			try {
				httpResponse = httpClient.execute(httpGet);
				String jsonString = EntityUtils.toString(httpResponse.getEntity(),HTTP.UTF_8);
				JSONArray jsonArr = new JSONArray(jsonString);
				
				String filmName = new String();
				for (int i = 0; i < jsonArr.length(); i++) {
					JSONObject jsonObj = jsonArr.getJSONObject(i);
					// rozbijamy na poszczególne pola
					String idIMDB = jsonObj.getString(IDIMDB);
					//String ranking = jsonObj.getString(RANKING);
					String rating = jsonObj.getString(RATING);
					String title = jsonObj.getString(TITLE);
					String urlPoster = jsonObj.getString(URLPOSTER);
					String year = jsonObj.getString(YEAR);
					
					filmName = "film"+i;
					
					Film f = new Film(filmName);
					
					f.idIMDB = idIMDB;
					//f.ranking = ranking;
					f.rating = rating;
					f.title = title;
					f.urlPoster = urlPoster;
					f.year = year;
					
					allMoviesList.add(f);
				}			
				
				//Log.d("#WA", allMoviesList.get(1).title);
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("#WA", "a");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("#WA", "b");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("#WA", e.getMessage());
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

			
			for(int i=0; i<allMoviesList.size(); i++){
				
				LinearLayout ll = new LinearLayout(MainActivity.this);
				ll.removeAllViews();
				ll.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 50));
				
				TextView tv = new TextView(MainActivity.this);
				
				tv.setText(allMoviesList.get(i).title);
				tv.setTextColor(Color.parseColor("#FFFFFF"));
				tv.setTextSize(24);
				
				ll.addView(tv);

				lLMainActivity_3.addView(ll);
			}
			
		}


	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}