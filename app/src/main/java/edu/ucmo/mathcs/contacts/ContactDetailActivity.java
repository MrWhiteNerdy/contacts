package edu.ucmo.mathcs.contacts;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import edu.ucmo.mathcs.contacts.db.ContactOperations;

public class ContactDetailActivity extends AppCompatActivity {

    ContactDetailFragment contactDetailFragment;
    SocialMediaDetailFragment socialMediaDetailFragment;

    private Contact contact;

    private ContactOperations contactOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail_fragment);

        contactOperations = new ContactOperations(this);
        contactOperations.open();

        Intent intent = getIntent();
        contact = (Contact) intent.getSerializableExtra("contact");

        Bundle bundle = new Bundle();
        bundle.putSerializable("contact", contact);

        contactDetailFragment = new ContactDetailFragment();
        contactDetailFragment.setArguments(bundle);
        socialMediaDetailFragment = new SocialMediaDetailFragment();
        socialMediaDetailFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().add(R.id.container, contactDetailFragment).commit();

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Contact"));
        tabs.addTab(tabs.newTab().setText("Social Media"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment selected = null;
                if (position == 0) {
                    selected = contactDetailFragment;
                }
                else if (position == 1) {
                    selected = socialMediaDetailFragment;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 102) {
            if (data != null && data.getSerializableExtra("contact") != null) {
                Contact updatedContact = (Contact) data.getSerializableExtra("contact");
                getSupportFragmentManager().beginTransaction().replace(R.id.container, contactDetailFragment).commit();
            } else {
                Toast.makeText(this, "Contact not updated", Toast.LENGTH_LONG).show();
            }
        }
    }

}
