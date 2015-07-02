package database;

import java.io.*;

public class Database {

    public void saveToFile(GameInformation gameInformation, File file) {
        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(gameInformation);

            oos.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameInformation loadFromFile(File file) {
        FileInputStream fis;
        ObjectInputStream ois;
        GameInformation gameInformation = null;

        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);

            gameInformation = (GameInformation)ois.readObject();

            ois.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return gameInformation;
    }



}
