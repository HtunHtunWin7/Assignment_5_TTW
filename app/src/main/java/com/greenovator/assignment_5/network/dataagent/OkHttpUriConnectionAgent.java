package com.greenovator.assignment_5.network.dataagent;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.greenovator.assignment_5.network.response.EventItemResponse;
import com.greenovator.assignment_5.utils.EventConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUriConnectionAgent implements EventDataAgent {

    private static OkHttpUriConnectionAgent objInstance;
    private OkHttpClient mHttppClient;

    private OkHttpUriConnectionAgent() {
        mHttppClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpUriConnectionAgent getObjInstance() {
        if (objInstance == null)
            objInstance = new OkHttpUriConnectionAgent();
        return objInstance;
    }

    @Override
    public void getEvents(GetEventsFromNetworkDelegate delegate) {
        new GetEventsTask(delegate, mHttppClient).execute();
    }

    public static class GetEventsTask extends AsyncTask<Void, Void, EventItemResponse> {
        private GetEventsFromNetworkDelegate getEventsFromNetworkDelegate;
        private OkHttpClient mokHttpClient;

        public GetEventsTask(GetEventsFromNetworkDelegate delegate, OkHttpClient okHttpClient) {
            getEventsFromNetworkDelegate = delegate;
            mokHttpClient = okHttpClient;
        }

        @Override
        protected EventItemResponse doInBackground(Void... voids) {

            RequestBody formBody = new FormBody.Builder()
                    .build();

            Request request = new Request.Builder().url(EventConstants.BASE_URL + EventConstants.GET_EVENT)
                    .post(formBody)
                    .build();

            Response response = null;
            try {
                response = mokHttpClient.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseString = response.body().string();

                    EventItemResponse getEventResponse = new Gson().fromJson(responseString, EventItemResponse.class);
                    return getEventResponse;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(EventItemResponse eventItemResponse) {
            super.onPostExecute(eventItemResponse);
            if (eventItemResponse != null) {
                if (eventItemResponse.ResponseOk()) {
                    getEventsFromNetworkDelegate.onSuccess(eventItemResponse.getEventVoList());
                } else {
                    getEventsFromNetworkDelegate.onFailure(EventConstants.ERROR_MESSAGE);
                }
            } else {
                getEventsFromNetworkDelegate.onFailure(EventConstants.ERROR_MESSAGE);
            }
        }
    }
}
