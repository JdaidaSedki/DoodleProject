package com.example.doodletunisie;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class delet_event  extends Activity {
	
	// JSON node keys
	private static final String TAG_NAME = "titre";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_event);
        Button deco=(Button)findViewById(R.id.button1);
        deco.setOnClickListener(new OnClickListener() {
       	
       	@Override
       	public void onClick(View v) {
       		 Intent i = new Intent(getApplicationContext(), authentificationora.class);
       			startActivity(i);
       		// TODO Auto-generated method stub
       		
       	}
       });
        // getting intent data
//        Intent in = getIntent();
//        
//        // Get JSON values from previous intent
//        String name = in.getStringExtra(TAG_NAME);
        
        // Displaying all values on the screen
        final EditText title = (EditText) findViewById(R.id.editText1);
      
        Button deletButton = (Button) findViewById(R.id.button3);
        
      //  title.setText(name);
        
        deletButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(title.getText().toString()!=""){
					delete(title.getText().toString());
				}else{
					Toast.makeText(delet_event.this, "vous remplir entrer le champ",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
       
    }
	protected void delete(String titl) {
		deleteTask deletTask = new deleteTask();
		deletTask.execute(titl);
	}
	
	private class deleteTask extends AsyncTask <String, Void, String> {
		private ProgressDialog dialog = 
	            new ProgressDialog(delet_event.this);
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
		protected void onPreExecute() {
            // TODO i18n
            dialog.setMessage("Please wait..");
            dialog.show();
        }
		
		@Override
		protected String doInBackground(String... params) {
			 HttpClient httpClient = new DefaultHttpClient();
			 HttpContext localContext = new BasicHttpContext();
             HttpDelete httpdelet = new HttpDelete("http://10.0.2.2:28250/thisisdoodle/webresources/entities.evenement/"+params[0]);
             String text = "";
             try {
                   HttpResponse response = httpClient.execute(httpdelet, localContext);
                   Toast.makeText(delet_event.this, "delete suscessufl",
							Toast.LENGTH_SHORT).show();
                   HttpEntity entity = response.getEntity();
                  text = getASCIIContentFromEntity(entity);
                   Log.i("", text);
                
 
                   
             } catch (Exception e) {
            	 return e.getLocalizedMessage();
             }
             return text;
             
		}	
		
		protected void onPostExecute(String results) {
			

		}
    }
}
