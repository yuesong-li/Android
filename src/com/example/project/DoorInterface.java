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

* Class   : DoorInterface

* Class functionality : Displays all the segments of Door's device, adds methods for updating device's image and goes back to main interface.
*****************************************************************************************************************************************
*/
public class DoorInterface extends Activity {
	private Button DoorbackButton;
	static Button DoorIV;
	private static String localStatus;
	boolean LoopStatus = true;
	Connection con = Connection.getConnection();
    static boolean DOOR =false;
    static boolean DoorStatus=false; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.door);
		DoorIV = (Button) findViewById(R.id.SwitchDoorButton);
		DoorbackButton = (Button) this.findViewById(R.id.Doorbackbutton);
		Doorcheck();                 
		DOOR=true;                  // change the boolean value to mark if  it has been initialized
	}
	public static void Doorcheck() {          // For initialization of Door'device and can be used to update device's image.
		localStatus = Connection.initStates;                    
		if (Connection.initStates.contains("door:unlocked")) {
			DoorIV.setBackgroundResource(R.drawable.dooropen);
			DoorStatus=true;
		} else {
			DoorIV.setBackgroundResource(R.drawable.doorclose);
			DoorStatus=false;
		}
	}
	public void DoorbuttonClicked(View view) {	           // Method to response to user's action 
		if(DoorStatus==false)
		{
		con.setResult("door:unlocked");
		String temp = Connection.initStates;
		while (localStatus.equals(temp)) {
			temp = Connection.initStates;
		}
		if (Connection.initStates.contains("door:unlocked")) {

			DoorIV.setBackgroundResource(R.drawable.doorclose);
			DoorStatus=true;
		} else {
		}
		con.UpdateForDeviceImages();
		}else if(DoorStatus==true)
		{
			con.setResult("door:locked");
			String temp = Connection.initStates;
			while (localStatus.equals(temp)) {
				temp = Connection.initStates;
			}
			if (Connection.initStates.contains("door:locked")) {

				DoorIV.setBackgroundResource(R.drawable.doorclose);
				DoorStatus=false;
			} else {
			}
			con.UpdateForDeviceImages();
		}
	}

	public void DoorbuttonBackClicked(View view) {
		Intent intent = new Intent();
		intent.setClass(DoorInterface.this, MainActivity.class);
		DoorInterface.this.startActivity(intent);
	}
}
