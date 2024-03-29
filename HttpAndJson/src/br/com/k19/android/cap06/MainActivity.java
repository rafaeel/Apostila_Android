package br.com.k19.android.cap06;

import java.io.*;
import java.net.*;
import org.json.*;
import android.app.Activity;
import android.os.*;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		TextView nameText = (TextView) findViewById(R.id.name_text);
		TextView phoneText = (TextView) findViewById(R.id.phone_text);
		TextView addressText = (TextView) findViewById(R.id.address_text);
		TextView cityText = (TextView) findViewById(R.id.city_text);
		TextView likesText = (TextView) findViewById(R.id.likes_text);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		String response = makeRequest("http://graph.facebook.com/k19treinamentos");
		
		try {
			JSONObject json = new JSONObject(response);
			String name = json.getString("name");
			String phone = json.getString("phone");
			int likes = json.getInt("likes");
			String address = json.getJSONObject("location").getString("street");
			String city = json.getJSONObject("location").getString("city");
			
			nameText.setText(name);
			phoneText.setText(getString(R.string.phone_label, phone));
			addressText.setText(getString(R.string.address_label, address));
			cityText.setText(getString(R.string.city_label, city));
			likesText.setText(getString(R.string.likes_label, likes));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private String makeRequest(String urlAddress){
		HttpURLConnection con = null;
		URL url = null;
		String response = null;
		try {
			url = new URL(urlAddress);
			con = (HttpURLConnection) url.openConnection();
			response = readStream(con.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.disconnect();
		}
		return response;
	}
	
	private String readStream(InputStream in){
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while((line = reader.readLine()) != null){
				builder.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
		return builder.toString();
	}
}
