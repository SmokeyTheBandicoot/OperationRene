package operationrene;

import java.util.HashMap;
import operationrene.mapframework.*;
import operationrene.mapframework.pointsofinterest.PointOfInterest;
import operationrene.mapframework.pointsofinterest.*;

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
        LevelSerializer.saveLevel(lm, "assets/levels/proceduralgeneration/safe1.dat");
        
        lm = generateSafeRoom2();
        LevelSerializer.saveLevel(lm, "assets/levels/proceduralgeneration/safe2.dat");
        
        lm = generateSafeRoom3();
        LevelSerializer.saveLevel(lm, "assets/levels/proceduralgeneration/safe3.dat");
        
        lm = generateVerticalCorridor(8, 4, MAX_MATRIX_WIDTH / 2 - 5);
        LevelSerializer.saveLevel(lm, "assets/levels/proceduralgeneration/corridor1.dat");
        
        lm = generateVerticalCorridor(8, 6, MAX_MATRIX_WIDTH / 2 - 5);
        LevelSerializer.saveLevel(lm, "assets/levels/proceduralgeneration/corridor2.dat");
        
        lm = generateHorizontalCorridor(8, 4, MAX_MATRIX_HEIGHT / 2 - 5);
        LevelSerializer.saveLevel(lm, "assets/levels/proceduralgeneration/corridor3.dat");
        
        lm = generateHorizontalCorridor(8, 6, MAX_MATRIX_HEIGHT / 2 - 5);
        LevelSerializer.saveLevel(lm, "assets/levels/proceduralgeneration/corridor4.dat");
    }
    
    
    // Utils
    private static Integer[][] generateMatrix(int width, int height) {
        Integer [][] matrix = new Integer[width][height];
        
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                if (i == 0 || j == 0 || i == width - 1 || j == height - 1) 
                    matrix[i][j] = 1;
                else 
                    matrix[i][j] = 0;
        
        return matrix;
    }
    
    private static void debugMatrix(Integer [][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.print("\n");
        }
        System.out.println("\n\n");
    }
    
    private static void debugPoints(HashMap<Location, PointOfInterest> map) {
        for (Location key : map.keySet())
            System.out.println(key.toString() + " --> " + map.get(key).toString());
    }
    
    private static void debugRooms(HashMap<Location, Room> map) {
        for (Location key : map.keySet())
            System.out.println(key.toString() + " --> " + map.get(key).toString());
    }
    
    // Safe rooms
    private static LevelMap generateSafeRoom1() {
        
        Integer [][] matrix = generateMatrix(9, 7);
        
        // Safe Room 1: 9x7 room, with safe, 1 minigame and locked door
        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        
        lockeds.put(new Location(7, 3), new Safe(-1, new int[]{}, 1, 1));
        lockeds.put(new Location(0, 3), new Door(1, new int []{}, 1, 1, false));
        unlocks.put(new Location(7, 2), new Key(-1, new int []{}));
        others.put(new Location(2, 1), new Alarm(-1, 5, 5));
        
        // Put the door in
        matrix[0][3] = 0;
        
        LevelMap lm = new LevelMap(-1, matrix, lockeds, unlocks, others, null);
        
        System.out.println("Generated room safe1");
        debugMatrix(matrix);
        
        return lm;
    }
    
    private static LevelMap generateSafeRoom2() {
        
        Integer [][] matrix = generateMatrix(13, 9);
        
        // Safe Room 1
        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        
        lockeds.put(new Location(11, 4), new Safe(-1, new int[]{}, 1, 1));
        lockeds.put(new Location(0, 4), new Door(1, new int []{}, 1, 1, false));
        unlocks.put(new Location(11, 7), new Key(-1, new int []{}));
        others.put(new Location(2, 1), new Alarm(-1, 7, 7));
        
        matrix[0][4] = 0;
        
        LevelMap lm = new LevelMap(-1, matrix, lockeds, unlocks, others, null);
        debugMatrix(matrix);
        
        return lm;
    }
    
    private static LevelMap generateSafeRoom3() {
        // Safe Room 1
        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        
        lockeds.put(new Location(11, 1), new Safe(-1, new int[]{}, 1, 1));
        lockeds.put(new Location(0, 2), new Door(1, new int []{}, 1, 1, false));
        unlocks.put(new Location(11, 9), new Key(-1, new int []{}));
        others.put(new Location(2, 1), new Alarm(-1, 9, 9));
        
        
        LevelMap lm = new LevelMap(-1, 
                new Integer[][]{
                  // 0  1  2  3  4  5  6  7  8  9 10 11 12
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // 0
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 1
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 2
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 3
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 4
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 5
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 6
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 7
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 8
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 9
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}},// 10
            // Lockeds, Unlockeds, Others, Rooms
            lockeds, unlocks, others, null);
        
        return lm;
    }
    
    
    // Minigame rooms without alarm
    private static LevelMap generateMinigame1() {
        // Minigame room 1
        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        
        lockeds.put(new Location(11, 1), new Safe(-1, new int[]{}, 1, 1));
        lockeds.put(new Location(0, 2), new Door(1, new int []{}, 1, 1, false));
        unlocks.put(new Location(11, 9), new Key(-1, new int []{}));
        others.put(new Location(2, 1), new Alarm(-1, 9, 9));
        
        
        LevelMap lm = new LevelMap(-1, 
                new Integer[][]{
                  // 0  1  2  3  4  5  6  7  8  9 10 11 12
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // 0
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 1
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 2
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 3
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 4
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 5
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 6
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 7
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 8
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 9
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}},// 10
            // Lockeds, Unlockeds, Others, Rooms
            lockeds, unlocks, others, null);
        
        return lm;
    } 
            
    // Minigame rooms with alarm
    
    
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
            rooms.put(new Location(0, curYOffset), new Room(curID++, leftRoomsWidth, maxRoomHeight));
            rooms.put(new Location(rightRoomsOffset, curYOffset), new Room(curID++, rightRoomsWidth, maxRoomHeight));
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
            rooms.put(new Location(curXOffset, 0), new Room(curID++, maxRoomWidth, upperRoomsHeight));
            rooms.put(new Location(curXOffset, bottomRoomsOffset), new Room(curID++, maxRoomWidth, bottomRoomsHeight));
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
