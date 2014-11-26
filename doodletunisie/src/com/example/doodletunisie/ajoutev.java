package com.example.doodletunisie;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ajoutev extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ajouterev);
		Button insert = (Button) findViewById(R.id.button1);
		insert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText lieu = (EditText) findViewById(R.id.lieu);
				EditText titre = (EditText) findViewById(R.id.title);
				EditText Descri = (EditText) findViewById(R.id.descri);
				EditText dateevent = (EditText) findViewById(R.id.date_event);
								Evenement ev = new Evenement(getApplicationContext());
				ev.add(lieu.getText().toString(),titre.getText().toString(),Descri.getText().toString(),dateevent.getText().toString());
				Toast.makeText(ajoutev.this, "ajout bien Fait",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

}
