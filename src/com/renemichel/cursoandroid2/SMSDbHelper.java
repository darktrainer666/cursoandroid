package com.renemichel.cursoandroid2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SMSDbHelper extends SQLiteOpenHelper{
	
	public static final String TABLE_NAME = "messages";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NUMBER_SOURCE = "number_source";
	public static final String COLUMN_TEXT = "text";
	public static final String COLUMN_DATE = "date";
	
	public static final String DATABASE_NAME = "messages.db";
	public static final int DATABASE_VERSION = 1;
	

	public SMSDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.i("curso","Se creo la tabla messages");
		db.execSQL("CREATE TABLE "+this.TABLE_NAME+"("
					+COLUMN_ID+" integer primary key autoincrement,"
					+COLUMN_NUMBER_SOURCE+ " text not null,"
					+COLUMN_TEXT+ " text not null,"
					+COLUMN_DATE+ " text not null);"
				);
		
		
		db.execSQL("INSERT INTO "+TABLE_NAME+"("+COLUMN_NUMBER_SOURCE+","+COLUMN_TEXT+","+COLUMN_DATE+") VALUES ('3310068316','hola que hace',datetime())");
		db.execSQL("INSERT INTO "+TABLE_NAME+"("+COLUMN_NUMBER_SOURCE+","+COLUMN_TEXT+","+COLUMN_DATE+") VALUES ('4611234567','hola que hace',datetime())");
		db.execSQL("INSERT INTO "+TABLE_NAME+"("+COLUMN_NUMBER_SOURCE+","+COLUMN_TEXT+","+COLUMN_DATE+") VALUES ('4345436765','hola que hace',datetime())");
		db.execSQL("INSERT INTO "+TABLE_NAME+"("+COLUMN_NUMBER_SOURCE+","+COLUMN_TEXT+","+COLUMN_DATE+") VALUES ('1516547453','hola que hace',datetime())");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
		onCreate(db);
	}
	
	

}
