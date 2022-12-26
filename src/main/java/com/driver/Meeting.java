package com.driver;

import java.time.LocalTime;
import java.io.*;
import java.util.*;

public class Meeting {
    private LocalTime startTime;
    private LocalTime endTime;

    public Meeting(LocalTime startTime, LocalTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }



    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

}

class meetingCompare implements Comparator<Meeting>
{
    public int compare(Meeting m1, Meeting m2)
    {
        if (m1.getStartTime().compareTo(m2.getStartTime())<0) return -1;
        if (m1.getStartTime().compareTo(m2.getStartTime())>0) return 1;
        else return 0;
    }
}
