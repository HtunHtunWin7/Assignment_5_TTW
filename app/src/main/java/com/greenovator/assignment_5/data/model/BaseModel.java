package com.greenovator.assignment_5.data.model;

import com.greenovator.assignment_5.network.dataagent.EventDataAgent;
import com.greenovator.assignment_5.network.dataagent.HttpUriConnectionAgent;
import com.greenovator.assignment_5.network.dataagent.OkHttpUriConnectionAgent;


public abstract class BaseModel {
    protected EventDataAgent mDataAgent;

    BaseModel() {
       // mDataAgent = HttpUriConnectionAgent.getObjInstance();
        mDataAgent = OkHttpUriConnectionAgent.getObjInstance();
    }

}
