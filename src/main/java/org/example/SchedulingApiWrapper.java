package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;

public class SchedulingApiWrapper {
    private final String key;
    private final String baseURL;

    public SchedulingApiWrapper(String key, String baseURL) {
        this.key = key;
        this.baseURL = baseURL;
    }

    private Response sendPOSTRequest(String url, RequestBody body) {
        try {
            String fullURL = baseURL + url;
            if (url.contains("?")) {
                fullURL += "token=" + key;
            }
            else { fullURL += "?token=" + key; }
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(fullURL)
                    .method("POST", body)
                    .build();
            return client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Response sendGETRequest(String url) {
        try {
            String fullURL = baseURL + url;
            if (url.contains("?")) {
                fullURL += "token=" + key;
            }
            else { fullURL += "?token=" + key; }
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(fullURL)
                    .method("GET",null)
                    .build();
            return client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean startRun() {
        Response res;
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody reqBod = RequestBody.create(mediaType, "");
        res = sendPOSTRequest("Start", reqBod);
        return res.isSuccessful();
    }

    public boolean endRun() {
        Response res;
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody reqBod = RequestBody.create(mediaType, "");
        res = sendPOSTRequest("End", reqBod);
        return res.isSuccessful();
    }

    public Schedule getInitialSchedule() {
        Schedule sched = new Schedule();

        ResponseBody responseBody = sendGETRequest("Schedule").body();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ArrayList<Appointment> myObjects = objectMapper.readValue(responseBody.string(), new TypeReference<ArrayList<Appointment>>(){});
            for (Appointment appt: myObjects) {
                sched.addAppointment(appt);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sched;
    }

    public boolean scheduleAppointment() {
        boolean apptWasMade = true;
        return apptWasMade;
    }
}
