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
		if(Connection.initStates.contains("coffee:on"))
		{
			CoffeeIV.setBackgroundResource(R.drawable.coffee);
		}else{
			CoffeeIV.setBackgroundResource(R.drawable.nocoffee);
		}

	}
	
	public void CoffeebuttonOnClicked(View view){
		con.setResult("coffee:on");
		Connection.initStates = con.getResult();
		if (Connection.initStates.contains("coffee:on")) {

			CoffeeIV.setBackgroundResource(R.drawable.coffee);

		} else {

		}
		
		con.UpdateForDeviceImages();
		CoffeeonButton.setVisibility(view.INVISIBLE);
		CoffeeoffButton.setVisibility(view.VISIBLE);
		
	}
	
	public void CoffeebuttonOffClicked(View view){
		con.setResult("coffee:off");
		Connection.initStates = con.getResult();
		
		if (Connection.initStates.contains("coffee:off")) {

			CoffeeIV.setBackgroundResource(R.drawable.nocoffee);

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


