package com.example.ap3_m2l_android;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bdFormM2L";
 
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

        db.execSQL("INSERT INTO users VALUES(1,'a@gmail.com',123)");
        db.execSQL("INSERT INTO users VALUES(2,'b@gmail.com',456)");

        String CREATE_DOMAINE_TABLE = "CREATE TABLE domaine ( " +
                "idD INTEGER PRIMARY KEY, " +
                "libelle TEXT)";

        db.execSQL(CREATE_DOMAINE_TABLE);

        db.execSQL("INSERT INTO domaine VALUES(1,'Gestion')");
        db.execSQL("INSERT INTO domaine VALUES(2,'Informatique')");
        db.execSQL("INSERT INTO domaine VALUES(3,'Développement durable')");
        db.execSQL("INSERT INTO domaine VALUES(4,'Secourisme')");
        db.execSQL("INSERT INTO domaine VALUES(5,'Communication')");

        String CREATE_FORMATION_TABLE = "CREATE TABLE formation ( " +
                "idDom INTEGER, "+
                "idF INTEGER, " +
                "libelle TEXT, "+
                "description TEST, "+
                "PRIMARY KEY(idDom,idF), "+
                "FOREIGN KEY(idDom) REFERENCES domaine(idD))";

        db.execSQL(CREATE_FORMATION_TABLE);

        db.execSQL("INSERT INTO formation VALUES(1,1,'Gestion 1', 'Description')");
        db.execSQL("INSERT INTO formation VALUES(1,2,'Gestion 2', 'Description')");
        db.execSQL("INSERT INTO formation VALUES(2,1,'Informatique 1', 'Description')");
        db.execSQL("INSERT INTO formation VALUES(2,2,'Informatique 2', 'Description')");
        db.execSQL("INSERT INTO formation VALUES(3,1,'Développement durable 1', 'Description')");
        db.execSQL("INSERT INTO formation VALUES(3,2,'Développement durable 2', 'Description')");
        db.execSQL("INSERT INTO formation VALUES(4,1,'Secourisme 1', 'Description')");
        db.execSQL("INSERT INTO formation VALUES(4,2,'Secourisme 2', 'Description')");
        db.execSQL("INSERT INTO formation VALUES(5,1,'Communication 1', 'Description')");
        db.execSQL("INSERT INTO formation VALUES(5,2,'Communication 2', 'Description')");

        String CREATE_SESSION_TABLE = "CREATE TABLE session ( " +
                "idDom INTEGER, "+
                "idForm INTEGER, "+
                "idS INTEGER, " +
                "dateDebut DATE, " +
                "dateFin DATE, "+
                "PRIMARY KEY(idS, idForm, idDom), "+
                "FOREIGN KEY(idDom) REFERENCES domaine(idD), "+
                "FOREIGN KEY(idForm) REFERENCES formation(idF))";

        db.execSQL(CREATE_SESSION_TABLE);

        db.execSQL("INSERT INTO session VALUES(1,1,1,'2023-09-01','2023-09-03')");
        db.execSQL("INSERT INTO session VALUES(1,1,2,'2023-09-10','2023-09-12')");
        db.execSQL("INSERT INTO session VALUES(1,2,1,'2023-09-20','2023-09-21')");
        db.execSQL("INSERT INTO session VALUES(1,2,2,'2023-09-28','2023-09-30')");
        db.execSQL("INSERT INTO session VALUES(2,1,1,'2023-10-01','2023-10-05')");
        db.execSQL("INSERT INTO session VALUES(2,1,2,'2023-10-15','2023-09-20')");
        db.execSQL("INSERT INTO session VALUES(2,2,1,'2023-10-23','2023-10-24')");
        db.execSQL("INSERT INTO session VALUES(2,2,2,'2023-10-30','2023-10-31')");
        db.execSQL("INSERT INTO session VALUES(3,1,1,'2023-11-05','2023-11-07')");
        db.execSQL("INSERT INTO session VALUES(3,1,2,'2023-11-13','2023-11-15')");
        db.execSQL("INSERT INTO session VALUES(3,2,1,'2023-11-20','2023-11-23')");
        db.execSQL("INSERT INTO session VALUES(3,2,2,'2023-12-05','2023-12-09')");
        db.execSQL("INSERT INTO session VALUES(4,1,1,'2023-12-15','2023-12-15')");
        db.execSQL("INSERT INTO session VALUES(4,1,2,'2023-12-20','2023-12-20')");
        db.execSQL("INSERT INTO session VALUES(4,2,1,'2024-01-02','2024-01-06')");
        db.execSQL("INSERT INTO session VALUES(4,2,2,'2024-01-10','2024-01-14')");
        db.execSQL("INSERT INTO session VALUES(5,1,1,'2024-01-20','2024-01-23')");
        db.execSQL("INSERT INTO session VALUES(5,1,2,'2024-02-01','2024-02-04')");
        db.execSQL("INSERT INTO session VALUES(5,2,1,'2024-02-10','2024-02-12')");
        db.execSQL("INSERT INTO session VALUES(5,2,2,'2024-02-20','2024-02-22')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
    }
    
    /**
     * CRUD operations (create "add", read "get", update, delete) book + get all books + delete all books
     */
 
    private static final String TABLE_USERS = "users";
    private static final String TABLE_DOMAINE = "domaine";
    private static final String TABLE_FORMATION = "formation";
    private static final String TABLE_SESSION = "session";

    private static final String USERS_KEY_ID = "id";
    private static final String USERS_KEY_LOGIN = "login";
    private static final String USERS_KEY_PASSWORD = "password";

    private static final String[] USERS_COLUMNS = {USERS_KEY_ID,USERS_KEY_LOGIN,USERS_KEY_PASSWORD};

    public boolean login(String login, String password) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE login=? AND password=?", new String[]{login,password});
    	if (mCursor != null) {
	    	if(mCursor.getCount() > 0)
	    	{
	    		return true;
	    	}
	    }
	    return false;
	}

    public String[] getDomaines() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT libelle FROM " + TABLE_DOMAINE, new String[]{});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                String[] res = new String[mCursor.getCount()];
                mCursor.moveToFirst();
                for (int i = 0; i < mCursor.getCount(); i++){
                    res[i] = mCursor.getString(0);
                    mCursor.moveToNext();
                }
                return res;
            }
        }
        return new String[]{};
    }

    public String[] getFormations(int idD) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT libelle FROM " + TABLE_FORMATION + " WHERE idDom=?", new String[]{Integer.toString(idD)});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                String[] res = new String[mCursor.getCount()];
                mCursor.moveToFirst();
                for (int i = 0; i < mCursor.getCount(); i++){
                    res[i] = mCursor.getString(0);
                    mCursor.moveToNext();
                }
                return res;
            }
        }
        return new String[]{};
    }

    public String[] getFormation(int idD, int idF) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT libelle, description FROM " + TABLE_FORMATION + " WHERE idDom=? AND idF=?", new String[]{Integer.toString(idD), Integer.toString(idF)});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                String[] res = new String[2];
                mCursor.moveToFirst();
                res[0] = mCursor.getString(0);
                res[1] = mCursor.getString(1);
                return res;
            }
        }
        return new String[]{};
    }

    public String[] getSessions(int idD, int idF) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("SELECT dateDebut, dateFin FROM " + TABLE_SESSION + " WHERE idDom=? AND idForm=?", new String[]{Integer.toString(idD), Integer.toString(idF)});
        String[] res = new String[mCursor.getCount()];
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {

                mCursor.moveToFirst();
                for (int i = 0; i < mCursor.getCount(); i++){
                    /*String Date1 = mCursor.getString(0);
                    String day1 =  Date1.substring(8,2);
                    String month1 =  Date1.substring(5,2);
                    String year1 =  Date1.substring(0,4);
                    String StrDate1 = String.format("{0}/{1}/{2}",day1, month1, year1);

                    String Date2 = mCursor.getString(1);
                    String day2 =  Date2.substring(8,2);
                    String month2 =  Date2.substring(5,2);
                    String year2 =  Date2.substring(0,4);
                    String StrDate2 = String.format("{0}/{1}/{2}",day2, month2, year2);*/

                    res[i] = "du " + mCursor.getString(0) +" au " + mCursor.getString(1);
                    mCursor.moveToNext();
                }
                return res;
            }
        }
        return res;
    }
}
