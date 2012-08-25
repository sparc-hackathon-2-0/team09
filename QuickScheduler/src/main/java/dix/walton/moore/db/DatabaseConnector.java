package dix.walton.moore.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseConnector extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "GMuter";

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE_CALENDARS = "create table Calendars (" +
			"_id integer primary key, " +
			"name text not null" +
			");";
	
	private static final String DATABASE_CREATE_EVENTS = "create table Events (" +
			"_id integer primary key autoincrement, " +
			"mute text not null, " +
			"unmute text not null, " +
			"title text" +
			");";
	
	public DatabaseConnector(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE_CALENDARS);
		database.execSQL(DATABASE_CREATE_EVENTS);
		
		loadSampleData(database);
	}

	private void loadSampleData(SQLiteDatabase database) {
		ContentValues values = new ContentValues();
		
		values.put("_id", 0);
		values.put("name", "MyCalendarName");
		
		database.insert("Calendars", null, values);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(DatabaseConnector.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS Calendars");
		database.execSQL("DROP TABLE IF EXISTS Events");
		onCreate(database);

	}

}
