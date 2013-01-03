package com.example.project;

import java.io.BufferedReader;

import com.example.project.R.color;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private RadioButton lightButton = null;
	private RadioButton coffeeButton = null;
	private RadioButton MediaButton = null;
	private RadioButton lightOutButton = null;
	private RadioButton HeatingButton = null;
	private RadioButton LoftHeatingButton = null;
	private RadioButton FanButton = null;
	private RadioButton DoorButton = null;
	private RadioButton BathButton = null;
	public Button loginButton = null;

	public static TextView RoomTemptv = null;
	public static TextView LoftTemptv = null;
    public static TextView RoomTempValue=null;
    public static TextView LoftTempValue=null;
    
	public static String accessLevel = "";
	public static boolean admission = false;
	public static BufferedReader in = null;
	private ImageView image;
	AnimationDrawable animationDrawable;
	Connection con = null;
	public static boolean loginaccess=false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.lightOutButton = (RadioButton) this
				.findViewById(R.id.lightOutmenu);
		this.lightButton = (RadioButton) this.findViewById(R.id.btn_0);
		this.FanButton = (RadioButton) this.findViewById(R.id.Fanmenu);
		this.HeatingButton = (RadioButton) this.findViewById(R.id.Heatingmenu);
		this.LoftHeatingButton = (RadioButton) this.findViewById(R.id.LoftHeating);
		this.coffeeButton = (RadioButton) this.findViewById(R.id.btn_1);
		this.MediaButton = (RadioButton) this.findViewById(R.id.btn_2);
		this.loginButton = (Button) this.findViewById(R.id.Main_logInButton);
		this.DoorButton = (RadioButton) this.findViewById(R.id.Doormenu);
		
		this.BathButton = (RadioButton) this.findViewById(R.id.Bathmenu);
		this.RoomTemptv = (TextView) this.findViewById(R.id.roomtemptv);
		this.LoftTemptv = (TextView) this.findViewById(R.id.lofttemptv);
		this.RoomTempValue=(TextView)this.findViewById(R.id.roomtempttvv);
		this.LoftTempValue=(TextView)this.findViewById(R.id.lofttemptttvv);
		tempcheck();
		image = (ImageView) findViewById(R.id.menuview);
		image.setBackgroundResource(R.drawable.animation);
		animationDrawable = (AnimationDrawable) image.getBackground();
		image.post(new Runnable() {
			public void run() {
				animationDrawable.start();
			}
		});

		if (admission == false) {
			lightButton.setEnabled(false);
			coffeeButton.setEnabled(false);
			MediaButton.setEnabled(false);
			FanButton.setEnabled(false);
			lightOutButton.setEnabled(false);
			LoftHeatingButton.setEnabled(false);
			HeatingButton.setEnabled(false);
			DoorButton.setEnabled(false);			
			BathButton.setEnabled(false);
			loginButton.setText("Log In");

		} else {

			if (accessLevel.equals("high")) {
				lightButton.setEnabled(true);
				coffeeButton.setEnabled(true);
				MediaButton.setEnabled(true);
				lightOutButton.setEnabled(true);
				FanButton.setEnabled(true);
				HeatingButton.setEnabled(true);
				LoftHeatingButton.setEnabled(true);
				DoorButton.setEnabled(true);
				BathButton.setEnabled(true);
				loginButton.setText("Log Out");
			} else if (accessLevel.equals("low")) {
				lightButton.setEnabled(true);
				MediaButton.setEnabled(true);
				lightOutButton.setEnabled(true);
				FanButton.setEnabled(true);
				BathButton.setEnabled(true);
				DoorButton.setEnabled(true);
				loginButton.setText("Log Out");

				// disable these interfaces for low-level user
				coffeeButton.setEnabled(false);
				HeatingButton.setEnabled(false);
				LoftHeatingButton.setEnabled(false);

			} else {
				admission = false;
			}

			image.setBackgroundResource(R.drawable.animation);
			animationDrawable = (AnimationDrawable) image.getBackground();
			image.post(new Runnable() {
				public void run() {
					animationDrawable.start();
				}
			});
			if(loginaccess==false)
			{
			Toast toast = Toast.makeText(getApplicationContext(),
					"You can control the house!", Toast.LENGTH_SHORT);
			ImageView img = new ImageView(getApplicationContext());
			img.setImageResource(R.drawable.userok);
			View toastview = toast.getView();
			LinearLayout linear = new LinearLayout(getApplicationContext());
			linear.setOrientation(LinearLayout.HORIZONTAL);
			linear.addView(img);
			linear.addView(toastview);
			toast.setView(linear);
			toast.show();
			loginaccess=true;
			}

		}
		this.loginButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (loginButton.getText().toString().equals("Log In")) {
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, LoginInterface.class);
					MainActivity.this.startActivity(intent);

				} else if (loginButton.getText().toString().equals("Log Out")) {
					image = (ImageView) findViewById(R.id.menuview);
					image.setBackgroundResource(R.drawable.animation);
					animationDrawable = (AnimationDrawable) image
							.getBackground();
					image.post(new Runnable() {
						public void run() {
							animationDrawable.start();
						}
					});
					try {
						  MyService ms=LoginInterface.arr.get(0);
						 // ms.cancel(true);
						  LoginInterface.arr.clear();
						  con.closeSocket();
						  Log.i("AAAAAAAA", "Has been logout");
						 // con.closeSocket();
					} catch (Exception e) {
						e.printStackTrace();
					}
					admission = false;
					lightButton.setEnabled(false);
					coffeeButton.setEnabled(false);
					MediaButton.setEnabled(false);
					FanButton.setEnabled(false);
					lightOutButton.setEnabled(false);
					HeatingButton.setEnabled(false);
					DoorButton.setEnabled(false);
					BathButton.setEnabled(false);
					loginButton.setText("Log In");
				}
			}

		});

		lightButton.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					lightButton.setBackgroundResource(R.drawable.radiopress);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					lightButton.setBackgroundResource(R.drawable.radio);
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, LightInInterface.class);
					MainActivity.this.startActivity(intent);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_MOVE) {

					return true;
				}
				return false;
			}
		});

		lightOutButton.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					lightOutButton.setBackgroundResource(R.drawable.radiopress);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					lightOutButton.setBackgroundResource(R.drawable.radio);
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, LightOutInterface.class);
					MainActivity.this.startActivity(intent);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_MOVE) {

					return true;
				}
				return false;
			}
		});
		FanButton.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					FanButton.setBackgroundResource(R.drawable.radiopress);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					FanButton.setBackgroundResource(R.drawable.radio);
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, FanInterface.class);
					MainActivity.this.startActivity(intent);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_MOVE) {

					return true;
				}
				return false;
			}
		});

		HeatingButton.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					HeatingButton.setBackgroundResource(R.drawable.radiopress);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					HeatingButton.setBackgroundResource(R.drawable.radio);
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, HeatingInterface.class);
					MainActivity.this.startActivity(intent);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_MOVE) {

					return true;
				}
				return false;
			}
		});
		LoftHeatingButton.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					LoftHeatingButton.setBackgroundResource(R.drawable.radiopress);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					LoftHeatingButton.setBackgroundResource(R.drawable.radio);
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, LoftHeatingInterface.class);
					MainActivity.this.startActivity(intent);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_MOVE) {

					return true;
				}
				return false;
			}
		});

		BathButton.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					BathButton.setBackgroundResource(R.drawable.radiopress);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					BathButton.setBackgroundResource(R.drawable.radio);
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, BathInterface.class);
					MainActivity.this.startActivity(intent);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_MOVE) {

					return true;
				}
				return false;
			}
		});

		DoorButton.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					DoorButton.setBackgroundResource(R.drawable.radiopress);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					DoorButton.setBackgroundResource(R.drawable.radio);
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, DoorInterface.class);
					MainActivity.this.startActivity(intent);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_MOVE) {

					return true;
				}
				return false;
			}
		});
		this.coffeeButton.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_DOWN) {

					coffeeButton.setBackgroundResource(R.drawable.radiopress);

					return true;
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					coffeeButton.setBackgroundResource(R.drawable.radio);
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, CoffeeInterface.class);
					MainActivity.this.startActivity(intent);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_MOVE) {

					return true;
				}
				return false;
			}
		});
		this.MediaButton.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					MediaButton.setBackgroundResource(R.drawable.radiopress);

					return true;
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					MediaButton.setBackgroundResource(R.drawable.radio);
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, MediaInterface.class);
					MainActivity.this.startActivity(intent);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_MOVE) {

					return true;
				}

				return false;
			}

		});

	}

	public static void tempcheck() {

		 if(admission==true)
		 {
		 String status=Connection.initStates;
		 String devicestatus[]=status.split(",");
		 String tempRoom=devicestatus[5];
		 String temploft=devicestatus[6];
		 String Roomvalue[]=tempRoom.split(":");
		 String loftvalue[]=temploft.split(":");
		 RoomTempValue.setText(Roomvalue[1]+"℃");
		 LoftTempValue.setText(loftvalue[1]+"℃");
		 }		
	}
}
