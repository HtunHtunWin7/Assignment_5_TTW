package com.greenovator.assignment_5.network.response;

import com.google.gson.annotations.SerializedName;
import com.greenovator.assignment_5.data.VO.EventVo;
import com.greenovator.assignment_5.utils.EventConstants;

import java.util.List;

public class EventItemResponse {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<EventVo> eventVoList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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


    public Boolean ResponseOk (){
        return code == EventConstants.EVETN_RESPONSE_OK && eventVoList != null;
    }
}
