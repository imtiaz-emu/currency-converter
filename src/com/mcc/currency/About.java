package com.mcc.currency;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class About extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		TextView myName;
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		myName = (TextView) findViewById(R.id.myName);
		myName.setTypeface(null, Typeface.BOLD);
	}	
}
