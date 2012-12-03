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

public class FanInterface extends Activity {
	private Button FanonButton;
	private Button FanoffButton;
	private Button FanbackButton;
	static ImageView FanIV;	
	boolean LoopStatus=true;
	
	Connection con = Connection.getConnection();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fan);
		FanIV = (ImageView) findViewById(R.id.Fan);
		FanonButton = (Button) this.findViewById(R.id.Fanyesbutton);
		FanoffButton = (Button) this.findViewById(R.id.Fannobutton);
		FanbackButton = (Button) this.findViewById(R.id.Fanbackbutton);
		//check();   
	   
	}
	public void check(){
		
		boolean ok = false;
		if (ok == false) {
			String response;
			try {
				LoginInterface login = new LoginInterface();
				response=LoginInterface.initsmarthouse[2];
				Log.i("DDDDDDDDDDDDDDDd", response+" the  array is -----------------------------2");
				if (response.equals("fan:on")) {
					FanIV.setBackgroundResource(R.drawable.fanopen);
				} else {
					FanIV.setBackgroundResource(R.drawable.fanclose);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			ok = true;
		}

	}
	
	public void FanbuttonOnClicked(View view){
		con.setResult("fan:on");
		con.getResult();
		
		FanoffButton.setVisibility(view.VISIBLE);
		FanonButton.setVisibility(view.INVISIBLE);
	}
	
	public void FanbuttonOffClicked(View view){
		con.setResult("fan:off");
		con.getResult();
		

		FanoffButton.setVisibility(view.INVISIBLE);
		FanonButton.setVisibility(view.VISIBLE);
		
	}
	
	public void FanbuttonBackClicked(View view){
		Intent intent = new Intent();
		intent.setClass(FanInterface.this, MainActivity.class);
		FanInterface.this.startActivity(intent);
	}
}
