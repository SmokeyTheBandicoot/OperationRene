package operationrene.mapframework.levelbuilder;

import java.io.*;
import operationrene.mapframework.LevelMap;

public class LevelSerializer {
    
    /**
     * Saves a level map in a specific path, in binary form
     * @param level LevelMap object to save
     * @param path Path where to save the level. Will overwrite if already exists
     */
    public static void saveLevel(LevelMap level, String path) {
        try {
            FileOutputStream fo = new FileOutputStream(path);
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(level);
            oo.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * Loads a level map from file
     * @param path Path where to load the level
     * @return a LevelMap object containing the file
     */
    public static LevelMap loadLevel(String path) {
        
        LevelMap lm = null;
        try {
            FileInputStream fi = new FileInputStream(path);
            ObjectInputStream oi = new ObjectInputStream(fi);
            
            lm = (LevelMap) oi.readObject();
            
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } 
        
        return lm;
    }

    
    

}
