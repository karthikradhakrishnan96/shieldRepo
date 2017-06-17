package com.endurance.shield.xmen.utils;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * Created by kai on 17/6/17.
 */
public class Cleaner {
    public static String cleanHtml(String unsafe){
        String safe;
        try {
            safe = Jsoup.clean(unsafe, Whitelist.basic());
        }
        catch(Exception E){
            safe = "<h1>Hey there</h1>";
        }
        return safe;
    }
}
