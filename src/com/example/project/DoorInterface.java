package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DoorInterface extends Activity {
	private Button DooronButton;
	private Button DooroffButton;
	private Button DoorbackButton;
	static ImageView DoorIV;
	
	boolean LoopStatus=true;
	static boolean DOOR=false;
	
	Connection con = Connection.getConnection();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.door);
		DoorIV = (ImageView) findViewById(R.id.Door);
		DooronButton = (Button) this.findViewById(R.id.Dooryesbutton);
		DooroffButton = (Button) this.findViewById(R.id.Doornobutton);
		DoorbackButton = (Button) this.findViewById(R.id.Doorbackbutton);
		Doorcheck();
		DOOR=true;
	   
	}
	public static void Doorcheck(){
		//String [] Doorint=LoginInterface.initsmarthouse[4].split(":");
		if(Connection.initStates.contains("door:open"))
		{
			DoorIV.setBackgroundResource(R.drawable.dooropen);
		}else{
			DoorIV.setBackgroundResource(R.drawable.doorclose);
		}
	}
	
	public void DoorbuttonOnClicked(View view){
		
		con.setResult("door:open");
		Connection.initStates = con.getResult();
		
		if (Connection.initStates.contains("door:open")) {

			DoorIV.setBackgroundResource(R.drawable.dooropen);

		} else {

		}
		
		con.UpdateForDeviceImages();
		DooronButton.setVisibility(view.INVISIBLE);
		DooroffButton .setVisibility(view.VISIBLE);
		
	}
	
	public void DoorbuttonOffClicked(View view){
		
		con.setResult("door:close");
		Connection.initStates = con.getResult();
		
		if (Connection.initStates.contains("door:close")) {

			DoorIV.setBackgroundResource(R.drawable.doorclose);

		} else {

		}

		con.UpdateForDeviceImages();
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
