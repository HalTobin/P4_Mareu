package com.example.maru.model;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Meeting implements Serializable {

    /** Identifier */
    private long id;

    /** Name */
    private String name;

    /** Room used for the meeting*/
    private String room;

    /** Date & Time*/
    private long dateTimeStamp;

    /** Color */
    private int colorInt;

    /** Adress */
    private List<String> users;

    /**
     * Constructor
     * @param id
     * @param name
     * @param room
     * @param dateTimeStamp
     * @param colorInt
     * @params users
     */
    public Meeting(long id, String name, String room, long dateTimeStamp, int colorInt, List<String> users) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.dateTimeStamp = dateTimeStamp;
        this.colorInt = colorInt;
        this.users = users;
    }

    /**
     * Constructor
     * @param id
     * @param name
     * @param room
     * @param date
     * @param colorHex
     * @params users
     */
    public Meeting(long id, String name, String room, Date date, String colorHex, List<String> users) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.dateTimeStamp = date.getTime();
        this.colorInt = Color.parseColor(colorHex);
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getColorInt() {
        return colorInt;
    }

    public void setColorInt(int colorInt) {
        this.colorInt = colorInt;
    }

    public String getColorHex() {
        return Integer.toString(this.colorInt, 16);
    }

    public void setColorHex(String colorHex) {
        this.colorInt = Color.parseColor(colorHex);
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public String getUsersString() {
        String myString = "";
        for (int c = 0; c < users.size(); c++) {
            if(c==users.size()-1) myString = myString + users.get(c);
            else myString = myString + users.get(c) + ", ";
        }

        if(myString.length() > 31) {
            if(myString.charAt(27)=='.') myString = myString.substring(0, 28) + "..";
            else myString = myString.substring(0, 28) + "...";
        }

        return myString;
    }

    public long getDateTimeStamp() {
        return dateTimeStamp;
    }

    public void setDateTimeStamp(long dateTimeStamp) {
        this.dateTimeStamp = dateTimeStamp;
    }

    public Date getDate() {
        return new Date(dateTimeStamp);
    }

    public void setDate(Date date) {
        this.dateTimeStamp = date.getTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(id, meeting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

