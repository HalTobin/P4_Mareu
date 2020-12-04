package com.example.maru.event;

import com.example.maru.model.Meeting;

/**
 * Event fired when a user deletes a Meeting
 */
public class DeleteUserEvent {

    /**
     * Meeting to delete
     */

    public String user;

    /**
     * Constructor.
     * @param user
     */

    public DeleteUserEvent(String user) { this.user = user; }
}
