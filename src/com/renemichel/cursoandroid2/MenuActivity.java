package com.renemichel.cursoandroid2;

import java.io.IOException;
import java.net.MalformedURLException;


import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MenuActivity extends Activity implements LocationListener {

	private LocationManager locManager;
	
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
		
		
		Button botonPostales = (Button) this.findViewById(R.id.botonPostales);
		botonPostales.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(MenuActivity.this, PostalesActivity.class);
				startActivity(i);
			}});
		
		
		this.locManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
		
		this.locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, this);
		
		Location loc = this.locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		TextView tvGeo = (TextView) this.findViewById(R.id.tvGeo);
		
		if(loc != null){
			tvGeo.setText(loc.getLatitude() + "," + loc.getLongitude());
			RequestHelper request = new RequestHelper("http://api.geonames.org/findNearbyPlaceNameJSON?username=cursoandroiddicis&lat="+loc.getLatitude()+"&lng="+loc.getLongitude(),this);
			request.execute();
			
			ProgressBar bar = (ProgressBar) this.findViewById(R.id.progressBar1);
			bar.setVisibility(View.VISIBLE);
			bar.setIndeterminate(true);
			//Log.i("curso", request.getmResult());
		}else{
			tvGeo.setText("No location");
		}
		
		
		//"http://api.geonames.org/findNearbyPlaceNameJSON?username=cursoandroiddicis&lat="+loc.getLatitude()+"&lng="+loc.getLongitude()
		/*RequestHelper request = new RequestHelper("http://api.geonames.org/findNearbyPlaceNameJSON?username=cursoandroiddicis&lat="+loc.getLatitude()+"&lng="+loc.getLongitude());
		request.execute();
		
		Log.i("curso", request.getmResult());*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		
		this.locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, this);
	}
	
	@Override
	public void onRestart(){
		super.onRestart();
		this.locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 10, this);
	}
	
	@Override
	public void onPause(){
		super.onPause();
		this.locManager.removeUpdates(this);
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		this.locManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location loc) {
		// TODO Auto-generated method stub
		TextView tvGeo = (TextView) this.findViewById(R.id.tvGeo);
		
		tvGeo.setText(loc.getLatitude() + "," + loc.getLongitude());
		
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}
