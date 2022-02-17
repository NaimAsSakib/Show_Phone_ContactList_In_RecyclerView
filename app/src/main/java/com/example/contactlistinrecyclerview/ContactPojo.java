package com.example.contactlistinrecyclerview;

public class ContactPojo {
    private String ContactImage;
    private String ContactName;
    private String ContactNumber;


    public ContactPojo(String contactImage, String contactName, String contactNumber) {
        ContactImage = contactImage;
        ContactName = contactName;
        ContactNumber = contactNumber;
    }

    public String getContactImage() {
        return ContactImage;
    }

    public void setContactImage(String contactImage) {
        ContactImage = contactImage;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }
}
