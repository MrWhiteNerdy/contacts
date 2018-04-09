package edu.ucmo.mathcs.contacts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ContactDetailActivity extends AppCompatActivity {

    private TextView nameTextView, phoneNumberTextView, facebookUsernameTextView,
            twitterUsernameTextView, instagramUsernameTextView, snapchatUsernameTextView,
            linkedinUsernameTextView;

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        Intent intent = getIntent();
        contact = (Contact) intent.getSerializableExtra("contact");

        nameTextView = findViewById(R.id.contact_detail_name);
        phoneNumberTextView = findViewById(R.id.contact_detail_phone_number);
        facebookUsernameTextView = findViewById(R.id.contact_detail_facebook_username);
        twitterUsernameTextView = findViewById(R.id.contact_detail_twitter_username);
        instagramUsernameTextView = findViewById(R.id.contact_detail_instagram_username);
        snapchatUsernameTextView = findViewById(R.id.contact_detail_snapchat_username);
        linkedinUsernameTextView = findViewById(R.id.contact_detail_linkedin_username);

        refreshContact(contact);

        TableRow facebookTableRow = findViewById(R.id.contact_detail_facebook_row);
        facebookTableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/" + contact.getFacebookUsername()));
                startActivity(intent);
            }
        });

        TableRow twitterTableRow = findViewById(R.id.contact_detail_twitter_row);
        twitterTableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://twitter.com/" + contact.getTwitterUsername()));
                startActivity(intent);
            }
        });

        TableRow instagramTableRow = findViewById(R.id.contact_detail_instagram_row);
        instagramTableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.instagram.com/" + contact.getInstagramUsername()));
                startActivity(intent);
            }
        });

        TableRow snapchatTableRow = findViewById(R.id.contact_detail_snapchat_row);
        snapchatTableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://snapchat.com/add/" + contact.getSnapchatUsername()));
                startActivity(intent);
            }
        });

        TableRow linkedinTableRow = findViewById(R.id.contact_detail_linkedin_row);
        linkedinTableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.linkedin.com/" + contact.getFacebookUsername()));
                startActivity(intent);
            }
        });

        Button callButton = findViewById(R.id.contact_detail_call_button);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + contact.getPhoneNumber()));
                startActivity(intent);
            }
        });

        Button textButton = findViewById(R.id.contact_detail_text_button);
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms: " + contact.getPhoneNumber()));
                startActivity(intent);
            }
        });

        Button editButton = findViewById(R.id.contact_detail_edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactDetailActivity.this, EditContactActivity.class);
                intent.putExtra("contact", contact);
                startActivityForResult(intent, 102);
            }
        });

        Button deleteButton = findViewById(R.id.contact_detail_delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Implement delete contact and refresh list view
                Toast.makeText(ContactDetailActivity.this, "Delete button pressed", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 102) {
            if (data != null && data.getSerializableExtra("contact") != null) {
                Contact updatedContact = (Contact) data.getSerializableExtra("contact");
                refreshContact(updatedContact);
            } else {
                Toast.makeText(this, "Contact not updated", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void refreshContact(Contact contact) {
        nameTextView.setText(contact.getFirstName() + " " + contact.getLastName());
        phoneNumberTextView.setText(contact.getPhoneNumber());
        facebookUsernameTextView.setText(contact.getFacebookUsername());
        twitterUsernameTextView.setText("@" + contact.getTwitterUsername());
        instagramUsernameTextView.setText("@" + contact.getInstagramUsername());
        snapchatUsernameTextView.setText("@" + contact.getSnapchatUsername());
        linkedinUsernameTextView.setText(contact.getLinkedinUsername());
    }

}
