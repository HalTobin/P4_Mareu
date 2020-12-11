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
    public List<Meeting> getMeetingsByDate(int year, int month, int day) {
        System.out.println("SELECTED : " + day+"/"+month+"/"+year);
        List<Meeting> meetingsFilter = new ArrayList<>();
        for(Meeting m:meetings) {
            System.out.println("COMPARED : " + m.getDay()+"/"+m.getMonth()+"/"+m.getYear());
            if(m.isDateEqual(year, month, day)) meetingsFilter.add(m);
        }
        return meetingsFilter;
    }

    @Override
    public List<Meeting> getMeetingsByRoom(String room) {
        List<Meeting> meetingsFilter = new ArrayList<>();
        for(Meeting m:meetings) {
            if(m.getRoom().equals(room)) meetingsFilter.add(m);
        }
        return meetingsFilter;
    }

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
