package com.example.doodletunisie;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ajoueter extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ajouter);
		Button insert = (Button) findViewById(R.id.button1);
		insert.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText nom = (EditText) findViewById(R.id.nom);
				EditText prenom = (EditText) findViewById(R.id.prenom);
				EditText Login = (EditText) findViewById(R.id.editText1);
				EditText adresse = (EditText) findViewById(R.id.adresse);
				EditText pass = (EditText) findViewById(R.id.editText2);
				EditText email = (EditText) findViewById(R.id.email);
				Authent inscr = new Authent(getApplicationContext());
				inscr.add(nom.getText().toString(),prenom.getText().toString(),Login.getText().toString(),adresse.getText().toString(), pass.getText().toString(),email.getText().toString());
				Toast.makeText(Ajoueter.this, "ajout est fait avec succes",
						Toast.LENGTH_SHORT).show();
			}
		});
	}

}
