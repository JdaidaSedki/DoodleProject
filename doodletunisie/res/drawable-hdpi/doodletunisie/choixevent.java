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
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;


public class choixevent extends Activity  {
	//Gson gson = new Gson();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affichagechoix);
        new LongRunningGetIO().execute();
    }

	
	
	
	private class LongRunningGetIO extends AsyncTask <Void, Void, String> {
		private ProgressDialog dialog = 
	            new ProgressDialog(choixevent.this);
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
			
			//EditText titre=(EditText)findViewById(R.id.me);
	      //  EditText pwd=(EditText)findViewById(R.id.editText2);
			//String titr=titre.getText().toString();
	       // String pd=pwd.getText().toString();
			 HttpClient httpClient = new DefaultHttpClient();
			 HttpContext localContext = new BasicHttpContext();
             HttpGet httpGet = new HttpGet("http://10.0.2.2:28250/thisisdoodle/webresources/entities.participant/participants/test13");
             String text = "";
             String test="ajout";
             try {
                   HttpResponse response = httpClient.execute(httpGet, localContext);
                   HttpEntity entity = response.getEntity();
       text = getASCIIContentFromEntity(entity);
                   
                //   JSONArray jsonArray = jsonResponse.getJSONArray("status");
                //for (int i = 0; i < jsonArray.length(); i++) {
                  
                   text = text + "Status = ";
                  // test = jsonResponse.getString("status");
                   //text = text +test+"\n";
                 ///  JSONObject json=new JSONObject();
               
                  
                   //Log.i("adresse :", addresse);
                   test="recup";
                
                   
             } catch (Exception e) {
            	// return e.getLocalizedMessage();
            	 e.printStackTrace();
            	 
             }
             return text;
             
		}	
		
		protected void onPostExecute(String results) {
			
			JSONArray contacts = null;

			if (results!=null) {
				Toast.makeText(choixevent.this, "recupération succées",
						Toast.LENGTH_SHORT).show();
				 try {
					contacts = new JSONArray(results);
				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		 			// looping through All Contacts
					for(int i = 0; i < contacts.length(); i++){
						JSONObject c = null;
						try {
							c = contacts.getJSONObject(i);
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						// Storing each json item in variable
						
						String debut;
						try {
							String id = c.getString("id");
							//debut = c.getString("debut");
							String pseudo = c.getString("pseudo");
							Toast.makeText(choixevent.this, id,
									Toast.LENGTH_SHORT).show();
							Toast.makeText(choixevent.this, pseudo,
									Toast.LENGTH_SHORT).show();
//							Toast.makeText(choixevent.this, fin,
//									Toast.LENGTH_SHORT).show();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						//Toast.makeText(MainActivity.this, id,
						//		Toast.LENGTH_SHORT).show();
						
					}
						
			}//end if
//	                 lieu.setText(lieuevnt);
//	                 descr.setText(descrieent);
//	                 titre.setText(titreevent);
			
				// TODO Auto-generated catch block
			
                 //text = text + "prenom : "+prenom+"\n\n";
            
				
				//EditText et = (EditText)findViewById(R.id.my_edit);
		
//			Button b = (Button)findViewById(R.id.my_button);
//			b.setClickable(true);
    }}}