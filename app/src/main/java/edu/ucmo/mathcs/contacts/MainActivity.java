package edu.ucmo.mathcs.contacts;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import edu.ucmo.mathcs.contacts.db.ContactDBHandler;
import edu.ucmo.mathcs.contacts.db.ContactOperations;

public class MainActivity extends ListActivity implements AppCompatCallback {

    private ContactOperations contactOperations;

    private SimpleCursorAdapter adapter;

    private AppCompatDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        delegate = AppCompatDelegate.create(this, this);
        delegate.onCreate(savedInstanceState);

        delegate.setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        delegate.setSupportActionBar(toolbar);

        contactOperations = new ContactOperations(this);
        contactOperations.open();

        String[] fromColumns = new String[]{
                ContactDBHandler.COLUMN_FIRST_NAME,
                ContactDBHandler.COLUMN_LAST_NAME
        };

        int[] toViews = {
                R.id.item_contact_picture,
                R.id.item_contact_name
        };

        adapter = new ContactCursorAdapter(this, R.layout.item_contact,
                contactOperations.getAllContacts(), fromColumns, toViews, 0);

        ListView listView = getListView();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Contact contact = contactOperations.getContact(id);
                Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);
                intent.putExtra("contact", contact);
                startActivityForResult(intent, 103);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
                startActivityForResult(intent, 101);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) {
            if (data != null && data.getSerializableExtra("contact") != null) {
                adapter.swapCursor(contactOperations.getAllContacts());
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Contact not added", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == 103) {
            adapter.swapCursor(contactOperations.getAllContacts());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        delegate.getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onSupportActionModeStarted(ActionMode mode) {

    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {

    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }

}
