package com.example.maru.ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.maru.R;
import com.example.maru.databinding.ItemListMeetingBinding;
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
        ItemListMeetingBinding view = ItemListMeetingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Meeting meeting = mMeetings.get(position);

        holder.binding.itemListMeetingTxtName.setText(meeting.getName());
        holder.binding.itemListMeetingImg.setColorFilter(meeting.getColorInt(), PorterDuff.Mode.SRC_ATOP);
        holder.binding.itemListDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });

        holder.binding.itemListMeetingTxtUsers.setText(meeting.getUsersString());
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemListMeetingBinding binding;

        public ViewHolder(ItemListMeetingBinding view) {
            super(view.getRoot());
            binding = view;
        }

    }

}
