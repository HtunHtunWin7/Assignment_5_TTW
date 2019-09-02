package com.greenovator.assignment_5.network.response;

import com.greenovator.assignment_5.data.VO.EventVo;

import java.util.List;

public class EventItemResponse {

    private String code;
    private String message;
    private List<EventVo> eventVoList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<EventVo> getEventVoList() {
        return eventVoList;
    }

    public void setEventVoList(List<EventVo> eventVoList) {
        this.eventVoList = eventVoList;
    }
}
