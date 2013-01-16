package com.example.project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
/*
****************************************************************************************************************************************
* Authors : Ding junkai, Gao Fang, Chen anxiao

* Class   : splash

* Class functionality : implements the method to show a animation before the main interface appears,makes application performance more professional and comfortable.
*****************************************************************************************************************************************
*/
public class splash extends Activity {
	private ProgressBar rectangleProgressBar;
	protected static final int STOP = 0x10000;
	protected static final int NEXT = 0x10001;
	private int iCount = 0;
	ImageView imageView; 
	int image_alpha = 255; 
	boolean isrung = false; 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		isrung = true; 
		imageView = (ImageView) this.findViewById(R.id.imageView1); 
		imageView.setImageResource(R.drawable.house); 
		imageView.setAlpha(image_alpha); 
		mHandler.postDelayed(new splashhandler(), 5000);
		rectangleProgressBar = (ProgressBar)findViewById(R.id.rectangleProgressBar);  
		rectangleProgressBar.setIndeterminate(false);  
		rectangleProgressBar.setVisibility(View.VISIBLE);  
		rectangleProgressBar.setMax(100);  
        rectangleProgressBar.setProgress(0); 
           Thread mThread = new Thread(new Runnable() {
        	public void run() { 
        		while (isrung) { 
        		for(int i=0 ; i < 5; i++){  
                      try{  
                          iCount = (i + 1) * 20;  
                          Thread.sleep(1000);
                          updateAlpha(); 
                          if(i == 4){  
                              Message msg = new Message();  
                              msg.what = STOP;  
                              mHandler.sendMessage(msg);  
                              break;  
                          }else{  
                              Message msg = new Message();  
                              msg.what = NEXT;  
                              mHandler.sendMessage(msg);  
                          }  
                      }catch (Exception e) {  
                          e.printStackTrace();  
                      }  
                  }  
        		}   
              }  
          });  
          mThread.start(); 	
	}	
	protected void updateAlpha() {
		if (image_alpha - 50 >= 0) { 
			image_alpha -= 50; 
			} 
		else { 
			image_alpha = 0; 
			isrung = false; 
			}
	}
	private Handler mHandler = new Handler(){  
        public void handleMessage(Message msg){  
            switch (msg.what) {  
            case STOP:  
                rectangleProgressBar.setVisibility(View.GONE);    
                Thread.currentThread().interrupt();  
                break;  
            case NEXT:  
                if(!Thread.currentThread().isInterrupted()){  
                    rectangleProgressBar.setProgress(iCount); 
                    super.handleMessage(msg); 
                    imageView.setAlpha(image_alpha); 
                    imageView.invalidate(); 
                }  
                break;  
            }  
        }  
    };  
	class splashhandler implements Runnable {
		public void run() {            			
			startActivity(new Intent(getApplication(), MainActivity.class));
			splash.this.finish();
		}
	}

}