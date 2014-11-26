package com.example.doodletunisie;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ajoutcre extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ajoutcre);
		Button insert = (Button) findViewById(R.id.button1);
		insert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText lieu = (EditText) findViewById(R.id.date);
				EditText titre = (EditText) findViewById(R.id.horaidebu);
				EditText Descri = (EditText) findViewById(R.id.horaifin);
				
								crenaux cr = new crenaux(getApplicationContext());
				cr.add(lieu.getText().toString(),titre.getText().toString(),Descri.getText().toString());
				Toast.makeText(ajoutcre.this, "ajout crenax bien Fait",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

}
