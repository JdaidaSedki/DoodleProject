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


public class EventsActivity extends Activity implements OnClickListener  {
	private static final String TAG_TITRE = "titre";
	private ImageButton ib;
	private Calendar cal;
	private int day;
	private int month;
	private int year;
	private EditText et;
	 private CheckBox chkIos, chkAndroid,chek3;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_layout);
        Button btn=(Button)findViewById(R.id.button2);
        ib = (ImageButton) findViewById(R.id.imageButton1);
		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);
		et = (EditText) findViewById(R.id.editText1);
		Button deco=(Button)findViewById(R.id.button1);
		 deco.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent i = new Intent(getApplicationContext(), authentificationora.class);
					startActivity(i);
				// TODO Auto-generated method stub
				
			}
		});
		ib.setOnClickListener(this);
		
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chkIos = (CheckBox) findViewById(R.id.checkBox1);
				chkAndroid = (CheckBox) findViewById(R.id.checkBox2); 
				chek3 = (CheckBox) findViewById(R.id.checkBox3);
				// TODO Auto-generated method stub
				EditText date=(EditText)findViewById(R.id.editText1);
		        EditText descr=(EditText)findViewById(R.id.editText2);
		        EditText lieu=(EditText)findViewById(R.id.editText3);
		        EditText titre=(EditText)findViewById(R.id.editText4);
				//String dat=date.getText().toString();
				///recupere date 
				
				String dat=date.getText().toString();
		        String desc=descr.getText().toString();
		        String lie=lieu.getText().toString();
		        Toast.makeText(EventsActivity.this, lie, 
		        Toast.LENGTH_SHORT).show();
		        String titr=titre.getText().toString();
				 HttpPost request = new HttpPost("http://10.0.2.2:28250/thisisdoodle/webresources/entities.evenement/efe");
	             request.setHeader("Accept", "application/json");
	             request.setHeader("Content-type", "application/json");
	          
	             String not = new String(" ");
	             try {
	             // Build JSON string
	            	 //JSONStringer o=new JSONStringer().object().key("dateEvnt").value("61329999600000").key("description").value("desc").key("lieu").value("lie").key("titre").value("titr").endObject();
	            	// JSONStringer o=new JSONStringer().object().key("lieu").value(lieu).key("titre").value(titre).key("description").value(descr).key("dateEvnt").value("61329999600000").endObject();
	            	 JSONObject json=new JSONObject();
	            	 json.put("dateEvnt", dat);
	            	 json.put("description", desc);
	            	 json.put("lieu", lie);
	            	 json.put("titre", titr);
	            	
	            
	            	 
	            	    StringEntity entity = new StringEntity(json.toString());
	            	    Toast.makeText(EventsActivity.this, json.toString() + "\n",
	    						Toast.LENGTH_SHORT).show();
	                    //Toast.makeText(this, o.toString() + "\n", Toast.LENGTH_LONG).show() ;
	                 
	                    request.setEntity(entity);
	                 
	                    // Send request to WCF service
	                    DefaultHttpClient httpClient = new DefaultHttpClient();
	                    HttpResponse response = httpClient.execute(request);
	                    // Log.d("WebInvoke", "Saving : " + response.getStatusLine().getStatusCode());
	                    Toast.makeText(EventsActivity.this, response.getStatusLine().getStatusCode() + "\n",
	    						Toast.LENGTH_SHORT).show();
	                   // 
                       
	                   
	    				//startActivity(ine);       

	                    //Toast.makeText(this, response.getStatusLine().getStatusCode() + "\n", Toast.LENGTH_LONG).show() ;
	                 
	                    }catch (Exception e) {
	                    not = "NOT ";
	                    }
	             Toast.makeText(EventsActivity.this, not + " ajout event successful ! " + "\n",
 						Toast.LENGTH_SHORT).show();
	                  //  Toast.makeText(this, not + " OK ! " + "\n", Toast.LENGTH_LONG).show() ;
	             if(chkIos.isChecked()){
	            	 Intent ine = new Intent(getApplicationContext(), deuxcreanux.class);
		    			
	                    ine.putExtra(TAG_TITRE, titr);
	                    //Intent in = new Intent(getApplicationContext(), ajoutcrenaux.class);
	    			
	                   // in.putExtra(TAG_TITRE, titr);
	                    startActivity(ine);
	        	 }else if(chkAndroid.isChecked()) {
	        		 Intent ine = new Intent(getApplicationContext(), troiscrenau.class);
		    			
	                    ine.putExtra(TAG_TITRE, titr);
	                    //Intent in = new Intent(getApplicationContext(), ajoutcrenaux.class);
	    			
	                   // in.putExtra(TAG_TITRE, titr);
	                    startActivity(ine);
	        		 
	        	 }else if(chek3.isChecked()){
	        		 Intent ine = new Intent(getApplicationContext(), quatrecreanaux.class);
		    			
	                    ine.putExtra(TAG_TITRE, titr);
	                    //Intent in = new Intent(getApplicationContext(), ajoutcrenaux.class);
	    			
	                   // in.putExtra(TAG_TITRE, titr);
	                    startActivity(ine);
	        	 }
	        	 else{
	        		 Toast.makeText(EventsActivity.this, "vous devez choisir une option",
	        					Toast.LENGTH_LONG).show();
	        	 }
	                 
	                 

	    }
				
				
			
		});
    }


    @Override
	public void onClick(View v) {
		showDialog(0);
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		return new DatePickerDialog(this, datePickerListener, year, month, day);
	}
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			et.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
					+ selectedYear);
		}
	};
}
