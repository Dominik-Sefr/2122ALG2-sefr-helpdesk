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
    public SaveToFile(){
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
    public <User> void Save(List<User> list) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("data/users.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * načte uživatele ze souboru
     * @return List<User>
     */
    public List<User> LoadUser(){
        try{
            FileInputStream fis = new FileInputStream("data/users.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            List<User> listFromFile = (List) obj;
            return listFromFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
