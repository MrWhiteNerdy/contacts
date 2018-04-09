package edu.ucmo.mathcs.contacts;

import java.io.Serializable;

public class Contact implements Serializable {

    private long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String facebookUsername;
    private String twitterUsername;
    private String instagramUsername;
    private String linkedinUsername;
    private String snapchatUsername;

    public Contact(String firstName, String lastName, String phoneNumber,
                   String facebookUsername, String twitterUsername, String instagramUsername,
                   String linkedinUsername, String snapchatUsername) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.facebookUsername = facebookUsername;
        this.twitterUsername = twitterUsername;
        this.instagramUsername = instagramUsername;
        this.linkedinUsername = linkedinUsername;
        this.snapchatUsername = snapchatUsername;
    }

    public Contact(long id, String firstName, String lastName, String phoneNumber,
                   String facebookUsername, String twitterUsername, String instagramUsername,
                   String linkedinUsername, String snapchatUsername) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.facebookUsername = facebookUsername;
        this.twitterUsername = twitterUsername;
        this.instagramUsername = instagramUsername;
        this.linkedinUsername = linkedinUsername;
        this.snapchatUsername = snapchatUsername;
    }

    public Contact() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFacebookUsername() {
        return facebookUsername;
    }

    public void setFacebookUsername(String facebookUsername) {
        this.facebookUsername = facebookUsername;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public String getInstagramUsername() {
        return instagramUsername;
    }

    public void setInstagramUsername(String instagramUsername) {
        this.instagramUsername = instagramUsername;
    }

    public String getLinkedinUsername() {
        return linkedinUsername;
    }

    public void setLinkedinUsername(String linkedinUsername) {
        this.linkedinUsername = linkedinUsername;
    }

    public String getSnapchatUsername() {
        return snapchatUsername;
    }

    public void setSnapchatUsername(String snapchatUsername) {
        this.snapchatUsername = snapchatUsername;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", facebookUsername='" + facebookUsername + '\'' +
                ", twitterUsername='" + twitterUsername + '\'' +
                ", instagramUsername='" + instagramUsername + '\'' +
                ", linkedinUsername='" + linkedinUsername + '\'' +
                ", snapchatUsername='" + snapchatUsername + '\'' +
                '}';
    }

}
