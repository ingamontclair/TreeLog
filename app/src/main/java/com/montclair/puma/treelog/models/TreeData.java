package com.montclair.puma.treelog.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Agni on 4/13/2017.
 */


// TreeData bean class
public class TreeData implements Serializable {
    private String treeId;
    private String latitude;
    private String longitude;
    private String streetAddress;
    private String propertyType; //designate tree as on private or city property
    private String species; //unique species name, as in the binomial nomenclature aka latin name
    private String treeType; //all tree types listed in tree_type array in Strings.xml
    private String datePlanted; // calendar
    private String treeDiametr;
    private String treeSize;
    private String treePitComments; //comments for tree pit condition if any
    private String bioticDamage; //possible biotic damages listed in biotic_source array in Strings.xml
    private String a_bioticDamage; //possible Abiotic damages listed in biotic_source array in Strings.xml
    private String treeHazard; //possible hazard groups in one string
    private String remove; //flag for removal condition
    private String photoPitURL; //url string  for photo of tree pit
    private String photoMainURL; //url string for the main photo
    private String dateCreated; // a date when a tree was created in DB
    private String treeName; //Name of the tree to display in the list or on the map
    private String treeMaker; //Name of the user that added the tree
    private List<TreeHistoryData> treeHistoryDatas;

    public List<TreeHistoryData> getTreeHistoryDatas() {
        return treeHistoryDatas;
    }

    public void setTreeHistoryDatas(List<TreeHistoryData> treeHistoryDatas) {
        this.treeHistoryDatas = treeHistoryDatas;
    }

    public TreeData() {
    }

    public TreeData(String streetAddress, String propertyType, String treeType, String treeName, String treeMaker) {
        this.streetAddress = streetAddress;
        this.propertyType = propertyType;
        this.treeType = treeType;
        this.treeName = treeName;
        this.treeMaker = treeMaker;
    }

    public String getTreeMaker() {
        return treeMaker;
    }

    public void setTreeMaker(String treeMaker) {
        this.treeMaker = treeMaker;
    }

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPhotoPitURL() {
        return photoPitURL;
    }

    public void setPhotoPitURL(String photoPitURL) {
        this.photoPitURL = photoPitURL;
    }

    public String getPhotoMainURL() {
        return photoMainURL;
    }

    public void setPhotoMainURL(String photoMainURL) {
        this.photoMainURL = photoMainURL;
    }

    public String getTreeHazard() {
        return treeHazard;
    }

    public void setTreeHazard(String treeHazard) {
        this.treeHazard = treeHazard;
    }

    public String getRemove() {
        return remove;
    }

    public void setRemove(String remove) {
        this.remove = remove;
    }


    public String getBioticDamage() {
        return bioticDamage;
    }

    public void setBioticDamage(String bioticDamage) {
        this.bioticDamage = bioticDamage;
    }

    public String getA_bioticDamage() {
        return a_bioticDamage;
    }

    public void setA_bioticDamage(String a_bioticDamage) {
        this.a_bioticDamage = a_bioticDamage;
    }

    public String getTreePitComments() {
        return treePitComments;
    }

    public void setTreePitComments(String treePitComments) {
        this.treePitComments = treePitComments;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getTreeType() {
        return treeType;
    }

    public void setTreeType(String treeType) {
        this.treeType = treeType;
    }

    public String getDatePlanted() {
        return datePlanted;
    }

    public void setDatePlanted(String datePlanted) {
        this.datePlanted = datePlanted;
    }

    public String getTreeDiametr() {
        return treeDiametr;
    }

    public void setTreeDiametr(String treeDiametr) {
        this.treeDiametr = treeDiametr;
    }

    public String getTreeSize() {
        return treeSize;
    }

    public void setTreeSize(String treeSize) {
        this.treeSize = treeSize;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
}
