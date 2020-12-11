package com.example.maru.event;

/**
 * Event fired when a user deletes a Meeting
 */
public class GetRoomEvent {

    /**
     * Meeting to delete
     */

    public String room;

    /**
     * Constructor.
     * @param room
     */

    public GetRoomEvent(String room) { this.room = room; }
}
