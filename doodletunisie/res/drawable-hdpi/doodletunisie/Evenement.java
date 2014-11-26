package com.example.doodletunisie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class Evenement extends SQLiteOpenHelper {
	SQLiteDatabase db;

	private static final String DATA_NAME = "baseevent"; // nom de la base
	// donnée
	private static final int DATA_VERSION = 1; // version de la base
	private static final String TABLE_NAME = "Evenementss"; // nom de table

	// les attributs de table_user
	private static final String KEY_LIEU = "lieu";
	private static final String KEY_TITRE = "titre";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_DATEEVENT = "date_event";
	private static final String KEY_ID = "id";

	public Evenement(Context context) {
		super(context, DATA_NAME, null, DATA_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_LIEU
				+ " TEXT NOT NULL, " + KEY_TITRE + " TEXT NOT NULL, "
				+ KEY_DESCRIPTION + " TEXT NOT NULL, " + KEY_DATEEVENT +" TEXT);");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String Delete_table = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(Delete_table);
		onCreate(db);

	}
	public void open() throws SQLiteException {

		db = this.getWritableDatabase();

	}

	public void close() {

		db.close();
	}

	public void add(String lieu, String titre, String desciption,String date_event) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(KEY_LIEU, lieu);
		values.put(KEY_TITRE, titre);
		values.put(KEY_DESCRIPTION, desciption);
		values.put(KEY_DATEEVENT, date_event);
		db.insert(TABLE_NAME, null, values);
		db.close();

	}

	public boolean recherche(String name) throws SQLException {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME
				+ " WHERE titre=? ", new String[] { KEY_LIEU, KEY_TITRE,
				KEY_DESCRIPTION, KEY_DATEEVENT });
		if (mCursor != null) {
			if (mCursor.getCount() > 0) {
				return true;
			}
		}
		return false;
	}

}
