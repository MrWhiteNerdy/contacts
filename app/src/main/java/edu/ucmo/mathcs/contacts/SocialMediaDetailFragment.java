package edu.ucmo.mathcs.contacts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by jansenmorby on 4/16/18.
 */

public class SocialMediaDetailFragment extends Fragment {
    private TextView facebookUsernameTextView,
            twitterUsernameTextView, instagramUsernameTextView, snapchatUsernameTextView,
            linkedinUsernameTextView;

    private Contact contact;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_social_media_detail_fragment, container, false);

        contact = (Contact) getArguments().getSerializable("contact");

        facebookUsernameTextView = view.findViewById(R.id.contact_detail_facebook_username);
        twitterUsernameTextView = view.findViewById(R.id.contact_detail_twitter_username);
        instagramUsernameTextView = view.findViewById(R.id.contact_detail_instagram_username);
        snapchatUsernameTextView = view.findViewById(R.id.contact_detail_snapchat_username);
        linkedinUsernameTextView = view.findViewById(R.id.contact_detail_linkedin_username);

        refreshContact(contact);

        TableRow facebookTableRow = view.findViewById(R.id.contact_detail_facebook_row);
        facebookTableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/" + contact.getFacebookUsername()));
                startActivity(intent);
            }
        });

        TableRow twitterTableRow = view.findViewById(R.id.contact_detail_twitter_row);
        twitterTableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://twitter.com/" + contact.getTwitterUsername()));
                startActivity(intent);
            }
        });

        TableRow instagramTableRow = view.findViewById(R.id.contact_detail_instagram_row);
        instagramTableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.instagram.com/" + contact.getInstagramUsername()));
                startActivity(intent);
            }
        });

        TableRow snapchatTableRow = view.findViewById(R.id.contact_detail_snapchat_row);
        snapchatTableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://snapchat.com/add/" + contact.getSnapchatUsername()));
                startActivity(intent);
            }
        });

        TableRow linkedinTableRow = view.findViewById(R.id.contact_detail_linkedin_row);
        linkedinTableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.linkedin.com/" + contact.getFacebookUsername()));
                startActivity(intent);
            }
        });

        return view;
    }

    private void refreshContact(Contact contact) {
        facebookUsernameTextView.setText(contact.getFacebookUsername());
        twitterUsernameTextView.setText("@" + contact.getTwitterUsername());
        instagramUsernameTextView.setText("@" + contact.getInstagramUsername());
        snapchatUsernameTextView.setText("@" + contact.getSnapchatUsername());
        linkedinUsernameTextView.setText(contact.getLinkedinUsername());
    }
}
