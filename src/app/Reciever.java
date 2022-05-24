package app;

import java.util.ArrayList;

public class Reciever {


    private String email = "";
    private String password = "";
    private ArrayList<Ticket> tickets;
    public Reciever(String _email, String _password){
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
