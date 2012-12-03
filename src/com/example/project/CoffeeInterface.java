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

public class CoffeeInterface extends Activity {
	private Button CoffeeonButton;
	private Button CoffeeoffButton;
	private Button CoffeebackButton;
	static ImageView CoffeeIV;
	
	Connection con = Connection.getConnection();
	
	boolean LoopStatus=true;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coffee);
		CoffeeIV = (ImageView) findViewById(R.id.Coffee);
		CoffeeonButton = (Button) this.findViewById(R.id.Coffeeyesbutton);
		CoffeeoffButton = (Button) this.findViewById(R.id.Coffeenobutton);
		CoffeebackButton = (Button) this.findViewById(R.id.Coffeebackbutton);

		//check();
	}
	public void check(){		
		boolean ok = false;
		if (ok == false) {
			String response;
			try {
				LoginInterface login = new LoginInterface();
				response=LoginInterface.initsmarthouse[6];
				Log.i("DDDDDDDDDDDDDDDd", response+" the  array is -----------------------------6");
				if (response.equals("coffee:on")) {
					CoffeeIV.setBackgroundResource(R.drawable.coffee);
				} else {
					CoffeeIV.setBackgroundResource(R.drawable.nocoffee);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			ok = true;
		}

	}
	
	public void CoffeebuttonOnClicked(View view){
		con.setResult("coffee:on");
		con.getResult();
		

		CoffeeonButton.setVisibility(view.INVISIBLE);
		CoffeeoffButton.setVisibility(view.VISIBLE);
		
	}
	
	public void CoffeebuttonOffClicked(View view){
		con.setResult("coffee:off");
		con.getResult();
		

		CoffeeoffButton.setVisibility(view.INVISIBLE);
		CoffeeonButton.setVisibility(view.VISIBLE);
	}
	
	public void CoffeebuttonBackClicked(View view){
		Intent intent = new Intent();
		intent.setClass(CoffeeInterface.this, MainActivity.class);
		CoffeeInterface.this.startActivity(intent);
	}

}


