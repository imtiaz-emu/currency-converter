package com.mcc.currency;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Output extends Activity{

	TextView disAskedCurrency, disCurrentMarketPrice, disCurrentPrice,bdt;
	ImageView disCurrency;
	String BuySell;
	Intent strtAbout, strtHelp;
	Button help, about;
	RelativeLayout menu;
	ImageView menuInput;
	int checkMenuStatus = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.output);
		disCurrency = (ImageView) findViewById(R.id.imgCurPriceBuyOrSell);
		disAskedCurrency = (TextView) findViewById(R.id.txtAskedCurrency);
		disCurrentMarketPrice = (TextView) findViewById(R.id.txtMarketPrice);
		disCurrentMarketPrice.setTypeface(null,Typeface.BOLD);
		disCurrentPrice = (TextView) findViewById(R.id.txtCurrentPrice);
		bdt = (TextView) findViewById(R.id.txtBDT);
		bdt.setTypeface(null,Typeface.BOLD);
		about = (Button) findViewById(R.id.btnAbout);
		help = (Button) findViewById(R.id.btnHelp);
		menu = (RelativeLayout) findViewById(R.id.popUpMenuLayout);
		menuInput = (ImageView) findViewById(R.id.imgMenuButton);
		
		
		
		BuySell = getIntent().getExtras().getString("putBuyOrSell");
		
		if(BuySell.equals("Sell"))
			disCurrency.setImageResource(R.drawable.currentpricesell);		
			
		
		String askedCur = getIntent().getExtras().getString("putAmmount");
		disAskedCurrency.setText(askedCur + "\nAsked Currency");
		disAskedCurrency.setTypeface(null,Typeface.BOLD);
		
		
		String cMarketPrice = getIntent().getExtras().getString("putCurrentMarketPrice");		
				
		double askedCurrency, currentMarketPrice, currentPriceTotal;
		
		askedCurrency = Double.parseDouble(askedCur);
		currentMarketPrice = Double.parseDouble(cMarketPrice);
		currentPriceTotal = askedCurrency*currentMarketPrice;
		disCurrentMarketPrice.setText(String.valueOf(round(currentPriceTotal, 3)));		
		disCurrentPrice.setText(round(Double.valueOf(cMarketPrice), 3)+"\nCurrent Market Price");
		disCurrentPrice.setTypeface(null,Typeface.BOLD);
		
		strtAbout = new Intent("com.mcc.currency.ABOUT");
		strtHelp = new Intent("com.mcc.currency.HELP");
		
		menuInput.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (checkMenuStatus == 0) {
					menu.setVisibility(View.VISIBLE);
					checkMenuStatus = 1;
				} else {
					menu.setVisibility(View.GONE);
					checkMenuStatus = 0;
				}

			}
		});
		
		about.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				menu.setVisibility(View.GONE);
				startActivity(strtAbout);
			}
		});
		
		help.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				menu.setVisibility(View.GONE);
				startActivity(strtHelp);
			}
		});
		
		
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}	
}
