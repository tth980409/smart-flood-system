package com.example.smartfloodsystem;

import java.util.UUID;

public class User {
    private UUID mUID;
    private String mUserEmail;
    private String mUserPassword;
    private String mFirstName;
    private String mLastName;
    private String mLocation;

    public User(){
        mUID = UUID.randomUUID();
    }

    public UUID getUID(){
        return mUID;
    }

    public void setUserEmail(String userEmail){
        mUserEmail = userEmail;
    }

    public String getUserEmail(){
        return mUserEmail;
    }

    public void setUserPassword(String userPassword){
        mUserPassword = userPassword;
    }

    public String getUserPassword(){
        return mUserPassword;
    }

    public void setUserFirstName(String firstName){
        mFirstName = firstName;
    }

    public String getUserFirstName(){
        return mFirstName;
    }

    public void setUserLastName(String lastName){
        mLastName = lastName;
    }

    public String getUserLastName(){
        return mLastName;
    }

    public void setUserLocation(String location){
        mLocation = location;
    }

    public String getUserLocation(){
        return mLocation;
    }
}