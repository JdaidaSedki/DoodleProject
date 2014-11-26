package com.example.doodletunisie;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SingleMenuItemActivity  extends Activity {
	
	// JSON node keys
	private static final String TAG_NAME = "titre";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        String name = in.getStringExtra(TAG_NAME);
        
        // Displaying all values on the screen
        final EditText title = (EditText) findViewById(R.id.editText1);
        final EditText pseudo = (EditText) findViewById(R.id.email_label);
        Button inviteButton = (Button) findViewById(R.id.mobile_label);
        
        title.setText(name);
        
        inviteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(!pseudo.getText().toString().equals(""))
					invite(pseudo.getText().toString(), title.getText().toString());
			}
		});
       
    }
	protected void invite(String text, String title) {
		InviteTask inviteTask = new InviteTask();
		inviteTask.execute(text, title);
	}
	
	private class InviteTask extends AsyncTask <String, Void, String> {
		
		protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
	       InputStream in = entity.getContent();
	         StringBuffer out = new StringBuffer();
	         int n = 1;
	         while (n>0) {
	             byte[] b = new byte[4096];
	             n =  in.read(b);
	             if (n>0) out.append(new String(b, 0, n));
	         }
	         return out.toString();
	    }
		
		@Override
		protected String doInBackground(String... params) {
			 HttpClient httpClient = new DefaultHttpClient();
			 HttpContext localContext = new BasicHttpContext();
             HttpGet httpGet = new HttpGet("http://10.0.2.2:28250/thisisdoodle/webresources/entities.evenement/inviter/"+params[0]+"/"+params[1]);
             String text = "";
             try {
                   HttpResponse response = httpClient.execute(httpGet, localContext);
                   HttpEntity entity = response.getEntity();
                   text = getASCIIContentFromEntity(entity);
                   Log.i("", text);
//                   JSONObject jsonResponse;
//                 //  JSONObject responseJSON = response.toString();
                  
                   
                   
                   try {
                	   JSONObject jsonObject = new JSONObject(text);
                	   String responseString = jsonObject.getString("status");
                	   if(responseString.equals("added")){
                		   Log.i("resp", "added");
                	   }else if(responseString.equals("utilisateur non trouvé")){
                		   Log.i("resp", "utilisateur non trouvé");
                	   }
				} catch (Exception e) {
					Log.i("resp", "deja ajouté");
				}
////                   JSONArray jsonArray = jsonResponse.getJSONArray("participant");
               
                   
             } catch (Exception e) {
            	 return e.getLocalizedMessage();
             }
             return text;
             
		}	
		
		protected void onPostExecute(String results) {
			

		}
    }
}
