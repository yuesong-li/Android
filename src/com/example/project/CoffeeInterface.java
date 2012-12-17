package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CoffeeInterface extends Activity {
	private Button CoffeeonButton;
	private Button CoffeeoffButton;
	private Button CoffeebackButton;
	static ImageView CoffeeIV;
	private static String localStatus;
	Connection con = Connection.getConnection();
	static boolean COFFEE=false;
	boolean LoopStatus=true;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coffee);
		CoffeeIV = (ImageView) findViewById(R.id.Coffee);
		CoffeeonButton = (Button) this.findViewById(R.id.Coffeeyesbutton);
		CoffeeoffButton = (Button) this.findViewById(R.id.Coffeenobutton);
		CoffeebackButton = (Button) this.findViewById(R.id.Coffeebackbutton);

		Coffeecheck();
		COFFEE=true;
	}
	public static void Coffeecheck(){		
		//String [] coffeeint=LoginInterface.initsmarthouse[6].split(":");
		localStatus = Connection.initStates;
		if(Connection.initStates.contains("coffee:on"))
		{
			CoffeeIV.setBackgroundResource(R.drawable.coffeeyes);
		}else{
			CoffeeIV.setBackgroundResource(R.drawable.coffeeno);
		}

	}
	
	public void CoffeebuttonOnClicked(View view){
		con.setResult("coffee:on");
		//Connection.initStates = con.getResult();
		String temp = Connection.initStates;
		while (localStatus.equals(temp)) {
			temp = Connection.initStates;
		}
		if (Connection.initStates.contains("coffee:on")) {

			CoffeeIV.setBackgroundResource(R.drawable.coffeeyes);

		} else {

		}
		
		con.UpdateForDeviceImages();
		CoffeeonButton.setVisibility(view.INVISIBLE);
		CoffeeoffButton.setVisibility(view.VISIBLE);
		
	}
	
	public void CoffeebuttonOffClicked(View view){
		con.setResult("coffee:off");
		//Connection.initStates = con.getResult();
		String temp = Connection.initStates;
		while (localStatus.equals(temp)) {
			temp = Connection.initStates;
		}
		if (Connection.initStates.contains("coffee:off")) {

			CoffeeIV.setBackgroundResource(R.drawable.coffeeno);

		} else {

		}
		
		con.UpdateForDeviceImages();
		CoffeeoffButton.setVisibility(view.INVISIBLE);
		CoffeeonButton.setVisibility(view.VISIBLE);
	}
	
	public void CoffeebuttonBackClicked(View view){
		Intent intent = new Intent();
		intent.setClass(CoffeeInterface.this, MainActivity.class);
		CoffeeInterface.this.startActivity(intent);
	}

}


