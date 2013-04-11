package br.com.k19.android.cap05;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddNameActivity extends Activity {
	private SharedPreferences prefs;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_name);
		
		prefs = getSharedPreferences(MainActivity.APP_PREFS, MODE_PRIVATE);
		
		final EditText name = (EditText) findViewById(R.id.name_edit_text);
		Button saveButton = (Button) findViewById(R.id.add_name_button);
		
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String username = name.getEditableText().toString();
				Editor editor = prefs.edit();
				editor.putString(MainActivity.USERNAME_KEY, username);
				editor.commit();
				finish();
			}
		});
	}
}
