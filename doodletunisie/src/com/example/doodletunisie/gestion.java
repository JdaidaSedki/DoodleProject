package com.example.doodletunisie;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

 
public class gestion extends Activity {
 
  private CheckBox chkIos, chkAndroid;
  private Button btnDisplay;
 
  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.gestion);
 Button deco=(Button)findViewById(R.id.button1);
 deco.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		 Intent i = new Intent(getApplicationContext(), authentificationora.class);
			startActivity(i);
		// TODO Auto-generated method stub
		
	}
});
	addListenerOnChkIos();
	addListenerOnButton();
  }
 
  public void addListenerOnChkIos() {
 
	chkIos = (CheckBox) findViewById(R.id.checkBox1);
 
	chkIos.setOnClickListener(new OnClickListener() {
 
	  @Override
	  public void onClick(View v) {
                //is chkIos checked?
		if (((CheckBox) v).isChecked()) {
			Toast.makeText(gestion.this,
		 	   "Bro, try Android :)", Toast.LENGTH_LONG).show();
		}
 
	  }
	});
 
  }
 
  public void addListenerOnButton() {
 
	chkIos = (CheckBox) findViewById(R.id.checkBox1);
	chkAndroid = (CheckBox) findViewById(R.id.checkBox2);
//	chkWindows = (CheckBox) findViewById(R.id.chkWindows);
	btnDisplay = (Button) findViewById(R.id.button3);
 
	btnDisplay.setOnClickListener(new OnClickListener() {
 
          //Run when button is clicked
	  @Override
	  public void onClick(View v) {
 if(chkIos.isChecked()){
	 Intent i = new Intent(getApplicationContext(), modfier_event.class);
		startActivity(i);
 }else if(chkAndroid.isChecked()) {
	 Intent i = new Intent(getApplicationContext(), delet_event.class);
		startActivity(i);
	 
 }else{
	 Toast.makeText(gestion.this, "vous devez choisir une option",
				Toast.LENGTH_LONG).show();
 }
//		StringBuffer result = new StringBuffer();
//		result.append("IPhone check : ").append(chkIos.isChecked());
//		result.append("\nAndroid check : ").append(chkAndroid.isChecked());
//		//result.append("\nWindows Mobile check :").append(chkWindows.isChecked());
// 
		
 
	  }
	});
 
  }
}