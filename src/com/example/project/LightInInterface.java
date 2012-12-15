package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LightInInterface extends Activity {
	private Button onButton;
	private Button offButton;
	private Button backButton;
	static ImageView lightInIV;

	LoginInterface li = new LoginInterface();
	Connection con = Connection.getConnection();

	boolean LoopStatus = true;
	static boolean LIGHTIN=false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.light);
		lightInIV = (ImageView) findViewById(R.id.light);
		onButton = (Button) this.findViewById(R.id.yesbutton);
		offButton = (Button) this.findViewById(R.id.nobutton);
		backButton = (Button) this.findViewById(R.id.lightbackbutton);
		LightIncheck();
		LIGHTIN=true;
	}

	public static void LightIncheck() {
		// String [] Lightint=LoginInterface.initsmarthouse[0].split(":");
		
		if (Connection.initStates.contains("lightIn:on")) {
			lightInIV.setBackgroundResource(R.drawable.lighton);
		} else {
			lightInIV.setBackgroundResource(R.drawable.lightoff);
		}
		
	}

	public void buttonOnClicked(View view) {

		con.setResult("lightIn:on");
		Connection.initStates = con.getResult();
		Log.i("AAAAAAAAAAAAAAAAAA",Connection.initStates);
		
		if (Connection.initStates.contains("lightIn:on")) {
			lightInIV.setBackgroundResource(R.drawable.lighton);
		} else {

		}
		
		
		con.UpdateForDeviceImages();
		onButton.setVisibility(view.INVISIBLE);
		offButton.setVisibility(view.VISIBLE);

	}

	public void buttonOffClicked(View view) {
		// offButton.setBackgroundResource(R.drawable.fanok);
		con.setResult("lightIn:off");
		Connection.initStates = con.getResult();
		if (Connection.initStates.contains("lightIn:off")) {
			lightInIV.setBackgroundResource(R.drawable.lightoff);
		} else {

		}
		con.UpdateForDeviceImages();
		onButton.setVisibility(view.VISIBLE);
		offButton.setVisibility(view.INVISIBLE);
	}

	public void buttonBackClicked(View view) {
		Intent intent = new Intent();
		intent.setClass(LightInInterface.this, MainActivity.class);
		LightInInterface.this.startActivity(intent);

	}

}
