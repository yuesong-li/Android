package com.example.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.os.AsyncTask;
import android.util.Log;
/*
****************************************************************************************************************************************
* Authors : Ding junkai, Gao Fang, Chen anxiao

* Class   : MyService

* Class functionality : implements the underlying listener method to get device's information from server
*                       refresh the updating information to make the device's image same as real device's statuses. 
*****************************************************************************************************************************************
*/
public class MyService extends AsyncTask<Void, Void, String> {
	private static Socket ssocket = null;
	private BufferedReader br = null;
	private final String TAG = "MYSERVICE";
	private Connection conn;
    static boolean run =true;
	public MyService() {                   // initialization for underlying listener 
		conn = Connection.getConnection();
		ssocket = conn.socket;
		try {
			br = new BufferedReader(new InputStreamReader(
					ssocket.getInputStream()), 8192);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.i(TAG, "Service is running on local port: " + ssocket.getLocalPort()
				+ ssocket.getRemoteSocketAddress());
	}
	@Override
	protected String doInBackground(Void... params) {
		try {
			listenForUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void listenForUpdate() throws Exception {    // waiting for server response,if message has been received,it will call handleupdate method to update information.
		while (run) {
			try {
				Log.i(TAG, "im in listen for update!");
				String msg = br.readLine();
				handleUpdate(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	public void handleUpdate(String update) {
		Log.i(TAG, "Received Update: " + update);
		conn.initStates = update;	
	}
}