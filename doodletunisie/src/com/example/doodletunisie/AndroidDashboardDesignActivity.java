package com.example.doodletunisie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class AndroidDashboardDesignActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
        
        /**
//         * Creating all buttons instances
//         * */
//        // Dashboard News feed button
        ImageButton btn_newsfeed = (ImageButton) findViewById(R.id.imageButton1);
        
        // Dashboard Friends button
        //ImageButton btn_friends = (ImageButton) findViewById(R.id.imageButton2);
        
        // Dashboard Messages button
        ImageButton btn_messages = (ImageButton) findViewById(R.id.imageButton3);
        
        // Dashboard Places button
        ImageButton btn_places = (ImageButton) findViewById(R.id.imageButton4);
        ImageButton delete = (ImageButton) findViewById(R.id.imageButton5);
delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), delet_event.class);
				startActivity(i);
			}
		});
        // Dashboard Events button
        
        
        /**
         * Handling all button click events
         * */
        
        // Listening to News Feed button click
       
        
        // Listening Messages button click
        btn_messages.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), AndroidJSONParsingActivity.class);
				startActivity(i);
			}
		});
        
        // Listening to Places button click
        btn_places.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), PlacesActivity.class);
				startActivity(i);
			}
		});
        
        // Listening to Events button click
        btn_newsfeed.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), EventsActivity.class);
				startActivity(i);
			}
		});
        
        // Listening to Photos button click
        
    }
