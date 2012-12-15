package com.example.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	String serverAddr = "194.47.32.63";
	int port = 8888;

	private Socket socket = null;
	private BufferedReader br = null;
	// private String receivedUpdate = "";
	private final String TAG = "MYSERVICE";

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	public MyService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		init();
		Log.i(TAG, "Service Initiated");
		listenForUpdate();
	}

	public void init() {
		try {
			socket = new Socket(serverAddr, port);
			br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			Log.i(TAG,
					"Service is running on local port: "
							+ socket.getLocalPort());
		} catch (UnknownHostException uhe) {
			Log.i(TAG, "Unknown host exception!");
		} catch (IOException ioe) {
			Log.i(TAG, "Failed in creating IO!");
		}
	}

	public void listenForUpdate() {
		// Connection conn = Connection.getConnection();
		// while (true) {
		// try {
		// updateMsgFromServer = conn.getResult();
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// }
		// Toast.makeText(this, "Received Update: Hello World",
		// Toast.LENGTH_SHORT);
		try {

			String msg = br.readLine();
			handleUpdate(msg);

		} catch (IOException ioe) {
			Log.i(TAG, ioe.getMessage());
		}

	}

	public void handleUpdate(String update) {
		Log.i(TAG, "Received Update: " + update);
	}
}
