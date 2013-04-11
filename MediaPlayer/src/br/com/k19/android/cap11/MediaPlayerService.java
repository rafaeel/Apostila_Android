package br.com.k19.android.cap11;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.IBinder;
import android.widget.Toast;

public class MediaPlayerService extends Service implements OnPreparedListener , OnErrorListener {
	private MediaPlayer mMediaPlayer;
	
	public int onStartCommand(Intent intent, int flags, int startId){
		Toast.makeText(this, "Inicando o Serviço", Toast.LENGTH_SHORT).show();
		
		try{
			mMediaPlayer = new MediaPlayer();
			Uri path = Uri.parse("android.resourse://br.com.k19.android.cap11/" + R.raw.sample);
			mMediaPlayer.setDataSource(getApplicationContext(), path);
			mMediaPlayer.setOnPreparedListener(this);
			mMediaPlayer.prepareAsync();
		} catch (IOException e) {
			
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	public void onDestroy(){
		Toast.makeText(this, "Terminando o Serviço", Toast.LENGTH_SHORT).show();
		if (mMediaPlayer != null) {
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}
	
	public IBinder onBind(Intent intent){
		return null;
	}
	
	public void onPrepared(MediaPlayer mp){
		mMediaPlayer.start();
	}
	
	public boolean onError(MediaPlayer arg0, int arg1, int arg2){
		return false;
	}
}
