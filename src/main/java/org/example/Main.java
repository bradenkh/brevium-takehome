package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        SchedulingApiWrapper api = new SchedulingApiWrapper(System.getenv("key"), System.getenv("base_url"));
        // start run
        api.startRun();
        // get the initial schedule
        Schedule schedule = api.getInitialSchedule();
        // get the appointment requests

        // sort appointment requests by scheduling difficulty

        // loop, start with appointments that have the hardest scheduling difficulty (low is hard)
            // schedule appt

        // verify that schedule has no errors

        // upload new appointments via schedule appt endpoint

    }
}