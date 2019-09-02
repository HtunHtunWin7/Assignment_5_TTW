package com.greenovator.assignment_5.view.holder;

import android.view.View;

import androidx.annotation.NonNull;
import com.greenovator.assignment_5.data.VO.EventVo;
import com.greenovator.assignment_5.delegate.EventDetailDelegate;

public class EventListHolder extends BaseViewHolder<EventVo> {
    private EventDetailDelegate detailDelegate;

    public EventListHolder(@NonNull View itemView,EventDetailDelegate delegate) {
        super(itemView);
        detailDelegate = delegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailDelegate.onClickDetail();
            }
        });
    }

    @Override
    public void bindData(EventVo data) {

    }
}
