package app;

import java.util.ArrayList;

public class Sender {
    private String email;
    private String password;
    private ArrayList<Ticket> tickets;
    public Sender(String _email,String _password){
        email = _email;
        password = _password;
        tickets = new ArrayList<Ticket>();
    }
    public void AddTicket(Ticket _ticket){
        tickets.add(_ticket);
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
}
