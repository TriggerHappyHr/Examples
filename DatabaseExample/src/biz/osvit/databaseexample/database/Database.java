package biz.osvit.databaseexample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import biz.osvit.databaseexample.models.UserModel;
import biz.osvit.databaseexample.models.UserModelWrapper;
import biz.osvit.databaseexample.utils.C;

public class Database {

	private DatabaseHelper mDatabaseHelper;
	public SQLiteDatabase mSqlDatabase;

	public Database(Context context) {
		mDatabaseHelper = new DatabaseHelper(context);
	}

	public class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, C.DatabaseConstants.DATABASE_NAME, null, C.DatabaseConstants.DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			String createDatabaseCmd = "create table " + C.DatabaseConstants.TABLE_USER_NAME + " ("
					+ C.DatabaseConstants.TABLE_USER_ID + " integer primary key autoincrement, "
					+ C.DatabaseConstants.TABLE_USER_FIRST_NAME + " text, "
					+ C.DatabaseConstants.TABLE_USER_LAST_NAME + " text, "
					+ C.DatabaseConstants.TABLE_USER_GENDER + " text, "
					+ C.DatabaseConstants.TABLE_USER_PHONE_NUMBER + " text);";
			db.execSQL(createDatabaseCmd);

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			onCreate(db);
		}				

		
	}
	
	public void insertUser(UserModel user) {

		mSqlDatabase = mDatabaseHelper.getWritableDatabase();

		ContentValues contentValues = new ContentValues();
		contentValues.put(C.DatabaseConstants.TABLE_USER_FIRST_NAME, user.getFirstName());
		contentValues.put(C.DatabaseConstants.TABLE_USER_LAST_NAME, user.getLastName());
		contentValues.put(C.DatabaseConstants.TABLE_USER_GENDER, user.getGender());
		contentValues.put(C.DatabaseConstants.TABLE_USER_PHONE_NUMBER, user.getPhoneNumber());
		mSqlDatabase.insertWithOnConflict(C.DatabaseConstants.TABLE_USER_NAME, null, contentValues, 0);

		mDatabaseHelper.close();
	}	
	
	public UserModelWrapper getAllUsers() {

		UserModelWrapper users = new UserModelWrapper();
		mSqlDatabase = mDatabaseHelper.getReadableDatabase();
		Cursor cursor = mSqlDatabase.query(C.DatabaseConstants.TABLE_USER_NAME, null, null, null, null, null, null);
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex(C.DatabaseConstants.TABLE_USER_ID));
			String firstName = cursor.getString(cursor.getColumnIndex(C.DatabaseConstants.TABLE_USER_FIRST_NAME));
			String lastName = cursor.getString(cursor.getColumnIndex(C.DatabaseConstants.TABLE_USER_LAST_NAME));
			String gender = cursor.getString(cursor.getColumnIndex(C.DatabaseConstants.TABLE_USER_GENDER));
			String phoneNumber = cursor.getString(cursor.getColumnIndex(C.DatabaseConstants.TABLE_USER_PHONE_NUMBER));

			UserModel user = new UserModel();
			user.setId(id);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setGender(gender);
			user.setPhoneNumber(phoneNumber);
			users.addUser(user);
		}

		mDatabaseHelper.close();
		cursor.close();
		return users;
	}

}
