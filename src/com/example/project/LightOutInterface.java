package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
/*
****************************************************************************************************************************************
* Authors : Ding junkai, Gao Fang, Chen anxiao

* Class   : LightOutInterface

* Class functionality : Displays all the segments of LightOut's device, adds methods for updating device's image and goes back to main interface.
*****************************************************************************************************************************************
*/
public class LightOutInterface extends Activity {
	
	private Button lightOutbackButton;
    static  Button lightOutIV;
    private static String localStatus;
	Connection con = Connection.getConnection();
	boolean LoopStatus = true;
    static boolean LightOutStatus=false;
	static boolean LIGHTOUT=false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lightout);
		lightOutIV = (Button) findViewById(R.id.Switchlightout);
		lightOutbackButton = (Button) this.findViewById(R.id.lightOutbackbutton);
		LightOutcheck();
		LIGHTOUT=true;                   // change the boolean value to mark if  it has been initialized
	}
	public static void LightOutcheck() {                        // For initialization of LightOut'device and can be used to update device's image.
		localStatus = Connection.initStates;
		if(Connection.initStates.contains("lightOut:on"))
		{
			lightOutIV.setBackgroundResource(R.drawable.outdoorlighton);
			LightOutStatus=true;
		}else{
			lightOutIV.setBackgroundResource(R.drawable.outdoorlightoff);
			LightOutStatus=false;
		}
	}
	public void lightOutbuttonClicked(View view) {		// Method to response to user's action 
		if(LightOutStatus==false)
		{
		con.setResult("lightOut:on");
		String temp = Connection.initStates;
		while (localStatus.equals(temp)) {
			temp = Connection.initStates;
		}
		if (Connection.initStates.contains("lightOut:on")) {
			lightOutIV.setBackgroundResource(R.drawable.outdoorlighton);
		} else {

		}
		con.UpdateForDeviceImages();
		}else if(LightOutStatus==true)
		{
			con.setResult("lightOut:off");
			String temp = Connection.initStates;
			while (localStatus.equals(temp)) {
				temp = Connection.initStates;
			}
			if (Connection.initStates.contains("lightOut:off")) {
				lightOutIV.setBackgroundResource(R.drawable.outdoorlightoff);
			} else {
			}
			con.UpdateForDeviceImages();
		}
	}
	public void lightOutbuttonBackClicked(View view) {
		Intent intent = new Intent();
		intent.setClass(LightOutInterface.this, MainActivity.class);
		LightOutInterface.this.startActivity(intent);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "test");
		menu.add(0, 1, 1, "Back");
		return super.onCreateOptionsMenu(menu);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			System.out.println("this is test");
			break;
		case 1:
			finish();
			break;
		default:
		}
		return super.onOptionsItemSelected(item);
	}

}
