package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FanInterface extends Activity {
	private Button FanonButton;
	private Button FanoffButton;
	private Button FanbackButton;
	static ImageView FanIV;	
	boolean LoopStatus=true;
	static boolean FAN=false;
	Connection con = Connection.getConnection();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fan);
		FanIV = (ImageView) findViewById(R.id.Fan);
		FanonButton = (Button) this.findViewById(R.id.Fanyesbutton);
		FanoffButton = (Button) this.findViewById(R.id.Fannobutton);
		FanbackButton = (Button) this.findViewById(R.id.Fanbackbutton);
		Fancheck();   
		FAN=true;
	   
	}
	public static void Fancheck(){
		//String [] Fanint=LoginInterface.initsmarthouse[2].split(":");
		if(Connection.initStates.contains("fan:on"))
		{
			FanIV.setBackgroundResource(R.drawable.fanopen);
		}else{
			FanIV.setBackgroundResource(R.drawable.fanclose);
		}

	}
	
	public void FanbuttonOnClicked(View view){
		con.setResult("fan:on");
		Connection.initStates = con.getResult();
		
		if (Connection.initStates.contains("fan:on")) {

			FanIV.setBackgroundResource(R.drawable.fanopen);

		} else {

		}
		
		con.UpdateForDeviceImages();
		FanoffButton.setVisibility(view.VISIBLE);
		FanonButton.setVisibility(view.INVISIBLE);
	}
	
	public void FanbuttonOffClicked(View view){
		con.setResult("fan:off");
		Connection.initStates = con.getResult();
		
		if (Connection.initStates.contains("fan:off")) {

			FanIV.setBackgroundResource(R.drawable.fanclose);

		} else {

		}
		
		con.UpdateForDeviceImages();
		FanoffButton.setVisibility(view.INVISIBLE);
		FanonButton.setVisibility(view.VISIBLE);
		
	}
	
	public void FanbuttonBackClicked(View view){
		Intent intent = new Intent();
		intent.setClass(FanInterface.this, MainActivity.class);
		FanInterface.this.startActivity(intent);
	}
}
