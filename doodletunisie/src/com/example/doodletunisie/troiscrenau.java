package com.example.doodletunisie;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONObject;

//import com.androidhive.dashboard.SingleMenuItemActivity.InviteTask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class troiscrenau extends Activity {
	private static final String TAG_TITRE = "titre";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trois_crenaux);
		
//	        
	        // Displaying all values on the screen
	        final EditText debu1 = (EditText) findViewById(R.id.editText1);
	        final EditText fin1 = (EditText) findViewById(R.id.editText2);
	        final EditText debu2 = (EditText) findViewById(R.id.editText3);
	        final EditText fin2 = (EditText) findViewById(R.id.editText4);
	        final EditText debu3=(EditText)findViewById(R.id.editText5);
	        final EditText fin3=(EditText)findViewById(R.id.editText6);
	        
	        Button crenauButton = (Button) findViewById(R.id.button3);
	        Intent ine = getIntent();
	       final  String title = ine.getStringExtra(TAG_TITRE);
	       Toast.makeText(troiscrenau.this, title, 
		           Toast.LENGTH_SHORT).show();
	        crenauButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				
			        
			        
					//String dat=date.getText().toString();
					///recupere date 
			        
					String deb1=debu1.getText().toString();
			        String fi1=fin1.getText().toString();
			        String deb2=debu2.getText().toString();
			        String fi2=fin2.getText().toString();
			        String deb3=debu3.getText().toString();
			        String fi3=fin3.getText().toString();
//			        Toast.makeText(deuxcreanux.this, lie, 
//			        Toast.LENGTH_SHORT).show();
			        //String titr=titre.getText().toString();
					 HttpPost request = new HttpPost("http://10.0.2.2:28250/thisisdoodle/webresources/entities.crenaux/creer/"+title);
		             request.setHeader("Accept", "application/json");
		             request.setHeader("Content-type", "application/json");
		          
		             String not = new String(" ");
		             try {
		             	 JSONObject json=new JSONObject();
		            	 json.put("debut", deb1);
		            	 json.put("fin", fi1);
		            	 JSONObject json1=new JSONObject();
		            	 json1.put("debut", deb2);
		            	 json1.put("fin", fi2);
		            	 JSONObject json2=new JSONObject();
		            	 json2.put("debut", deb3);
		            	 json2.put("fin", fi3);
		            	 JSONArray jsonarray=new JSONArray();
		            	 jsonarray.put(json);
		            	 jsonarray.put(json1);
		            	 jsonarray.put(json2);
		            	 
		            	    StringEntity entity = new StringEntity(jsonarray.toString());
		            	    Toast.makeText(troiscrenau.this, jsonarray.toString() + "\n",
		    						Toast.LENGTH_SHORT).show();
		                    //Toast.makeText(this, o.toString() + "\n", Toast.LENGTH_LONG).show() ;
		                 
		                    request.setEntity(entity);
		                 
		                    // Send request to WCF service
		                    DefaultHttpClient httpClient = new DefaultHttpClient();
		                    HttpResponse response = httpClient.execute(request);
		                    // Log.d("WebInvoke", "Saving : " + response.getStatusLine().getStatusCode());
		                    Toast.makeText(troiscrenau.this, response.getStatusLine().getStatusCode() + "\n",
		    						Toast.LENGTH_SHORT).show();
		                  
		                    //Toast.makeText(this, response.getStatusLine().getStatusCode() + "\n", Toast.LENGTH_LONG).show() ;
		                 
		                    }catch (Exception e) {
		                    not = "NOT ";
		                    }
		             Toast.makeText(troiscrenau.this, not + " ajout crenaux successful ! " + "\n",
	 						Toast.LENGTH_SHORT).show();
		                  //  Toast.makeText(this, not + " OK ! " + "\n", Toast.LENGTH_LONG).show() ;
		                 
		                 

		    }
					
					
				
			});
	    }
	}
