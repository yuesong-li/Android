package com.example.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.os.AsyncTask;
import android.util.Log;

public class MyService extends AsyncTask<Void, Void, String> {

	private static Socket ssocket = null;
	private BufferedReader br = null;
	
	// private String receivedUpdate = "";
	private final String TAG = "MYSERVICE";
	private Connection conn;

	public MyService() {
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
		listenForUpdate();
		return null;
	}

	public void listenForUpdate() {
		while (true) {
			try {
				Log.i(TAG, "im in listen for update!");
				String msg = br.readLine();
				handleUpdate(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void handleUpdate(String update) {
		Log.i(TAG, "Received Update: " + update);
		conn.initStates = update;	
		//conn.UpdateForDeviceImages();
	}
	
	
}