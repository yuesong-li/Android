package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginInterface extends Activity {

	// public static String initStauts = null;
	// public static String[] initsmarthouse = new String[8];
	private Button LoginButton = null;
	private Button BackButton = null;

	private EditText UserNameField = null;
	private EditText PassWordField = null;

	public String UserName = "";
	public String PassWord = "";

	
	// Connection con = Connection.getConnection();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		this.LoginButton = (Button) this.findViewById(R.id.Login_button);
		this.BackButton = (Button) this.findViewById(R.id.Login_Backbutton);

		this.UserNameField = (EditText) this
				.findViewById(R.id.Login_UserNameet);
		this.PassWordField = (EditText) this
				.findViewById(R.id.Login_PassWordet);

		this.LoginButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			
				
				if (!UserNameField.getText().toString().equals("")
						&& !PassWordField.getText().toString().equals("")) {
					Connection conn = Connection.getConnection();
					// sending username:password to server for authentication
					conn.setResult(UserNameField.getText() + ":"
							+ PassWordField.getText());
					String result = conn.getResult();
					Log.i("TAG", "Result from login : " + result);
					
					String[] splits = result.split(":");
					
					if (splits[1].equals("fail")) {
						Intent intent = new Intent();
						intent.setClass(LoginInterface.this, MainActivity.class);
						LoginInterface.this.startActivity(intent);
					} else {
						if (splits[1].equals("high")) {
							MainActivity.accessLevel = "high";
						} else if (splits.equals("low")) {
							MainActivity.accessLevel = "low";
						} else {
							MainActivity.accessLevel = "error";
						}
						MainActivity.admission = true;
						Connection.initStates = conn.getResult();
						Log.i("TAG", "States : " + Connection.initStates);
						
						Intent intent = new Intent();
						intent.setClass(LoginInterface.this, MainActivity.class);
						LoginInterface.this.startActivity(intent);
						
						MyService myservice = new MyService();
						myservice.execute();
					}
				} else {
					// Toast toast = Toast.makeText(getApplicationContext(),
					// "Error!Please try again..", Toast.LENGTH_LONG);
					Toast toast = Toast.makeText(getApplicationContext(),
							"Please re-enter your username or password",
							Toast.LENGTH_LONG);
					ImageView img = new ImageView(getApplicationContext());
					img.setImageResource(R.drawable.errortip);
					View toastview = toast.getView();
					LinearLayout linear = new LinearLayout(
							getApplicationContext());
					linear.setOrientation(LinearLayout.HORIZONTAL);
					linear.addView(img);
					linear.addView(toastview);
					toast.setView(linear);
					toast.show();
				}
			}
		});

		this.BackButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
		
				Intent intent = new Intent();
				intent.setClass(LoginInterface.this, MainActivity.class);
				LoginInterface.this.startActivity(intent);
			}
		});
	}

}
