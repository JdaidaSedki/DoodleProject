package com.example.doodletunisie;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class ajoutcrenaux extends Activity   {
		
	 private CheckBox chkIos, chkAndroid,chek3;
	  private Button btnDisplay;
	 
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ajoutercreneau);
	 
		addListenerOnChkIos();
		addListenerOnButton();
	  }
	 
	  public void addListenerOnChkIos() {
	 
		chkIos = (CheckBox) findViewById(R.id.checkBox1);
	 
		chkIos.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
	                //is chkIos checked?
			if (((CheckBox) v).isChecked()) {
				Toast.makeText(ajoutcrenaux.this,
			 	   "Bro, try Android :)", Toast.LENGTH_LONG).show();
			}
	 
		  }
		});
	 
	  }
	 
	  public void addListenerOnButton() {
	 
		chkIos = (CheckBox) findViewById(R.id.checkBox1);
		chkAndroid = (CheckBox) findViewById(R.id.checkBox2); 
		chek3 = (CheckBox) findViewById(R.id.checkBox3);
		btnDisplay = (Button) findViewById(R.id.button2);
	 
		btnDisplay.setOnClickListener(new OnClickListener() {
	 
	          //Run when button is clicked
		  @Override
		  public void onClick(View v) {
	 if(chkIos.isChecked()){
		 Intent i = new Intent(getApplicationContext(), deuxcreanux.class);
			startActivity(i);
	 }else if(chkAndroid.isChecked()) {
		 Intent i = new Intent(getApplicationContext(), troiscrenau.class);
			startActivity(i);
		 
	 }else if(chek3.isChecked()){
		 Intent i = new Intent(getApplicationContext(), quatrecreanaux.class);
			startActivity(i);
	 }
	 else{
		 Toast.makeText(ajoutcrenaux.this, "vous devez choisir une option",
					Toast.LENGTH_LONG).show();
	 }
//			StringBuffer result = new StringBuffer();
//			result.append("IPhone check : ").append(chkIos.isChecked());
//			result.append("\nAndroid check : ").append(chkAndroid.isChecked());
//			//result.append("\nWindows Mobile check :").append(chkWindows.isChecked());
	// 
			
	 
		  }
		});
	 
	  }}