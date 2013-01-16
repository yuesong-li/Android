package com.example.project;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/*
****************************************************************************************************************************************
* Authors : Ding junkai, Gao Fang, Chen anxiao

* Class   : CoffeeInterface

* Class functionality : Displays all the segments of Coffee's device, adds methods for updating device's image and goes back to main interface.
*****************************************************************************************************************************************

*/
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
		COFFEE=true;   // change the boolean value to mark if  it has been initialized
	}
	public static void Coffeecheck(){		// For initialization of Coffee'device and can be used to update device's image.
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
	public void CoffeebuttonClicked(View view){     // Method to response to user's action 
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


