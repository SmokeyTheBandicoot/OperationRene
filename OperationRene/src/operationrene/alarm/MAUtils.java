/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.alarm;

/**
 *
 * @author Amministratore
 */
public class MAUtils {
    
    public static int getRandomIntBetween(int min, int max) {
        int x = (int) (Math.random()*((max - min) + 1) + min);
        return x;
    }
}
