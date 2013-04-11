package br.com.k19.android.cap05;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.content.SharedPreferences;

public class MainActivity extends Activity {
	
	final static String APP_PREFS = "app_prefs";
	final static String USERNAME_KEY = "username";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
	protected void onResume(){
		super.onResume();
		
		SharedPreferences prefs = getSharedPreferences(APP_PREFS, MODE_PRIVATE);
		String username = prefs.getString(USERNAME_KEY, null);
		
		TextView message = (TextView) findViewById(R.id.welcome_message);
		Button addNameButton = (Button) findViewById(R.id.add_name_button);
		
		if (username != null){
			message.setText("Bem vindo, " + username + "!");
			addNameButton.setText("Trocar de nome");
		} else {
			message.setText("Voce nao cadastrou seu nome...");
			addNameButton.setText("Adicionar nome");
		}
		
		addNameButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, AddNameActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
