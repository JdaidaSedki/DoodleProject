package com.example.doodletunisie;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class affiche_event extends ListActivity {

	// url to make request
	private static String url = "http://10.0.2.2:28250/thisisdoodle/webresources/entities.evenement/efe";
	// JSON Node names
	private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_ID = "id";
	private static final String TAG_TITRE = "titre";
	private static final String TAG_LIEU = "lieu";
	private static final String TAG_DESCRIPTION = "description";
private static final String TAG_DATEEVENT = "dateEvnt";
//	private static final String TAG_PHONE = "phone";
//	private static final String TAG_PHONE_MOBILE = "mobile";
//	private static final String TAG_PHONE_HOME = "home";
//	private static final String TAG_PHONE_OFFICE = "office";

	// contacts JSONArray
	JSONArray contacts = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.afficage_event);
		
		// Hashmap for ListView
		ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

		 HttpClient httpClient = new DefaultHttpClient();
		 HttpContext localContext = new BasicHttpContext();
         HttpGet httpGet = new HttpGet(url);
         String text = "";
         try {
               HttpResponse response;
			response = httpClient.execute(httpGet, localContext);
               HttpEntity entity = response.getEntity();
               text = getASCIIContentFromEntity(entity);
               
             //  JSONObject responseJSON = response.toString();
              
            contacts = new JSONArray(text);
  			// looping through All Contacts
			for(int i = 0; i < contacts.length(); i++){
				JSONObject c = contacts.getJSONObject(i);
				
				// Storing each json item in variable
				String id = c.getString(TAG_ID);
				String titre = c.getString(TAG_TITRE);
				String lieu = c.getString(TAG_LIEU);
				String desciption = c.getString(TAG_DESCRIPTION);
				//String dateEvnt= c.getString(TAG_DATEEVENT);

				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				
				// adding each child node to HashMap key => value
				map.put(TAG_ID, id);
				map.put(TAG_TITRE, titre);
				map.put(TAG_LIEU, lieu);
				map.put(TAG_DESCRIPTION, desciption);
				//map.put(TAG_DATEEVENT, dateEvnt);

				// adding HashList to ArrayList
				contactList.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/**
		 * Updating parsed JSON data into ListView
		 * */
		ListAdapter adapter = new SimpleAdapter(this, contactList,
				R.layout.list_item,
				new String[] { TAG_TITRE, TAG_LIEU ,TAG_DESCRIPTION}, new int[] {
						R.id.name, R.id.email,R.id.mobile});

		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();

		// Launching new screen on Selecting Single ListItem
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String titre = ((TextView) view.findViewById(R.id.name)).getText().toString();
				String lieu=((TextView) view.findViewById(R.id.email)).getText().toString();
				String description=((TextView) view.findViewById(R.id.mobile)).getText().toString();
			//Date date_event=((Date)view.findViewById(R.id.date)).getDate().to;
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), evenment_detail.class);
				in.putExtra(TAG_TITRE, titre);
				in.putExtra(TAG_LIEU,lieu);
				in.putExtra(TAG_DESCRIPTION, description);
				//in.putExtra(TAG_DATEEVENT, date_event);
				startActivity(in);

			}
		});
		
		

	}
	
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
	

}