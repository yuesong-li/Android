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

public class HeatingInterface extends Activity {
	private Button HeatingonButton;
	private Button HeatingoffButton;
	private Button HeatingbackButton;
	static ImageView HeatingIV;	
	boolean LoopStatus=true;
	Connection con = Connection.getConnection();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.heating);
		HeatingIV = (ImageView) findViewById(R.id.Heating);
		HeatingonButton = (Button) this.findViewById(R.id.Heatingyesbutton);
		HeatingoffButton = (Button) this.findViewById(R.id.Heatingnobutton);
		HeatingbackButton = (Button) this.findViewById(R.id.Heatingbackbutton);

		//check();
		
	}
	public void check(){		
		boolean ok = false;
		if (ok == false) {
			String response;
			try {
				LoginInterface login = new LoginInterface();
				response=LoginInterface.initsmarthouse[3];
				Log.i("DDDDDDDDDDDDDDDd", response+" the  array is -----------------------------3");
				if (response.equals("heating:on")) {
					HeatingIV.setBackgroundResource(R.drawable.heating);
				} else {
					HeatingIV.setBackgroundResource(R.drawable.noheating);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			ok = true;
		}

	}
	
	public void HeatingbuttonOnClicked(View view){
		con.setResult("heating:on");
		con.getResult();
		

		
		HeatingonButton.setVisibility(view.INVISIBLE);
		HeatingoffButton .setVisibility(view.VISIBLE);
		
	}
	
	public void HeatingbuttonOffClicked(View view){
		con.setResult("heating:off");
		con.getResult();
		

		HeatingoffButton.setVisibility(view.INVISIBLE);
		HeatingonButton.setVisibility(view.VISIBLE);	
	}
	
	public void HeatingbuttonBackClicked(View view){
		Intent intent = new Intent();
		intent.setClass(HeatingInterface.this, MainActivity.class);
		HeatingInterface.this.startActivity(intent);
		
	}
}
