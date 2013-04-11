package br.com.k19.android.cap09_02;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button createNotification = (Button) findViewById(R.id.create_notification_button);
		createNotification.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
				
				Notification notification = new Notification.Builder(MainActivity.this)
												.setContentTitle(getString(R.string.new_notification))
												.setContentText(getString(R.string.notification_content))
													.setSmallIcon(R.drawable.ic_launcher)
												.setContentIntent(pendingIntent)
												.getNotification();
				notification.flags |= Notification.FLAG_AUTO_CANCEL;
				
				NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				notificationManager.notify (0,notification);
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
