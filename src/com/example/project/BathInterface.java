package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class BathInterface extends Activity {
	private Button BathonButton;
	private Button BathoffButton;
	private Button BathbackButton;
	static ImageView BathIV;

	boolean LoopStatus = true;

	Connection con = Connection.getConnection();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bath);
		BathIV = (ImageView) findViewById(R.id.Bath);
		BathonButton = (Button) this.findViewById(R.id.Bathyesbutton);
		BathoffButton = (Button) this.findViewById(R.id.Bathnobutton);
		BathbackButton = (Button) this.findViewById(R.id.Bathbackbutton);
		check();

	}

	public void check() {
		//String[] bathStatus = LoginInterface.initsmarthouse[7].trim().split(":");
		if (Connection.initStates.contains("bath:on")) {
			BathIV.setBackgroundResource(R.drawable.bathok);
		} else {
			BathIV.setBackgroundResource(R.drawable.bathno);
		}

	}

	public void BathbuttonOnClicked(View view) {
		con.setResult("bath:on");
		Connection.initStates = con.getResult();
		//
		if (Connection.initStates.contains("bath:on")) {

			BathIV.setBackgroundResource(R.drawable.bathok);

		} else {

		}

		BathonButton.setVisibility(view.INVISIBLE);
		BathoffButton.setVisibility(view.VISIBLE);
	}

	public void BathbuttonOffClicked(View view) {
		con.setResult("bath:off");
		Connection.initStates = con.getResult();
		if (Connection.initStates.contains("bath:off")) {

			BathIV.setBackgroundResource(R.drawable.bathno);

		} else {

		}
		BathoffButton.setVisibility(View.INVISIBLE);
		BathonButton.setVisibility(View.VISIBLE);

	}

	public void BathbuttonBackClicked(View view) {
		Intent intent = new Intent();
		intent.setClass(BathInterface.this, MainActivity.class);
		BathInterface.this.startActivity(intent);
	}
}
