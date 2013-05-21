package com.example.cursoandroid2;

import java.io.IOException;
import java.net.MalformedURLException;

import android.os.AsyncTask;
import android.util.Log;

public class RequestHelper extends AsyncTask{

	private String mUrl;
	private String mResult;
	
	public RequestHelper(String URL){
		this.mUrl = URL;
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
		Log.i("curso",mResult);
		
	}

	public String getmResult() {
		return mResult;
	}

}
