package com.example.puma.treelog.models;

import java.io.Serializable;

/**
 * Created by Agni on 4/19/2017.
 */

public class TreeHistoryData implements Serializable{
    private String treeHistoryID; //primaty ID for new stage of the tree
    private String treeID; // for existing tree
    private String userID; //for existing user
    private String entryDate; //current date as modified
    private String treeTrunkPicURL; //URL for trunk picture changed
    private String treeHistoryDiametr; //history of diameter changin
    private String treeHistorySize; //history of size changing
    private String treeHistoryBiotic; //history of biotic changes
    private String treeHistory_a_Biotic; //history of abiotic changes
    private String treeHistoryPitURL; //URL of tree pit changed
    private String treeHistoryPitComments; //new comments for tree pit condition
    private String treeHistoryHazard; //new tree hazard info

    public String getTreeHistoryID() {
        return treeHistoryID;
    }

    public void setTreeHistoryID(String treeHistoryID) {
        this.treeHistoryID = treeHistoryID;
    }

    public String getTreeID() {
        return treeID;
    }

    public void setTreeID(String treeID) {
        this.treeID = treeID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getTreeTrunkPicURL() {
        return treeTrunkPicURL;
    }

    public void setTreeTrunkPicURL(String treeTrunkPicURL) {
        this.treeTrunkPicURL = treeTrunkPicURL;
    }

    public String getTreeHistoryDiametr() {
        return treeHistoryDiametr;
    }

    public void setTreeHistoryDiametr(String treeHistoryDiametr) {
        this.treeHistoryDiametr = treeHistoryDiametr;
    }

    public String getTreeHistorySize() {
        return treeHistorySize;
    }

    public void setTreeHistorySize(String treeHistorySize) {
        this.treeHistorySize = treeHistorySize;
    }

    public String getTreeHistoryBiotic() {
        return treeHistoryBiotic;
    }

    public void setTreeHistoryBiotic(String treeHistoryBiotic) {
        this.treeHistoryBiotic = treeHistoryBiotic;
    }

    public String getTreeHistory_a_Biotic() {
        return treeHistory_a_Biotic;
    }

    public void setTreeHistory_a_Biotic(String treeHistory_a_Biotic) {
        this.treeHistory_a_Biotic = treeHistory_a_Biotic;
    }

    public String getTreeHistoryPitURL() {
        return treeHistoryPitURL;
    }

    public void setTreeHistoryPitURL(String treeHistoryPitURL) {
        this.treeHistoryPitURL = treeHistoryPitURL;
    }

    public String getTreeHistoryPitComments() {
        return treeHistoryPitComments;
    }

    public void setTreeHistoryPitComments(String treeHistoryPitComments) {
        this.treeHistoryPitComments = treeHistoryPitComments;
    }

    public String getTreeHistoryHazard() {
        return treeHistoryHazard;
    }

    public void setTreeHistoryHazard(String treeHistoryHazard) {
        this.treeHistoryHazard = treeHistoryHazard;
    }
}
