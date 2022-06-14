package utils;

import app.Ticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * třída pracuje s časy
 */
public class TimeDate {
    /**
     * konstruktor
     */
    public TimeDate() {
    }

    /**
     * vrací čas ve stringu
     * @return String
     */
    public String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    /**
     * porovnává časy dvou ticketů
     * @param o1
     * @param o2
     * @return int
     */
    public int comp(Ticket o1, Ticket o2) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        try {
            return f.parse(o1.getTime()).compareTo(f.parse(o2.getTime()));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}