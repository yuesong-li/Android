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

* Class   : StoveInterface

* Class functionality : Displays all the segments of Stove's device, adds methods for updating device's image and goes back to main interface.
*****************************************************************************************************************************************
*/
public class StoveInterface extends Activity {
	private Button StoveonButton;
	private Button StoveoffButton;
	private Button StovebackButton;
	static ImageView StoveIV;	
	boolean LoopStatus=true;
	Connection con = Connection.getConnection();
	static boolean STOVE=false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stove);
		StoveIV = (ImageView) findViewById(R.id.Stove);
		StoveonButton = (Button) this.findViewById(R.id.Stoveyesbutton);
		StoveoffButton = (Button) this.findViewById(R.id.Stovenobutton);
		StovebackButton = (Button) this.findViewById(R.id.Stovebackbutton);
		Stovecheck();
		STOVE=true;                  // change the boolean value to mark if  it has been initialized
	}
	public static void Stovecheck(){		 // For initialization of stove'device and can be used to update device's image.
		if(Connection.initStates.contains("stove:on"))
		{
			StoveIV.setBackgroundResource(R.drawable.stoveopen);
		}else{
			StoveIV.setBackgroundResource(R.drawable.stoveclose);
		}
	}
	public void StovebuttonOnClicked(View view){                   // Method to response to user's action 
		con.setResult("stove:on");
		Connection.initStates = con.getResult(); 
		if(Connection.initStates.contains("stove:on"))
		{
			StoveIV.setBackgroundResource(R.drawable.stoveopen);
		}else{
		}
		con.UpdateForDeviceImages();
		StoveonButton.setVisibility(view.INVISIBLE);
		StoveoffButton.setVisibility(view.VISIBLE);
	}
	
	public void StovebuttonOffClicked(View view){              // Method to response to user's action 
		con.setResult("stove:off");
		Connection.initStates = con.getResult();
        
		if(Connection.initStates.contains("stove:off"))
		{
			StoveIV.setBackgroundResource(R.drawable.stoveclose);
		}else{	
		}
		con.UpdateForDeviceImages();		
		StoveoffButton.setVisibility(view.INVISIBLE);
		StoveonButton.setVisibility(view.VISIBLE);
	}
	
	public void StovebuttonBackClicked(View view){
		Intent intent = new Intent();
		intent.setClass(StoveInterface.this, MainActivity.class);
		StoveInterface.this.startActivity(intent);
	}
}
