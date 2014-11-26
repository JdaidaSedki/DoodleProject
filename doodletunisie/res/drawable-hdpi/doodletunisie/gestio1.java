package com.example.doodletunisie;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

 
public class gestio1 extends Activity {
 
  private CheckBox chekboxajout, chkAndroid;
  private Button btnDisplay;
 
  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.menusqlite);
 
	addListenerOnChkIos();
	 chekboxajout = (CheckBox) findViewById(R.id.checkBox1);
		chkAndroid = (CheckBox) findViewById(R.id.checkBox2);
//		chkWindows = (CheckBox) findViewById(R.id.chkWindows);
		btnDisplay = (Button) findViewById(R.id.button3);
	 
		btnDisplay.setOnClickListener(new OnClickListener() {
	 
	          //Run when button is clicked
		  @Override
		  public void onClick(View v) {
	 if(chekboxajout.isChecked()){
		 Intent i = new Intent(getApplicationContext(), ajoutev.class);
			startActivity(i);
	 }else if(chkAndroid.isChecked()) {
		 Intent i = new Intent(getApplicationContext(), delet_event.class);
			startActivity(i);
		 
	 }else{
		 Toast.makeText(gestio1.this, "vous devez choisir une option",
					Toast.LENGTH_LONG).show();
	 }
//			StringBuffer result = new StringBuffer();
//			result.append("IPhone check : ").append(chkIos.isChecked());
//			result.append("\nAndroid check : ").append(chkAndroid.isChecked());
//			//result.append("\nWindows Mobile check :").append(chkWindows.isChecked());
	// 
			
	 
		  }
		});
	 
  }
 
  public void addListenerOnChkIos() {
 
	chekboxajout = (CheckBox) findViewById(R.id.checkBox1);
 
	chekboxajout.setOnClickListener(new OnClickListener() {
 
	  @Override
	  public void onClick(View v) {
                //is chkIos checked?
		if (((CheckBox) v).isChecked()) {
			Toast.makeText(gestio1.this,
		 	   "Bro, try Android :)", Toast.LENGTH_LONG).show();
		}
 
	  }
	});
 
  }
 
  public void addListenerOnButton() {
 
	 
  }
}