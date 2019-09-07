package com.greenovator.assignment_5.network.dataagent;

import com.greenovator.assignment_5.network.response.EventItemResponse;
import com.greenovator.assignment_5.network.response.HouseApi;
import com.greenovator.assignment_5.utils.EventConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUriConnectionAgent implements EventDataAgent {
    private static RetrofitUriConnectionAgent objInstance;
    private GetEventsFromNetworkDelegate getEventsFromNetworkDelegate;
    private HouseApi houseApi;

    public static RetrofitUriConnectionAgent getObjInstance() {
        if (objInstance == null)
            objInstance = new RetrofitUriConnectionAgent();
        return objInstance;
    }

    private RetrofitUriConnectionAgent() {
        final OkHttpClient mHttppClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EventConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mHttppClient)
                .build();
        houseApi = retrofit.create(HouseApi.class);

    }

    @Override
    public void getEvents(final GetEventsFromNetworkDelegate delegate) {
        Call<EventItemResponse> call = houseApi.getAllHouse();
        call.enqueue(new Callback<EventItemResponse>() {
            @Override
            public void onResponse(Call<EventItemResponse> call, Response<EventItemResponse> response) {
                EventItemResponse eventItemResponse = response.body();
                delegate.onSuccess(eventItemResponse.getEventVoList());
            }

            @Override
            public void onFailure(Call<EventItemResponse> call, Throwable t) {
                delegate.onFailure(t.getMessage());
            }
        });

    }
}
