/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.utils;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Giuse
 */
public class RandomUtilsTest {
    
    public RandomUtilsTest() {
    }

    /**
     * Test of getRandomIntBetween method, of class RandomUtils.
     */
    @Test
    public void testGetRandomIntBetween() {
        System.out.println("getRandomIntBetween");
        int min = 1;
        int max = 6;
        int result = RandomUtils.genRandomInt(min, max);
        assertTrue(result >= min && result <= max);
    }
    
}
