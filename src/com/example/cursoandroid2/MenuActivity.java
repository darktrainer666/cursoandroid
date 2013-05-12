package com.example.cursoandroid2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.view.View.OnClickListener;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		ImageButton botonCalc = (ImageButton) this.findViewById(R.id.botonCalc);
		
		botonCalc.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Log.i("curso","entrando al activity calculadora");
				
				Intent i = new Intent(MenuActivity.this, MainActivity.class);
				startActivity(i);
				
			}

		});
		
		ImageButton botonSMS = (ImageButton) this.findViewById(R.id.botonMensajes);
		
		botonSMS.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(MenuActivity.this, MessagesMainListActivity.class);
				startActivity(i);
				
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}

}
