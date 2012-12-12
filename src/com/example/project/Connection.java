package com.example.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
	public String msg = null;
	public static String initStates;
	Socket socket = null;

	private static Connection connection = new Connection();

	public Connection() {
		this.socket = getSocket();
	}

	public static Connection getConnection() {
		if (connection == null) {
			synchronized (Connection.class) {
				if (connection == null) {
					connection = new Connection();
				}
			}

		}
		return connection;
	}

	public void setResult(String str) {
		try {
			if (str != null) {
				// if(true){
				// getInputContent();
				// }
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

	public Socket getSocket() {
		Socket socket = null;
		try {
			socket = new Socket("194.47.46.135", 8888);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return socket;
	}

	private void getInputContent() throws IOException {
		if (socket == null) {
			socket = getSocket();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			msg = in.readLine();
		} else {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			msg = in.readLine();
		}
	}

	private void getOutputContent(String str) throws IOException {
		if (socket == null) {
			Socket socket = getSocket();
			PrintWriter out = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			out.println(str);
			out.flush();
		} else {
			PrintWriter out = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			out.println(str);
			out.flush();
		}
	}

	public String ListenForUpdate() throws Exception {
		String message = "lightIn:off";
		if (socket.getInputStream().available() > 0) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			message = in.readLine();
			String[] updatearr = new String[2];
			updatearr = message.split(":");
			// if (updatearr[0].equals("lightIn")) {
			// LoginInterface.initsmarthouse[0] = message;
			// return message;
			// } else if (updatearr[0].equals("lighOut")) {
			// LoginInterface.initsmarthouse[1] = message;
			// return message;
			// } else if (updatearr[0].equals("fan")) {
			// LoginInterface.initsmarthouse[2] = message;
			// return message;
			// } else if (updatearr[0].equals("heating")) {
			// LoginInterface.initsmarthouse[3] = message;
			// return message;
			// } else if (updatearr[0].equals("door")) {
			// LoginInterface.initsmarthouse[4] = message;
			// return message;
			// } else if (updatearr[0].equals("stove")) {
			// LoginInterface.initsmarthouse[5] = message;
			// return message;
			// } else if (updatearr[0].equals("coffee")) {
			// LoginInterface.initsmarthouse[6] = message;
			// return message;
			// } else if (updatearr[0].equals("bath")) {
			// LoginInterface.initsmarthouse[7] = message;
			// return message;
			// }
		}
		return message;
	}

	public void closeSocket() throws Exception {
		socket.close();
	}
	// String msg0[]=new String[2];
	// String msg1[]=new String[2];
	// String msg2[]=new String[2];
	// String msg3[]=new String[2];
	// String msg4[]=new String[2];
	// String msg5[]=new String[2];
	// String msg6[]=new String[2];
	// String msg7[]=new String[2];
	// if(LoginInterface.initsmarthouse[0] != null)
	// {
	// msg0=LoginInterface.initsmarthouse[0].split(":");
	// if(msg0[1].equals("on"))
	// {
	// LightInterface.lightInIV.setBackgroundResource(R.drawable.lighton);
	// }else{
	// LightInterface.lightInIV.setBackgroundResource(R.drawable.lightoff);
	// }
	// }
	// if(LoginInterface.initsmarthouse[1] != null)
	// {
	// msg1=LoginInterface.initsmarthouse[1].split(":");
	// if(msg1[1].equals("on"))
	// {
	// LightOutInterface.lightOutIV.setBackgroundResource(R.drawable.lighton);
	// }else{
	// LightOutInterface.lightOutIV.setBackgroundResource(R.drawable.lightoff);
	// }
	// }
	// if(LoginInterface.initsmarthouse[2] != null)
	// {
	// msg2=LoginInterface.initsmarthouse[2].split(":");
	// if(msg2[1].equals("on"))
	// {
	// FanInterface.FanIV.setBackgroundResource(R.drawable.fanok);
	// }else{
	// FanInterface.FanIV.setBackgroundResource(R.drawable.fanno);
	// }
	// }
	// if(LoginInterface.initsmarthouse[3] != null)
	// {
	// msg3=LoginInterface.initsmarthouse[3].split(":");
	// if(msg3[1].equals("on"))
	// {
	// HeatingInterface.HeatingIV.setBackgroundResource(R.drawable.heating);
	// }else{
	// HeatingInterface.HeatingIV.setBackgroundResource(R.drawable.noheating);
	// }
	// }
	// if(LoginInterface.initsmarthouse[4] != null)
	// {
	// msg4=LoginInterface.initsmarthouse[4].split(":");
	// if(msg4[1].equals("on"))
	// {
	// DoorInterface.DoorIV.setBackgroundResource(R.drawable.dooropen);
	// }else{
	// DoorInterface.DoorIV.setBackgroundResource(R.drawable.doorno);
	// }
	// }
	// if(LoginInterface.initsmarthouse[5] != null)
	// {
	// msg5=LoginInterface.initsmarthouse[5].split(":");
	// if(msg5[1].equals("on"))
	// {
	// StoveInterface.StoveIV.setBackgroundResource(R.drawable.stoveopen);
	// }else{
	// StoveInterface.StoveIV.setBackgroundResource(R.drawable.stoveclose);
	// }
	// }
	// if(LoginInterface.initsmarthouse[6] != null)
	// {
	// msg6=LoginInterface.initsmarthouse[6].split(":");
	// if(msg6[1].equals("on"))
	// {
	// CoffeeInterface.CoffeeIV.setBackgroundResource(R.drawable.coffee);
	// }else{
	// CoffeeInterface.CoffeeIV.setBackgroundResource(R.drawable.nocoffee);
	// }
	// }
	// if(LoginInterface.initsmarthouse[7] != null)
	// {
	// msg7=LoginInterface.initsmarthouse[7].split(":");
	// if(msg7[1].equals("on"))
	// {
	// BathInterface.BathIV.setBackgroundResource(R.drawable.bathok);
	// }else{
	// BathInterface.BathIV.setBackgroundResource(R.drawable.bathno);
	// }
	// }
}
