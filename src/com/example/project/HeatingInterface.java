package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HeatingInterface extends Activity {
	private Button HeatingonButton;
	private Button HeatingoffButton;
	private Button HeatingbackButton;
	static ImageView HeatingIV;	
	boolean LoopStatus=true;
	Connection con = Connection.getConnection();
	static boolean HEATING=false; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.heating);
		HeatingIV = (ImageView) findViewById(R.id.Heating);
		HeatingonButton = (Button) this.findViewById(R.id.Heatingyesbutton);
		HeatingoffButton = (Button) this.findViewById(R.id.Heatingnobutton);
		HeatingbackButton = (Button) this.findViewById(R.id.Heatingbackbutton);
		Heatingcheck();
		HEATING=true;
		
	}
	public static void Heatingcheck(){		
		//String [] Heatingint=LoginInterface.initsmarthouse[3].split(":");
		if(Connection.initStates.contains("heaterRoom:on"))
		{
			HeatingIV.setBackgroundResource(R.drawable.heating);
		}else{
			HeatingIV.setBackgroundResource(R.drawable.noheating);
		}
	}
	
	public void HeatingbuttonOnClicked(View view){
		con.setResult("heaterRoom:on");
		Connection.initStates = con.getResult();
		
		if (Connection.initStates.contains("heaterRoom:on")) {

			HeatingIV.setBackgroundResource(R.drawable.heating);

		} else {

		}
		
	
		con.UpdateForDeviceImages();
		HeatingonButton.setVisibility(view.INVISIBLE);
		HeatingoffButton .setVisibility(view.VISIBLE);
		
	}
	
	public void HeatingbuttonOffClicked(View view){
		con.setResult("heaterRoom:off");
		Connection.initStates = con.getResult();
		
		if (Connection.initStates.contains("heaterRoom:off")) {

			HeatingIV.setBackgroundResource(R.drawable.noheating);

		} else {

		}
		
		con.UpdateForDeviceImages();
		HeatingoffButton.setVisibility(view.INVISIBLE);
		HeatingonButton.setVisibility(view.VISIBLE);	
	}
	
	public void HeatingbuttonBackClicked(View view){
		Intent intent = new Intent();
		intent.setClass(HeatingInterface.this, MainActivity.class);
		HeatingInterface.this.startActivity(intent);
		
	}
}
