package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
		FAN=true;
	   
	}
	public static void Fancheck(){		
		//String [] Heatingint=LoginInterface.initsmarthouse[3].split(":");
		localStatus = Connection.initStates;
		if(Connection.initStates.contains("fan:on"))
		{
			FanIV.setBackgroundResource(R.drawable.fanopen);
			FanStatus=true;
		}else{
			FanIV.setBackgroundResource(R.drawable.fanclose);
			FanStatus=false;
		}
	}
	
	public void FanbuttonClicked(View view){
		
		if(FanStatus==false)
		{
		con.setResult("fan:on");
		String temp = Connection.initStates;
		while (localStatus.equals(temp)) {
			temp = Connection.initStates;
		}
		if (Connection.initStates.contains("fan:on")) {

			FanIV.setBackgroundResource(R.drawable.fanopen);
			FanStatus=true;

		} else {

		}
		con.UpdateForDeviceImages();
		
		}else if(FanStatus==true)
		{
			con.setResult("fan:off");
			//Connection.initStates = con.getResult();
			String temp = Connection.initStates;
			while (localStatus.equals(temp)) {
				temp = Connection.initStates;
			}
			if (Connection.initStates.contains("fan:off")) {

				FanIV.setBackgroundResource(R.drawable.fanclose);
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
