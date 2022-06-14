package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída pro vytvoření uživatele a uchování jeho informací
 */
public class User implements Serializable {
    private String email;
    private String password;
    private List<Ticket> tickets;

    /**
     * konstruktor
     * @param _email
     * @param _password
     */
    public User(String _email,String _password){
        email = _email;
        password = _password;
        tickets = new ArrayList<Ticket>();
    }

    /**
     * přidá ticket do listu uživatele
     * @param _ticket
     */
    public void AddTicket(Ticket _ticket){
        tickets.add(_ticket);

    }

    /**
     * vrátí email uživatele
     * @return string
     */
    public String getEmail() {
        return email;
    }

    /**
     * vrátí heslo uživatele
     * @return string
     */
    public String getPassword() {
        return password;
    }

    /**
     * nahraje změněný list
     * @param ticks
     */
    public void setTickets(List<Ticket> ticks) {
        this.tickets = ticks;
    }

    /**
     * vrátí list s tickety
     * @return List<Ticket>
     */
    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * odstraní ticket
     * @param subject
     */
    public void RemoveTicket(String subject) {
        for(int i = 0; i < tickets.size(); i++){
            if(subject.equals(tickets.get(i).getSubject())){
                tickets.remove(i);
                break;
            }
        }
    }

    /**
     * zkontroluje zda ticket již existuje
     * @param subject
     * @return boolean
     */
    public boolean CheckifExists(String subject){
        for(int i = 0; i < tickets.size(); i++){
            if(subject.equals(tickets.get(i).getSubject())){
                return true;
            }
        }
        return false;
    }
}
