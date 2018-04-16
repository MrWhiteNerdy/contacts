package edu.ucmo.mathcs.contacts;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import edu.ucmo.mathcs.contacts.db.ContactDBHandler;


public class ContactCursorAdapter extends SimpleCursorAdapter {

    private Context mContext;
    private Context appContext;
    private int layout;
    private Cursor c;
    private final LayoutInflater inflater;

    public ContactCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.mContext = context;
        this.layout = layout;
        this.inflater = LayoutInflater.from(context);
        this.c = c;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);

        TextView fullNameTextView = view.findViewById(R.id.item_contact_name);
        ImageView pictureImageView = view.findViewById(R.id.item_contact_picture);

        int firstNameIndex = cursor.getColumnIndex(ContactDBHandler.COLUMN_FIRST_NAME);
        int lastNameIndex = cursor.getColumnIndex(ContactDBHandler.COLUMN_LAST_NAME);

        String fullName = cursor.getString(firstNameIndex) + " " + cursor.getString(lastNameIndex);
        fullNameTextView.setText(fullName);

        pictureImageView.setImageResource(R.drawable.default_contact);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(layout, null);
    }
}
