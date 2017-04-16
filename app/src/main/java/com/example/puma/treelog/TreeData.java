package com.example.puma.treelog;

import java.io.Serializable;

/**
 * Created by Agni on 4/13/2017.
 */


// TreeData bean class
public class TreeData implements Serializable {
    private String latitude;
    private String longitude;
    private String streetAddress;
    private String propertyType; //private of city
    private String species; //unique species, like wiki name
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

    public TreeData() {
    }

    public TreeData(String streetAddress, String propertyType, String treeType, String treeName) {
        this.streetAddress = streetAddress;
        this.propertyType = propertyType;
        this.treeType = treeType;
        this.treeName = treeName;
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
/*NOT listed from client's list: tree trunk picture, other possible pictures*/
    /*TODO: add missing pictures in this bean and design */
    /*TODO: add dateModyfied and log*/

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
