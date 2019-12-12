package operationrene.mapframework.levelbuilder;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import operationrene.mapframework.LevelMap;

public class LevelSerializer {

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
