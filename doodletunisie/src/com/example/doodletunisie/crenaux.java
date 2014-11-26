package com.example.doodletunisie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class crenaux extends SQLiteOpenHelper {
	SQLiteDatabase db;

	private static final String DATA_NAME = "baseevent"; // nom de la base
	// donnée
	private static final int DATA_VERSION = 1; // version de la base
	private static final String TABLE_NAME = "creanaux"; // nom de table

	// les attributs de table_user
	private static final String KEY_DATE = "date";
	private static final String KEY_HORAIREDEBU = "horaire_debut";
	private static final String KEY_HORAIREFIN = "horaire_fin";
	private static final String KEY_ID = "id";

	public crenaux(Context context) {
		super(context, DATA_NAME, null, DATA_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_DATE
				+ " TEXT NOT NULL, " + KEY_HORAIREDEBU + " TEXT NOT NULL, "
				+ KEY_HORAIREFIN + " TEXT NOT NULL);");

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

	public void add(String date, String horaidebu, String horaifin) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(KEY_DATE, date);
		values.put(KEY_HORAIREDEBU, horaidebu);
		values.put(KEY_HORAIREFIN, horaifin);
		db.insert(TABLE_NAME, null, values);
		db.close();

	}

	public boolean recherche(String date) throws SQLException {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME
				+ " WHERE date=? ", new String[] { KEY_DATE, KEY_HORAIREDEBU,
				KEY_HORAIREFIN});
		if (mCursor != null) {
			if (mCursor.getCount() > 0) {
				return true;
			}
		}
		return false;
	}

}
