package com.example.doodletunisie;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ExampleActivity extends Activity {
@Override
public void onCreate(Bundle savedState) { 
super.onCreate(savedState);
setContentView(R.layout.activity_main);
TextView tt = (TextView) findViewById(R.id.textView1);
Button test = (Button) findViewById(R.id.button1);
test.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
});

if(isNetworkConnected(this)){
	Toast.makeText(ExampleActivity.this, "connexion avec oracle etablie",
			Toast.LENGTH_SHORT).show();
// Si c'est ok 
//Ici votre code :) 
}
else{
	Toast.makeText(ExampleActivity.this, "connexion avec oracle etablie",
			Toast.LENGTH_SHORT).show();
}
}

public static boolean isNetworkConnected(Context context) {
ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
return (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected());
}
}