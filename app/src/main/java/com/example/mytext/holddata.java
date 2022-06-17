package com.example.mytext;

import java.io.Serializable;

public class holddata  {
    String name;
    String type;
    String bed;
    String address;
    String contact;
    String image;
    String normalBed;
    String oxygenBed;
    String ICUBed,stre;

    public holddata() {

    }

    public holddata(String name, String type, String bed, String address, String contact, String image,String normalBed,String oxygenBed,String ICUBed) {
        this.name = name;
        this.type = type;
        this.bed = bed;
        this.address = address;
        this.contact = contact;
        this.image = image;
        this.normalBed=normalBed;
        this.oxygenBed=oxygenBed;
        this.ICUBed=ICUBed;
        this.stre=stre;

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

    public void setType(String type) {
      this.type = type;
    }

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

    public String getNormalBed() {
        return normalBed;
    }

    public void setNormalBed(String normalBed) {
        this.normalBed = normalBed;
    }

    public String getOxygenBed() {
        return oxygenBed;
    }

    public void setOxygenBed(String oxygenBed) {
        this.oxygenBed = oxygenBed;
    }

    public String getICUBed() {
        return ICUBed;
    }

    public void setICUBed(String ICUBed) {
        this.ICUBed = ICUBed;
    }
}