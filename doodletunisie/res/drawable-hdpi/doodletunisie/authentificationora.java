package com.example.doodletunisie;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONObject;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class authentificationora extends Activity implements OnClickListener  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.authen);
		
		 findViewById(R.id.button1).setOnClickListener(this);
		 

		 Button inscrit= (Button)findViewById(R.id.inscrit);
			inscrit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(), inscription.class);
					startActivity(i);
					
				}
			});
    }
    public  boolean checkInternetConnection() {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isFound;
		if (conMgr.getActiveNetworkInfo() != null
                && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
			Toast.makeText(this, "permession internet ok ", 50000).show();
            //Log.d("Internet Connection  Present","");
            isFound=true;
        } else {
        	
        	
        	Toast.makeText(this, "Connection Not Present", 50000).show();
           // Log.d("Internet Connection Not Present","");
            isFound= false;
        	
        }
        return isFound;
    }
    
	@Override
	public void onClick(View arg0) {
		Button b = (Button)findViewById(R.id.button1);
		b.setClickable(false);
		boolean test=checkInternetConnection();
		if(test==false){
			
			// Si c'est ok 
			Intent ho = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(ho);
			Toast.makeText(this, "No connection", 50000).show();
			
			}
		else{
			Toast.makeText(this, "ok", 50000).show();
			//Ici votre code :) 
			new LongRunningGetIO().execute();
			
		}
		
		
		
	}
	
	
	private class LongRunningGetIO extends AsyncTask <Void, Void, String> {
		private ProgressDialog dialog = 
	            new ProgressDialog(authentificationora.this);
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
		protected void onPreExecute() {
            // TODO i18n
            dialog.setMessage("Please wait..");
            dialog.show();
        }
		
		@Override
		protected String doInBackground(Void... params) {
			
			EditText login=(EditText)findViewById(R.id.editText1);
	        EditText pwd=(EditText)findViewById(R.id.editText2);
			String log=login.getText().toString();
	        String pd=pwd.getText().toString();
			 HttpClient httpClient = new DefaultHttpClient();
			 HttpContext localContext = new BasicHttpContext();
             HttpGet httpGet = new HttpGet("http://10.0.2.2:28250/thisisdoodle/webresources/entities.participant/connexion/"+log+"/"+pd);
             String text = "";
             String test="";
             try {
                   HttpResponse response = httpClient.execute(httpGet, localContext);
                   HttpEntity entity = response.getEntity();
       text = getASCIIContentFromEntity(entity);
                   JSONObject jsonResponse;
                 //  JSONObject responseJSON = response.toString();
                  
                   jsonResponse = new JSONObject(text);
                //   JSONArray jsonArray = jsonResponse.getJSONArray("status");
                //for (int i = 0; i < jsonArray.length(); i++) {
                  
                   text = text + "Status = ";
                   test = jsonResponse.getString("status");
                   text = text +test+"\n";
                /*   String email = jsonObject.getString("email");
                   text = text + "email : "+email+"\n";
                   int id = jsonObject.getInt("id");
                   text = text + "id : "+id+"\n";
                   int mdp = jsonObject.getInt("mdp");
                   text = text + "mdp : "+mdp+"\n";
                   String nom = jsonObject.getString("nom");
                   text = text + "nom : "+nom+"\n";
                   String prenom = jsonObject.getString("prenom");
                   text = text + "prenom : "+prenom+"\n\n";
              
                   Log.i("adresse :", addresse);*/
                   
                
                   
             } catch (Exception e) {
            	// return e.getLocalizedMessage();
            	 e.printStackTrace();
            	 
             }
             return test;
             
		}	
		
		protected void onPostExecute(String results) {
			

			if (results.equals("success")) {
				Toast.makeText(authentificationora.this, "authentification réussi avec succées",
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getApplicationContext(), AndroidDashboardDesignActivity.class);
				startActivity(intent);
				//EditText et = (EditText)findViewById(R.id.my_edit);
				//et.setText(results);
			}else{
				Intent ho = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(ho);
				Toast.makeText(authentificationora.this, "No Connection",
		Toast.LENGTH_SHORT).show();
//				Toast.makeText(authentificationora.this, "authentification échoué",
//						Toast.LENGTH_SHORT).show();
			}
			Button b = (Button)findViewById(R.id.button1);
			b.setClickable(true);
		}
    }
	
	
}
