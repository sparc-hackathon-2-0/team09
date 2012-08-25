//package dix.walton.moore.db;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import org.gmuter.model.MyCalendar;
//
//import java.util.ArrayList;
//
//public class CalendarsDBAdaptor {
//
//	public static final String KEY_ROWID = "_id";
//	public static final String KEY_NAME = "name";
//	private static final String DATABASE_TABLE = "Calendars";
//	private Context context;
//	private SQLiteDatabase database;
//	private DatabaseConnector dbHelper;
//
//	public CalendarsDBAdaptor(Context context) {
//		this.context = context;
//	}
//
//	public CalendarsDBAdaptor open() throws SQLException {
//		dbHelper = new DatabaseConnector(context);
//		database = dbHelper.getWritableDatabase();
//		return this;
//	}
//
//	public void close() {
//		dbHelper.close();
//	}
//
//	public long createCalendar(MyCalendar calendar) {
//		ContentValues values = new ContentValues();
//
//		values.put(KEY_ROWID,calendar.getId());
//		values.put(KEY_NAME,calendar.getName());
//
//		return database.insert(DATABASE_TABLE, null, values);
//	}
//
//	public boolean updateMyCalendar(MyCalendar calendar)
//	{
//		ContentValues values = new ContentValues();
//
//		values.put(KEY_NAME, calendar.getName());
//
//		return database.update(DATABASE_TABLE, values, KEY_ROWID + "=" + calendar.getId(), null) > 0;
//	}
//
//	public boolean deleteMyCalendar(MyCalendar calendar) {
//		return database.delete(DATABASE_TABLE, KEY_ROWID + "=" + calendar.getId(), null) > 0;
//	}
//
//	public MyCalendar getMyCalendar(long rowId) throws SQLException {
////		ArrayList<MyCalendar> deviceArray = new ArrayList<MyCalendar>();
//		Cursor mCursor = database.query(
//				true,
//				DATABASE_TABLE,
//				new String[] {
//						KEY_ROWID,
//						KEY_NAME
//						},
//				KEY_ROWID + "=" + rowId, null, null, null, null, null);
//		if (mCursor.isLast())
//		{
////			do
////			{
//				MyCalendar tmp = new MyCalendar();
//				tmp.setId(mCursor.getInt(mCursor.getColumnIndex(KEY_ROWID)));
//				tmp.setName((mCursor.getString(mCursor.getColumnIndex(KEY_NAME))));
//				return tmp;
//				//
////				deviceArray.add(tmp);
////
////			} while (mCursor.moveToNext());
//		} else {
//			return null;
//		}
//
//	}
//
//	public MyCalendar getMyCalendar(String calendarName) throws SQLException {
////		ArrayList<MyCalendar> deviceArray = new ArrayList<MyCalendar>();
////		Cursor mCursor = database.query(
////				true,
////				DATABASE_TABLE,
////				new String[] {
////						KEY_ROWID,
////						KEY_NAME
////						},
////				KEY_NAME + "=" + calendarName, null, null, null, null, null);
//		String qryStr = "SELECT name FROM Calendars WHERE name=\"" + calendarName + "\" AND _id NOT NULL;";
//		System.out.println(qryStr);
//		Cursor mCursor = database.rawQuery(qryStr , null);
//		if (mCursor.isLast())
//		{
////			do
////			{
//				MyCalendar tmp = new MyCalendar();
//				tmp.setId(mCursor.getInt(mCursor.getColumnIndex(KEY_ROWID)));
//				tmp.setName((mCursor.getString(mCursor.getColumnIndex(KEY_NAME))));
//				return tmp;
//				//
////				deviceArray.add(tmp);
////
////			} while (mCursor.moveToNext());
//		} else {
//			return null;
//		}
//
//	}
//
//	public ArrayList<MyCalendar> getAllMyCalendars() throws SQLException {
//		ArrayList<MyCalendar> deviceArray = new ArrayList<MyCalendar>();
//		Cursor mCursor = database.query(
//				true,
//				DATABASE_TABLE,
//				new String[] {
//						KEY_ROWID,
//						KEY_NAME
//						},
//				null, null, null, null, null, null);
//		if (mCursor.moveToFirst())
//		{
//			do
//			{
//				MyCalendar tmp = new MyCalendar();
//				tmp.setId(mCursor.getInt(mCursor.getColumnIndex(KEY_ROWID)));
//				tmp.setName((mCursor.getString(mCursor.getColumnIndex(KEY_NAME))));
//				deviceArray.add(tmp);
//
//			} while (mCursor.moveToNext());
//		}
//		return deviceArray;
//	}
//
//}
//package dix.walton.moore.db;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import org.gmuter.model.MyCalendar;
//
//import java.util.ArrayList;
//
//public class CalendarsDBAdaptor {
//
//	public static final String KEY_ROWID = "_id";
//	public static final String KEY_NAME = "name";
//	private static final String DATABASE_TABLE = "Calendars";
//	private Context context;
//	private SQLiteDatabase database;
//	private DatabaseConnector dbHelper;
//
//	public CalendarsDBAdaptor(Context context) {
//		this.context = context;
//	}
//
//	public CalendarsDBAdaptor open() throws SQLException {
//		dbHelper = new DatabaseConnector(context);
//		database = dbHelper.getWritableDatabase();
//		return this;
//	}
//
//	public void close() {
//		dbHelper.close();
//	}
//
//	public long createCalendar(MyCalendar calendar) {
//		ContentValues values = new ContentValues();
//
//		values.put(KEY_ROWID,calendar.getId());
//		values.put(KEY_NAME,calendar.getName());
//
//		return database.insert(DATABASE_TABLE, null, values);
//	}
//
//	public boolean updateMyCalendar(MyCalendar calendar)
//	{
//		ContentValues values = new ContentValues();
//
//		values.put(KEY_NAME, calendar.getName());
//
//		return database.update(DATABASE_TABLE, values, KEY_ROWID + "=" + calendar.getId(), null) > 0;
//	}
//
//	public boolean deleteMyCalendar(MyCalendar calendar) {
//		return database.delete(DATABASE_TABLE, KEY_ROWID + "=" + calendar.getId(), null) > 0;
//	}
//
//	public MyCalendar getMyCalendar(long rowId) throws SQLException {
////		ArrayList<MyCalendar> deviceArray = new ArrayList<MyCalendar>();
//		Cursor mCursor = database.query(
//				true,
//				DATABASE_TABLE,
//				new String[] {
//						KEY_ROWID,
//						KEY_NAME
//						},
//				KEY_ROWID + "=" + rowId, null, null, null, null, null);
//		if (mCursor.isLast())
//		{
////			do
////			{
//				MyCalendar tmp = new MyCalendar();
//				tmp.setId(mCursor.getInt(mCursor.getColumnIndex(KEY_ROWID)));
//				tmp.setName((mCursor.getString(mCursor.getColumnIndex(KEY_NAME))));
//				return tmp;
//				//
////				deviceArray.add(tmp);
////
////			} while (mCursor.moveToNext());
//		} else {
//			return null;
//		}
//
//	}
//
//	public MyCalendar getMyCalendar(String calendarName) throws SQLException {
////		ArrayList<MyCalendar> deviceArray = new ArrayList<MyCalendar>();
////		Cursor mCursor = database.query(
////				true,
////				DATABASE_TABLE,
////				new String[] {
////						KEY_ROWID,
////						KEY_NAME
////						},
////				KEY_NAME + "=" + calendarName, null, null, null, null, null);
//		String qryStr = "SELECT name FROM Calendars WHERE name=\"" + calendarName + "\" AND _id NOT NULL;";
//		System.out.println(qryStr);
//		Cursor mCursor = database.rawQuery(qryStr , null);
//		if (mCursor.isLast())
//		{
////			do
////			{
//				MyCalendar tmp = new MyCalendar();
//				tmp.setId(mCursor.getInt(mCursor.getColumnIndex(KEY_ROWID)));
//				tmp.setName((mCursor.getString(mCursor.getColumnIndex(KEY_NAME))));
//				return tmp;
//				//
////				deviceArray.add(tmp);
////
////			} while (mCursor.moveToNext());
//		} else {
//			return null;
//		}
//
//	}
//
//	public ArrayList<MyCalendar> getAllMyCalendars() throws SQLException {
//		ArrayList<MyCalendar> deviceArray = new ArrayList<MyCalendar>();
//		Cursor mCursor = database.query(
//				true,
//				DATABASE_TABLE,
//				new String[] {
//						KEY_ROWID,
//						KEY_NAME
//						},
//				null, null, null, null, null, null);
//		if (mCursor.moveToFirst())
//		{
//			do
//			{
//				MyCalendar tmp = new MyCalendar();
//				tmp.setId(mCursor.getInt(mCursor.getColumnIndex(KEY_ROWID)));
//				tmp.setName((mCursor.getString(mCursor.getColumnIndex(KEY_NAME))));
//				deviceArray.add(tmp);
//
//			} while (mCursor.moveToNext());
//		}
//		return deviceArray;
//	}
//
//}
