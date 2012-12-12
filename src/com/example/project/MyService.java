package com.example.project;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
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
			updateMsgFromServer = conn.getResult();
			Log.i(TAG, "Received Update: " + updateMsgFromServer);
		}
	}

}
