package operationrene.utils;

import java.util.Random;

public class RandomUtils {
    
    /**
     * Generates a random int between start and end (both inclusive)
     * @param start start int
     * @param end end int
     * @return a random int between start and int (even if inverted)
     */
    public static int genRandomInt(int start, int end) {
        
        int size = end - start;
        if (size == 0) return size;
        
        if (end < start){
            return genRandomInt(end, start);
        }
        
        return new Random().nextInt(size + 1) + start;

    }
    
}
