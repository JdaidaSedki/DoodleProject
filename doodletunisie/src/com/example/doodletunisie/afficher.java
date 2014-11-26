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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class afficher  extends Activity implements OnClickListener {
	//Gson gson = new Gson();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afficher_even_perso);
        findViewById(R.id.button3).setOnClickListener(this);        
    }
    @Override
	public void onClick(View arg0) {
		Button b = (Button)findViewById(R.id.button3);
		b.setClickable(false);
		new LongRunningGetIO().execute();
	}
	
	private class LongRunningGetIO extends AsyncTask <Void, Void, String> {
		
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
		protected String doInBackground(Void... params) {
			 HttpClient httpClient = new DefaultHttpClient();
			 HttpContext localContext = new BasicHttpContext();
             HttpGet httpGet = new HttpGet("http://10.0.2.2:28250/thisisdoodle/webresources/entities.participant/connexion/efe/143");
             String text = "";
             try {
                   HttpResponse response = httpClient.execute(httpGet, localContext);
                   HttpEntity entity = response.getEntity();
       text = getASCIIContentFromEntity(entity);
                   JSONObject jsonResponse;
                 //  JSONObject responseJSON = response.toString();
                  
                   jsonResponse = new JSONObject(text);
                   JSONArray jsonArray = jsonResponse.getJSONArray("participant");
                for (int i = 0; i < jsonArray.length(); i++) {
                   JSONObject jsonObject = jsonArray.getJSONObject(i);
                   text = text + "Participant : \n";
                   String addresse = jsonObject.getString("adresse");
                   text = text + "Adresse : "+addresse+"\n";
                   String email = jsonObject.getString("email");
                   text = text + "email : "+email+"\n";
                   int id = jsonObject.getInt("id");
                   text = text + "id : "+id+"\n";
                   int mdp = jsonObject.getInt("mdp");
                   text = text + "mdp : "+mdp+"\n";
                   String nom = jsonObject.getString("nom");
                   text = text + "nom : "+nom+"\n";
                   String prenom = jsonObject.getString("prenom");
                   text = text + "prenom : "+prenom+"\n\n";
              
                   Log.i("adresse :", addresse);
                }
                   
             } catch (Exception e) {
            	 return e.getLocalizedMessage();
             }
             return text;
             
		}	
		
		protected void onPostExecute(String results) {
			

			if (results!=null) {
				EditText et = (EditText)findViewById(R.id.my_edit);
				et.setText(results);
			}
			Button b = (Button)findViewById(R.id.button3);
			b.setClickable(true);
		}
    }
}

