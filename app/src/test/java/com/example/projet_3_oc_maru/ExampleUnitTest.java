package com.example.projet_3_oc_maru;

import androidx.annotation.NonNull;

import com.example.projet_3_oc_maru.di.DI;
import com.example.projet_3_oc_maru.models.Meeting;
import com.example.projet_3_oc_maru.models.RoomMeeting;
import com.example.projet_3_oc_maru.service.DummyMeetingGenerator;
import com.example.projet_3_oc_maru.service.MeetingApiService;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private MeetingApiService service;

    @Before


    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings  = DummyMeetingGenerator.DUMMY_ROOM_MEETS;
        assertEquals(meetings,expectedMeetings);
    }

    @Test
    public void addMeetingWithSuccess() {
        Meeting meetingToAdd = DummyMeetingGenerator.DUMMY_ROOM_MEETS.get(0);
        service.createMeeting(meetingToAdd);
        assertTrue(service.getMeetings().contains(meetingToAdd));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }


    @Test
    public void getMeetingsFilterRoom() {
        String expectedMeetings = service.getMeetings().get(0).getMeetingRoom().getmNameRoomMeeting();
        assertEquals(service.getMeetingsFilterRoom("Peach").get(0).getMeetingRoom().getmNameRoomMeeting(), expectedMeetings);
    }

    @Test
    public void getMeetingFilterDate() {
        String expectedMeetings = service.getMeetings().get(0).getMeetingRoom().getmNameRoomMeeting();
        assertEquals(service.getMeetingsFilterDate(LocalDate.of(2021, 02, 14)).get(0).getMeetingRoom().getmNameRoomMeeting(), expectedMeetings);
    }

    @Test
    public void resetFilter(){
        service.resetFilter();
        assertTrue(service.getRes().isEmpty());

    }


}