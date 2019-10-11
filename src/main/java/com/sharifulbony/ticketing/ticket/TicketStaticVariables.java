package com.sharifulbony.ticketing.ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TicketStaticVariables {

    public static HashMap<String,String> priorities=new HashMap<>();
    static {
        priorities.put("CRITICAL","Critical");
        priorities.put("SEVERE","Severe");
        priorities.put("HIGH","High");
        priorities.put("MODERATE","Moderate");
        priorities.put("LOW","Low");
    }


    public static HashMap<String,String> status=new HashMap<>();
    static {
        status.put("OPEN","Open");
        status.put("CLOSE","Close");
    }
}
