package edu.ucmo.mathcs.contacts;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.ucmo.mathcs.contacts.db.ContactOperations;

public class ContactDetailFragment extends Fragment {

    private Contact contact;

    private ContactOperations contactOperations;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        contactOperations = new ContactOperations(getActivity());
        contactOperations.open();

        View view = inflater.inflate(R.layout.activity_contact_detail_fragment, container, false);

        contact = (Contact) getArguments().getSerializable("contact");

        TextView nameTextView = view.findViewById(R.id.contact_detail_name);
        TextView phoneNumberTextView = view.findViewById(R.id.contact_detail_phone_number);

        String fullName = contact.getFirstName() + " " + contact.getLastName();

        nameTextView.setText(fullName);
        phoneNumberTextView.setText(contact.getPhoneNumber());

        Button callButton = view.findViewById(R.id.contact_detail_call_button);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + contact.getPhoneNumber()));
                startActivity(intent);
            }
        });

        Button textButton = view.findViewById(R.id.contact_detail_text_button);
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms: " + contact.getPhoneNumber()));
                startActivity(intent);
            }
        });

        Button editButton = view.findViewById(R.id.contact_detail_edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditContactActivity.class);
                intent.putExtra("contact", contact);
                startActivityForResult(intent, 102);
            }
        });

        Button deleteButton = view.findViewById(R.id.contact_detail_delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteMessage();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 102) {
            if (data != null && data.getSerializableExtra("contact") != null) {
                Contact updatedContact = (Contact) data.getSerializableExtra("contact");
                Bundle bundle = new Bundle();
                bundle.putSerializable("contact", updatedContact);
                setArguments(bundle);
                ((ContactDetailActivity)getActivity()).getSocialMediaDetailFragment().setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .detach(this).attach(this).commit();
            } else {
                Toast.makeText(getContext(), "Contact not updated", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showDeleteMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete Contact");
        builder.setMessage("Are you sure you want to delete this contact?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                contactOperations.deleteContact(contact);
                Intent intent = new Intent();

                getActivity().setResult(Activity.RESULT_OK, intent);

                getActivity().finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity().getApplicationContext(), "Contact not deleted", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
