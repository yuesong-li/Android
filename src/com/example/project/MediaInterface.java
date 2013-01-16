package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*
****************************************************************************************************************************************
* Authors : Ding junkai, Gao Fang, Chen anxiao

* Class   : MediaInterface

* Class functionality : Displays all the segments of Media's device, adds methods for updating device's image and goes back to main interface.
*****************************************************************************************************************************************
*/
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
		MEDIA = true;                      // change the boolean value to mark if  it has been initialized
	}

	public static void meidaIncheck() {          // For initialization of media'device and can be used to update device's image.
		localStatus = Connection.initStates;
		if (Connection.initStates.contains("media:on")) {
			mediaIV.setBackgroundResource(R.drawable.musicplay);
			mediaStatus=true;	
		} else {
			mediaIV.setBackgroundResource(R.drawable.musistop);
			mediaStatus=false;
		}
	}
	public void buttonmeidaClicked(View view) {        // Method to response to user's action 
		if(mediaStatus==false)
		{
		con.setResult("media:on");
		String temp = Connection.initStates;
		while (localStatus.equals(temp)) {
			temp = Connection.initStates;
		}
		if (Connection.initStates.contains("media:on")) {
			mediaIV.setBackgroundResource(R.drawable.musicplay);
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
				mediaIV.setBackgroundResource(R.drawable.musistop);
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
