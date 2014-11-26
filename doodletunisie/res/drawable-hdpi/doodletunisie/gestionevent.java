package com.example.doodletunisie;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

 
public class gestionevent extends Activity {
 
  //private CheckBox chek1, chk2,chk3;
  private Button btnDisplay;
 
  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.configevent);
	CheckBox chek1=(CheckBox)findViewById(R.id.checkBox1);
	CheckBox chek2=(CheckBox)findViewById(R.id.checkBox2);
	CheckBox chek3=(CheckBox)findViewById(R.id.checkBox3);
	Button binsc=(Button)findViewById(R.id.button2);
	
	
  }
 
  /*public void addListenerOnChkIos() {
 
	  chek1 = (CheckBox) findViewById(R.id.checkBox1);
 
	  chek1.setOnClickListener(new OnClickListener() {
 
	  @Override
	  public void onClick(View v) {
                //is chkIos checked?
		if (((CheckBox) v).isChecked()) {
			Toast.makeText(gestionevent.this,
		 	   "Bro, try Android :)", Toast.LENGTH_LONG).show();
		}
 
	  }
	});
 
  }
 
  public void addListenerOnButton() {
 
	  chek1 = (CheckBox) findViewById(R.id.checkBox1);
	  chk2 = (CheckBox) findViewById(R.id.checkBox2);
	chk3 = (CheckBox) findViewById(R.id.checkBox3);
	btnDisplay = (Button) findViewById(R.id.button3);
 
	btnDisplay.setOnClickListener(new OnClickListener() {
 
          //Run when button is clicked
	  @Override
	  public void onClick(View v) {
 if(chek1.isChecked()){
	 Intent i = new Intent(getApplicationContext(), ajoutev.class);
		startActivity(i);
 }else if(chk2.isChecked()) {
	 Intent i = new Intent(getApplicationContext(), delet_event.class);
		startActivity(i);
	 
 }else{
	 Toast.makeText(gestionevent.this, "vous devez choisir une option",
				Toast.LENGTH_LONG).show();
 }
//		StringBuffer result = new StringBuffer();
//		result.append("IPhone check : ").append(chkIos.isChecked());
//		result.append("\nAndroid check : ").append(chkAndroid.isChecked());
//		//result.append("\nWindows Mobile check :").append(chkWindows.isChecked());
// 
		
 
	  }
	});
 
  }*/
}