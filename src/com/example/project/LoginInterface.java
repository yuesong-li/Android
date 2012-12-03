package com.example.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginInterface extends Activity {

	public String initstauts = null;
	public static String[] initsmarthouse = new String[8];
	private Button LoginButton = null;
	private Button BackButton = null;

	private EditText UserNameField = null;
	private EditText PassWordField = null;

	public String UserName = "";
	public String PassWord = "";
	
	Connection con = Connection.getConnection();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		this.LoginButton = (Button) this.findViewById(R.id.Login_button);
		this.BackButton = (Button) this.findViewById(R.id.Login_Backbutton);

		this.UserNameField = (EditText) this.findViewById(R.id.Login_UserNameet);
		this.PassWordField = (EditText) this.findViewById(R.id.Login_PassWordet);

		this.LoginButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (UserNameField.getText().toString().equals(UserName) && PassWordField.getText().toString().equals(PassWord)) {
					MainActivity.admission = true;
//					con.setResult(null);
					Intent intent = new Intent();
					intent.setClass(LoginInterface.this, MainActivity.class);
					LoginInterface.this.startActivity(intent);

				} else {
					Toast toast = Toast.makeText(getApplicationContext(), "Error!Please try again..", Toast.LENGTH_LONG);
					ImageView img = new ImageView(getApplicationContext());
					img.setImageResource(R.drawable.errortip);
					View toastview = toast.getView();
					LinearLayout linear = new LinearLayout(getApplicationContext());
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
