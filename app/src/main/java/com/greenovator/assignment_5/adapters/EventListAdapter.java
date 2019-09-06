package com.greenovator.assignment_5.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.greenovator.assignment_5.R;
import com.greenovator.assignment_5.data.VO.EventVo;
import com.greenovator.assignment_5.delegate.EventDetailDelegate;
import com.greenovator.assignment_5.view.holder.EventListHolder;

public class EventListAdapter extends BaseRecyclerAdapter<EventListHolder, EventVo> {
private EventDetailDelegate eventDetailDelegate;

    public EventListAdapter(EventDetailDelegate delegate) {
        eventDetailDelegate = delegate;

    }

    @NonNull
    @Override
    public EventListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item_list,parent,false);
        return new EventListHolder(view,eventDetailDelegate);
    }




}
