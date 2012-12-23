package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HeatingInterface extends Activity {
	
	private Button HeatingbackButton;
	static Button HeatingIV;	
	boolean LoopStatus=true;
	Connection con = Connection.getConnection();
	static boolean HEATING=false; 
	static boolean HeatingStatus=false;
	private static String localStatus;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.heating);
		HeatingIV = (Button) findViewById(R.id.SwitchHeatingbutton);
		HeatingbackButton = (Button) this.findViewById(R.id.Heatingbackbutton);
		Heatingcheck();
		HEATING=true;
		
	}
	public static void Heatingcheck(){		
		//String [] Heatingint=LoginInterface.initsmarthouse[3].split(":");
		localStatus = Connection.initStates;
		if(Connection.initStates.contains("heaterRoom:on"))
		{
			HeatingIV.setBackgroundResource(R.drawable.indoorheateron);
			HeatingStatus=true;
		}else{
			HeatingIV.setBackgroundResource(R.drawable.indoorheateroff);
			HeatingStatus=false;
		}
	}
	
	public void HeatingbuttonClicked(View view){
		
		if(HeatingStatus==false)
		{
		con.setResult("heaterRoom:on");
		String temp = Connection.initStates;
		while (localStatus.equals(temp)) {
			temp = Connection.initStates;
		}
		if (Connection.initStates.contains("heaterRoom:on")) {

			HeatingIV.setBackgroundResource(R.drawable.indoorheateron);
			HeatingStatus=true;

		} else {

		}
		con.UpdateForDeviceImages();
		
		}else if(HeatingStatus==true)
		{
			con.setResult("heaterRoom:off");
			//Connection.initStates = con.getResult();
			String temp = Connection.initStates;
			while (localStatus.equals(temp)) {
				temp = Connection.initStates;
			}
			if (Connection.initStates.contains("heaterRoom:off")) {

				HeatingIV.setBackgroundResource(R.drawable.indoorheateroff);
				HeatingStatus=false;

			} else {

			}
			
			con.UpdateForDeviceImages();
		}
	}
	public void HeatingbuttonBackClicked(View view){
		Intent intent = new Intent();
		intent.setClass(HeatingInterface.this, MainActivity.class);
		HeatingInterface.this.startActivity(intent);
		
	}
}
