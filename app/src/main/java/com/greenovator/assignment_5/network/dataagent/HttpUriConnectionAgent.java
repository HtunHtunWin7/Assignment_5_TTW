package com.greenovator.assignment_5.network.dataagent;

import android.os.AsyncTask;


import com.google.gson.Gson;
import com.greenovator.assignment_5.network.response.EventItemResponse;
import com.greenovator.assignment_5.utils.EventConstants;

import org.apache.http.NameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class HttpUriConnectionAgent implements EventDataAgent {
    private static HttpUriConnectionAgent objInstance;

    private HttpUriConnectionAgent() {

    }

    public static HttpUriConnectionAgent getObjInstance() {
        if (objInstance == null)
            objInstance = new HttpUriConnectionAgent();
        return objInstance;
    }

    @Override
    public void getEvents(GetEventsFromNetworkDelegate delegate) {
        new GetEventsTask(delegate).execute();

    }

    public static class GetEventsTask extends AsyncTask<Void, Void, EventItemResponse> {
        private String accessToken;
        private GetEventsFromNetworkDelegate getEventsFromNetworkDelegate;

        public GetEventsTask(GetEventsFromNetworkDelegate delegate) {
            getEventsFromNetworkDelegate = delegate;
            //this.accessToken = accessToken;
        }

        @Override
        protected EventItemResponse doInBackground(Void... voids) {
            URL url;
            BufferedReader reader = null;
            StringBuilder stringBuilder;
            try {
                url = new URL(EventConstants.BASE_URL + EventConstants.GET_EVENT);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setReadTimeout(15 * 1000);
                connection.setDoInput(true);
                connection.setDoOutput(true);

                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                writer.flush();
                writer.close();
                outputStream.close();
                connection.connect();

                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                stringBuilder = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                String responseString = stringBuilder.toString();
                EventItemResponse response = new Gson().fromJson(responseString, EventItemResponse.class);

                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            boolean first = true;

            for (NameValuePair pair : params) {
                if (first)
                    first = false;
                else result.append("&");
                result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(EventItemResponse eventItemResponse) {
            super.onPostExecute(eventItemResponse);
        }
    }


}
