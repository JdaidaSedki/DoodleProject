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
import org.json.JSONException;
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


public class modfier_event extends Activity implements OnClickListener  {
public static int id1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modifier_event);
		Button deco=(Button)findViewById(R.id.button1);
		 deco.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 Intent i = new Intent(getApplicationContext(), authentificationora.class);
					startActivity(i);
				// TODO Auto-generated method stub
				
			}
		});
		 findViewById(R.id.button2).setOnClickListener(this);
		 Button modifier=(Button)findViewById(R.id.button3);
		 
		 modifier.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//String dat=date.getText().toString();
				///recupere date 
				final EditText date=(EditText)findViewById(R.id.editText1);
				final EditText lieu=(EditText)findViewById(R.id.editText2);
				final EditText descr=(EditText)findViewById(R.id.editText3);		 
				final EditText titre=(EditText)findViewById(R.id.editText4);
				String dat=date.getText().toString();
				String lieueve=lieu.getText().toString();
				String description = descr.getText().toString();
				String title=titre.getText().toString();
				
				
//		        Toast.makeText(deuxcreanux.this, lie, 
//		        Toast.LENGTH_SHORT).show();
		        //String titr=titre.getText().toString();
				 HttpPost request = new HttpPost("http://10.0.2.2:28250/thisisdoodle/webresources/entities.evenement");
	             request.setHeader("Accept", "application/json");
	             request.setHeader("Content-type", "application/json");
	          
	             String not = new String(" ");
	             try {
	             // Build JSON string
	            	 //JSONStringer o=new JSONStringer().object().key("dateEvnt").value("61329999600000").key("description").value("desc").key("lieu").value("lie").key("titre").value("titr").endObject();
	            	// JSONStringer o=new JSONStringer().object().key("lieu").value(lieu).key("titre").value(titre).key("description").value(descr).key("dateEvnt").value("61329999600000").endObject();
	            	 JSONObject json=new JSONObject();
	            	 json.put("id", id1);
	            	 json.put("dateEvnt", dat);
	            	 json.put("lieu", lieueve);
	            	 json.put("description", description);
	            	 json.put("titre", title);
	            	
	            	// json.put("pseudo", pseudop);
	            
	            	 
	            	    StringEntity entity = new StringEntity(json.toString());
	            	    Toast.makeText(modfier_event.this, json.toString() + "\n",
	    						Toast.LENGTH_SHORT).show();
	                    //Toast.makeText(this, o.toString() + "\n", Toast.LENGTH_LONG).show() ;
	                 
	                    request.setEntity(entity);
	                 
	                    // Send request to WCF service
	                    DefaultHttpClient httpClient = new DefaultHttpClient();
	                    HttpResponse response = httpClient.execute(request);
	                    // Log.d("WebInvoke", "Saving : " + response.getStatusLine().getStatusCode());
	                    Toast.makeText(modfier_event.this, response.getStatusLine().getStatusCode() + "\n",
	    						Toast.LENGTH_SHORT).show();
	                  
	                    //Toast.makeText(this, response.getStatusLine().getStatusCode() + "\n", Toast.LENGTH_LONG).show() ;
	                 
	                    }catch (Exception e) {
	                    not = "NOT ";
	                    }
	             Toast.makeText(modfier_event.this, not + " Modification successful ! " + "\n",
 						Toast.LENGTH_SHORT).show();
	                  //  Toast.makeText(this, not + " OK ! " + "\n", Toast.LENGTH_LONG).show() ;
	                 
	                 

	    }
		});

    }
	
    
	@Override
	public void onClick(View arg0) {
		Button b = (Button)findViewById(R.id.button2);
		b.setClickable(false);
		
			//Ici votre code :) 
			new LongRunningGetIO().execute();		
		
	}
	
	
	private class LongRunningGetIO extends AsyncTask <Void, Void, String> {
		private ProgressDialog dialog = 
	            new ProgressDialog(modfier_event.this);
		final EditText date=(EditText)findViewById(R.id.editText1);
		final EditText lieu=(EditText)findViewById(R.id.editText2);
		final EditText descr=(EditText)findViewById(R.id.editText3);		 
		final EditText titre=(EditText)findViewById(R.id.editText4);
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
			
			EditText titre=(EditText)findViewById(R.id.editText4);
	      //  EditText pwd=(EditText)findViewById(R.id.editText2);
			String titr=titre.getText().toString();
	       // String pd=pwd.getText().toString();
			 HttpClient httpClient = new DefaultHttpClient();
			 HttpContext localContext = new BasicHttpContext();
             HttpGet httpGet = new HttpGet("http://10.0.2.2:28250/thisisdoodle/webresources/entities.evenement/lister/"+titr);
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
			

			if (results!=null) {
				Toast.makeText(modfier_event.this, "recupération succées",
						Toast.LENGTH_SHORT).show();
				JSONObject jsonResponse;
                //  JSONObject responseJSON = response.toString();
                 
                  try {
					jsonResponse = new JSONObject(results);
				int id = jsonResponse.getInt("id");
				id1=id;
	                 //text = text + "id : "+id+"\n";
	                 String dateevent = jsonResponse.getString("dateEvnt");
	                 String lieuevnt  = jsonResponse.getString("lieu");
//	                 text = text + "mdp : "+mdp+"\n";
	                 String descrieent = jsonResponse.getString("description");
//	                 text = text + "nom : "+nom+"\n";
	                 String titreevent = jsonResponse.getString("titre");
	                 date.setText(dateevent);
	                 lieu.setText(lieuevnt);
	                 descr.setText(descrieent);
	                 titre.setText(titreevent);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
                 //text = text + "prenom : "+prenom+"\n\n";
            
				
				//EditText et = (EditText)findViewById(R.id.my_edit);
				//et.setText(results);
			}else{
				Toast.makeText(modfier_event.this, "recupération échoué",
						Toast.LENGTH_SHORT).show();
			}
			Button b = (Button)findViewById(R.id.button1);
			b.setClickable(true);
		}
    }
	////////////////modification
	
	
}
