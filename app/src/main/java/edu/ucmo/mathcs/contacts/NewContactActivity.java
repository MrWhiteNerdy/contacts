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

public class NewContactActivity extends AppCompatActivity {

    private ContactOperations contactOperations;

    private EditText firstNameEditText, lastNameEditText, phoneNumberEditText, facebookEditText,
            twitterEditText, instagramEditText, linkedinEditText, snapchatEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        contactOperations = new ContactOperations(this);
        contactOperations.open();

        firstNameEditText = findViewById(R.id.new_contact_first_name_edit_text);
        lastNameEditText = findViewById(R.id.new_contact_last_name_edit_text);
        phoneNumberEditText = findViewById(R.id.new_contact_phone_number_edit_text);
        facebookEditText = findViewById(R.id.new_contact_facebook_edit_text);
        twitterEditText = findViewById(R.id.new_contact_twitter_edit_text);
        instagramEditText = findViewById(R.id.new_contact_instagram_edit_text);
        linkedinEditText = findViewById(R.id.new_contact_linkedin_edit_text);
        snapchatEditText = findViewById(R.id.new_contact_snapchat_edit_text);

        Button saveButton = findViewById(R.id.new_contact_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = addNewContact();

                Intent intent = new Intent();
                intent.putExtra("contact", contact);

                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });

        Button cancelButton = findViewById(R.id.new_contact_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private Contact addNewContact() {
        String formattedNumber = PhoneNumberUtils.formatNumber(phoneNumberEditText.getText().toString(), "US");

        Contact contact = new Contact(firstNameEditText.getText().toString(),
                lastNameEditText.getText().toString(), formattedNumber,
                facebookEditText.getText().toString(), twitterEditText.getText().toString(),
                instagramEditText.getText().toString(), linkedinEditText.getText().toString(),
                snapchatEditText.getText().toString());

        return contactOperations.addContact(contact);
    }
}
