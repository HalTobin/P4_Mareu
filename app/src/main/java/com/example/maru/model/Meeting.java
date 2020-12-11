package com.example.maru.model;

import android.graphics.Color;

import java.io.Serializable;
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

    /** Year */
    private int year;

    /** Month */
    private int month;

    /** Day */
    private int day;

    /** Hour */
    private int hour;

    /** Minute */
    private int min;

    /** Color */
    private int colorInt;

    /** Adress */
    private List<String> users;

    /**
     * Constructor
     * Every parameters = null
     */
    public Meeting() {

    }

    /**
     * Constructor
     * @param id
     * @param name
     * @param room
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param min
     * @param colorHex
     * @params users
     */
    public Meeting(long id, String name, String room, int year, int month, int day, int hour, int min, String colorHex, List<String> users) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() { return day; }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
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

    public String getTitleString() {
        String myString = String.format("%02d", hour) + "h" + String.format("%02d", min) + " - " + this.getRoom() + " - " + this.getName();
        return myString;
    }

    public String getUsersString() {
        String myString = "";
        for (int c = 0; c < users.size(); c++) {
            if(c==users.size()-1) myString = myString + users.get(c);
            else myString = myString + users.get(c) + ", ";
        }
        return myString;
    }

    public boolean isDateEqual(int year, int month, int day) {
        boolean isEqual = true;
        if(this.year != year) isEqual = false;
        if(this.month != month) isEqual = false;
        if(this.day != day) isEqual = false;
        return isEqual;
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

