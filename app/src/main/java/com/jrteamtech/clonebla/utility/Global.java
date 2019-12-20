package com.jrteamtech.clonebla.utility;

public class Global {

    Global(){}

    public static String GOOGLE_MAP_SEARCH_PLACE_URL = "https://maps.googleapis.com/maps/api/place/textsearch/xml";

    static String selected_time;

    public static String getSelected_time() {
        return selected_time;
    }

    public static void setSelected_time(String selected_time) {
        Global.selected_time = selected_time;
    }
}