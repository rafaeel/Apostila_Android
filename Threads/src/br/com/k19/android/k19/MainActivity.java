package br.com.k19.android.k19;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	
	private Handler handler;
	private ProgressBar progress;
	private Button startButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		progress = (ProgressBar) findViewById(R.id.progress_bar);
		startButton = (Button) findViewById(R.id.start_button);
		handler = new Handler();
		
		startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Runnable runnable = new Runnable() {
				public void run(){
					for(int i = 1; i <= 1000; i++){
						final int value = i;
						try{
							Thread.sleep(1000);
						} catch (InterruptedException e){
							e.printStackTrace();
						}
						//handler.post(new Runnable(){
						runOnUiThread(new Runnable(){
							public void run(){
								progress.setProgress(value);
							}
						});
					}
				}
			};
					
			new Thread(runnable).start();
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
