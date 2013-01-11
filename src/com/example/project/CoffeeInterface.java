package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CoffeeInterface extends Activity {
	private Button CoffeebackButton;
	static Button CoffeeIV;
	private static String localStatus;
	Connection con = Connection.getConnection();
	static boolean COFFEE=false;
	boolean LoopStatus=true;
	static boolean CoffeeStatus =false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coffee);
		CoffeeIV = (Button) findViewById(R.id.SwitchCoffeebutton);
		CoffeebackButton = (Button) this.findViewById(R.id.Coffeebackbutton);

		Coffeecheck();
		COFFEE=true;
	}
	public static void Coffeecheck(){		
		//String [] coffeeint=LoginInterface.initsmarthouse[6].split(":");
		localStatus = Connection.initStates;
		if(Connection.initStates.contains("coffee:on"))
		{
			CoffeeIV.setBackgroundResource(R.drawable.coffeeon);
			CoffeeStatus=true;
		}else{
			CoffeeIV.setBackgroundResource(R.drawable.coffeeoff);
			CoffeeStatus=false;
		}

	}
	public void CoffeebuttonClicked(View view){

		if(CoffeeStatus==false)
		{
		con.setResult("coffee:on");
		String temp = Connection.initStates;
		while (localStatus.equals(temp)) {
			temp = Connection.initStates;
		}
		if (Connection.initStates.contains("coffee:on")) {
			CoffeeIV.setBackgroundResource(R.drawable.coffeeon);
			CoffeeStatus=true;
		} else {

		}
		con.UpdateForDeviceImages();
		
		
		}else{
			
			con.setResult("coffee:off");
			String temp = Connection.initStates;
			while (localStatus.equals(temp)) {
				temp = Connection.initStates;
			}
			if (Connection.initStates.contains("coffee:off")) {
				CoffeeIV.setBackgroundResource(R.drawable.coffeeoff);
				CoffeeStatus=false;
			} else {

			}
			con.UpdateForDeviceImages();
			
		}

	}
	
	public void CoffeebuttonBackClicked(View view){
		Intent intent = new Intent();
		intent.setClass(CoffeeInterface.this, MainActivity.class);
		CoffeeInterface.this.startActivity(intent);
	}

}


