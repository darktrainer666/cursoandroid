package com.example.cursoandroid2;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MessagesMainListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_messages_main_list);
		
		ListView lvMessages = (ListView) this.findViewById(R.id.lvMessages);
		
		MessageDataSource dataSource = new MessageDataSource(this);
		dataSource.open();
		final ArrayList<Message> allMessages = dataSource.getAllMessages();
		
		//ArrayAdapter<Message> adapter = new ArrayAdapter<Message>(this, android.R.layout.simple_list_item_1, allMessages);
		SMSThreadListAdapter adapter = new SMSThreadListAdapter(this, R.layout.item_list_sms, allMessages);
		
		
		
		lvMessages.setAdapter(adapter);
		
		lvMessages.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
					Log.i("curso", "item clickeado: "+ String.valueOf(position));
					
					Intent i = new Intent(MessagesMainListActivity.this, SMSComposerActivity.class);
					
					i.putExtra("number",allMessages.get(position).getNumber_source());
					
					startActivity(i);
			}});
		
		
		Button buttonNewMessage = (Button) this.findViewById(R.id.buttonNewMessage);
		
		buttonNewMessage.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MessagesMainListActivity.this, SMSComposerActivity.class);
				startActivity(i);
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_messages_main_list, menu);
		return true;
	}

}
