package com.example.puma.treelog.models;

import java.io.Serializable;

/**
 * Created by Agni on 4/18/2017.
 */

public class UserType implements Serializable {
    private String userTypeID;
    private String userTypeDescription;
    private String userRole;

    public String getUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(String userTypeID) {
        this.userTypeID = userTypeID;
    }

    public String getUserTypeDescription() {
        return userTypeDescription;
    }

    public void setUserTypeDescription(String userTypeDescription) {
        this.userTypeDescription = userTypeDescription;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
