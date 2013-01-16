package com.example.project;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.app.Dialog;
/*
****************************************************************************************************************************************
* Authors : Ding junkai, Gao Fang, Chen anxiao

* Class   : LightInInterface

* Class functionality : Displays all the segments of LightIn's device, adds methods for updating device's image and goes back to main interface.
*****************************************************************************************************************************************
*/
public class LightInInterface extends Activity {
	private Button backButton;
	static Button lightInIV;
	private static String localStatus;
	LoginInterface li = new LoginInterface();
	Connection con = Connection.getConnection();
    static boolean lightInStatus =false;
	boolean LoopStatus = true;
	static boolean LIGHTIN = false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.light);
		lightInIV = (Button) findViewById(R.id.SwitchlightInButton);
		backButton = (Button) this.findViewById(R.id.lightbackbutton);
		LightIncheck();
		LIGHTIN = true;                      // change the boolean value to mark if  it has been initialized
	}
	public static void LightIncheck() {             // For initialization of LightIn'device and can be used to update device's image.
		localStatus = Connection.initStates;
		if (Connection.initStates.contains("lightIn:on")) {
			lightInIV.setBackgroundResource(R.drawable.lighton);
			lightInStatus=true;			
		} else {
			lightInIV.setBackgroundResource(R.drawable.lightoff);
			lightInStatus=false;
		}
	}
	public void buttonlightInClicked(View view) {            // Method to response to user's action 
		if(lightInStatus==false)
		{
		con.setResult("lightIn:on");
		String temp = Connection.initStates;
		while (localStatus.equals(temp)) {
			temp = Connection.initStates;
		}
		if (Connection.initStates.contains("lightIn:on")) {
			lightInIV.setBackgroundResource(R.drawable.lighton);
			lightInStatus=true;
		} else {
		}
		con.UpdateForDeviceImages();		
		}else{		
			con.setResult("lightIn:off");
			String temp = Connection.initStates;
			while (localStatus.equals(temp)) {
				temp = Connection.initStates;
			}
			if (Connection.initStates.contains("lightIn:off")) {
				lightInIV.setBackgroundResource(R.drawable.lightoff);
				lightInStatus=false;
			} else {
			}
			con.UpdateForDeviceImages();
		}
	}
	public void buttonBackClicked(View view) {
		Intent intent = new Intent();
		intent.setClass(LightInInterface.this, MainActivity.class);
		LightInInterface.this.startActivity(intent);
	}

}
