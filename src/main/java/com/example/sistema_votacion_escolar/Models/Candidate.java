package com.example.sistema_votacion_escolar.Models;

public class Candidate {

    private int id;
    private String name;
    private String photoPath;
    private String campaignDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photo) {
        this.photoPath = photoPath;
    }

    public String getCampaignDetails() {
        return campaignDetails;
    }

    public void setCampaignDetails(String campaignDetails) {
        this.campaignDetails = campaignDetails;
    }
}
