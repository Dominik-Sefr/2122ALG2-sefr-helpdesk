package app;

public class Ticket {
    private String subject;
    private String message;
    private String from;
    private String to;
    private Boolean done;

    public Ticket(String _subject, String _message, String _from, String _to){
        subject = _subject;
        message = _message;
        from = _from;
        to = _to;
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
}
