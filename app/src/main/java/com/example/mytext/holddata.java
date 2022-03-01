package com.example.mytext;

import java.io.Serializable;

public class holddata  {
    String name;
    String type;
    String bed;
    String address;
    String contact;
    String image;
    String Nbed;
    String Obed;
    String Ibed;

    public holddata() {

    }

    public holddata(String name, String type, String bed, String address, String contact, String image,String normalbed,String obed,String ibed) {
        this.name = name;
        this.type = type;
        this.bed = bed;
        this.address = address;
        this.contact = contact;
        this.image = image;
        this.Nbed=normalbed;
        this.Obed=obed;
        this.Ibed=ibed;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

  //  public void setType(String type) {
   //     this.type = type;
   // }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getNbed() {
        return Nbed;
    }

    public void setNbed(String normalbed) {
        this.Nbed = normalbed;
    }

    public String getObed() {
        return Obed;
    }

    public void setObed(String obed) {
        this.Obed = obed;
    }

    public String getIbed() {
        return Ibed;
    }

    public void setIbed(String ibed) {
        this.Ibed = ibed;
    }
}