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

public class LightInterface extends Activity {
	private Button onButton;
	private Button offButton;
	private Button backButton;
	static ImageView lightInIV;

	LoginInterface li = new LoginInterface();
	Connection con = Connection.getConnection();

	boolean LoopStatus = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.light);
		lightInIV = (ImageView) findViewById(R.id.light);
		onButton = (Button) this.findViewById(R.id.yesbutton);
		offButton = (Button) this.findViewById(R.id.nobutton);
		backButton = (Button) this.findViewById(R.id.lightbackbutton);
		//check();
		
		
//		try {
//			String message=con.ListenForUpdate();
//			System.out.println("执行到了-----------------"+message);
//			String update[]=message.split(":");
//			if(update[1].equals("on"))
//			{
//				lightInIV.setBackgroundResource(R.drawable.lighton);
//			}else if(update[1].equals("off"))
//			{
//				lightInIV.setBackgroundResource(R.drawable.lightoff);
//			}
//				
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
		   
		
	}

	public void check() {

		boolean ok = false;
		if (ok == false) {
			String response;
			try {
//				LoginInterface login = new LoginInterface();
				response = LoginInterface.initsmarthouse[0];
				Log.i("CCCCCCCCCCCCCCCCCCCCC", response + " the  array is -----------------------------0");
				if (response.equals("lightIn:on")) {
					lightInIV.setBackgroundResource(R.drawable.lighton);
				} else {
					lightInIV.setBackgroundResource(R.drawable.lightoff);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			ok = true;
		}

	}

	public void buttonOnClicked(View view) {
		//onButton.setBackgroundResource(R.drawable.fanno);		
	
		con.setResult("lightIn:on");
		String msg = con.getResult();
//		if (msg.equals("lightIn:on")) {
//			lightInIV.setBackgroundResource(R.drawable.lighton);
//		} else {
//
//		}
//		Log.i("lightOut", msg);

		onButton.setVisibility(view.INVISIBLE);
		offButton.setVisibility(view.VISIBLE);

	}

	public void buttonOffClicked(View view) {
		//offButton.setBackgroundResource(R.drawable.fanok);
		con.setResult("lightIn:off");
		String msg = con.getResult();
//		if (msg.equals("lightIn:off")) {
//			lightInIV.setBackgroundResource(R.drawable.lightoff);
//		} else {
//
//		}
//		Log.i("lightOut", msg);

	    onButton.setVisibility(view.VISIBLE);
		offButton.setVisibility(view.INVISIBLE);
	}

	public void buttonBackClicked(View view) {
		Intent intent = new Intent();
		intent.setClass(LightInterface.this, MainActivity.class);
		LightInterface.this.startActivity(intent);

	}

}
