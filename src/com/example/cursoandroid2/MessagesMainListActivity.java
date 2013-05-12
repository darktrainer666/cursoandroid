package com.example.cursoandroid2;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MessagesMainListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_messages_main_list);
		
		ListView lvMessages = (ListView) this.findViewById(R.id.lvMessages);
		
		MessageDataSource dataSource = new MessageDataSource(this);
		dataSource.open();
		ArrayList<Message> allMessages = dataSource.getAllMessages();
		
		ArrayAdapter<Message> adapter = new ArrayAdapter<Message>(this, android.R.layout.simple_list_item_1, allMessages);
		
		lvMessages.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_messages_main_list, menu);
		return true;
	}

}
