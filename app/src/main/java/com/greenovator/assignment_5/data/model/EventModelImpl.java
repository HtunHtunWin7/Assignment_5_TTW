package com.greenovator.assignment_5.data.model;

import com.greenovator.assignment_5.data.VO.EventVo;
import com.greenovator.assignment_5.network.dataagent.EventDataAgent;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventModelImpl extends BaseModel implements EventModel {


    private static EventModelImpl objInstance;
    private Map<Integer, EventVo> evetnsDataRepository;

    //static method
    public static EventModelImpl getObjInstance() {
        if (objInstance == null) {
            objInstance = new EventModelImpl();
        }
        return objInstance;
    }


    private EventModelImpl() {
        this.evetnsDataRepository = new HashMap<>();
    }


    @Override
    public void getEvents(GetEventsFromNetworkDelegate delegate) {
        mDataAgent.getEvents(new EventDataAgent.GetEventsFromNetworkDelegate() {
            @Override
            public void onSuccess(List<EventVo> eventVos) {

            }

            @Override
            public void onFailure(String errorMessage) {

            }
        });
    }
}
