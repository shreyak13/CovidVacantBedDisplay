package com.example.mytext;

public class dataholder  {
    String icname;
    String ictype;
    String icbed;
    String icaddress;
    String iccontact;
    String icimage;
    String icNbed;
    String icObed;

    public dataholder(){

    }

    public dataholder(String icname, String ictype, String icbed, String icaddress, String iccontact, String icimage, String icNbed, String icObed) {
        this.icname = icname;
        this.ictype = ictype;
        this.icbed = icbed;
        this.icaddress = icaddress;
        this.iccontact = iccontact;
        this.icimage = icimage;
        this.icNbed = icNbed;
        this.icObed = icObed;
    }





    public String getIcname() {
        return icname;
    }

    public void setIcname(String icname) {
        this.icname = icname;
    }

    public String getIctype() {
        return ictype;
    }

    public void setIctype(String ictype) {
        this.ictype = ictype;
    }

    public String getIcbed() {
        return icbed;
    }

    public void setIcbed(String icbed) {
        this.icbed = icbed;
    }

    public String getIcaddress() {
        return icaddress;
    }

    public void setIcaddress(String icaddress) {
        this.icaddress = icaddress;
    }

    public String getIccontact() {
        return iccontact;
    }

    public void setIccontact(String iccontact) {
        this.iccontact = iccontact;
    }

    public String getIcimage() {
        return icimage;
    }

    public void setIcimage(String icimage) {
        this.icimage = icimage;
    }

    public String getIcNbed() {
        return icNbed;
    }

    public void setIcNbed(String icNbed) {
        this.icNbed = icNbed;
    }

    public String getIcObed() {
        return icObed;
    }

    public void setIcObed(String icObed) {
        this.icObed = icObed;
    }
}
