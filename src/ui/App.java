package ui;

import app.MailSender;
import app.Ticket;
import app.User;
import utils.Register;
import utils.SaveToFile;
import utils.TimeDate;
import utils.emailChecker;

import java.sql.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {
    private static Scanner sc;
    private static List<User> userList;
    private static SaveToFile stf;
    private static TimeDate td;
    private static Register reg;
    private static emailChecker eC;
    private static MailSender ms;
    public static void main(String[] args) {
        userList = new ArrayList<User>();
        try {
            stf = new SaveToFile();
        }
        catch(Exception e){
            System.out.println("Špatný soubor");
        }
        eC = new emailChecker();
        reg = new Register();
        sc = new Scanner(System.in);
        td = new TimeDate();
        ms = new MailSender();
        if(stf.NewFileCheck()){
            try {
                stf.Save(userList);
            }
            catch(Exception e){
                System.out.println("Špatný soubor");
            }
        }
        try{
            userList = stf.LoadUser();
        } catch(Exception e){
            System.out.println("Chyba vstupního souboru, smaž users.txt");
            System.out.println("Ukončuji program...");
            return;
        }
        final User reciever = new User("sefrhelpdeskjava@centrum.cz", "Semestralka2022");
        userList.add(reciever);
        try {
            stf.Save(userList);
        }
        catch(Exception e){
            System.out.println("Špatný soubor");
        }
        User logged = new User("noone@noone.com", "heslo");
        Boolean boollogged = false;
        while (true){
            if(!boollogged) {
                System.out.println("Vyber si co chceš dělat:\n1) Přihlásit se\n2) Registrovat se\n?) Odejít");
                String temp = sc.next();
                if (temp.equals("1")) {
                    System.out.println("Zadej email: ");
                    String logmail = "";
                    int erroremail = 3;
                    while (erroremail >= 0) {
                        String regmail_temp = sc.next();
                        if (eC.isValid(regmail_temp)) {
                            logmail = regmail_temp;
                            break;
                        } else {
                            System.out.println("Zadal jsi špatně email, zadej ho znovu máš " + erroremail + " pokusy: ");
                            erroremail--;
                        }
                    }
                    if(erroremail > 0) {
                        System.out.println("Zadej heslo: ");
                        int error = 3;
                        while (error >= 0) {
                            String logpass = sc.next();
                            if (reg.login(userList, logmail, logpass)) {
                                for (User u : userList) {
                                    if (u.getEmail().equals(logmail)) {
                                        logged = u;
                                        boollogged = true;
                                        break;
                                    }
                                }
                                if (boollogged) {
                                    break;
                                }
                            } else {
                                System.out.println("Zadal jsi špatně email nebo heslo, zadej ho znovu máš " + error + " pokusy: ");
                                error--;
                            }
                        }
                    }
                } else if (temp.equals("2")) {
                    System.out.println("Zadej email: ");
                    String regmail = "";
                    int errormail = 3;
                    while (errormail >= 0) {
                        String regmail_temp = sc.next();
                        if (eC.isValid(regmail_temp)) {
                            regmail = regmail_temp;
                            break;
                        } else {
                            System.out.println("Zadal jsi špatně email, zadej ho znovu máš " + errormail + " pokusy: ");
                        }
                    }
                    System.out.println("Zadej heslo: ");
                    String regpass = sc.next();
                    if(reg.addUser(userList, regmail, regpass)){
                        userList.add(new User(regmail,regpass));
                        try {
                            stf.Save(userList);
                        }
                        catch(Exception e){
                            System.out.println("Špatný soubor");
                        }
                    }
                } else {
                    System.out.println("Ukládám data");
                    try {
                        stf.Save(userList);
                    }
                    catch(Exception e){
                        System.out.println("Špatný soubor");
                    }
                    System.out.println("Ukončuji program...");
                    return;
                }
            }
            else{
                String menu2 = "";
                if(logged.getEmail().equals(reciever.getEmail())){
                    System.out.println("--- Přihlášen: " + logged.getEmail() + " ---");
                    System.out.println("?) Odhlásit se\n2) tickety\n3) Odebrat ticket");
                    menu2 = sc.next();
                }
                else {
                    System.out.println("--- Přihlášen: " + logged.getEmail() + " ---");
                    System.out.println("?) Odhlásit se\n1) Napsat ticket\n2) Moje tickety\n3) Odebrat ticket");
                    menu2 = sc.next();

                    if (menu2.equals("1")) {
                        System.out.println("--- Přihlášen: " + logged.getEmail() + " ---");
                        System.out.println("Zadej předmět: ");
                        String subject = sc.next();
                        if (!(logged.CheckifExists(subject))) {
                            System.out.println("Zadej zprávu: ");
                            String message = sc.next();
                            Ticket t = new Ticket(subject, message, logged.getEmail(), reciever.getEmail(), td.getTime());
                            if (ms.Send(logged.getEmail(), logged.getPassword(), reciever.getEmail(), subject, message)) {
                                System.out.println("Ticket odeslán");
                                logged.AddTicket(t);
                                reciever.AddTicket(t);
                                try {
                                    stf.Save(userList);
                                }
                                catch(Exception e){
                                    System.out.println("Špatný soubor");
                                }
                            } else {
                                System.out.println(logged.getPassword() + " " + logged.getEmail());
                                System.out.println("Ticket nebyl odeslán");
                            }
                        } else {
                            System.out.println("Ticket už existuje");
                        }
                    }
                }
                if(menu2.equals("2")){
                    System.out.println("--- Přihlášen: " + logged.getEmail() + " ---");
                    int i = 0;
                    for(Ticket t : logged.getTickets()){
                        System.out.println(i + " " + t.getSubject() + " : " + ((t.getDone()) ? "Hotovo" : "Aktivní"));
                        i++;
                    }
                    System.out.println("\n0-n) pro zobrazení ticketu zadejte jeho číslo\ns) sort podle jmen\nr) sort podle času\nv) uložit tickety do souboru\n?) zpět\n");
                    String inpt = sc.next();
                    for(int j = 0; j < logged.getTickets().size(); j++){
                        if(Integer.toString(j).equals(inpt)){
                            System.out.println("test");
                            System.out.println(logged.getTickets().get(j));
                            if(logged.getEmail().equals(reciever.getEmail())){
                                System.out.println("1) splní ticket");
                                String temp = sc.next();
                                if(temp.equals("1")){
                                    logged.getTickets().get(j).setDone(true);
                                    for(User u : userList){
                                        if(u.getEmail().equals(logged.getTickets().get(j).getFrom())){
                                            for(Ticket t : u.getTickets()){
                                                if(t.getSubject().equals(logged.getTickets().get(j).getSubject())){
                                                    t.setDone(true);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(inpt.equals("s")) {
                        Collections.sort(logged.getTickets());
                        int p = 0;
                        for(Ticket t : logged.getTickets()){
                            System.out.println(p + " " + t.getSubject() + " : " + ((t.getDone()) ? "Hotovo" : "Aktivní"));
                            p++;
                        }
                    }
                    if(inpt.equals("r")){
                        List<Ticket> copy = new ArrayList<>(logged.getTickets());
                        Collections.sort(copy, (Ticket t1, Ticket t2) -> td.comp(t1,t2));
                        logged.setTickets(copy);
                        int p = 0;
                        for(Ticket t : logged.getTickets()){
                            System.out.println(p + " " + t.getSubject() + " : " + ((t.getDone()) ? "Hotovo" : "Aktivní"));
                            p++;
                        }
                    }
                    if(inpt.equals("v")){
                        StringBuilder strn = new StringBuilder();
                        List<Ticket> copy = new ArrayList<>(logged.getTickets());
                        strn.append("Tickets of user: " + logged.getEmail());
                        for(Ticket tick : copy){
                            strn.append("\n" + tick.toString());
                        }
                        try{
                            stf.SaveTicketsToFile(strn.toString());
                        } catch(Exception e){
                            System.out.println("Chyba v uložení do souboru");
                        }
                    }
                }
                else if(menu2.equals("3")){
                    System.out.println("--- Přihlášen: " + logged.getEmail() + " ---");
                    System.out.println("Napiš předmět ticketu, který chceš odebrat: ");
                    String subject = sc.next();
                    if(logged.CheckifExists(subject)) {
                        if (ms.Send(logged.getEmail(), logged.getPassword(), reciever.getEmail(), "Zrušení ticketu", "Ticket '" + subject + "' byl zrušen")) {
                            System.out.println("Ticket smazán");
                            if(logged.getEmail().equals(reciever.getEmail())) {
                                logged.RemoveTicket(subject);
                                for(User u : userList){
                                    for(Ticket t : u.getTickets()){
                                        if(t.getSubject().equals(subject)){
                                            u.RemoveTicket(subject);
                                        }
                                    }
                                }
                            }
                            else {
                                logged.RemoveTicket(subject);
                                reciever.RemoveTicket(subject);
                            }
                            try {
                                stf.Save(userList);
                            }
                            catch(Exception e){
                                System.out.println("Špatný soubor");
                            }
                        } else {
                            System.out.println("Ticket nebylo možné smazat");
                        }
                    }
                    else{
                        System.out.println("Ticket neexistuje");
                    }
                }
                else {
                    boollogged = false;
                    try {
                        stf.Save(userList);
                    }
                    catch(Exception e){
                        System.out.println("Špatný soubor");
                    }
                    continue;
                }
            }

        }
    }
}
