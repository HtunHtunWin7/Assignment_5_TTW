package com.greenovator.assignment_5.network.response;

import com.greenovator.assignment_5.utils.EventConstants;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HouseApi {

    @POST(EventConstants.GET_EVENT)
    Call<EventItemResponse> getAllHouse();
}
