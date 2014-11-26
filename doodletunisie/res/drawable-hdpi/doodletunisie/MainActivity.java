package com.example.doodletunisie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.authentifacationlite);
		Button tt = (Button) findViewById(R.id.inscrit);
		Button test = (Button) findViewById(R.id.button1);
		
		test.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Authent rt = new Authent(getApplicationContext());
				EditText Login = (EditText) findViewById(R.id.editText11);
				EditText pass = (EditText) findViewById(R.id.editText22);
				if (rt.Login(Login.getText().toString(), pass.getText()
						.toString()) == true) {
					Toast.makeText(MainActivity.this, "Good",
							Toast.LENGTH_SHORT).show();
					Intent ho = new Intent(getApplicationContext(), menusqlite.class);
					startActivity(ho);
				} else {
					Toast.makeText(MainActivity.this, "Erreur d'auth",
							Toast.LENGTH_SHORT).show();
					Login.setText("");
					pass.setText(" ");
				}
			}
		});

		tt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent inf = new Intent(v.getContext(), Ajoueter.class);
				startActivity(inf);

			}
		});
	}

}
