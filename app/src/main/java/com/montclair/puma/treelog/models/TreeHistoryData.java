package com.montclair.puma.treelog.models;

import android.net.Uri;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Agni on 4/19/2017.
 */

public class TreeHistoryData implements Serializable{
    private String treeHistoryID; //primary ID for new stage of the tree
    private String treeID; // for existing tree
    private String userID; //for existing user
    private String userName; //for name of user updating
    private String entryDate; //current date as modified
    private String treeTrunkPicURL; //URL for trunk picture changed
    private String treeHistoryDiametr; //history of diameter changes
    private String treeHistorySize; //history of size changes
    private String treeHistoryBiotic; //history of biotic changes
    private String treeHistory_a_Biotic; //history of abiotic changes
    private String treeHistoryPitURL; //URL of tree pit changed
    private String treeHistoryPitComments; //new comments for tree pit condition
    private String treeHistoryHazard; //new tree hazard info
   // private TreeData treeData;
    private List<TreeImages> treeImages;
  //  private List<String> treeImageUri;

    public TreeHistoryData() {
    }

    public TreeHistoryData(String treeID, String userID, String entryDate, String treeTrunkPicURL, String treeHistoryDiametr, String treeHistorySize, String treeHistoryBiotic, String treeHistory_a_Biotic, String treeHistoryPitURL, String treeHistoryPitComments, String treeHistoryHazard, String userName) {
        this.treeID = treeID;
        this.userID = userID;
        this.userName = userName;
        this.entryDate = entryDate;
        this.treeTrunkPicURL = treeTrunkPicURL;
        this.treeHistoryDiametr = treeHistoryDiametr;
        this.treeHistorySize = treeHistorySize;
        this.treeHistoryBiotic = treeHistoryBiotic;
        this.treeHistory_a_Biotic = treeHistory_a_Biotic;
        this.treeHistoryPitURL = treeHistoryPitURL;
        this.treeHistoryPitComments = treeHistoryPitComments;
        this.treeHistoryHazard = treeHistoryHazard;
        //this.treeImageUri=treeImageUri;
    }


    public List<TreeImages> getTreeImages() {
        return treeImages;
    }

    public void setTreeImages(List<TreeImages> treeImages) {
        this.treeImages = treeImages;
    }

    //public TreeData getTreeData() {
      //  return treeData;
    //}

    //public void setTreeData(TreeData treeData) {
        //this.treeData = treeData;
   // }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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
