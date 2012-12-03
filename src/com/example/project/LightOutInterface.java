package com.example.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class LightOutInterface extends Activity {
	private Button lighOutonButton;
	private Button lightOutoffButton;
	private Button lightOutbackButton;
	static ImageView lightOutIV;

	LoginInterface li = new LoginInterface();
	Connection con = Connection.getConnection();
	boolean LoopStatus = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lightout);
		lightOutIV = (ImageView) findViewById(R.id.lightOut);
		lighOutonButton = (Button) this.findViewById(R.id.lightOutyesbutton);
		lightOutoffButton = (Button) this.findViewById(R.id.lightOutnobutton);
		lightOutbackButton = (Button) this.findViewById(R.id.lightOutbackbutton);
		//check();
	}

	public void check() {
		boolean ok = false;
		if (ok == false) {
			String response;
			try {
//				LoginInterface login = new LoginInterface();
				response = LoginInterface.initsmarthouse[1];
				Log.i("DDDDDDDDDDDDDDDd", response	+ " the  array is -----------------------------1");
				if (response.equals("lightIn:on")) {
					lightOutIV.setBackgroundResource(R.drawable.lighton);
				} else {
					lightOutIV.setBackgroundResource(R.drawable.lightoff);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			ok = true;
		}
	}

	public void lightOutbuttonOnClicked(View view) {
		//lighOutonButton.setBackgroundResource(R.drawable.fanno);		
		con.setResult("lightOut:on");
		String msg = con.getResult();
//		if (msg.equals("lightOut:on")) {
//			lightOutIV.setBackgroundResource(R.drawable.lighton);
//		} else {
//
//		}
//		Log.i("lightOut", msg);

		lighOutonButton.setVisibility(view.INVISIBLE);
		lightOutoffButton.setVisibility(view.VISIBLE);

	}

	public void lightOutbuttonOffClicked(View view) {
//		lightOutoffButton.setBackgroundResource(R.drawable.fanok);
		con.setResult("lightOut:off");
		String msg = con.getResult();
//		if (msg.equals("lightOut:off")) {
//			lightOutIV.setBackgroundResource(R.drawable.lightoff);
//		} else {
//
//		}
//		Log.i("lightOut", msg);

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
