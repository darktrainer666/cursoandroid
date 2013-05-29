package com.renemichel.cursoandroid2;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;

public class SMSComposerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smscomposer);
		
		
		Intent i = this.getIntent();
		
		if(i.hasExtra("number")){
			
			Log.i("curso","Contiene el extra 'number'");
			
			Bundle extras = i.getExtras();
			String number = extras.getString("number");
			
			EditText editNumber = (EditText) this.findViewById(R.id.tvNumberComposer);
			
			editNumber.setText(number);
			
		}
		
		
		
		Button botonSend = (Button) this.findViewById(R.id.buttonSend);
		
		botonSend.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				EditText editNumberC = (EditText) findViewById(R.id.tvNumberComposer);
				EditText editTextC = (EditText) findViewById(R.id.tvTextComposer);
				
				String number = editNumberC.getEditableText().toString();
				String text = editTextC.getEditableText().toString();
				
				/*if(number.isEmpty() || text.isEmpty()){
					Log.w("curso","Campos vacios");
					return;
				}*/
				
				Log.i("curso","datos: "+number+" text: "+text);
				
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(number, null, text, null, null);
				
				//TODO: insertar en la db el mensaje y refrescar la lista
				
				editTextC.setText("");
				
			}});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_smscomposer, menu);
		return true;
	}

}
