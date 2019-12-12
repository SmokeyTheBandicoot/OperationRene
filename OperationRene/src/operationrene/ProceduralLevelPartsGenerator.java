package operationrene;

import operationrene.mapframework.matrixprops.Size;
import operationrene.mapframework.matrixprops.Location;
import java.util.HashMap;
import java.util.Random;
import operationrene.alarm.MapAlarm;
import operationrene.alarm.MapAlarm.Dimension;
import operationrene.alarm.MapAlarmFactory;
import operationrene.mapframework.*;
import operationrene.mapframework.levelbuilder.LevelBuilder;
import operationrene.mapframework.pointsofinterest.PointOfInterest;
import operationrene.mapframework.pointsofinterest.*;
import operationrene.mapframework.pointsofinterest.Room.Direction;
import operationrene.utils.MatrixUtils;
import static operationrene.utils.MatrixUtils.*;
import operationrene.utils.SizeUtils;



public class ProceduralLevelPartsGenerator {

    // MAX MATRIX SIZE:
    // 48 x 26 TILES
    public static final int MAX_MATRIX_HEIGHT = 26;
    public static final int MAX_MATRIX_WIDTH = 48;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        
        LevelMap lm;
          
        lm = generateSafeRoom1();
        LevelBuilder.saveLevel(lm, "assets/levels/proceduralgeneration/safe1.dat");
        
        lm = generateSafeRoom2();
        LevelBuilder.saveLevel(lm, "assets/levels/proceduralgeneration/safe2.dat");
        
        lm = generateSafeRoom3();
        LevelBuilder.saveLevel(lm, "assets/levels/proceduralgeneration/safe3.dat");
        
        lm = generateVerticalCorridor(8, 4, MAX_MATRIX_WIDTH / 2 - 5);
        LevelBuilder.saveLevel(lm, "assets/levels/proceduralgeneration/corridor1.dat");
        
        lm = generateVerticalCorridor(8, 6, MAX_MATRIX_WIDTH / 2 - 5);
        LevelBuilder.saveLevel(lm, "assets/levels/proceduralgeneration/corridor2.dat");
        
        lm = generateHorizontalCorridor(8, 4, MAX_MATRIX_HEIGHT / 2 - 5);
        LevelBuilder.saveLevel(lm, "assets/levels/proceduralgeneration/corridor3.dat");
        
        lm = generateHorizontalCorridor(8, 6, MAX_MATRIX_HEIGHT / 2 - 5);
        LevelBuilder.saveLevel(lm, "assets/levels/proceduralgeneration/corridor4.dat");
        
        final int offset = 8;
        for (int x = 0; x <= 14; x++) {
            lm = generateEasyRoom(offset + x, offset - 2 + x / 2, (new Random()).nextInt(offset - 3 + x/2) + 1, x + 3);
            String s = String.format("assets/levels/proceduralgeneration/room%dx%d_%d.dat", offset + x, offset - 2 + x / 2, x);
            System.out.println(s);
            LevelBuilder.saveLevel(lm, s);
        }
        
    }
    
    
    // Utils
    private static Integer[][] generateMatrix(int height, int width) {
        Integer [][] matrix = new Integer[height][width];
        
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                if (i == 0 || j == 0 || i == height - 1 || j == width - 1) 
                    matrix[i][j] = 1;
                else 
                    matrix[i][j] = 0;
        
        return matrix;
    }
    
    private static void debugPoints(HashMap<Location, PointOfInterest> map) {
        for (Location key : map.keySet())
            System.out.println(key.toString() + " --> " + map.get(key).toString());
        // System.out.println("\n");
    }
    
    private static void debugRooms(HashMap<Location, Room> map) {
        for (Location key : map.keySet())
            System.out.println(key.toString() + " --> " + map.get(key).toString());
        // System.out.println("\n");
    }
    
    
    
    // Safe rooms
    private static LevelMap generateSafeRoom1() {
        
        Integer [][] matrix = generateMatrix(7, 9);
        
        // Safe Room 1: 9x7 room, with safe, 1 minigame and locked door
        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        
        lockeds.put(new Location(3, 7), new Safe(-1, new int[]{}, new Size(1, 1)));
        lockeds.put(new Location(3, 0), new Door(1, new int []{}, new Size(1, 1), false));
        unlocks.put(new Location(2, 7), new Key(-1, new int []{}));
        others.put(new Location(1, 2), new Alarm(-1, new Size(5, 5)));
        
        // Put the door in
        matrix[3][0] = 0;
        
        LevelMap lm = new LevelMap(-1, matrix, lockeds, unlocks, others, null);
        
        System.out.println("Room: SAFE_1");
        MatrixUtils.debugMatrix(matrix);
        
        return lm;
    }
    
    private static LevelMap generateSafeRoom2() {
        
        Integer [][] matrix = generateMatrix(9, 13);
        
        // Safe Room 1
        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        
        lockeds.put(new Location(4, 11), new Safe(-1, new int[]{}, new Size(1, 1)));
        lockeds.put(new Location(4, 0), new Door(1, new int []{}, new Size(1, 1), false));
        unlocks.put(new Location(7, 11), new Key(-1, new int []{}));
        others.put(new Location(1, 2), new Alarm(-1, new Size(7, 7)));
        
        matrix[4][0] = 0;
        
        LevelMap lm = new LevelMap(-1, matrix, lockeds, unlocks, others, null);
        
        System.out.println("Room: SAFE_2");
        debugMatrix(matrix);
        debugPoints(others);
        
        return lm;
    }
    
    private static LevelMap generateSafeRoom3() {
        
        Integer [][] matrix = generateMatrix(11, 13);
        
        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        
        lockeds.put(new Location(1, 11), new Safe(-1, new int[]{}, new Size(1, 1)));
        lockeds.put(new Location(2, 0), new Door(1, new int []{}, new Size(1, 1), false));
        unlocks.put(new Location(9, 11), new Key(-1, new int []{}));
        others.put(new Location(1, 2), new Alarm(-1, new Size(9, 9)));
        
        matrix[2][0] = 0;
        
        LevelMap lm = new LevelMap(-1, matrix, lockeds, unlocks, others, null);
        
        System.out.println("Room SAFE_3");
        debugMatrix(matrix);
        debugPoints(others);
        
        return lm;
    }
    
    
    // Minigame rooms without alarm
    /** private static LevelMap generateMinigame1() {
         
        Integer [][] matrix = generateMatrix(11, 13);

        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        
        lockeds.put(new Location(2, 0), new Door(1, new int []{}, 1, 1, false));
        unlocks.put(new Location(9, 11), new Key(-1, new int []{}));
        others.put(new Location(1, 2), new Alarm(-1, 9, 9));
        
        matrix [2][0] = 0;
        
        LevelMap lm = new LevelMap(-1, matrix, lockeds, unlocks, others, null);
        
        return lm;
    } 
    
    private static LevelMap generateMinigame2() {
         
        Integer [][] matrix = generateMatrix(11, 15);

        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> others = new HashMap<>();

        lockeds.put(new Location(2, 0), new Door(1, new int []{}, 1, 1, false));
        unlocks.put(new Location(9, 11), new Key(-1, new int []{}));
        others.put(new Location(1, 2), new Alarm(-1, 9, 9));
        
        matrix [2][0] = 0;
        
        LevelMap lm = new LevelMap(-1, matrix, lockeds, unlocks, others, null);
        
        return lm;
    } **/
            
    // Minigame rooms with alarm
    private static LevelMap generateEasyRoom(int width, int height, int doorOffset, int unlockOffset) {
        
        Integer [][] matrix = generateMatrix(height, width);
        
        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();   
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        
        // Generate door randomly in the left door
        lockeds.put(new Location(doorOffset, 0), new Door(-1, new int[]{}, new Size(1, 1), false));
        unlocks.put(new Location(unlockOffset, width - 2), new Key(-1, new int[]{-1}));
        
        // In order to put a potential alarm, get the maximum useful area
        final int maxW = width - 4;
        final int maxH = height - 4;
        int curX = 0;
        
        Dimension minigameDim = MapAlarm.getMinigameDimensions().get(SizeUtils.getBiggestFittingSize(MapAlarm.getMinigameDimensions(), new Size(maxW, maxH), true));

        MapAlarmFactory factory = new MapAlarmFactory();
        factory.createRandomMapAlarm(minigameDim);
        
        others.put(new Location(1, 2), new Alarm(-1, minigameDim.getDimSize()));
        
        matrix[doorOffset][0] = 0;
        
        LevelMap lm = new LevelMap(-1, matrix, lockeds, unlocks, others, null);
        
        debugMatrix(matrix);
        debugPoints(lockeds);
        debugPoints(unlocks);
        debugPoints(others);
        
        return lm;
    }
    
    
    
    // Corridors
    /// Straight corridors
    private static LevelMap generateVerticalCorridor(int width, int roomNumber, int leftRoomsOffset) {
        
        Integer [][] matrix = generateMatrix(MAX_MATRIX_HEIGHT, MAX_MATRIX_WIDTH);
        
        // Creates a corridor in the room
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][leftRoomsOffset] = 1;
            matrix[i][leftRoomsOffset + width] = 1;
        }
        
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        HashMap<Location, Room> rooms = new HashMap<>();
        
        final int maxRoomHeight = (int) Math.floor((MAX_MATRIX_HEIGHT / (roomNumber / 2)) - 1);
        final int leftRoomsWidth = leftRoomsOffset + 1;
        final int rightRoomsOffset = leftRoomsOffset + width;
        final int rightRoomsWidth = MAX_MATRIX_WIDTH - rightRoomsOffset;
        
        
        int curYOffset = 0;
        int curID = 1;
        
        do {
            // Rooms to the left of the corridor
            rooms.put(new Location(0, curYOffset), new Room(curID++, new Size(leftRoomsWidth, maxRoomHeight), Direction.Right));
            // Right rooms
            rooms.put(new Location(rightRoomsOffset, curYOffset), new Room(curID++, new Size(rightRoomsWidth, maxRoomHeight), Direction.Left));
            curYOffset += maxRoomHeight;
        } while (curYOffset + maxRoomHeight < MAX_MATRIX_HEIGHT);
        
        
        
        others.put(new Location(leftRoomsOffset + width / 2, 1), new EntryPoint(0));
        
        
        LevelMap lm = new LevelMap(0, matrix, null, null, others, null);
        
        debugMatrix(matrix);
        debugPoints(others);
        debugRooms(rooms);
        
        return lm;
    }
    
    private static LevelMap generateHorizontalCorridor(int width, int roomNumber, int upperRoomsOffset) {
        
        Integer [][] matrix = generateMatrix(MAX_MATRIX_HEIGHT, MAX_MATRIX_WIDTH);
        
        // Creates a corridor in the room
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[upperRoomsOffset][i] = 1;
            matrix[upperRoomsOffset + width][i] = 1;
        }
        
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        HashMap<Location, Room> rooms = new HashMap<>();
        
        final int maxRoomWidth = (int) Math.floor((MAX_MATRIX_WIDTH / (roomNumber / 2)) - 1);
        final int upperRoomsHeight = upperRoomsOffset + 1;
        final int bottomRoomsOffset = upperRoomsOffset + width;
        final int bottomRoomsHeight = MAX_MATRIX_HEIGHT - bottomRoomsOffset;
        
        
        int curXOffset = 0;
        int curID = 1;
        
        do {
            // Upper rooms
            rooms.put(new Location(curXOffset, 0), new Room(curID++, new Size(maxRoomWidth, upperRoomsHeight), Direction.Down));
            // Bottom rooms
            rooms.put(new Location(curXOffset, bottomRoomsOffset), new Room(curID++, new Size(maxRoomWidth, bottomRoomsHeight), Direction.Up));
            curXOffset += maxRoomWidth;
        } while (curXOffset + maxRoomWidth < MAX_MATRIX_WIDTH);
        
        
        
        others.put(new Location(1, upperRoomsOffset + width / 2), new EntryPoint(0));
        
        
        LevelMap lm = new LevelMap(0, matrix, null, null, others, null);
        
        debugMatrix(matrix);
        debugPoints(others);
        debugRooms(rooms);
        
        return lm;
    }
    
    
}
