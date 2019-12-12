/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.utils;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miky Gargiulo
 */
public class FileUtilsTest {
    
    public FileUtilsTest() {
    }

    /**
     * Test of listFilesInDirectory method, of class FileUtils.
     */
    @Test
    public void testListFilesInDirectory() {
        System.out.println("listFilesInDirectory");
        String path = "assets/levels/proceduralgeneration/corridors";
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add(path + "/corridor1.dat");
        expResult.add(path + "/corridor2.dat");
        expResult.add(path + "/corridor3.dat");
        expResult.add(path + "/corridor4.dat");
        
        ArrayList<String> result = FileUtils.listFilesInDirectory(path);

        assertEquals(expResult, result);
    }
    
}
