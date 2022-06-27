package utils;

import app.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * třída pracuje se soubory
 */
public class SaveToFile {
    /**
     * kontroluje zda je soubor prázdný
     * @return
     */
    public boolean NewFileCheck(){
        File file = new File("data/users.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            if (br.readLine() == null) {
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Konstruktor, vytváří nový soubor
     */
    public SaveToFile() throws IOException {
        File f = new File("data/users.txt");
        if(!f.exists()){
            List<User> list = new ArrayList<User>();
            list.add(new User("email@email.com", "heslo"));
            Save(list);
        }
    }

    /**
     * ukládá list do souboru
     * @param list
     * @param <User>
     */
    public <User> void Save(List<User> list) throws IOException {
        FileOutputStream fos;
            fos = new FileOutputStream("data/users.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            fos.close();
            oos.close();
    }

    /**
     * načte uživatele ze souboru
     * @return List<User>
     */
    public List<User> LoadUser() throws IOException, ClassNotFoundException {
            FileInputStream fis = new FileInputStream("data/users.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            List<User> listFromFile = (List) obj;
            fis.close();
            ois.close();
            return listFromFile;

    }

    /**
     * uloží všechny tickety uživatele do souboru
     */

    public void SaveTicketsToFile(String tickets) throws IOException {
        Writer writer = null;
        writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("data/tickety.txt"), "utf-8"));
        writer.write(tickets);
        writer.close();
    }
}
