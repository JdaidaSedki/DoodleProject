package com.example.doodletunisie;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes.Name;

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
import org.json.JSONStringer;

import com.androidhive.dashboard.model.DateChreno;


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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class evenment_detail  extends Activity implements OnItemSelectedListener {
	
	// JSON node keys
	private static final String TAG_NAME = "titre";
	//private static final String TAG_TITRE = "titre";
	private static final String TAG_LIEU = "lieu";
	private static final String TAG_DESCRIPTION = "description";
private static final String TAG_DATEEVENT = "dateEvnt";
private Spinner liste = null;

	ArrayList<DateChreno>dates = new ArrayList<DateChreno>();
	
	ArrayAdapter<String> dataAdapter;
	
	Spinner spinner;
	
	String selectedID;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_event);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        String name = in.getStringExtra(TAG_NAME);
        
        String lizueven=in.getStringExtra(TAG_LIEU);
        String description=in.getStringExtra(TAG_DESCRIPTION);
       // String dateev=in.getStringExtra(TAG_DATEEVENT);
        
        // Displaying all values on the screen
        final EditText titre=(EditText)findViewById(R.id.editText1);
        final EditText pseudo=(EditText)findViewById(R.id.editText2);
        new LongRunningGetIO().execute();	
//        Toast.makeText(evenment_detail.this, id1,
//				Toast.LENGTH_SHORT).show();
//		Toast.makeText(evenment_detail.this, debut1,
//				Toast.LENGTH_SHORT).show();
//		Toast.makeText(evenment_detail.this, fin1,
//				Toast.LENGTH_SHORT).show();
 spinner = (Spinner) findViewById(R.id.spinner1);
 titre.setText(name);
 pseudo.setText(lizueven);
 

 
 // Spinner click listener
 spinner.setOnItemSelectedListener(this);
 Button part =(Button)findViewById(R.id.participer);
 part.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		participate();
	}
});
 

}
	

private void fillSpinner(){
	
	selectedID = dates.get(0).getId();
	 // Spinner Drop down elements
	 List<String> categories = new ArrayList<String>();
	 
	 for (DateChreno dateChreno : dates) {
		 categories.add("date de "+dateChreno.getDateDebut()+" à "+dateChreno.getDateFin());
	 }
	 
	 // Creating adapter for spinner
		dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
		
		// Drop down layout style - list view with radio button
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);
}
@Override
public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
	// On selecting a spinner item
	String item = parent.getItemAtPosition(position).toString();
	
	// Showing selected spinner item
	Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

	selectedID = dates.get(position).getId();
	
}

public void onNothingSelected(AdapterView<?> arg0) {
	// TODO Auto-generated method stub
	
} 

private void participate(){
	new LongRunningGetIO1().execute();
}
////////////*****récuperation de cranaux avec le  titre de l'évènement


private class LongRunningGetIO extends AsyncTask <Void, Void, String> {
	private ProgressDialog dialog = 
            new ProgressDialog(evenment_detail.this);
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
	/*protected void onPreExecute() {
        // TODO i18n
        dialog.setMessage("Please wait..");
        dialog.show();
    }*/
	
	@Override
	protected String doInBackground(Void... params) {
Intent in = getIntent();
        
        // Get JSON values from previous intent
        String name = in.getStringExtra(TAG_NAME);
		
		 HttpClient httpClient = new DefaultHttpClient();
		 HttpContext localContext = new BasicHttpContext();
         HttpGet httpGet = new HttpGet("http://10.0.2.2:28250/thisisdoodle/webresources/entities.crenaux/"+name);
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
//			Toast.makeText(evenment_detail.this, "recupération succées",
//					Toast.LENGTH_SHORT).show();
			 try {
				contacts = new JSONArray(results);
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	 			// looping through All Contacts
			 dates = new ArrayList<DateChreno>();
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
						debut = c.getString("debut");
						String fin = c.getString("fin");
						DateChreno dateChreno = new DateChreno(id, debut, fin);
						dates.add(dateChreno);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					fillSpinner();
				
//				Toast.makeText(evenment_detail.this, id2,
//							Toast.LENGTH_SHORT).show();
//					
				}
					
		}
	
}

	
	
	
///////////////////////////////fin recuperation
}

////////////*****participer à un évènement l'évènement


private class LongRunningGetIO1 extends AsyncTask <Void, Void, String> {
private ProgressDialog dialog = 
new ProgressDialog(evenment_detail.this);
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
/*protected void onPreExecute() {
// TODO i18n
dialog.setMessage("Please wait..");
dialog.show();
}*/

@Override
protected String doInBackground(Void... params) {
Intent in = getIntent();

// Get JSON values from previous intent

HttpClient httpClient = new DefaultHttpClient();
HttpContext localContext = new BasicHttpContext();
HttpPost request = new HttpPost("http://10.0.2.2:28250/thisisdoodle/webresources/entities.response/fff/efe");
String text = "";
try {
	request.setHeader("Accept", "application/json");
    request.setHeader("Content-type", "application/json");
 
    String not = new String(" ");
    try {
    // Build JSON string
   	 JSONStringer o=new JSONStringer().object().key("choix").value(selectedID).endObject();
   	 
   	    StringEntity entity = new StringEntity(o.toString());
           
           //Toast.makeText(this, o.toString() + "\n", Toast.LENGTH_LONG).show() ;
        
           request.setEntity(entity);
        
           // Send request to WCF service
        
           }catch (Exception e) {
           not = "NOT ";
           }
        
           //Toast.makeText(this, not + " OK ! " + "\n", Toast.LENGTH_LONG).show() ;

   HttpResponse response = httpClient.execute(request, localContext);
   HttpEntity entity = response.getEntity();
   Log.i("response", entity.toString());
text = getASCIIContentFromEntity(entity);
   
//   JSONArray jsonArray = jsonResponse.getJSONArray("status");
//for (int i = 0; i < jsonArray.length(); i++) {
  
   text = text + "Status = ";
  // test = jsonResponse.getString("status");
   //text = text +test+"\n";
 ///  JSONObject json=new JSONObject();

  
   //Log.i("adresse :", addresse);

   
} catch (Exception e) {
// return e.getLocalizedMessage();
 e.printStackTrace();
 
}
return text;

}	

protected void onPostExecute(String results) {

if (results!=null) {
Log.i("result", results.toString())	;

	}
		
}

}




///////////////////////////////fin recuperation
}
