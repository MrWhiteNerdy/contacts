package edu.ucmo.mathcs.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import edu.ucmo.mathcs.contacts.db.ContactOperations;

public class ContactDetailActivity extends AppCompatActivity {

    private ContactDetailFragment contactDetailFragment;
    private SocialMediaDetailFragment socialMediaDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        ContactOperations contactOperations = new ContactOperations(this);
        contactOperations.open();

        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("contact");

        Bundle bundle = new Bundle();
        bundle.putSerializable("contact", contact);

        contactDetailFragment = new ContactDetailFragment();
        contactDetailFragment.setArguments(bundle);
        socialMediaDetailFragment = new SocialMediaDetailFragment();
        socialMediaDetailFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().add(R.id.container, contactDetailFragment).commit();

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Contact Info"));
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

    public Fragment getSocialMediaDetailFragment() {
        return socialMediaDetailFragment;
    }

}
