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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class BathInterface extends Activity {
	private Button BathonButton;
	private Button BathoffButton;
	private Button BathbackButton;
	static ImageView BathIV;
	
	boolean LoopStatus=true;
	
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
	public void check(){
		
//		String [] bathint=LoginInterface.initsmarthouse[7].split(":")
//		if(bathint[1].equals("on"))
			
	}
	
	public void BathbuttonOnClicked(View view){
		con.setResult("bath:on");
        con.getResult();
//
//		if (msg.equals("bath:on")) {
//
//			BathIV.setBackgroundResource(R.drawable.bathok);
//
//		} else {
//
//		}
		//Log.i("bath", msg);

		BathonButton.setVisibility(view.INVISIBLE);
		BathoffButton.setVisibility(view.VISIBLE);
	}
	
	
	public void BathbuttonOffClicked(View view){
		con.setResult("bath:off");
        con.getResult();
//
//		if (msg.equals("bath:off")) {
//
//			BathIV.setBackgroundResource(R.drawable.bathno);
//		} else {
//			//
//		}
//		Log.i("bath", msg);
		BathoffButton.setVisibility(view.INVISIBLE);
		BathonButton.setVisibility(view.VISIBLE);
		
	}
	
	public void BathbuttonBackClicked(View view){
		Intent intent = new Intent();
		intent.setClass(BathInterface.this, MainActivity.class);
		BathInterface.this.startActivity(intent);
	}
}
