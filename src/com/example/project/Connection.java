package com.example.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.util.Log;

public class Connection {
	public String msg = null;
	public static String initStates;
	static Socket socket = null;
	static BufferedReader in = null;
	static PrintWriter out = null;
	static String serverAddr = "194.47.41.235";// "169.254.220.37";
	static int port = 8888;
	private static Connection connection = null;// new Connection();
	private final String TAG = "CONNECTION";

	// public Connection() {
	// getSocket();
	// }

	public static Connection getConnection() {
		if (connection == null) {
			synchronized (Connection.class) {
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

	public void closeSocket() throws Exception {
		socket.close();
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
		if (LightInInterface.LIGHTIN == true) {
			LightInInterface.LightIncheck();
		}
		if (LightOutInterface.LIGHTOUT == true) {
			LightOutInterface.LightOutcheck();
		}
		if (StoveInterface.STOVE == true) {
			StoveInterface.Stovecheck();
		}
		//

	}

}
