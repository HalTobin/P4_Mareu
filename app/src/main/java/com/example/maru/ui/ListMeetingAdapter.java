package com.example.maru.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.maru.databinding.FragmentItemListMeetingBinding;
import com.example.maru.event.DeleteMeetingEvent;
import com.example.maru.model.Meeting;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ListMeetingAdapter extends RecyclerView.Adapter<ListMeetingAdapter.ViewHolder> {

    private final List<Meeting> mMeetings;

    public ListMeetingAdapter(List<Meeting> items) {
        mMeetings = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FragmentItemListMeetingBinding view = FragmentItemListMeetingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Meeting meeting = mMeetings.get(position);
        holder.binding.itemListMeetingTxtName.setText(meeting.getName());
        Glide.with(holder.binding.itemListMeetingImg.getContext())
                .load(meeting.getColorHex())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.binding.itemListMeetingImg);

        holder.binding.itemListDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private FragmentItemListMeetingBinding binding;

        public ViewHolder(FragmentItemListMeetingBinding view) {
            super(view.getRoot());
            binding = view;
        }

    }

}
