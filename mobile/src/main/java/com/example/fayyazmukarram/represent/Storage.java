package com.example.fayyazmukarram.represent;

import java.util.ArrayList;

/**
 * Created by fayyazmukarram on 3/10/16.
 */
public class Storage implements java.io.Serializable {

    public  String position;
    public String name;
    public String party;
    public String web;
    public String email;
    public String twitter;
    public String image;
    public long tweet;
    public int number;
    public ArrayList<String> comList;
    public ArrayList<String> billList;

    //Setter Methods
    public void setNumber(int number){

        this.number = number;
    }
    public void setPosition(String position){

        this.position = position;
    }
    public void setName(String name){

        this.name = name;
    }
    public void setParty(String party){

        this.party = party;
    }
    public void setWeb(String web){

        this.web = web;
    }
    public void setEmail(String email){

        this.email = email;
    }
    public void setTwitter (String twitter){

        this.twitter = twitter;
    }
    public void setTweet (long tweet){

        this.tweet = tweet;
    }
    public void setImage(String image) {

        this.image = image;
    }
    public void setComList (ArrayList<String> comList){

        this.comList = comList;
    }
    public void setbillList(ArrayList<String> bList){

        this.billList = bList;
    }
    //Getter Methods
    public int getNumber() {

        return number;
    }
    public String getPosition(){
        return  position;
    }
    public String getName() {

        return name;
    }
    public String getParty() {

        return party;
    }
    public String getWeb() {
        return web;
    }
    public String getEmail() {
        return email;
    }
    public String getTwitter() {
        return twitter;
    }
    public long getTweet() {
        return tweet;
    }
    public String getImage() {
        return image;
    }
    public ArrayList<String> getComList() {
        return comList;
    }
    public ArrayList<String> getbillList() {
        return billList;
    }
    public Object get(int childPosititon) {
        return number;
    }
}
