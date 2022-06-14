package app;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;

/**
 * Třída pro konstrukci jednoho ticketu
 */
public class Ticket implements Comparable<Ticket>, Serializable {
    private String subject;
    private String message;
    private String from;
    private String to;
    private Boolean done;
    private String time;

    /**
     * vrátí čas vytvoření ticketu
     * @return string
     */
    public String getTime() {
        return time;
    }

    /**
     * konstruktor
     * @param _subject
     * @param _message
     * @param _from
     * @param _to
     * @param _time
     */
    public Ticket(String _subject, String _message, String _from, String _to, String _time){
        subject = _subject;
        message = _message;
        from = _from;
        to = _to;
        time = _time;
        done = false;
    }

    /**
     * přepsaná metoda compareTo pro porovnání podle názvu ticketu
     * @param other
     * @return int
     */
    @Override
    public int compareTo( Ticket other) {
        return this.subject.compareTo(other.subject);
    }

    /**
     * vrátí předmět
     * @return String
     */
    public String getSubject() {
        return subject;
    }

    /**
     * vrátí zprávu
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * vrátí odesílatele
     * @return String
     */
    public String getFrom() {
        return from;
    }

    /**
     * vrátí příjemce
     * @return String
     */
    public String getTo() {
        return to;
    }

    /**
     * vrátí "hotovost" ticketu
     * @return boolean
     */
    public Boolean getDone() {
        return done;
    }

    /**
     * nastaví hotovost ticketu
     * @param done
     */
    public void setDone(Boolean done) {
        this.done = done;
    }

    /**
     * toString metoda pro výpis ticketu
     * @return String
     */
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("\n\nfrom: " + this.from + "\nto: ");
        s.append(this.to + "\nSubject: ");
        s.append(this.subject + "\nMessage: ");
        s.append(this.message + "\nState: ");
        s.append(this.done + "\n\n");
        s.append(this.time + "\n");
        return s.toString();
    }
}
