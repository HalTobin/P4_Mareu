package com.example.maru.service;

import com.example.maru.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DummyMeetingGenerator {

    public static List<String> DUMMY_USERS = Arrays.asList(
            "admin@lamzon.com", "rh@lamzon.com", "comm@lamzone.com", "user1@lamzon.com", "user2@lamzon.com", "user3@lamzon.com", "user4@lamzon.com"
    );

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(1, "Point hebdomadaire", "A2", new Date(2020, 11, 4, 9, 30), "#FF0000", DUMMY_USERS),
            new Meeting(2, "Achat matériel", "C4", new Date(2020, 14, 7, 14, 0), "#0000FF", Arrays.asList(DUMMY_USERS.get(0), DUMMY_USERS.get(5), DUMMY_USERS.get(6))),
            new Meeting(3, "Point hebdomadaire", "A2", new Date(2020, 14, 11, 14, 0), "#FF0000",  DUMMY_USERS),
            new Meeting(4, "Acceuil nouvel recrue", "A7", new Date(2020, 14, 12, 11, 0), "#FF0000",  Arrays.asList(DUMMY_USERS.get(1), DUMMY_USERS.get(3), DUMMY_USERS.get(4))),
            new Meeting(5, "Point hebdomadaire", "A2", new Date(2020, 14, 18, 9, 30), "#FF0000",  DUMMY_USERS),
            new Meeting(6, "Point avant fermeture pour Noël", "A2", new Date(2020, 14, 18, 14, 0), "#FF0000",  DUMMY_USERS),
            new Meeting(7, "Point pour la reprise", "A2", new Date(2021, 1, 4, 9, 30), "#FF0000",  DUMMY_USERS),
            new Meeting(8, "Recrutement personnel", "B9", new Date(2021, 1, 5, 14, 0), "#00FF00", Arrays.asList(DUMMY_USERS.get(0), DUMMY_USERS.get(1), DUMMY_USERS.get(3)))
    );

    static List<String> generateUsers() {
        return new ArrayList<>(DUMMY_USERS);
    }

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
    }