/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.utils;

import java.util.HashMap;
import java.util.Set;
import operationrene.mapframework.matrixprops.Flipping;
import operationrene.mapframework.matrixprops.Location;
import operationrene.mapframework.matrixprops.Rotation;
import operationrene.mapframework.matrixprops.Size;
import operationrene.mapframework.pointsofinterest.Alarm;
import operationrene.mapframework.pointsofinterest.PointOfInterest;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miky Gargiulo
 */
public class HashMapUtilsTest {
    
    public HashMapUtilsTest() {
    }
    
    /**
     * Test of rotate method, of class HashMapUtils.
     */
    @Test
    public void testRotateAndFlip() {
        
        System.out.println("rotate");    
        HashMap<Location, PointOfInterest> hash = new HashMap<>();
        hash.put(new Location(1, 1), new Alarm(-1, new Size(5, 5)));
        Size roomSize = new Size (9, 9);
        hash = new HashMap<>(HashMapUtils.flip(hash, Flipping.BOTH, roomSize));
        System.out.println(printHash(hash.keySet(), roomSize));
        System.out.println("end");
        
        /*
        hash.put(new Location(1, 2), new Alarm(0, new Size(5, 5)));
        hash.put(new Location(1, 1), new Alarm(0, new Size(5, 5)));
        hash.put(new Location(1, 0), new Alarm(0, new Size(5, 5)));
        
        Size s = new Size(7, 7);
        String str = "";
        
        // ORIGINAL
        System.out.println("Original");
        str = printHash(hash.keySet(), s);
        System.out.println("---" + str + "---");
        assertEquals(str, 
                "O X O O O O O \n" +
                "O X O O O O O \n" +
                "O X O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n");
        System.out.println("\n");
        
        // Rotation RIGHT
        System.out.println("Right rotation");
        str = printHash(HashMapUtils.rotate(new HashMap<>(hash), Rotation.RIGHT, s).keySet(), s);
        System.out.println(str);
        assertEquals(str, 
                "O O O O O O O \n" +
                "O O O O X X X \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n");
        System.out.println("\n");
        
        // Rotation LEFT
        System.out.println("Left rotation");
        str = printHash(HashMapUtils.rotate(new HashMap<>(hash), Rotation.LEFT, s).keySet(), s);
        System.out.println(str);
        assertEquals(str, 
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "X X X O O O O \n" +
                "O O O O O O O \n");
        System.out.println("\n");
        
        // Rotation of 180 degrees
        System.out.println("180 degrees rotation");
        str = printHash(HashMapUtils.rotate(new HashMap<>(hash), Rotation.DEG180, s).keySet(), s);
        System.out.println(str);
        assertEquals(str, 
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O X O \n" +
                "O O O O O X O \n" +
                "O O O O O X O \n");
        System.out.println("\n");
        
        // Flipping Horizontal
        System.out.println("Horizontal flipping");
        str = printHash(HashMapUtils.flip(new HashMap<>(hash), Flipping.HORIZONTAL, s).keySet(), s);
        System.out.println(str);
        assertEquals(str, 
                "O O O O O X O \n" +
                "O O O O O X O \n" +
                "O O O O O X O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n");
        System.out.println("\n");
        
        // Flipping Vertical
        System.out.println("Vertical flipping");
        str = printHash(HashMapUtils.flip(new HashMap<>(hash), Flipping.VERTICAL, s).keySet(), s);
        System.out.println(str);
        assertEquals(str, 
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O X O O O O O \n" +
                "O X O O O O O \n" +
                "O X O O O O O \n");
        System.out.println("\n");
        
        // Both flipping
        System.out.println("Both flipping");
        str = printHash(HashMapUtils.flip(new HashMap<>(hash), Flipping.BOTH, s).keySet(), s);
        System.out.println(str);
        assertEquals(str, 
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O O O \n" +
                "O O O O O X O \n" +
                "O O O O O X O \n" +
                "O O O O O X O \n");
        System.out.println("\n");
        */
    }
    
    private String printHash(Set<Location> set, Size s) {
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for (int y = 0; y < s.getHeight(); y++) {
            for (int x = 0; x < s.getWidth(); x++){
                found = false;
                for (Location l : set)
                    if (l.getX() == x && l.getY() == y) {
                        sb.append("X ");
                        found = true;
                        break;
                    }
                if (!found) sb.append("O ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    
}
