package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MediaInterface  extends Activity{

	private Button backButton;
	static Button mediaIV;
	private static String localStatus;
    Connection con = Connection.getConnection();	
   static boolean mediaStatus =false;
	boolean LoopStatus = true;
	static boolean MEDIA = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media);
		mediaIV = (Button) findViewById(R.id.SwitchmeidaButton);
		backButton = (Button) this.findViewById(R.id.mediabackbutton);
		meidaIncheck();
		MEDIA = true;
	}

	public static void meidaIncheck() {
		// String [] Lightint=LoginInterface.initsmarthouse[0].split(":");
		localStatus = Connection.initStates;
		if (Connection.initStates.contains("media:on")) {
			mediaIV.setBackgroundResource(R.drawable.music);
			mediaStatus=true;
			
			
		} else {
			mediaIV.setBackgroundResource(R.drawable.musicoff);
			mediaStatus=false;
		}

	}

	public void buttonmeidaClicked(View view) {

		if(mediaStatus==false)
		{
		con.setResult("media:on");
		String temp = Connection.initStates;
		while (localStatus.equals(temp)) {
			temp = Connection.initStates;
		}
		if (Connection.initStates.contains("media:on")) {
			mediaIV.setBackgroundResource(R.drawable.music);
			mediaStatus=true;
		} else {
			
		}
		con.UpdateForDeviceImages();	
		}else if(mediaStatus==true){
			
			con.setResult("media:off");
			String temp = Connection.initStates;
			while (localStatus.equals(temp)) {
				temp = Connection.initStates;
			}
			if (Connection.initStates.contains("media:off")) {
				mediaIV.setBackgroundResource(R.drawable.musicoff);
				mediaStatus=false;
			} else {
			}
			con.UpdateForDeviceImages();			
		}

	}

	public void buttonBackClicked(View view) {
		Intent intent = new Intent();
		intent.setClass(MediaInterface.this, MainActivity.class);
		MediaInterface.this.startActivity(intent);

	}

}
