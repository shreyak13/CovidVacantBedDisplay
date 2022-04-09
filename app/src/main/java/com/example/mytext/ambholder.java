package com.example.mytext;

public class ambholder {
    String name,address,type,contact,image;

    ambholder(){

    }

    public ambholder(String name, String address, String type, String contact, String image) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.contact = contact;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
