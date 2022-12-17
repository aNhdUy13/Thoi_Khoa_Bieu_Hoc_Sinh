package com.nda.timetable.Models;

public class Timetable {
    private int id;
    private String day, slot, time, subject;

    public Timetable(int id, String day, String slot, String time, String subject) {
        this.id = id;
        this.day = day;
        this.slot = slot;
        this.time = time;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
