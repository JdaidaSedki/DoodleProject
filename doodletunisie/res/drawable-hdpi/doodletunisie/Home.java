package com.example.doodletunisie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		TextView tt = (TextView) findViewById(R.id.textView2);
		TextView tr = (TextView) findViewById(R.id.textView1);
		tt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent event = new Intent(getApplicationContext(), ajoutev.class);
				startActivity(event);
				
			}
		});
		tr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent eve = new Intent(getApplicationContext(), ajoutcre.class);
				startActivity(eve);
				
			}
		});
	}

}
