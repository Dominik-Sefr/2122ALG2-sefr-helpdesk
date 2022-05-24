package ui;

import app.MailSender;
import app.Ticket;
import app.User;
import utils.Register;
import utils.SaveToFile;
import utils.TimeDate;
import utils.emailChecker;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static Scanner sc;
    private static ArrayList<User> userList;
    private static SaveToFile stf;
    private static TimeDate td;
    private static Register reg;
    private static emailChecker eC;
    private static MailSender ms;
    public static void main(String[] args) {
        stf = new SaveToFile();
        eC = new emailChecker();
        reg = new Register();
        sc = new Scanner(System.in);
        td = new TimeDate();
        ms = new MailSender();
        userList = stf.LoadUser();
        final User reciever = new User("sefrhelpdeskjava@gmail.com", "semestralka2022");
        User logged = new User("noone@noone.com", "heslo");
        Boolean boollogged = false;
        while (true){
            if(!boollogged) {
                System.out.println("HELP DESK\nVyber si co chceš dělat:\n1) Přihlásit se\n2) Registrovat se\n3) Odejít");
                String temp = sc.next();
                if (temp.equals("1")) {
                    System.out.println("Zadej email: ");
                    String logmail;
                    while (true) {
                        String regmail_temp = sc.next();
                        if (eC.isValid(regmail_temp)) {
                            logmail = regmail_temp;
                            break;
                        } else {
                            System.out.println("Zadal jsi špatně email, zadej ho správně: ");
                        }
                    }
                    System.out.println("Zadej heslo: ");
                    int error = 3;
                    while(error > 0) {
                        String logpass = sc.next();
                        if (reg.login(userList, logmail, logpass)) {
                            for (User u : userList) {
                                if (u.getEmail().equals(logmail)) {
                                    logged = u;
                                    boollogged = true;
                                    break;
                                }
                            }
                            if(boollogged){
                                break;
                            }
                        } else {
                            System.out.println("Zadal jsi špatně email nebo heslo, zadej ho znovu máš "+ error +" pokusy: ");
                            error--;
                        }
                    }
                } else if (temp.equals("2")) {
                    System.out.println("Zadej email: ");
                    String regmail;
                    while (true) {
                        String regmail_temp = sc.next();
                        if (eC.isValid(regmail_temp)) {
                            regmail = regmail_temp;
                            break;
                        } else {
                            System.out.println("Zadal jsi špatně email, zadej ho správně: ");
                        }
                    }
                    System.out.println("Zadej heslo: ");
                    String regpass = sc.next();
                    if(reg.addUser(userList, regmail, regpass)){
                        userList.add(new User(regmail,regpass));
                        stf.Save(userList);
                    }
                } else {
                    System.out.println("Ukládám data");
                    stf.Save(userList);
                    System.out.println("Ukončuji program...");
                    return;
                }
            }
            else{
                System.out.println("--- Přihlášen: " + logged.getEmail() + " ---");
                System.out.println("0) Odhlásit se\n1) Napsat ticket\n2) Moje tickety\n3) Odebrat ticket");
                String menu2 = sc.next();

                if(menu2.equals("1")){
                    System.out.println("--- Přihlášen: " + logged.getEmail() + " ---");
                    System.out.println("Zadej předmět: ");
                    String subject = sc.next();
                    System.out.println("Zadej zprávu: ");
                    String message = sc.next();
                    Ticket t = new Ticket(subject, message, logged.getEmail(), reciever.getEmail(), td.getTime());
                    logged.AddTicket(t);
                    stf.Save(userList);
                    ms.Send(logged.getEmail(), logged.getPassword(), reciever.getEmail(), subject, message);
                }
                else if(menu2.equals("2")){
                    System.out.println("--- Přihlášen: " + logged.getEmail() + " ---");
                    int i = 0;
                    for(Ticket t : logged.getTickets()){
                        System.out.println(i + " " + t.getSubject() + " : " + t.getDone());
                        i++;
                    }
                }
                else if(menu2.equals("3")){
                    System.out.println("--- Přihlášen: " + logged.getEmail() + " ---");
                    System.out.println("Napiš předmět ticketu, který chceš odebrat: ");
                    String subject = sc.next();
                    logged.RemoveTicket(subject);
                    stf.Save(userList);
                    ms.Send(logged.getEmail(), logged.getPassword(), reciever.getEmail(), "Zrušení ticketu", "Ticket '" + subject + "' byl zrušen");
                }
                else {
                    boollogged = false;
                    stf.Save(userList);
                    continue;
                }
            }

        }
    }
}
