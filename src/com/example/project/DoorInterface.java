package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
		DOOR=true;

	}

	public static void Doorcheck() {
		localStatus = Connection.initStates;
		if (Connection.initStates.contains("door:unlocked")) {
			DoorIV.setBackgroundResource(R.drawable.dooropen);
			DoorStatus=true;
		} else {
			DoorIV.setBackgroundResource(R.drawable.doorclose);
			DoorStatus=false;
		}

	}

	public void DoorbuttonClicked(View view) {
		
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
