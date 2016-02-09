package com.mcc.currency;

import java.util.zip.Inflater;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class Input extends Activity {

	Button submit, help, about;
	Spinner cur;
	Intent strtOp;
	EditText convAmmount;
	TextView exchngeType;
	int f = 99;
	String buyOrSell = "Sell";
	ImageView menuInput;
	RelativeLayout menu;
	int checkMenuStatus = 0;
	Intent strtHelp, strtAbout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.input);

		final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
		final ImageView swBtn = (ImageView) findViewById(R.id.imgSwitchButton);
		exchngeType = (TextView) findViewById(R.id.exchangeType);
		menuInput = (ImageView) findViewById(R.id.imgMenuButton);
		menu = (RelativeLayout) findViewById(R.id.popUpMenuLayout);
		about = (Button) findViewById(R.id.btnAbout);
		help = (Button) findViewById(R.id.btnHelp);
		
		
		//exchngeType.setVisibility(View.VISIBLE);
		menuInput.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(checkMenuStatus == 0)
				{
					menu.setVisibility(View.VISIBLE);
					checkMenuStatus = 1;
				}else{
					menu.setVisibility(View.GONE);
					checkMenuStatus = 0;
				}
				
			}
		});
		
		strtAbout = new Intent("com.mcc.currency.ABOUT");
		strtHelp = new Intent("com.mcc.currency.HELP");
		
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
		
		

		swBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				// TODO Auto-generated method stub
				if (f == 0) {
					swBtn.setImageResource(R.drawable.buttonbuyselected);
					f = 1;
					buyOrSell = "Buy";
					exchngeType.setText("Exchange Type: " + buyOrSell);
				} else {
					swBtn.setImageResource(R.drawable.buttonsellselected);
					f = 0;
					buyOrSell = "Sell";
					exchngeType.setText("Exchange Type: " + buyOrSell);
				}

			}
		});

		OnClickListener listnr = new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {
					int flag = 0;

					strtOp = new Intent("com.mcc.currency.OUTPUT");

					convAmmount = (EditText) findViewById(R.id.edtAmmount);

					if (buyOrSell.equals("Buy")) {
						flag = 0;
					} else {
						flag = 1;
					}

					// Send whether to buy or sell
					strtOp.putExtra("putBuyOrSell", buyOrSell);

					cur = (Spinner) findViewById(R.id.spnrCurrency);
					String currency = cur.getSelectedItem().toString();
					String currencyDef = null;

					if (currency.equals("USD"))
						currencyDef = currency + " United States Dollar";
					if (currency.equals("JPY"))
						currencyDef = currency + " Japanese Yen";
					if (currency.equals("GBP"))
						currencyDef = currency + " Great British Pound";
					if (currency.equals("AUD"))
						currencyDef = currency + " Australian dollar";
					if (currency.equals("EUR"))
						currencyDef = currency + " Euro";
					if (currency.equals("CAD"))
						currencyDef = currency + " Canadian Dollar";
					if (currency.equals("SEK"))
						currencyDef = currency + " Swedish Krona";
					if (currency.equals("SGD"))
						currencyDef = currency + " Singaporean Dollar";
					if (currency.equals("CNH"))
						currencyDef = currency + " Chinese Offshore Yuan";
					if (currency.equals("INR"))
						currencyDef = currency + " Indian Rupee";

					String ammount = convAmmount.getText().toString();

					

						if (!ammount.equals("") && !ammount.equals(null)) {

							strtOp.putExtra("putCurrencyDef", currencyDef);

							String webContent = getIntent().getExtras()
									.getString("putWebsiteText");

							/******************** get currency and separate from webContent *********/
							String curMarketPrice = null;
							try {
								String[] ar = webContent.split("\\s+");
								int len = ar.length;
								for (int I = 0; I < len; I++) {
									if (currency.equals(ar[I])) {
										if (flag == 0) {
											curMarketPrice = ar[I + 1];
											break;
										} else {
											curMarketPrice = ar[I + 2];
											break;
										}
									}
								}

							} catch (Exception e) {
								e.printStackTrace();
							}

							strtOp.putExtra("putCurrentMarketPrice",
									curMarketPrice);

							/***********************************************************************/

							strtOp.putExtra("putAmmount", ammount);
							strtOp.putExtra("CurrencyContent", webContent);							
							startActivity(strtOp);
							
						}

						else {
							dlgAlert.setMessage("Check input and submit again!");
							dlgAlert.setTitle("Currency Converter");
							dlgAlert.setPositiveButton("OK", null);
							dlgAlert.setCancelable(true);
							dlgAlert.create().show();
						}
					
				} catch (Exception e) {					
					e.printStackTrace();
				}

			}
		};

		// Button submit = (Button) findViewById(R.id.btnSubmit);
		ImageView submit = (ImageView) findViewById(R.id.imgSubmitButton);
		submit.setOnClickListener(listnr);

	}
}
