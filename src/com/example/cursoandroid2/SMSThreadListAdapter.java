package com.example.cursoandroid2;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.View;

public class SMSThreadListAdapter extends ArrayAdapter<Message>{

	private ArrayList<Message> messages;
	private Context ctx;
	
	public SMSThreadListAdapter(Context context, int textViewResourceId,
			ArrayList<Message> objects) {
		super(context, textViewResourceId, objects);
		
		messages = objects;
		ctx = context;
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		View v = convertView;
		
		if(v == null){
			
			LayoutInflater vi = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.item_list_sms, null);
			
		}
		
		
		Message temp = messages.get(position);
		
		TextView tvNumero = (TextView) v.findViewById(R.id.tvNumber);
		TextView tvText = (TextView) v.findViewById(R.id.tvText);
		TextView tvDate = (TextView) v.findViewById(R.id.tvDate);
		
		tvNumero.setText(temp.getNumber_source());
		tvText.setText(temp.getText());
		tvDate.setText(temp.getDate());
		
		return v;
	}

}
