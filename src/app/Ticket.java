package app;

import java.io.Serializable;

public class Ticket implements Serializable {
    private String subject;
    private String message;
    private String from;
    private String to;
    private Boolean done;
    private String time;

    public String getTime() {
        return time;
    }

    public Ticket(String _subject, String _message, String _from, String _to, String _time){
        subject = _subject;
        message = _message;
        from = _from;
        to = _to;
        time = _time;
        done = false;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
    @Override
    public String toString(){
        String s = "From: " + getFrom() + "\nTo: " + getTo() + "\nSubject: " + getSubject() + "\nMessage: " + getMessage() + "\nDate: " + getTime() + "\nDone: " + getDone();
        return s;
    }
}
