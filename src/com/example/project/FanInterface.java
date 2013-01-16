package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/*
*************************************************************************************************************************************
* Authors : Ding junkai, Gao Fang, Chen anxiao

* Class   : FanInterface

* Class functionality : Displays all the segments of fan's device,adds methods for updating device's image and goes back to main interface.
**************************************************************************************************************************************
*/

public class FanInterface extends Activity {
	private Button FanbackButton;
	static Button FanIV;	
	boolean LoopStatus=true;
	static boolean FAN=false;
	static boolean FanStatus=false;
	Connection con = Connection.getConnection();
	private static String localStatus;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fan);
		FanIV = (Button) findViewById(R.id.SwitchFanbutton);
		FanbackButton = (Button) this.findViewById(R.id.Fanbackbutton);
		Fancheck();   
		FAN=true;                    // change the boolean value to mark if  it has been initialized
	}
	public static void Fancheck(){		  // For initialization of fan'device and can be used to update device's image.
		localStatus = Connection.initStates;
		if(Connection.initStates.contains("fan:on"))
		{
			FanIV.setBackgroundResource(R.drawable.newfanopen);
			FanStatus=true;
		}else{
			FanIV.setBackgroundResource(R.drawable.newfanclose);
			FanStatus=false;
		}
	}
	public void FanbuttonClicked(View view){	  // Method to response to user's action 
		if(FanStatus==false)
		{
		con.setResult("fan:on");
		String temp = Connection.initStates;
		while (localStatus.equals(temp)) {
			temp = Connection.initStates;
		}
		if (Connection.initStates.contains("fan:on")) {

			FanIV.setBackgroundResource(R.drawable.newfanopen);
			FanStatus=true;
		} else {
		}
		con.UpdateForDeviceImages();
		}else if(FanStatus==true)
		{
			con.setResult("fan:off");
			String temp = Connection.initStates;
			while (localStatus.equals(temp)) {
				temp = Connection.initStates;
			}
			if (Connection.initStates.contains("fan:off")) {
				FanIV.setBackgroundResource(R.drawable.newfanclose);
				FanStatus=false;
			} else {
			}
			con.UpdateForDeviceImages();
		}
	}
	public void FanbuttonBackClicked(View view){
		Intent intent = new Intent();
		intent.setClass(FanInterface.this, MainActivity.class);
		FanInterface.this.startActivity(intent);
		
	}
}
