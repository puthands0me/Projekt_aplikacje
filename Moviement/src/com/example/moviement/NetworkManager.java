package com.example.moviement;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkManager {
	
	public static boolean Network(Context context) {
		// TODO Auto-generated constructor stub
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo = connectivityManager.getActiveNetworkInfo();
		
		if (networkinfo == null || !networkinfo.isConnected()) {
			return false;
			
		} else {
			return true;
		}
	}

}
