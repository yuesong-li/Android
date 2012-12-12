package com.example.project;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	String updateMsgFromServer = "";
	final String TAG = "MYSERVICE";

	public MyService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		listenForUpdate();
		return START_STICKY;
	}

	public void listenForUpdate() {
		Connection conn = Connection.getConnection();
		while (true) {
			try {
				updateMsgFromServer = conn.getResult();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			Toast.makeText(getBaseContext(), "Received Update: "
					+ updateMsgFromServer, 30);
			Log.i(TAG, "Received Update: " + updateMsgFromServer);
			// Log.i(TAG, "Error");
		}
	}
}
