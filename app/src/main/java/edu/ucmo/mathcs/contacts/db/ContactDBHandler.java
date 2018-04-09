package edu.ucmo.mathcs.contacts.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contacts_database";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_FACEBOOK_USERNAME = "facebook_username";
    public static final String COLUMN_TWITTER_USERNAME = "twitter_username";
    public static final String COLUMN_INSTAGRAM_USERNAME = "instagram_username";
    public static final String COLUMN_LINKEDIN_USERNAME = "linkedin_username";
    public static final String COLUMN_SNAPCHAT_USERNAME = "snapchat_username";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_CONTACTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_FIRST_NAME + " TEXT, " +
                    COLUMN_LAST_NAME + " TEXT, " +
                    COLUMN_PHONE_NUMBER + " TEXT, " +
                    COLUMN_FACEBOOK_USERNAME + " TEXT, " +
                    COLUMN_TWITTER_USERNAME + " TEXT, " +
                    COLUMN_INSTAGRAM_USERNAME + " TEXT, " +
                    COLUMN_LINKEDIN_USERNAME + " TEXT, " +
                    COLUMN_SNAPCHAT_USERNAME + " TEXT" +
                    ")";

    public ContactDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

}
