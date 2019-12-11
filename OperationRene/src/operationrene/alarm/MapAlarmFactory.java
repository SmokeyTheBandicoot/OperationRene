package operationrene.alarm;

import java.util.Random;
import operationrene.alarm.MapAlarm.Dimension;

public class MapAlarmFactory {
    
    // This parameter is used to know how many derived classes are present.
    // To dynamically get this value, Reflection would be needed.
    private final static int MINIGAMES_NUMBER = 3;
    
    public MapAlarmFactory() {
        
    }
    
    public MapAlarm createRandomMapAlarm(Dimension dim) {
        switch (new Random().nextInt(MINIGAMES_NUMBER)) {
            case 0:
                PressureTilesAlarm p = new PressureTilesAlarm(dim);
                p.randomize();
                return p;
            case 1:
                FixedLasersAlarm l = new FixedLasersAlarm(dim);
                l.randomize();
                return l;
            default:
                PulsatingLasersAlarm pl = new PulsatingLasersAlarm(dim);
                pl.randomize();
                return pl;
        }
    }
    
}
