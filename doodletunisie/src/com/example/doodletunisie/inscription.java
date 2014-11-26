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


public class inscription  extends Activity {
	private static final String TAG_TITRE = "titre";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inscription);
      
//	        
	        // Displaying all values on the screen
	        final EditText adresse = (EditText) findViewById(R.id.adresse);
	        final EditText email = (EditText) findViewById(R.id.email);
	        final EditText pwd = (EditText) findViewById(R.id.mdp);
	        final EditText nom = (EditText) findViewById(R.id.nom);
	        final EditText prenom = (EditText) findViewById(R.id.prenom);
	        final EditText pseudo = (EditText) findViewById(R.id.pseudo);
	        Button deco=(Button)findViewById(R.id.button1);
	        deco.setOnClickListener(new OnClickListener() {
	       	
	       	@Override
	       	public void onClick(View v) {
	       		 Intent i = new Intent(getApplicationContext(), authentificationora.class);
	       			startActivity(i);
	       		// TODO Auto-generated method stub
	       		
	       	}
	       });
	        Button inscrit = (Button) findViewById(R.id.binscrit);
	        inscrit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					//String dat=date.getText().toString();
					///recupere date 
			       
					String adress=adresse.getText().toString();
			        String mail=email.getText().toString();
			        String password=pwd.getText().toString();
			        String nomp=nom.getText().toString();
			        String prenomp=prenom.getText().toString();
			        String pseudop=pseudo.getText().toString();
//			        Toast.makeText(deuxcreanux.this, lie, 
//			        Toast.LENGTH_SHORT).show();
			        //String titr=titre.getText().toString();
					 HttpPost request = new HttpPost("http://10.0.2.2:28250/thisisdoodle/webresources/entities.participant");
		             request.setHeader("Accept", "application/json");
		             request.setHeader("Content-type", "application/json");
		          
		             String not = new String(" ");
		             try {
		             // Build JSON string
		            	 //JSONStringer o=new JSONStringer().object().key("dateEvnt").value("61329999600000").key("description").value("desc").key("lieu").value("lie").key("titre").value("titr").endObject();
		            	// JSONStringer o=new JSONStringer().object().key("lieu").value(lieu).key("titre").value(titre).key("description").value(descr).key("dateEvnt").value("61329999600000").endObject();
		            	 JSONObject json=new JSONObject();
		            	 json.put("adresse", adress);
		            	 json.put("email", mail);
		            	 json.put("mdp", password);
		            	 json.put("nom", nomp);
		            	 json.put("prenom", prenomp);
		            	 json.put("pseudo", pseudop);
		            
		            	 
		            	    StringEntity entity = new StringEntity(json.toString());
		            	    Toast.makeText(inscription.this, json.toString() + "\n",
		    						Toast.LENGTH_SHORT).show();
		                    //Toast.makeText(this, o.toString() + "\n", Toast.LENGTH_LONG).show() ;
		                 
		                    request.setEntity(entity);
		                 
		                    // Send request to WCF service
		                    DefaultHttpClient httpClient = new DefaultHttpClient();
		                    HttpResponse response = httpClient.execute(request);
		                    // Log.d("WebInvoke", "Saving : " + response.getStatusLine().getStatusCode());
		                    Toast.makeText(inscription.this, response.getStatusLine().getStatusCode() + "\n",
		    						Toast.LENGTH_SHORT).show();
		                  
		                    //Toast.makeText(this, response.getStatusLine().getStatusCode() + "\n", Toast.LENGTH_LONG).show() ;
		                    Authent inscr = new Authent(getApplicationContext());
		    				inscr.add(nom.getText().toString(),prenom.getText().toString(),pseudo.getText().toString(),adresse.getText().toString(), pwd.getText().toString(),email.getText().toString());
		    				Toast.makeText(inscription.this, "ajout au base locale est fait avec succes",
		    						Toast.LENGTH_SHORT).show();
		                    }catch (Exception e) {
		                    not = "NOT ";
		                    }
		             Toast.makeText(inscription.this, not + " inscription successful ! " + "\n",
	 						Toast.LENGTH_SHORT).show();
		                  //  Toast.makeText(this, not + " OK ! " + "\n", Toast.LENGTH_LONG).show() ;
		                 
		                 

		    }
					
					
				
			});
	    }
	}
