package com.example.maru.model;

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

    /** Color */
    private String colorHex;

    /** Adress */
    private List<String> users;

    /** Date & Time*/
    private Date date;

    /**
     * Constructor
     * @param id
     * @param name
     * @param colorHex
     * @params users
     */
    public Meeting(long id, String name, String colorHex, List<String> users) {
        this.id = id;
        this.name = name;
        this.colorHex = colorHex;
        this.users = users;
    }

    /**
     * Constructor
     * @param id
     * @param name
     * @param colorHex
     */
    public Meeting(long id, String name, String colorHex) {
        this.id = id;
        this.name = name;
        this.colorHex = colorHex;
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

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

