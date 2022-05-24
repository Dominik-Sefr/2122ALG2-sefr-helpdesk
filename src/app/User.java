package app;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String email;
    private String password;
    private ArrayList<Ticket> tickets;
    public User(String _email,String _password){
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
    public void RemoveTicket(String subject) {
        for(int i = 0; i < tickets.size(); i++){
            if(subject.equals(tickets.get(i).getSubject())){
                tickets.remove(i);
                break;
            }
        }
    }
}
