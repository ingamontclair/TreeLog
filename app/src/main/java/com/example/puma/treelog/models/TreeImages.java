package com.example.puma.treelog.models;

import java.io.Serializable;

/**
 * Created by Agni on 4/19/2017.
 */

public class TreeImages implements Serializable{
    private String treeImageID; //image ID primary
    private String treeHistoryID; //id of history event
    private String treeImageURL; //image URL
    private String treeDescription; //tree Image description i. e. leaf, trunk, blooming etc
    private String treeImageUploadDate; //tree image modified date
    private TreeHistoryData treeHistoryData;

    public TreeHistoryData getTreeHistoryData() {
        return treeHistoryData;
    }

    public void setTreeHistoryData(TreeHistoryData treeHistoryData) {
        this.treeHistoryData = treeHistoryData;
    }

    public TreeImages() {
    }

    public TreeImages(String treeDescription) {
        this.treeDescription = treeDescription;

    }
    public String getTreeImageID() {
        return treeImageID;
    }

    public void setTreeImageID(String treeImageID) {
        this.treeImageID = treeImageID;
    }

    public String getTreeHistoryID() {
        return treeHistoryID;
    }

    public void setTreeHistoryID(String treeHistoryID) {
        this.treeHistoryID = treeHistoryID;
    }

    public String getTreeImageURL() {
        return treeImageURL;
    }

    public void setTreeImageURL(String treeImageURL) {
        this.treeImageURL = treeImageURL;
    }

    public String getTreeDescription() {
        return treeDescription;
    }

    public void setTreeDescription(String treeDescription) {
        this.treeDescription = treeDescription;
    }

    public String getTreeImageUploadDate() {
        return treeImageUploadDate;
    }

    public void setTreeImageUploadDate(String treeImageUploadDate) {
        this.treeImageUploadDate = treeImageUploadDate;
    }
}
