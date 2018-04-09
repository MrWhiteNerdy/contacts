package edu.ucmo.mathcs.contacts;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.ucmo.mathcs.contacts.db.ContactOperations;

public class EditContactActivity extends AppCompatActivity {

    private ContactOperations contactOperations;

    private EditText firstNameEditText, lastNameEditText, phoneNumberEditText, facebookEditText,
            twitterEditText, instagramEditText, linkedinEditText, snapchatEditText;

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        contact = (Contact) getIntent().getSerializableExtra("contact");

        contactOperations = new ContactOperations(this);
        contactOperations.open();

        firstNameEditText = findViewById(R.id.edit_contact_first_name_edit_text);
        lastNameEditText = findViewById(R.id.edit_contact_last_name_edit_text);
        phoneNumberEditText = findViewById(R.id.edit_contact_phone_number_edit_text);
        facebookEditText = findViewById(R.id.edit_contact_facebook_edit_text);
        twitterEditText = findViewById(R.id.edit_contact_twitter_edit_text);
        instagramEditText = findViewById(R.id.edit_contact_instagram_edit_text);
        linkedinEditText = findViewById(R.id.edit_contact_linkedin_edit_text);
        snapchatEditText = findViewById(R.id.edit_contact_snapchat_edit_text);

        firstNameEditText.setText(contact.getFirstName());
        lastNameEditText.setText(contact.getLastName());
        phoneNumberEditText.setText(contact.getPhoneNumber());
        facebookEditText.setText(contact.getFacebookUsername());
        twitterEditText.setText(contact.getTwitterUsername());
        instagramEditText.setText(contact.getInstagramUsername());
        linkedinEditText.setText(contact.getLinkedinUsername());
        snapchatEditText.setText(contact.getSnapchatUsername());

        Button saveButton = findViewById(R.id.edit_contact_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String formattedNumber = PhoneNumberUtils.formatNumber(phoneNumberEditText.getText().toString(),
                        "US");

                contact.setFirstName(firstNameEditText.getText().toString());
                contact.setLastName(lastNameEditText.getText().toString());
                contact.setPhoneNumber(formattedNumber);
                contact.setFacebookUsername(facebookEditText.getText().toString());
                contact.setTwitterUsername(twitterEditText.getText().toString());
                contact.setInstagramUsername(instagramEditText.getText().toString());
                contact.setSnapchatUsername(snapchatEditText.getText().toString());
                contact.setLinkedinUsername(linkedinEditText.getText().toString());

                contactOperations.updateContact(contact);

                Intent intent = new Intent();
                intent.putExtra("contact", contact);

                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });

        Button cancelButton = findViewById(R.id.edit_contact_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
