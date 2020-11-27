package com.example.maru.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.maru.R;
import com.example.maru.databinding.ActivityAddMeetingBinding;
import com.example.maru.databinding.ActivityListMeetingBinding;
import com.example.maru.di.DI;
import com.example.maru.model.Meeting;
import com.example.maru.service.MeetingApiService;

import java.util.Arrays;

import petrov.kristiyan.colorpicker.ColorPicker;

public class AddMeetingActivity extends BaseActivity<ActivityAddMeetingBinding> {

    private MeetingApiService myApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myApiService = DI.getMeetingApiService();

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


        init();

        binding.activityAddMeetingImgColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialColor();
            }
        });

        binding.activityAddMeetingBtAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialAddUser();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {

    }

    private void openDialColor() {
        System.out.println("CLICK");
        final ColorPicker colorPicker = new ColorPicker(this);
        colorPicker.disableDefaultButtons(true);
        colorPicker.addListenerButton(getString(R.string.select), new ColorPicker.OnButtonListener() {
            @Override
            public void onClick(View v, int position, int color) {
                binding.activityAddMeetingImgColor.setColorFilter(color);
                colorPicker.dismissDialog();
            }
        });
        colorPicker.addListenerButton(getString(R.string.cancel), new ColorPicker.OnButtonListener() {
            @Override
            public void onClick(View v, int position, int color) {
                colorPicker.dismissDialog();
            }
        });

        colorPicker.setTitle(getString(R.string.pick_a_color)).setColumns(5).show();
    }

    private void openDialAddUser() {

    }

    private void addMeeting() {
        Meeting meeting = new Meeting(myApiService.getNextId(), "Test", "A9", 1452834, "FFFFFF", Arrays.asList("a@mail.fr", "f.a@mail.com"));
        myApiService.createMeeting(meeting);
        finish();
    }

    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

}
