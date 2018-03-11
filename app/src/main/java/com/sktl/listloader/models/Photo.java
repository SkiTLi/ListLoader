package com.sktl.listloader.models;


/**
 * Created by USER-PC on 05.03.2018.
 */

public class Photo {


    public String id;
    public String secret;
    public String server;
    public String farm;

    //not use here
    public String owner;
    public String title;
    public byte ispublic;
    public byte isfriend;
    public byte isfamily;


    public Photo(String tId, String tOwner, String tSecret,
                 String tServer, String tFarm, String tTitle) {
        id = tId;
        owner = tOwner;
        secret = tSecret;
        server = tServer;
        farm = tFarm;
        title = tTitle;
    }


}
