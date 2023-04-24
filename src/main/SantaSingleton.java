package main;

import java.util.ArrayList;

public class SantaSingleton {

    private static SantaSingleton singleInstance = null;

    public ArrayList<Copil> childrenList;

    public static SantaSingleton Singleton()
    {
        // To ensure only one instance is created
        if (singleInstance == null) {
            singleInstance = new SantaSingleton();
        }
        return singleInstance;
    }

}
