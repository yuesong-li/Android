package com.example.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import android.view.View.OnTouchListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends Activity {

	private RadioButton lightButton = null;
	private RadioButton coffeeButton = null;
	private RadioButton temperatureButton = null;
	private RadioButton lightOutButton=null;
	private RadioButton HeatingButton=null;
	private RadioButton StoveButton=null;
	private RadioButton FanButton=null;
	private RadioButton DoorButton=null;
	private RadioButton BathButton=null;
	public Button loginButton=null;
	
	public static String loginInfo="";	
	public static boolean admission=false;
	public static BufferedReader in = null;
	private ImageView image;
	AnimationDrawable animationDrawable;
	
	Socket socket = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.lightOutButton = (RadioButton) this.findViewById(R.id.lightOutmenu);
		this.lightButton = (RadioButton) this.findViewById(R.id.btn_0);
		this.FanButton = (RadioButton) this.findViewById(R.id.Fanmenu);
		this.HeatingButton = (RadioButton) this.findViewById(R.id.Heatingmenu);
		this.coffeeButton = (RadioButton) this.findViewById(R.id.btn_1);
		this.temperatureButton = (RadioButton) this.findViewById(R.id.btn_2);
		this.loginButton=(Button)this.findViewById(R.id.Main_logInButton);
		this.DoorButton = (RadioButton) this.findViewById(R.id.Doormenu);
		this.StoveButton = (RadioButton) this.findViewById(R.id.Stovemenu);
		this.BathButton = (RadioButton) this.findViewById(R.id.Bathmenu);
		
		image = (ImageView) findViewById(R.id.menuview);
		image.setBackgroundResource(R.drawable.animation);
		animationDrawable = (AnimationDrawable)image.getBackground();
		image.post(new Runnable() {
		    public void run()  {
		            animationDrawable.start();
		        }
		});

		if(admission==false){			
			lightButton.setEnabled(false);
			coffeeButton.setEnabled(false);
			temperatureButton.setEnabled(false);
			FanButton.setEnabled(false);
			lightOutButton.setEnabled(false);
			HeatingButton.setEnabled(false);
			DoorButton.setEnabled(false);
			StoveButton.setEnabled(false);
			BathButton.setEnabled(false);
			loginButton.setText("Log In");
			
		}else{		
			lightButton.setEnabled(true);
			coffeeButton.setEnabled(true);
			temperatureButton.setEnabled(true);
			lightOutButton.setEnabled(true);
			FanButton.setEnabled(true);
			HeatingButton.setEnabled(true);
			DoorButton.setEnabled(true);
			StoveButton.setEnabled(true);
			BathButton.setEnabled(true);
			loginButton.setText("Log Out");			
			
			image.setBackgroundResource(R.drawable.animation);
			animationDrawable = (AnimationDrawable)image.getBackground();
			image.post(new Runnable() {
			    public void run()  {
			            animationDrawable.start();
			        }
			});
			Toast toast=Toast.makeText(getApplicationContext(), "You can control the house!", Toast.LENGTH_SHORT);
			ImageView img=new ImageView(getApplicationContext());
			img.setImageResource(R.drawable.userok);
			View toastview=toast.getView();
			LinearLayout linear=new LinearLayout(getApplicationContext());
			linear.setOrientation(LinearLayout.HORIZONTAL);
			linear.addView(img);
			linear.addView(toastview);
			toast.setView(linear); 
            toast.show(); 
			           
		}		
		this.loginButton.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
				if(loginButton.getText().toString().equals("Log In"))
				{
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, LoginInterface.class);
					MainActivity.this.startActivity(intent);
					
				}else if(loginButton.getText().toString().equals("Log Out")){					
					image = (ImageView) findViewById(R.id.menuview);
					image.setBackgroundResource(R.drawable.animation);
					animationDrawable = (AnimationDrawable)image.getBackground();
					image.post(new Runnable() {
					    public void run()  {
					            animationDrawable.start();
					        }
					});
					admission=false;
					lightButton.setEnabled(false);
					coffeeButton.setEnabled(false);
					temperatureButton.setEnabled(false);
					FanButton.setEnabled(false);
					lightOutButton.setEnabled(false);
					HeatingButton.setEnabled(false);
					DoorButton.setEnabled(false);
					StoveButton.setEnabled(false);
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
					intent.setClass(MainActivity.this, LightInterface.class);
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
		StoveButton.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					StoveButton.setBackgroundResource(R.drawable.radiopress);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					StoveButton.setBackgroundResource(R.drawable.radio);
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, StoveInterface.class);
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
		this.temperatureButton.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					temperatureButton.setBackgroundResource(R.drawable.radiopress);

					return true;
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					temperatureButton.setBackgroundResource(R.drawable.radio);
					Intent intent = new Intent();
					intent.setClass(MainActivity.this,	TemperatureInterface.class);
					MainActivity.this.startActivity(intent);
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_MOVE) {

					return true;
				}

				return false;
			}

		});


	}

	

//	public Socket getConnection() {
//		
//		if(socket==null)
//		{
//		try {
//			socket = new Socket("194.47.40.66", 8000);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return socket;
//		}else{
//			return socket;
//		}
//	}
//
//	public BufferedReader getContent() throws IOException{
//		Socket socket = getConnection();
//		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//		return in;
//	}
//	
//	public PrintWriter sendContent() throws IOException{
//		Socket socket = getConnection();
//		PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
//		return out;
//	}

}
