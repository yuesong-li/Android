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

public class DoorInterface extends Activity {
	private Button DooronButton;
	private Button DooroffButton;
	private Button DoorbackButton;
	static ImageView DoorIV;
	
	boolean LoopStatus=true;
	
	Connection con = Connection.getConnection();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.door);
		DoorIV = (ImageView) findViewById(R.id.Door);
		DooronButton = (Button) this.findViewById(R.id.Dooryesbutton);
		DooroffButton = (Button) this.findViewById(R.id.Doornobutton);
		DoorbackButton = (Button) this.findViewById(R.id.Doorbackbutton);

		//check();
	   
	}
	public void check(){		
		boolean ok = false;
		if (ok == false) {
			String response;
			try {
//				LoginInterface login = new LoginInterface();
				response=LoginInterface.initsmarthouse[4];
				Log.i("DDDDDDDDDDDDDDDd", response+" the  array is -----------------------------4");
				if (response.equals("door:on")) {
					DoorIV.setBackgroundResource(R.drawable.dooropen);
				} else {
					DoorIV.setBackgroundResource(R.drawable.doorclose);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			ok = true;
		}

	}
	
	public void DoorbuttonOnClicked(View view){
		
		con.setResult("door:on");
		String msg = con.getResult();
		

		DooronButton.setVisibility(view.INVISIBLE);
		DooroffButton .setVisibility(view.VISIBLE);
		
	}
	
	public void DoorbuttonOffClicked(View view){
		
		con.setResult("door:off");
		String msg = con.getResult();

		
		DooroffButton.setVisibility(view.INVISIBLE);
		DooronButton.setVisibility(view.VISIBLE);		
	}
	
	public void DoorbuttonBackClicked(View view){
		Intent intent = new Intent();
		intent.setClass(DoorInterface.this, MainActivity.class);
		DoorInterface.this.startActivity(intent);

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
