package operationrene.mapframework.levelbuilder;

import java.util.HashMap;

public final class TileIDMapping {
    
    private static HashMap<TileName, Integer> mapping;
    
    private TileIDMapping() {
        
        mapping = new HashMap<>();
        
        mapping.put(TileName.Floor, 0);
        mapping.put(TileName.Wall, 1);
        mapping.put(TileName.Door, 2);
        
        mapping.put(TileName.Alarm, 3);
        mapping.put(TileName.BlueTile, 4);
        mapping.put(TileName.RedTile, 5);
        
        mapping.put(TileName.RedCircle, 6);
        mapping.put(TileName.BlueCircle, 21);
        mapping.put(TileName.YellowCircle, 11);
        mapping.put(TileName.GreenCircle, 16);
        mapping.put(TileName.PurpleCircle, 26);
        mapping.put(TileName.OrangeCircle, 31);
        
        mapping.put(TileName.RedSquare, 7);
        mapping.put(TileName.BlueSquare, 22);
        mapping.put(TileName.YellowSquare, 12);
        mapping.put(TileName.GreenSquare, 17);
        mapping.put(TileName.PurpleSquare, 27);
        mapping.put(TileName.OrangeSquare, 32);
        
        mapping.put(TileName.RedHollow, 8);
        mapping.put(TileName.BlueHollow, 23);
        mapping.put(TileName.YellowHollow, 13);
        mapping.put(TileName.GreenHollow, 18);
        mapping.put(TileName.PurpleHollow, 28);
        mapping.put(TileName.OrangeHollow, 33);
        
        mapping.put(TileName.RedStar, 9);
        mapping.put(TileName.BlueStar, 24);
        mapping.put(TileName.YellowStar, 14);
        mapping.put(TileName.GreenStar, 19);
        mapping.put(TileName.PurpleStar, 29);
        mapping.put(TileName.OrangeStar, 34);
        
        mapping.put(TileName.RedCross, 10);
        mapping.put(TileName.BlueCross, 25);
        mapping.put(TileName.YellowCross, 15);
        mapping.put(TileName.GreenCross, 20);
        mapping.put(TileName.PurpleCross, 30);
        mapping.put(TileName.OrangeCross, 35);     
    }

    private static HashMap<TileName, Integer> getMappingPvt() {
        return mapping;
    }
    
    
   
    public static Integer getMapping(TileName tn) {
 
        if (mapping == null) {
            mapping = new TileIDMapping().getMappingPvt();
        }
        return mapping.get(tn);
    }
    
    public enum TileName {
        
        // General
        Floor,
        Wall,
        Door,
        
        // Alarms
        Alarm,
        BlueTile,
        RedTile,
        
        // Colored tiles
        RedCircle,
        BlueCircle,
        YellowCircle,
        GreenCircle,
        PurpleCircle,
        OrangeCircle,
        
        RedSquare,
        BlueSquare,
        YellowSquare,
        GreenSquare,
        PurpleSquare,
        OrangeSquare,
        
        RedHollow,
        BlueHollow,
        YellowHollow,
        GreenHollow,
        PurpleHollow,
        OrangeHollow,
        
        RedStar,
        BlueStar,
        YellowStar,
        GreenStar,
        PurpleStar,
        OrangeStar,
        
        RedCross,
        BlueCross,
        YellowCross,
        GreenCross,
        PurpleCross,
        OrangeCross
        
        
    }
    
}