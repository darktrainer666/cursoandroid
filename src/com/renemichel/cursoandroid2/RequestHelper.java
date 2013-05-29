package com.renemichel.cursoandroid2;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;

public class RequestHelper extends AsyncTask{

	private String mUrl;
	private String mResult;
	private Context mCtx;
	
	public RequestHelper(String URL, Context ctx){
		this.mUrl = URL;
		this.mCtx = ctx;
	}
	
	private String parseJSONString(String jsonString) throws JSONException{
		
		String parsedJson = "";
		
		JSONObject geonames = new JSONObject(jsonString);
		JSONArray geonamesArray = geonames.getJSONArray("geonames");
		
		JSONObject geonames0 = geonamesArray.getJSONObject(0);
		
		parsedJson = geonames0.getString("name") + ", " + geonames0.getString("adminName1") + ", " + geonames0.getString("countryName");
		
		
		return parsedJson;
	}
	
	
	@Override
	protected Object doInBackground(Object... arg0) {
		
		String result = "";
		
		try {
			result = NetUtils.RequestURL(this.mUrl);
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			Log.e("curso", e.getMessage());
			result = "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("curso", e.getMessage());
			result = "";
		} catch(Exception e){
			Log.e("curso", e.getMessage());
			result = "";
		}
		
		return result;
	}
	
	
	@Override
	protected void onPostExecute(Object result){
		
		this.mResult = (String) result;
		String geoReverseString = "";
		
		try {
			geoReverseString = this.parseJSONString(mResult);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("curso",e.getMessage());
		}
		
		
		Activity menuActivity = (Activity) this.mCtx;
		TextView tvGeoReverseString = (TextView) menuActivity.findViewById(R.id.tvGeoReverse);
		tvGeoReverseString.setText(geoReverseString);
		
		ProgressBar bar = (ProgressBar) menuActivity.findViewById(R.id.progressBar1);
		bar.setVisibility(View.GONE);
		
		Log.i("curso",mResult);
		
	}

	public String getmResult() {
		return mResult;
	}

}
