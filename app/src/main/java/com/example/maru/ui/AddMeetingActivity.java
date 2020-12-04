package com.example.maru.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.maru.R;
import com.example.maru.base.BaseActivity;
import com.example.maru.databinding.ActivityAddMeetingBinding;
import com.example.maru.di.DI;
import com.example.maru.dialog.PickUserDialog;
import com.example.maru.event.DeleteUserEvent;
import com.example.maru.event.GetUserEvent;
import com.example.maru.model.Meeting;
import com.example.maru.service.MeetingApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import petrov.kristiyan.colorpicker.ColorPicker;

public class AddMeetingActivity extends BaseActivity<ActivityAddMeetingBinding> implements PickUserDialog.PickUserDialogListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private MeetingApiService myApiService;

    private ListUserAdapter myAdapter;

    private Meeting newMeeting;
    private String newMeetingName;
    private int newMeetingColor = 0x000000;
    private int newMeetingDay;
    private int newMeetingMonth;
    private int newMeetingYear;
    private int newMeetingHour;
    private int newMeetingMin;
    private String newMeetingRoom;
    private ArrayList<String> newMeetingUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myApiService = DI.getMeetingApiService();

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        binding.activityAddMeetingImgColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialColor();
            }
        });

        binding.activityAddMeetingBtPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialDate();
            }
        });

        binding.activityAddMeetingBtPickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialTime();
            }
        });

        binding.activityAddMeetingBtAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialAddUser();
            }
        });

        binding.activityAddMeetingBtCreateMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMeeting();
            }
        });

        binding.activityAddMeetingTxtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                newMeetingName = s.toString();
                isFilled();
            }
        });

        binding.activityAddMeetingTxtRoom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                newMeetingRoom = s.toString();
                isFilled();
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

    private void initRecycler() {
        myAdapter = new ListUserAdapter(newMeetingUsers);

        binding.activityAddMeetingListUser.setLayoutManager(new LinearLayoutManager(this));
        binding.activityAddMeetingListUser.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.activityAddMeetingListUser.setAdapter(myAdapter);

        isFilled();
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecycler();
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

    @Subscribe
    public void onDeleteUser(DeleteUserEvent event) {
        newMeetingUsers.remove(event.user);
        initRecycler();
    }

    private void openDialColor() {
        final ColorPicker colorPickerDialog = new ColorPicker(this);
        colorPickerDialog.disableDefaultButtons(true);
        colorPickerDialog.addListenerButton(getString(R.string.cancel), new ColorPicker.OnButtonListener() {
            @Override
            public void onClick(View v, int position, int color) {
                colorPickerDialog.dismissDialog();
            }
        });
        colorPickerDialog.addListenerButton(getString(R.string.select), new ColorPicker.OnButtonListener() {
            @Override
            public void onClick(View v, int position, int color) {
                newMeetingColor = color;
                binding.activityAddMeetingImgColor.setColorFilter(newMeetingColor);
                colorPickerDialog.dismissDialog();
            }
        });
        colorPickerDialog.setTitle(getString(R.string.pick_a_color)).setColumns(5).show();
        isFilled();
    }

    private void openDialDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void openDialTime() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                this,
                Calendar.getInstance().get(Calendar.MINUTE),
                Calendar.getInstance().get(Calendar.HOUR),
                true);
        timePickerDialog.show();
    }

    private void openDialAddUser() {
        PickUserDialog pickUserDialog = new PickUserDialog();
        pickUserDialog.show(getSupportFragmentManager(), "Pick User Dialog");
    }

    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        binding.activityAddMeetingTxtDate.setText(String.format("%02d", dayOfMonth) + "/" + String.format("%02d", month+1) + "/" + String.format("%04d", year));
        newMeetingDay = dayOfMonth;
        newMeetingMonth = month;
        newMeetingYear = year;
        openDialTime();
        isFilled();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        binding.activityAddMeetingTxtTime.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
        newMeetingHour = hourOfDay;
        newMeetingMin = minute;
        isFilled();
    }

    @Override
    public void sendUser(String user) {
        if(!newMeetingUsers.contains(user)) newMeetingUsers.add(user);
        initRecycler();
    }

    private boolean isFilled() {
        if(binding.activityAddMeetingTxtName.getText().toString().isEmpty()) {
            binding.activityAddMeetingBtCreateMeeting.setBackground(getDrawable(R.drawable.rounded_shape));
            return false;
        }
        if(newMeetingColor==0x000000) {
            binding.activityAddMeetingBtCreateMeeting.setBackground(getDrawable(R.drawable.rounded_shape));
            return false;
        }
        if(binding.activityAddMeetingTxtDate.getText()==getString(R.string.hint_date)) {
            binding.activityAddMeetingBtCreateMeeting.setBackground(getDrawable(R.drawable.rounded_shape));
            return false;
        }
        if(binding.activityAddMeetingTxtTime.getText()==getString(R.string.hint_hour)) {
            binding.activityAddMeetingBtCreateMeeting.setBackground(getDrawable(R.drawable.rounded_shape));
            return false;
        }
        if(binding.activityAddMeetingTxtRoom.getText().toString().isEmpty()) {
            binding.activityAddMeetingBtCreateMeeting.setBackground(getDrawable(R.drawable.rounded_shape));
            return false;
        }
        if(newMeetingUsers.isEmpty()) {
            binding.activityAddMeetingBtCreateMeeting.setBackground(getDrawable(R.drawable.rounded_shape));
            return false;
        }

        binding.activityAddMeetingBtCreateMeeting.setBackground(getDrawable(R.drawable.rounded_shape_on));
        return true;
    }

    private void createMeeting() {
        if(isFilled())
        {
            newMeeting = new Meeting();
            newMeeting.setId(myApiService.getNextId());
            newMeeting.setName(newMeetingName);
            newMeeting.setRoom(newMeetingRoom);
            newMeeting.setDate(new Date(newMeetingYear, newMeetingMonth, newMeetingDay, newMeetingHour, newMeetingMin));
            newMeeting.setColorInt(newMeetingColor);
            newMeeting.setUsers(newMeetingUsers);
            myApiService.createMeeting(newMeeting);
            finish();
        }
    }
}
