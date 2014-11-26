package com.example.doodletunisie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Authent extends SQLiteOpenHelper {
	private static final String DATA_NAME = "baseevent"; // nom de la base
															// donnée
	private static final int DATA_VERSION = 1; // version de la base
	private static final String TABLE_NAME = "participant"; // nom de table

	// les attributs de table_user
	private static final String KEY_LOGIN = "login";
	private static final String KEY_PASSORD = "password";
	private static final String KEY_NOM = "nom";
	private static final String KEY_PRENOM = "prenom";
	private static final String KEY_ADRESSE = "adresse";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_ID = "id";

	public Authent(Context context) {
		super(context, DATA_NAME, null, DATA_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String Create_table = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NOM
				+ " TEXT NOT NULL," + KEY_PRENOM + " TEXT NOT NULL," + KEY_LOGIN + " TEXT NOT NULL," + KEY_ADRESSE + " TEXT NOT NULL," + KEY_PASSORD + " TEXT NOT NULL," + KEY_EMAIL + " TEXT NOT NULL" + ")";
		db.execSQL(Create_table);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String Delete_table = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(Delete_table);
		onCreate(db);

	}

	public void add(String nom,String prenom,String login,String adresse, String pass,String email) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(KEY_NOM, nom);
		values.put(KEY_PRENOM, prenom);
		values.put(KEY_LOGIN, login);
		values.put(KEY_ADRESSE, adresse);
		values.put(KEY_PASSORD, pass);
		values.put(KEY_EMAIL, email);
		db.insert(TABLE_NAME, null, values);

	}

	public boolean Login(String username, String password) throws SQLException {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME
				+ " WHERE login=? AND password=?", new String[] { username,
				password });
		if (mCursor != null) {
			if (mCursor.getCount() > 0) {
				return true;
			}
		}
		return false;
	}

}
