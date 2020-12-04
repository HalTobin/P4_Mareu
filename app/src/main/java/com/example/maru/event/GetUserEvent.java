package com.example.maru.event;

/**
 * Event fired when a user deletes a Meeting
 */
public class GetUserEvent {

    /**
     * Meeting to delete
     */

    public String user;

    /**
     * Constructor.
     * @param user
     */

    public GetUserEvent(String user) { this.user = user; }
}
