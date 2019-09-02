package com.greenovator.assignment_5.data.model;

import com.greenovator.assignment_5.data.VO.EventVo;


import java.util.List;

public interface EventModel {
    void getEvents(GetEventsFromNetworkDelegate delegate);

    interface GetEventsFromNetworkDelegate {
        void onSuccess(List<EventVo> eventVos);

        void onFailure(String errorMessage);
    }
}
