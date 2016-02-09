package com.mcc.currency;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.util.ByteArrayBuffer;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Website;
import android.util.Log;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		final Intent startInputActivity = new Intent("com.mcc.currency.INPUT");	
Thread timer = new Thread(){
			
			public void run(){				
				try{
					String websiteText = requestPost("http://zapp.mcc.com.bd/dev/currency_converter/currency.php");

					/*String[]ar = websiteText.split("\\s+");
					int len = ar.length;
					for(int I=0;I<len; I++)
					{
						System.out.println(I+"MMMMMM  "+ar[I]);
						
					}*/
					startInputActivity.putExtra("putWebsiteText", websiteText);
					sleep(4000);					
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{		
					
					startActivity(startInputActivity);
				}
			}
		};
		timer.start();
				
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}	
	
	public String requestPost(String url){
		//url = URLEncoder.encode(url);
		String myString = null;
		    	InputStream myInputStream =null;
		    	StringBuilder sb = new StringBuilder();
		    	sb.append("");
		    	URL url2;
		    	try {
		    	url2 = new URL(url);
		    	HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
		    	conn.setDoOutput(true);
		    	conn.setRequestMethod("POST");
		    	OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    	// this is were we're adding post data to the request
		    	wr.write(sb.toString());
		    	wr.flush();
		    	myInputStream = conn.getInputStream();
		    	BufferedInputStream bis = new BufferedInputStream(myInputStream);	        	
		        	ByteArrayBuffer baf = new ByteArrayBuffer(100);
		        	int current = 0;
		        	while((current = bis.read()) != -1){ 
		                baf.append((byte)current); 
		           }         	
		        	myString = new String(baf.toByteArray());
		        	System.out.println(">>>>>>>>>>>>>>>   "+myString);
		    	wr.close();
		    	conn.disconnect();
		    	} catch (Exception e) {
		            //handle the exception !
		    	System.out.println("tap  "+e.toString());
		    	myString = "Error: "+e.getMessage();
		    	
		    	}
		    	return myString;	
		    }

	
	
}
