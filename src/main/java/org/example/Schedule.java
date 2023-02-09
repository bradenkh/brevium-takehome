package org.example;

import javax.print.Doc;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Schedule {
    private HashMap<LocalDate, HashMap<Integer, HashMap<Integer, Appointment>>> schedule;

    public Schedule() {
        this.schedule = new HashMap<>();
        LocalDate start = LocalDate.of(2021, 11, 1);
        LocalDate end = LocalDate.of(2021,12,31);

        for (int i = 0; start.plusDays(i).isBefore(end); i++) {
            switch (start.plusDays(i).getDayOfWeek()){
                case SATURDAY:
                    // we don't work this day!
                    break;
                case SUNDAY:
                    // or this day!
                    break;
                default:
                    HashMap<Integer, HashMap<Integer, Appointment>> doctorScheds = new HashMap<>();
                    doctorScheds.put(1, new HashMap<>());
                    doctorScheds.put(2, new HashMap<>());
                    doctorScheds.put(3, new HashMap<>());
                    schedule.put(start.plusDays(i), doctorScheds);
                    break;
            }
        }
    }

    public void addAppointment(Appointment appt) {
        LocalDate date = appt.getAppointmentLocalDate();
        int doctorId = appt.getDoctorId();
        if (appt.getIsNewPatientAppointment()) {
            // check for 3 and 4pm slots, if either is empty, schedule
            if(schedule.get(date).get(doctorId).get(3) == null) {
                schedule.get(date).get(doctorId).put(3,appt);
            }
            else if (schedule.get(date).get(doctorId).get(4) == null) {
                schedule.get(date).get(doctorId).put(4,appt);
            }
            else {
                // throw scheduling exception
            }
        }
        // else schedule in the earliest available
    }

}
