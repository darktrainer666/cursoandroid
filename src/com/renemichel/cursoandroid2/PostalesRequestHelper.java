package com.renemichel.cursoandroid2;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.view.View;

public class PostalesRequestHelper extends AsyncTask {

	private String mUrl = "http://api.geonames.org/postalCodeSearchJSON?username=cursoandroiddicis&country=MX";
	private String mPostalCode;
	private Context mCtx;
	
	public PostalesRequestHelper(String postalCode, Context ctx){
		
		this.mPostalCode = postalCode;
		this.mCtx = ctx;
		
	}
	
	
	private ArrayList<String> parseJSONString(String jsonString) throws JSONException{
		
		ArrayList<String> listaColonias = new ArrayList<String>();
		
		JSONObject rootObject = new JSONObject(jsonString);
		JSONArray postalCodesArray = rootObject.getJSONArray("postalCodes");
		
		for(int i = 0; i < postalCodesArray.length(); i++){
			
			listaColonias.add(postalCodesArray.getJSONObject(i).getString("placeName"));
			
		}
		
		return listaColonias;
	}
	
	
	@Override
	protected Object doInBackground(Object... params) {
		
		String result = "";
		
		try {
			result = NetUtils.RequestURL(mUrl + "&postalcode=" + this.mPostalCode);
		} catch (MalformedURLException e) {
			Log.e("curso",e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("curso",e.getMessage());
		}
		
		
		return result;
	}

	
	@Override
	protected void onPostExecute(Object result) {
		
		String res = (String) result;
		ArrayList<String> lista = null;
		
		try {
			lista = this.parseJSONString(res);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("curso",e.getMessage());
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.mCtx, android.R.layout.simple_list_item_1, lista);
		
		Activity postalActivity = (Activity) this.mCtx;
		ListView lvPostal = (ListView) postalActivity.findViewById(R.id.lvPostales);
		
		lvPostal.setAdapter(adapter);
		
		ProgressBar bar = (ProgressBar) postalActivity.findViewById(R.id.barPostales);
		bar.setVisibility(View.GONE);
		
		lvPostal.setVisibility(View.VISIBLE);
	}
}
