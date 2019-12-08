/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.mapframework.levelbuilder;

import java.util.HashMap;
import operationrene.mapframework.LevelMap;
import operationrene.mapframework.Location;
import operationrene.mapframework.pointsofinterest.PointOfInterest;
import operationrene.mapframework.pointsofinterest.PointOfInterest.PointType;
import operationrene.mapframework.pointsofinterest.*;
import operationrene.utils.ProgressTree;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProgressionRandomizerTest {
    
    Integer[][] matrix = new Integer[][]{
            {1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1}
        };
        
    HashMap<Location, PointOfInterest> lockeds = new HashMap<>();
    HashMap<Location, PointOfInterest> unlockeds = new HashMap<>();
    HashMap<Location, PointOfInterest> other = new HashMap<>();
    
    public ProgressionRandomizerTest() {
        lockeds.put(new Location(0, 0), new Safe(0, new int[]{}, 1, 1));
        lockeds.put(new Location(1, 1), new Door(0, new int[]{}, 1, 1, false));
        unlockeds.put(new Location(2, 2), new Key(0, new int[]{57}));
    }

    /**
     * Test of randomize method, of class ProgressionRandomizer.
     * @throws java.lang.Exception
     */
    @Test//(expected = Exception.class)
    public void testRandomizeWithoutSafe() throws AssertionError, Exception {
        System.out.println("randomize");
        
        LevelMap lm = new LevelMap(0, matrix, lockeds, unlockeds, other);
        ProgressionRandomizer instance = new ProgressionRandomizer(lm);
        
        instance.randomize();

        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
    
    @Test
    public void testRandomize() throws Exception {
 
        LevelMap lm = new LevelMap(0, matrix, lockeds, unlockeds, other);
        ProgressionRandomizer pr = new ProgressionRandomizer(lm);
        System.out.println("Starting randomization...");
        
        ProgressTree<Location> pt = pr.randomize();
        ProgressTree.Node<Location> root = pt.getRoot();
        
        if (lm.getLockedObjects().get(root.key).getPointType() != PointType.Safe)
            fail("Root is not a safe");
        
        if (!lm.getUnlockingObjects().containsKey(root.left.key))
            fail("Child of root is not an unlocking object");
        
        testTree(lm, pt.getRoot());
         
    }
    
    private void testTree(LevelMap lm, ProgressTree.Node<Location> current) {
        
        if (current == null) return;
        if (lm.getUnlockingObjects().containsKey(current.key) == true){
                
            if (current.left != null && lm.getUnlockingObjects().containsKey(current.left.key)) {
                testTree(lm, current.left);
                fail("An unlocking object is followed by another unlocking object (left)");
            }
                
            if (current.right != null && lm.getUnlockingObjects().containsKey(current.right.key))
                testTree(lm, current.right);
                fail("An unlocking object is followed by another unlocking object (right)");  
        }
        
        if (lm.getLockedObjects().containsKey(current.key) == true) {
            if (current.left != null && lm.getLockedObjects().containsKey(current.left.key)) {
                testTree(lm, current.left);
                fail("A locked object is followed by another locked object (left)");
            }    
            if (current.right != null && lm.getLockedObjects().containsKey(current.right.key)) {
                testTree(lm, current.right);
                fail("A locked object is followed by another locked object (right)");  
            }
        }
        
        
    }
}
