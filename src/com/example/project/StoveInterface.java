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

public class StoveInterface extends Activity {
	private Button StoveonButton;
	private Button StoveoffButton;
	private Button StovebackButton;
	static ImageView StoveIV;	
	boolean LoopStatus=true;
	
	Connection con = Connection.getConnection();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stove);
		StoveIV = (ImageView) findViewById(R.id.Stove);
		StoveonButton = (Button) this.findViewById(R.id.Stoveyesbutton);
		StoveoffButton = (Button) this.findViewById(R.id.Stovenobutton);
		StovebackButton = (Button) this.findViewById(R.id.Stovebackbutton);
		//check();
	}
	public void check(){		
		boolean ok = false;
		if (ok == false) {
			String response;
			try {
				LoginInterface login = new LoginInterface();
				response=LoginInterface.initsmarthouse[5];
				Log.i("DDDDDDDDDDDDDDDd", response+" the  array is -----------------------------5");
				if (response.equals("stove:on")) {
					StoveIV.setBackgroundResource(R.drawable.stoveopen);
				} else {
					StoveIV.setBackgroundResource(R.drawable.stoveclose);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			ok = true;
		}

	}
	
	public void StovebuttonOnClicked(View view){
		con.setResult("stove:on");
		String msg = con.getResult();
//		if (msg.equals("stove:on")) {
//			StoveIV.setBackgroundResource(R.drawable.stoveopen);
//		} else {
//
//		}
//		Log.i("stove", msg);
		StoveonButton.setVisibility(view.INVISIBLE);
		StoveoffButton.setVisibility(view.VISIBLE);
	}
	
	public void StovebuttonOffClicked(View view){
		con.setResult("stove:off");
		String msg = con.getResult();
//		if (msg.equals("stove:off")) {
//			StoveIV.setBackgroundResource(R.drawable.stoveclose);
//		} else {
//			//
//		}
//		Log.i("stove", msg);

		StoveoffButton.setVisibility(view.INVISIBLE);
		StoveonButton.setVisibility(view.VISIBLE);
	}
	
	public void StovebuttonBackClicked(View view){
		Intent intent = new Intent();
		intent.setClass(StoveInterface.this, MainActivity.class);
		StoveInterface.this.startActivity(intent);
	}
}
