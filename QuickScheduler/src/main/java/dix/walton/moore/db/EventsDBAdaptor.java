//package dix.walton.moore.db;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import org.gmuter.model.MyEvent;
//
//import java.util.ArrayList;
//
//public class EventsDBAdaptor {
//
//	public static final String KEY_ROWID = "_id";
//	public static final String KEY_TITLE = "title";
//	public static final String KEY_MUTE = "mute";
//	public static final String KEY_UNMUTE = "unmute";
//	private static final String DATABASE_TABLE = "Events";
//	private Context context;
//	private SQLiteDatabase database;
//	private DatabaseConnector dbHelper;
//
//	public EventsDBAdaptor(Context context) {
//		this.context = context;
//	}
//
//	public EventsDBAdaptor open() throws SQLException {
//		dbHelper = new DatabaseConnector(context);
//		database = dbHelper.getWritableDatabase();
//		return this;
//	}
//
//	public void close() {
//		dbHelper.close();
//	}
//
//	public long createEvent(MyEvent event) {
//		ContentValues values = new ContentValues();
//
//		values.put(KEY_ROWID,event.getId());
//		values.put(KEY_MUTE,event.getMuteStr());
//		values.put(KEY_UNMUTE,event.getUnmuteStr());
//		values.put(KEY_TITLE,event.getTitle());
//
//		return database.insert(DATABASE_TABLE, null, values);
//	}
//
//	public boolean updateMyEvent(MyEvent event)
//	{
//		ContentValues values = new ContentValues();
//
//		values.put(KEY_ROWID,event.getId());
//		values.put(KEY_MUTE,event.getMuteStr());
//		values.put(KEY_UNMUTE,event.getUnmuteStr());
//		values.put(KEY_TITLE,event.getTitle());
//
//		return database.update(DATABASE_TABLE, values, KEY_ROWID + "=" + event.getId(), null) > 0;
//	}
//
//	public boolean deleteMyEvent(MyEvent event) {
//		return database.delete(DATABASE_TABLE, KEY_ROWID + "=" + event.getId(), null) > 0;
//	}
//
//	public MyEvent getMyEvent(long rowId) throws SQLException {
////		ArrayList<MyEvent> deviceArray = new ArrayList<MyEvent>();
//		Cursor mCursor = database.query(
//				true,
//				DATABASE_TABLE,
//				new String[] {
//						KEY_ROWID,
//						KEY_MUTE,
//						KEY_UNMUTE,
//						KEY_TITLE
//						},
//				KEY_ROWID + "=" + rowId, null, null, null, null, null);
//		if (mCursor.isLast())
//		{
////			do
////			{
//				MyEvent tmp = new MyEvent();
//				tmp.setId(mCursor.getInt(mCursor.getColumnIndex(KEY_ROWID)));
//				tmp.setMute((mCursor.getString(mCursor.getColumnIndex(KEY_MUTE))));
//				tmp.setUnmute((mCursor.getString(mCursor.getColumnIndex(KEY_UNMUTE))));
//				tmp.setTitle((mCursor.getString(mCursor.getColumnIndex(KEY_TITLE))));
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
//	public MyEvent getMyEvent(String eventMuteStr) throws SQLException {
//
//		String qryStr = "SELECT mute FROM Events WHERE mute=\"" + eventMuteStr + "\";";
//		System.out.println(qryStr);
//		Cursor mCursor = database.rawQuery(qryStr , null);
//		if (mCursor.moveToFirst())
//		{
//			MyEvent tmp = new MyEvent();
//			tmp.setMute((mCursor.getString(mCursor.getColumnIndex(KEY_MUTE))));
//			return tmp;
//		} else {
//			return null;
//		}
//
//	}
//
//	public ArrayList<MyEvent> getAllMyEvents() throws SQLException {
//		ArrayList<MyEvent> eventArray = new ArrayList<MyEvent>();
//		Cursor mCursor = database.query(
//				true,
//				DATABASE_TABLE,
//				new String[] {
//						KEY_ROWID,
//						KEY_MUTE,
//						KEY_UNMUTE,
//						KEY_TITLE
//						},
//				null, null, null, null, null, null);
//		if (mCursor.moveToFirst())
//		{
//			do
//			{
//				MyEvent tmp = new MyEvent();
//				tmp.setId(mCursor.getInt(mCursor.getColumnIndex(KEY_ROWID)));
//				tmp.setMute((mCursor.getString(mCursor.getColumnIndex(KEY_MUTE))));
//				tmp.setUnmute((mCursor.getString(mCursor.getColumnIndex(KEY_UNMUTE))));
//				tmp.setTitle((mCursor.getString(mCursor.getColumnIndex(KEY_TITLE))));
//				eventArray.add(tmp);
//
//			} while (mCursor.moveToNext());
//		}
//		return eventArray;
//	}
//
//}
