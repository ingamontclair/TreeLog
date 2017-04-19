package com.example.puma.treelog.models;

/**
 * Created by Agni on 4/13/2017.
 */

//TreeSession is a singleton to simplify passing treeData between activities

public class TreeSession {

    private static TreeSession ourInstance = new TreeSession();

    public static TreeSession getInstance() {
        return ourInstance;
    }

    private TreeSession() {
    }

    private TreeData treeData;
    private User user;

    public TreeData getTreeData() {
        return treeData;
    }

    public void setTreeData(TreeData treeData) {
        this.treeData = treeData;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

