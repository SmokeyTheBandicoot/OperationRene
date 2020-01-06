package operationrene.datastructures;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProgressTreeTest {

    public ProgressTreeTest() {
    }

    /**
     * Test of getRoot method, of class ProgressTree.
     *
     * @Test public void testGetRoot() { System.out.println("getRoot");
     * ProgressTree instance = null; ProgressTree.Node expResult = null;
     * ProgressTree.Node result = instance.getRoot(); assertEquals(expResult,
     * result); // TODO review the generated test code and remove the default
     * call to fail. fail("The test case is a prototype."); }
     *
     * /**
     * Test of insert method, of class ProgressTree.
     *
     * @Test public void testInsert() { System.out.println("insert"); Object
     * nodeKey = null; Object parentKey = null; ProgressTree instance = null;
     * boolean expResult = false; boolean result = instance.insert(nodeKey,
     * parentKey); assertEquals(expResult, result); // TODO review the generated
     * test code and remove the default call to fail. fail("The test case is a
     * prototype."); }
     *
     * /**
     * Test of contains method, of class ProgressTree.
     *
     * @Test public void testContains() { System.out.println("contains");
     * ProgressTree instance = null; boolean expResult = false; boolean result =
     * instance.contains(null); assertEquals(expResult, result); // TODO review
     * the generated test code and remove the default call to fail. fail("The
     * test case is a prototype."); }
     *
     * /**
     * Test of getNode method, of class ProgressTree.
     *
     * @Test public void testGetNode() { System.out.println("getNode");
     * ProgressTree instance = null; ProgressTree.Node expResult = null;
     * ProgressTree.Node result = instance.getNode(null);
     * assertEquals(expResult, result); // TODO review the generated test code
     * and remove the default call to fail. fail("The test case is a
     * prototype."); }
     *
     * /**
     * Test of height method, of class ProgressTree.
     *
     * @Test public void testHeight() { System.out.println("height"); Object key
     * = null; ProgressTree instance = null; int expResult = 0; int result =
     * instance.height(key); assertEquals(expResult, result); // TODO review the
     * generated test code and remove the default call to fail. fail("The test
     * case is a prototype."); }
     *
     * /**
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
