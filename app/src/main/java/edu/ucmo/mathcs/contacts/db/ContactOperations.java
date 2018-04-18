package edu.ucmo.mathcs.contacts.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.ucmo.mathcs.contacts.Contact;

public class ContactOperations {

    private SQLiteOpenHelper dbHandler;
    private SQLiteDatabase database;

    private static final String[] ALL_COLUMNS = {
            ContactDBHandler.COLUMN_ID,
            ContactDBHandler.COLUMN_FIRST_NAME,
            ContactDBHandler.COLUMN_LAST_NAME,
            ContactDBHandler.COLUMN_PHONE_NUMBER,
            ContactDBHandler.COLUMN_FACEBOOK_USERNAME,
            ContactDBHandler.COLUMN_TWITTER_USERNAME,
            ContactDBHandler.COLUMN_INSTAGRAM_USERNAME,
            ContactDBHandler.COLUMN_LINKEDIN_USERNAME,
            ContactDBHandler.COLUMN_SNAPCHAT_USERNAME
    };

    public ContactOperations(Context context) {
        dbHandler = new ContactDBHandler(context);
    }

    public void open() {
        database = dbHandler.getWritableDatabase();
    }

    public void close() {
        dbHandler.close();
    }

    public Contact addContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(ContactDBHandler.COLUMN_FIRST_NAME, contact.getFirstName());
        values.put(ContactDBHandler.COLUMN_LAST_NAME, contact.getLastName());
        values.put(ContactDBHandler.COLUMN_PHONE_NUMBER, contact.getPhoneNumber());
        values.put(ContactDBHandler.COLUMN_FACEBOOK_USERNAME, contact.getFacebookUsername());
        values.put(ContactDBHandler.COLUMN_TWITTER_USERNAME, contact.getTwitterUsername());
        values.put(ContactDBHandler.COLUMN_INSTAGRAM_USERNAME, contact.getInstagramUsername());
        values.put(ContactDBHandler.COLUMN_LINKEDIN_USERNAME, contact.getLinkedinUsername());
        values.put(ContactDBHandler.COLUMN_SNAPCHAT_USERNAME, contact.getSnapchatUsername());

        long insertId = database.insert(ContactDBHandler.TABLE_CONTACTS, null, values);
        contact.setId(insertId);

        return contact;
    }

    public Contact getContact(long id) {
        Cursor cursor = database.query(ContactDBHandler.TABLE_CONTACTS, ALL_COLUMNS,
                ContactDBHandler.COLUMN_ID + "= ?", new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        return new Contact(cursor.getLong(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6), cursor.getString(7),
                cursor.getString(8));
    }

    public Cursor getAllContacts() {
        Cursor cursor = database.query(ContactDBHandler.TABLE_CONTACTS, ALL_COLUMNS, null,
                null, null, null, ContactDBHandler.COLUMN_LAST_NAME);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public Contact updateContact(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(ContactDBHandler.COLUMN_FIRST_NAME, contact.getFirstName());
        values.put(ContactDBHandler.COLUMN_LAST_NAME, contact.getLastName());
        values.put(ContactDBHandler.COLUMN_PHONE_NUMBER, contact.getPhoneNumber());
        values.put(ContactDBHandler.COLUMN_FACEBOOK_USERNAME, contact.getFacebookUsername());
        values.put(ContactDBHandler.COLUMN_TWITTER_USERNAME, contact.getTwitterUsername());
        values.put(ContactDBHandler.COLUMN_INSTAGRAM_USERNAME, contact.getInstagramUsername());
        values.put(ContactDBHandler.COLUMN_LINKEDIN_USERNAME, contact.getLinkedinUsername());
        values.put(ContactDBHandler.COLUMN_SNAPCHAT_USERNAME, contact.getSnapchatUsername());

        database.update(ContactDBHandler.TABLE_CONTACTS, values,
                ContactDBHandler.COLUMN_ID + "= ?",
                new String[]{String.valueOf(contact.getId())});

        return getContact(contact.getId());
    }

    public void deleteContact(Contact contact) {
        database.delete(ContactDBHandler.TABLE_CONTACTS,
                ContactDBHandler.COLUMN_ID + "= ?",
                new String[]{String.valueOf(contact.getId())});
    }

}
