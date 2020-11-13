package com.example.maru.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.maru.R;
import com.example.maru.databinding.ActivityListMeetingBinding;

import java.util.ArrayList;

public class ListMeetingActivity extends AppCompatActivity {

    private ActivityListMeetingBinding binding;

    private ListMeetingAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecycler();
    }

    private void initRecycler() {
        myAdapter = new ListMeetingAdapter(new ArrayList());
        binding.listMeetings.setLayoutManager(new LinearLayoutManager(this));
        binding.listMeetings.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.listMeetings.setAdapter(myAdapter);
    }
}