package br.com.k19.android.cap05_02;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends Activity {
	private NotesDao dao;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_note);
		
		dao = new NotesDao(this);
		dao.open();
		
		Button saveButton = (Button) findViewById(R.id.save_note_button);
		final EditText noteText = (EditText) findViewById(R.id.note_text);
		
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String note = noteText.getEditableText().toString();
				dao.create(note);
				finish();
			}
		});
	}
	
	protected void onResume(){
		dao.open();
		super.onResume();
	}
	
	protected void onPause(){
		dao.close();
		super.onPause();
	}
}
