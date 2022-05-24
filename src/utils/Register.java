package utils;

import app.MailSender;
import app.User;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.ArrayList;

public class Register {
    public Register(){}
    public Boolean addUser(ArrayList<User> users, String email, String password){
        Boolean itsin = true;
        for(User u : users){
            if(u.getEmail().equals(email)){
                itsin = false;
            }
        }
        return itsin;
    }
    public Boolean login(ArrayList<User> users, String email, String password){
        Boolean itsin = false;
        for(User u : users){
            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                itsin = true;
            }
        }
        return itsin;
    }
}
