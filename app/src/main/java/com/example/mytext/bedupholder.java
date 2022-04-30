package com.example.mytext;

public class bedupholder {

     String upnormalbed;
     String upoxygenbed;
     String upicubed,stre;

public bedupholder(){}


    public bedupholder(String stre,String upnormalbed, String upoxygenbed, String upicubed) {
        this.upnormalbed = upnormalbed;
        this.upoxygenbed = upoxygenbed;
        this.upicubed = upicubed;
        this.stre=stre;
    }

    public String getStre() {
        return stre;
    }

    public void setStre(String stre) {
        this.stre = stre;
    }

    public String getUpnormalbed() {
        return upnormalbed;
    }

    public void setUpnormalbed(String upnormalbed) {
        this.upnormalbed = upnormalbed;
    }

    public String getUpoxygenbed() {
        return upoxygenbed;
    }

    public void setUpoxygenbed(String upoxygenbed) {
        this.upoxygenbed = upoxygenbed;
    }

    public String getUpicubed() {
        return upicubed;
    }

    public void setUpicubed(String upicubed) {
        this.upicubed = upicubed;
    }
}
