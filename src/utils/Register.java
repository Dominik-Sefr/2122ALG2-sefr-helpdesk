package utils;

import app.MailSender;
import app.User;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.ArrayList;
import java.util.List;

/**
 * třída přidává uživatele do seznamu uživatelů
 */
public class Register {
    /**
     * konstruktor
     */
    public Register(){}

    /**
     * přidá uživatele
     * @param users
     * @param email
     * @param password
     * @return boolean
     */
    public Boolean addUser(List<User> users, String email, String password){
        Boolean itsin = true;
        for(User u : users){
            if(u.getEmail().equals(email)){
                itsin = false;
            }
        }
        return itsin;
    }

    /**
     * kontrola přihlášení
     * @param users
     * @param email
     * @param password
     * @return boolean
     */
    public Boolean login(List<User> users, String email, String password){
        Boolean itsin = false;
        for(User u : users){
            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                itsin = true;
            }
        }
        return itsin;
    }
}
