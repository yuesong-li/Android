package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LightOutInterface extends Activity {
	private Button lighOutonButton;
	private Button lightOutoffButton;
	private Button lightOutbackButton;
    static  ImageView lightOutIV;

	LoginInterface li = new LoginInterface();
	Connection con = Connection.getConnection();
	boolean LoopStatus = true;

	static boolean LIGHTOUT=false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lightout);
		lightOutIV = (ImageView) findViewById(R.id.lightOut);
		lighOutonButton = (Button) this.findViewById(R.id.lightOutyesbutton);
		lightOutoffButton = (Button) this.findViewById(R.id.lightOutnobutton);
		lightOutbackButton = (Button) this.findViewById(R.id.lightOutbackbutton);
		LightOutcheck();
		LIGHTOUT=true;
	}

	public static void LightOutcheck() {
		//String [] Lightint=LoginInterface.initsmarthouse[1].split(":");
		if(Connection.initStates.contains("lightOut:on"))
		{
			lightOutIV.setBackgroundResource(R.drawable.lighton);
		}else{
			lightOutIV.setBackgroundResource(R.drawable.lightoff);
		}
	}

	public void lightOutbuttonOnClicked(View view) {		
		con.setResult("lightOut:on");
		Connection.initStates = con.getResult();
		if (Connection.initStates.contains("lightOut:on")) {
			lightOutIV.setBackgroundResource(R.drawable.lighton);
		} else {

		}
		
		con.UpdateForDeviceImages();
		lighOutonButton.setVisibility(view.INVISIBLE);
		lightOutoffButton.setVisibility(view.VISIBLE);

	}

	public void lightOutbuttonOffClicked(View view) {
		con.setResult("lightOut:off");
		Connection.initStates = con.getResult();
		if (Connection.initStates.contains("lightOut:off")) {
			lightOutIV.setBackgroundResource(R.drawable.lightoff);
		} else {

		}
		con.UpdateForDeviceImages();
		lighOutonButton.setVisibility(view.VISIBLE);
		lightOutoffButton.setVisibility(view.INVISIBLE);

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
