package com.example.maru.service;

import com.example.maru.model.Meeting;

import java.util.List;

/**
 * Neighbour API client
 */
public interface MeetingApiService {

    /**
     * Get all my Meetings
     * @return {@Link List}
     */
    List<Meeting> getMeetings();

    List<String> getUsers();

    /**
     * Delete a meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Create a meeting
     */
    void createMeeting(Meeting meeting);

}
