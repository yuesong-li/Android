package com.example.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import android.util.Log;
/*
**************************************************************************************************************************************************************************
* Authors : Ding junkai, Gao Fang, Chen anxiao

* Class   : Connection

* Class functionality : adds methods to establish the connection and update all device's statuses,implements methods for sending message to server and responses to server. 
***************************************************************************************************************************************************************************
*/
public class Connection {
	public String msg = null;
	public static String initStates;
	static Socket socket = null;
	static BufferedReader in = null;
	static PrintWriter out = null;
	static String serverAddr = "169.254.220.37";//   Server part's IP Address.
	static int port = 8888;                    // Server part's port number.
	private static Connection connection = null;
	private final String TAG = "CONNECTION";
	public static Connection getConnection() { // establish the connection, if the connection exists,the connection will be synchronized  
		if (connection == null) {
			synchronized (Connection.class) 
			{
				if (connection == null) {
					connection = new Connection();
					try {
						socket = new Socket(serverAddr, port);
						in = new BufferedReader(new InputStreamReader(
								socket.getInputStream()));
						out = new PrintWriter(new OutputStreamWriter(
								socket.getOutputStream()));
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return connection;
	}
	public void setResult(String str) {
		try {
			if (str != null) {
				getOutputContent(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getResult() {
		try {
			getInputContent();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}
	private void getInputContent() throws IOException {
		if (connection == null) {
			getConnection();
			msg = in.readLine();
		} else {
			msg = in.readLine();
		}
	}
	private void getOutputContent(String str) throws IOException {
		if (connection == null) {
			getConnection();
			out.println(str);
			out.flush();
		} else {
			out.println(str);
			out.flush();
		}
	}
	public String ListenForUpdate() {
		String msgFromServer = "";
		if (connection == null) {
			getConnection();
			try {
				while (true) {
					msgFromServer = in.readLine();
				}
			} catch (IOException ioe) {
				System.out.println("Failed in reading updates!");
				Log.i(TAG, ioe.getMessage());
			}
		} else {
		}
		return msgFromServer;
	}

	public static void closeSocket() throws Exception {
        in.close();
        out.close();
        socket.close();
        socket=null;
        connection=null;
	}

	public void UpdateForDeviceImages() {
		if (BathInterface.BATH == true) {
			BathInterface.Bathcheck();
		}
		if (CoffeeInterface.COFFEE == true) {
			CoffeeInterface.Coffeecheck();
		}
		if (DoorInterface.DOOR == true) {
			DoorInterface.Doorcheck();
		}
		if (FanInterface.FAN == true) {
			FanInterface.Fancheck();
		}
		if (HeatingInterface.HEATING == true) {
			HeatingInterface.Heatingcheck();
		}
		if (LoftHeatingInterface.LoftHEATING == true) {
			LoftHeatingInterface.LoftHeatingcheck();
		}
		if (LightInInterface.LIGHTIN == true) {
			LightInInterface.LightIncheck();
		}
		if (LightOutInterface.LIGHTOUT == true) {
			LightOutInterface.LightOutcheck();
		}
		if (StoveInterface.STOVE == true) {
			StoveInterface.Stovecheck();
		}
		if (MediaInterface.MEDIA == true) {
			MediaInterface.meidaIncheck();
		}
		MainActivity.tempcheck();
	}
}
