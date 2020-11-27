package com.example.maru.service;

import com.example.maru.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    private List<String> rooms = DummyMeetingGenerator.generateRooms();

    private List<String> users = DummyMeetingGenerator.generateUsers();

    @Override
    public List<Meeting> getMeetings() { return meetings; }

    @Override
    public List<String> getRooms() { return rooms; }

    @Override
    public List<String> getUsers() { return users; }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public long getNextId() {
        return meetings.get(meetings.size()-1).getId() + 1;
    }

}
