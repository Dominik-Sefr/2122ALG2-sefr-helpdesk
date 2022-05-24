package utils;

import app.User;

import java.io.*;
import java.util.ArrayList;

public class SaveToFile {
    public SaveToFile(){
        File f = new File("data/users.txt");
        if(!f.exists()){
            ArrayList<User> list = new ArrayList<User>();
            list.add(new User("email@email.com", "heslo"));
            Save(list);
        }
    }
    public <User> void Save(ArrayList<User> list) {
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
    public ArrayList<User> LoadUser(){
        try{
            FileInputStream fis = new FileInputStream("data/users.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            ArrayList<User> listFromFile = (ArrayList) obj;
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
