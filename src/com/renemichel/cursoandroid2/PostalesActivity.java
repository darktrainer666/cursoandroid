package com.renemichel.cursoandroid2;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class PostalesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_postales);
		
		
		final ProgressBar bar = (ProgressBar) this.findViewById(R.id.barPostales);
		bar.setVisibility(View.GONE);
		
		
		final EditText editPostalCode = (EditText) this.findViewById(R.id.editPostales);
		
		final ListView lvPostales = (ListView) this.findViewById(R.id.lvPostales);
		
		Button botonSend = (Button) this.findViewById(R.id.botonPostalesSend);
		botonSend.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				bar.setVisibility(View.VISIBLE);
				bar.setIndeterminate(true);
				
				lvPostales.setVisibility(View.INVISIBLE);
				
				String postalCode = editPostalCode.getEditableText().toString();
				
				PostalesRequestHelper request = new PostalesRequestHelper(postalCode, PostalesActivity.this);
				request.execute();
				
			}});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_postales, menu);
		return true;
	}

}
