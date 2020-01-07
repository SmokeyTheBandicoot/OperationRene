package operationrene.datastructures;

import junit.framework.Assert;
import operationrene.mapframework.matrixprops.Location;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProgressTreeTest {

    public ProgressTreeTest() {
    }

    /**
     * Test of getRoot method, of class ProgressTree.
     *
     */
    @Test 
    public void testGetRoot() { System.out.println("getRoot");
        Location l = new Location(1, 1);
        ProgressTree<Location> pt = new ProgressTree<>(l);
        assertEquals(true, pt.contains(l, pt.getRoot())); 
    }
    
     @Test 
     public void testInsert() { 
         System.out.println("insert");
         Location l = new Location(1, 1);
         Location l2 = new Location(2, 2);
         ProgressTree<Location> pt = new ProgressTree<>(l);
         pt.insert(l2, l);
         assertTrue(pt.contains(l, pt.getRoot()));
         assertTrue(pt.contains(l2, pt.getRoot()));
     } 
     
     /**
     * Test of height method, of class ProgressTree.
     *
     * @Test public void testHeight() { System.out.println("height"); Object key
     * = null; ProgressTree instance = null; int expResult = 0; int result =
     * instance.height(key); assertEquals(expResult, result); // TODO review the
     * generated test code and remove the default call to fail. fail("The test
     * case is a prototype."); }
     *
     */

    /*
     * Test of printTree method, of class ProgressTree.
     */
    @Test
    public void testPrintTree() {
        System.out.println("printTree");
        ProgressTree<Integer> instance = new ProgressTree<>(12);
        instance.insert(11, instance.getRoot().key);
        instance.insert(15, 11);
        instance.insert(9, 12);
        String expResult
                = "(12)\n"
                + ".   (11)\n"
                + ".   .   (15)\n"
                + ".   .   .   ~null\n"
                + ".   .   .   ~null\n"
                + ".   .   ~null\n"
                + ".   (9)\n"
                + ".   .   ~null\n"
                + ".   .   ~null";
        String result = instance.printTree(".   ");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

}
