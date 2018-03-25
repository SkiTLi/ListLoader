package com.sktl.listloader.models;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by USER-PC on 05.03.2018.
 */


@Entity
public class Photo {

    @PrimaryKey
    private String id;

    private String secret;
    private String server;
    private String farm;

    //not used here
//    private String owner;
//    private String title;
//    private byte ispublic;
//    private byte isfriend;
//    private byte isfamily;

//    public Photo(String tId, String tOwner, String tSecret,
//                 String tServer, String tFarm, String tTitle) {
//        id = tId;
//        owner = tOwner;
//        secret = tSecret;
//        server = tServer;
//        farm = tFarm;
//        title = tTitle;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }
}
