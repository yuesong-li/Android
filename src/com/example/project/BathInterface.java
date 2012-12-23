package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class BathInterface extends Activity {

	private Button BathbackButton;
	static Button BathIV;
	private static String localStatus;
	boolean LoopStatus = true;

	Connection con = Connection.getConnection();
    static boolean BATH =false;
    static boolean BathStatus=false;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bath);
		BathIV = (Button) findViewById(R.id.SwitchBathbutton);
		BathbackButton = (Button) this.findViewById(R.id.Bathbackbutton);
		Bathcheck();
		BATH=true;

	}

	public static void Bathcheck() {
		localStatus = Connection.initStates;
		if (Connection.initStates.contains("bath:on")) {
			BathIV.setBackgroundResource(R.drawable.bathok);
			BathStatus=true;
		} else {
			BathIV.setBackgroundResource(R.drawable.bathno);
			BathStatus=false;
		}

	}

	public void BathbuttonClicked(View view) {
		
		if(BathStatus==false)
		{
		con.setResult("bath:on");
		String temp = Connection.initStates;
		while (localStatus.equals(temp)) {
			temp = Connection.initStates;
		}
		if (Connection.initStates.contains("bath:on")) {

			BathIV.setBackgroundResource(R.drawable.bathok);
			BathStatus=true;

		} else {

		}
		con.UpdateForDeviceImages();
		}else if(BathStatus==true)
		{
			con.setResult("bath:off");
			String temp = Connection.initStates;
			while (localStatus.equals(temp)) {
				temp = Connection.initStates;
			}
			if (Connection.initStates.contains("bath:off")) {

				BathIV.setBackgroundResource(R.drawable.bathno);
				BathStatus=false;

			} else {

			}
			con.UpdateForDeviceImages();
		}
	}

	public void BathbuttonBackClicked(View view) {
		Intent intent = new Intent();
		intent.setClass(BathInterface.this, MainActivity.class);
		BathInterface.this.startActivity(intent);
	}
}
