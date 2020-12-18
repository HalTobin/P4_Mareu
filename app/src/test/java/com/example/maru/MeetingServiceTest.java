package com.example.maru;

import com.example.maru.di.DI;
import com.example.maru.model.Meeting;
import com.example.maru.service.DummyMeetingGenerator;
import com.example.maru.service.MeetingApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class MeetingServiceTest {

    private MeetingApiService service;

    @Before
    public void setup() { service = DI.getNewInstanceApiService(); }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedNeighbours = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void getMeetingsByDateWithSuccess() {
        int year = 2020; int month = 11; int day = 4;
        List<Meeting> meetingsByDate = service.getMeetingsByDate(year, month, day);
        for (Meeting m:meetingsByDate) {
            assertEquals(year, m.getYear());
            assertEquals(month, m.getMonth());
            assertEquals(day, m.getDay());
        }
    }

    @Test
    public void getMeetingsByRoomWithSuccess() {
        String room = "A2";
        List<Meeting> meetingsByRoom = service.getMeetingsByRoom(room);
        for (Meeting m:meetingsByRoom) {
            assertEquals(m.getRoom(), room);
        }
    }

    @Test
    public void getUsersWithSuccess() {
        List<String> users = service.getUsers();
        List<String> expectedNeighbours = DummyMeetingGenerator.DUMMY_USERS;
        assertThat(users, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void getRoomsWithSuccess() {
        List<String> rooms = service.getRooms();
        List<String> expectedNeighbours = DummyMeetingGenerator.DUMMY_ROOMS;
        assertThat(rooms, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void getNextIdWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        assertEquals(meetings.get(meetings.size()-1).getId()+1, service.getNextId());
    }

    @Test
    public void createMeetingWithSuccess() {
        Meeting meetingToAdd = new Meeting(service.getNextId(), "Test Unitaire", "A2", 2021, 1, 10, 14,0, "#F84C44", service.getUsers());
        service.createMeeting(meetingToAdd);
        assertTrue(service.getMeetings().contains(meetingToAdd));
    }
}