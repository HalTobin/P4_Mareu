package com.example.maru.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.maru.R;
import com.example.maru.di.DI;
import com.example.maru.databinding.ActivityListMeetingBinding;
import com.example.maru.event.DeleteMeetingEvent;
import com.example.maru.model.Meeting;
import com.example.maru.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class ListMeetingActivity extends BaseActivity<ActivityListMeetingBinding> {

    private ListMeetingAdapter myAdapter;

    private MeetingApiService myApiService;
    private List<Meeting> myMeetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApiService = DI.getMeetingApiService();

        binding.addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMeeting();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecycler();
    }

    private void initRecycler() {
        myMeetings = myApiService.getMeetings();

        myAdapter = new ListMeetingAdapter(myMeetings);
        binding.listMeetings.setLayoutManager(new LinearLayoutManager(this));
        binding.listMeetings.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.listMeetings.setAdapter(myAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteMeetingEvent event) {
        myApiService.deleteMeeting(event.meeting);
        initRecycler();
    }

    void addMeeting() {
        AddMeetingActivity.navigate(this);
    }
}