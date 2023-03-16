package com.example.ap3_m2l_android;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bdDigicode";
 
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); 
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE users ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "login TEXT, "+
                "password TEXT )";

        db.execSQL(CREATE_USER_TABLE);

        // Creation d'un jeu d'essai
        
        db.execSQL("INSERT INTO users VALUES(1,'a@gmail.com',123)");
        db.execSQL("INSERT INTO users VALUES(2,'b@gmail.com',456)");

        String CREATE_DIGICODE_TABLE = "CREATE TABLE digicode ( " +
                "salle TEXT, " +
                "date Date, " +
                "digicode TEXT, " +
                "primary key (salle, date)" +
                ");";


        db.execSQL(CREATE_DIGICODE_TABLE);

        // Creation d'un jeu d'essai

        db.execSQL("INSERT INTO digicode VALUES('Majorelle','2023-03-14','0123')");
        db.execSQL("INSERT INTO digicode VALUES('Gruber','2023-03-14','0123')");
        db.execSQL("INSERT INTO digicode VALUES('Lamour','2023-03-14','0123')");
        db.execSQL("INSERT INTO digicode VALUES('Longwy','2023-03-14','0123')");
        db.execSQL("INSERT INTO digicode VALUES('Majorelle','2023-03-15','4567')");

    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");

    }
    
    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */
 
    private static final String TABLE_USERS = "users";
    private static final String TABLE_DIGICODE = "digicode";

 
    private static final String USERS_KEY_ID = "id";
    private static final String USERS_KEY_LOGIN = "login";
    private static final String USERS_KEY_PASSWORD = "password";
    

    
    private static final String[] USERS_COLUMNS = {USERS_KEY_ID,USERS_KEY_LOGIN,USERS_KEY_PASSWORD};


    public boolean login(String login, String password) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE login=? AND password=?", new String[]{login,password});
    	
    	if (mCursor != null) {
    		// S'il y a un resultat...
	    	if(mCursor.getCount() > 0)
	    	{
	    		return true;
	    	}
	    }
    	
	    return false;
	}

    public String recupDigicode(int day, int month, int year, String salle) {

        //2015-12-17
        String Date = String.format ("{0000}-{00}-{00}", year, month, day);


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT digicode FROM " + TABLE_DIGICODE + " WHERE salle=? AND date=?", new String[]{salle,Date});

        if (mCursor != null) {
            // S'il y a un resultat...

            if(mCursor.getCount() > 0)
            {
                return mCursor.getString(0);
            }
        }

        return "";
    }
}
