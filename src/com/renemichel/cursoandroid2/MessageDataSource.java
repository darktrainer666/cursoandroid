package com.renemichel.cursoandroid2;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MessageDataSource {

	private SMSDbHelper helper;
	private SQLiteDatabase db;
	private Context ctx;
	
	
	public MessageDataSource(Context context){
		
		helper = new SMSDbHelper(context);
		this.ctx = context;
	}
	
	public void open(){
		this.db = helper.getWritableDatabase();
	}
	
	public void close(){
		this.db.close();
	}
	
	public Message createMessage(String number_source, String text){
		
		db.execSQL("INSERT INTO "+SMSDbHelper.TABLE_NAME+"("+
					SMSDbHelper.COLUMN_NUMBER_SOURCE+","
					+SMSDbHelper.COLUMN_TEXT+","
					+SMSDbHelper.COLUMN_DATE+") VALUES ('"+number_source+"','"+text+"',datetime())");
		
		Message messageInserted = new Message(number_source, text);
		
		return messageInserted;
	}
	
	
	public ArrayList<Message> getAllMessages(){
		
		Cursor cursor = db.rawQuery("SELECT "+SMSDbHelper.COLUMN_ID+","+SMSDbHelper.COLUMN_NUMBER_SOURCE+","+SMSDbHelper.COLUMN_TEXT+","+SMSDbHelper.COLUMN_DATE+" FROM "+SMSDbHelper.TABLE_NAME,null);
		ArrayList<Message> lista = new ArrayList<Message>();
		
		Message temp = null;
		
		while(cursor.moveToNext()){
			
			temp = new Message(cursor.getString(1), cursor.getString(2));
			temp.setId(cursor.getLong(0));
			temp.setDate(cursor.getString(3));
			lista.add(temp);
		}
		
		return lista;
	}
	
}
