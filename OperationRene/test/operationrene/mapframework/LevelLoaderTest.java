/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.mapframework;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mikig
 */
public class LevelLoaderTest {
    
    public LevelLoaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadLevel method, of class LevelLoader.
     */
    @Test
    public void testLoadLevel() {
        System.out.println("loadLevel");
        LevelMap expResult = null;
        LevelMap result = LevelLoader.loadLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveLevel method, of class LevelLoader.
     */
    @Test
    public void testSaveLevel() {
        System.out.println("saveLevel");
        LevelMap level = null;
        String path = "";
        String expResult = "";
        String result = LevelLoader.saveLevel(level, path);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
