package operationrene.utils;

import java.io.File;
import java.util.*;

public class FileUtils {
    
    /**
     * Returns a list of paths in a specific directory
     * @param path
     * @return list of paths. Null if path is not a directory or there are no files
     */
    public static ArrayList<String> listFilesInDirectory(String path) {
        
        ArrayList<String> files = new ArrayList<>();
        File dir = new File(path);
        File[] dirFiles;
        
        if (dir.isDirectory())
            dirFiles = dir.listFiles();
        else return null;
   
        if (dirFiles != null) {
            for (File child : dirFiles) {
                files.add(path + "/" + child.getName());
            }
        }
        
        return files;
    }
    
}
