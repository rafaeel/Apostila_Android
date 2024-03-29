package br.com.k19.android.cap11;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.k19.android.cap11.R.id;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	private Uri fileUri;
	
	private static Uri getUri(int type){
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
												"Camera Output");
		if (! mediaStorageDir.exists()){
			if (! mediaStorageDir.mkdirs()){
				Log.d("Camera Output", "nao foi possivel criar o diretorio");
				return null;
			}
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		File mediaFile;
		if(type == MEDIA_TYPE_IMAGE){
			mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
		} else if (type == MEDIA_TYPE_VIDEO){
			mediaFile = new File(mediaStorageDir.getPath() + File.separator + "VID_" + timeStamp + ".mp4");
		} else { 
			return null;
		}
		
		return Uri.fromFile(mediaFile);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button captureButton = (Button) findViewById(id.button_photo);
		captureButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				fileUri = getUri(MEDIA_TYPE_IMAGE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
				
				startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
			}
		});
		
		captureButton = (Button) findViewById(id.button_video);
		captureButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				
				fileUri = getUri(MEDIA_TYPE_VIDEO);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
				intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
				
				startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE){
			if(resultCode == RESULT_OK) {
				Toast.makeText(this, "Imagem salva em: \n" + fileUri.getPath(), Toast.LENGTH_LONG).show();
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Cancelou a captura", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Ocorreu algum problema inesperado", Toast.LENGTH_LONG).show();
			}
		}
		
		if(requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE){
			if(resultCode == RESULT_OK){
				Toast.makeText(this, "Video salvo em: \n" + fileUri.getPath(), Toast.LENGTH_LONG).show();
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Cancelou a captura", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Ocorreu algum problema inesperado", Toast.LENGTH_LONG).show();
			}
		}
	}

}
