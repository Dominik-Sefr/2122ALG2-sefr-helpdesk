package ui;

import app.MailSender;

public class App {
    public static void main(String[] args) {
        MailSender emailr = new MailSender();
        emailr.Send("sefrdominik22@gmail.com", "Prototype123654789", "sefrhelpdeskjava@gmail.com", "NEco", "seznam");
    }
}
