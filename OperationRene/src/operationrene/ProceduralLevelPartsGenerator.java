package operationrene;

import java.util.ArrayList;
import operationrene.mapframework.matrixprops.Size;
import operationrene.mapframework.matrixprops.Location;
import java.util.HashMap;
import java.util.Random;
import operationrene.alarm.MapAlarm;
import operationrene.alarm.MapAlarm.Dimension;
import operationrene.alarm.MapAlarmFactory;
import operationrene.mapframework.*;
import operationrene.mapframework.levelbuilder.LevelSerializer;
import operationrene.mapframework.matrixprops.*;
import operationrene.mapframework.pointsofinterest.PointOfInterest;
import operationrene.mapframework.pointsofinterest.*;
import static operationrene.utils.MatrixUtils.*;
import operationrene.utils.RandomUtils;
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
        
        lm = generateVerticalCorridor(8, 4, MAX_MATRIX_WIDTH / 2 - 5);
        LevelSerializer.saveLevel(lm, "assets/levels/proceduralgeneration/corridors/corridor1.dat");
        
        lm = generateVerticalCorridor(8, 6, MAX_MATRIX_WIDTH / 2 - 5);
        LevelSerializer.saveLevel(lm, "assets/levels/proceduralgeneration/corridors/corridor2.dat");
        
        /*lm = generateHorizontalCorridor(8, 4, MAX_MATRIX_HEIGHT / 2 - 5);
        LevelSerializer.saveLevel(lm, "assets/levels/proceduralgeneration/corridors/corridor3.dat");
        
        lm = generateHorizontalCorridor(8, 6, MAX_MATRIX_HEIGHT / 2 - 5);
        LevelSerializer.saveLevel(lm, "assets/levels/proceduralgeneration/corridors/corridor4.dat");
        */
        
        lm = generateSafeRoom1();
        LevelSerializer.saveLevel(lm, "assets/levels/proceduralgeneration/safes/safe1.dat");
        
        lm = generateSafeRoom2();
        LevelSerializer.saveLevel(lm, "assets/levels/proceduralgeneration/safes/safe2.dat");
        
        lm = generateSafeRoom3();
        LevelSerializer.saveLevel(lm, "assets/levels/proceduralgeneration/safes/safe3.dat");
        
        lm = generateSafeRoom4();
        LevelSerializer.saveLevel(lm, "assets/levels/proceduralgeneration/safes/safe4.dat");
        
        final int offset = 8;
        for (int x = 0; x <= 14; x++) {
            lm = generateEasyRoom(offset + x, offset - 2 + x / 2, (new Random()).nextInt(offset - 5 + x/2) + 1, x + 3);
            String s = String.format("assets/levels/proceduralgeneration/rooms/room%dx%d_%d.dat", offset + x, offset - 2 + x / 2, x);
            System.out.println(s);
            LevelSerializer.saveLevel(lm, s);
        }
        
    }
    
    
    /**
     * Generates a matrix with all zeroes, except the borders, which are all ones
     * @param height height of the matrix
     * @param width width of the matrix
     * @return 
     */
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
    
    public static void debugPoints(HashMap<Location, PointOfInterest> map) {
        for (Location key : map.keySet())
            System.out.println(key.toString() + " --> " + map.get(key).toString());
        // System.out.println("\n");
    }
    
    public static void debugRooms(HashMap<Location, Room> map) {
        for (Location key : map.keySet())
            System.out.println(key.toString() + " --> " + map.get(key).toString());
        // System.out.println("\n");
    }
    
    
    
    // Safe rooms
    private static LevelMap generateSafeRoom1() {
        
        Integer [][] matrix = generateMatrix(7, 11);
        
        // Safe Room 1: 11x7 (internal 9x5) room, with safe, 1 alarm and locked door
        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        
        lockeds.put(new Location(8, 2), new Safe(1, null, new Size(2, 2)));
        lockeds.put(new Location(0, 3), new Door(1, null, new Size(1, 2), false));
        unlocks.put(new Location(9, 5), new Key(1, -1, null));
        others.put(new Location(3, 1), new Alarm(1, new Size(5, 5)));
        others.put(new Location(2, 1), new MinigameIdentifier(1, MinigameIdentifier.AlarmType.UNSELECTED));
        
        // Put the door in
        matrix[3][0] = 2;
        matrix[4][0] = 2;
        
        LevelMap lm = new LevelMap(-1, matrix, lockeds, unlocks, others, null);
        
        System.out.println("Room: SAFE_1");
        debugMatrix(matrix);
        debugPoints(lockeds);
        debugPoints(unlocks);
        debugPoints(others);
        
        return lm;
    }
    
    private static LevelMap generateSafeRoom2() {
        
        Integer [][] matrix = generateMatrix(9, 13);
        
        // Safe Room 1
        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        
        lockeds.put(new Location(11, 1), new Safe(1, null, new Size(1, 1)));
        lockeds.put(new Location(0, 4), new Door(1, null, new Size(1, 2), false));
        unlocks.put(new Location(11, 7), new Key(1, -1, null));
        others.put(new Location(3, 1), new Alarm(1, new Size(7, 7)));    
        others.put(new Location(2, 1), new MinigameIdentifier(1, MinigameIdentifier.AlarmType.UNSELECTED));
        
        matrix[4][0] = 2;
        matrix[5][0] = 2;
        
        LevelMap lm = new LevelMap(-1, matrix, lockeds, unlocks, others, null);
        
        System.out.println("Room: SAFE_2");
        debugMatrix(matrix);
        debugPoints(lockeds);
        debugPoints(unlocks);
        debugPoints(others);
        
        return lm;
    }
    
    private static LevelMap generateSafeRoom3() {
        
        Integer [][] matrix = generateMatrix(11, 15);
        
        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        
        lockeds.put(new Location(13, 1), new Safe(1, null, new Size(1, 1)));
        lockeds.put(new Location(0, 4), new Door(1, null, new Size(1, 2), false));
        unlocks.put(new Location(13, 9), new Key(1, -1, null));
        others.put(new Location(3, 1), new Alarm(1, new Size(9, 9)));    
        others.put(new Location(2, 1), new MinigameIdentifier(1, MinigameIdentifier.AlarmType.UNSELECTED));
        
        matrix[2][0] = 2;
        matrix[3][0] = 2;
        
        LevelMap lm = new LevelMap(-1, matrix, lockeds, unlocks, others, null);
        
        System.out.println("Room SAFE_3");
        debugMatrix(matrix);
        debugPoints(lockeds);
        debugPoints(unlocks);
        debugPoints(others);
        
        return lm;
    }
    
    private static LevelMap generateSafeRoom4() {
        
        Integer [][] matrix = generateMatrix(7, 9);
        
        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        
        lockeds.put(new Location(7, 1), new Safe(1, null, new Size(1, 1)));
        lockeds.put(new Location(0, 3), new Door(1, null, new Size(1, 2), false));
        unlocks.put(new Location(7, 5), new Key(1, -1, null));
        
        // Put the door in
        matrix[3][0] = 2;
        matrix[4][0] = 2;
        
        LevelMap lm = new LevelMap(-1, matrix, lockeds, unlocks, others, null);
        
        System.out.println("Room: SAFE_4");
        debugMatrix(matrix);
        debugPoints(lockeds);
        debugPoints(unlocks);
        debugPoints(others);
        
        return lm;
    }
    
            
    // Minigame rooms with alarm
    /* private static LevelMap generateEasyRoom(int width, int height, int doorOffset, int unlockOffset) {
        
        Integer [][] matrix = generateMatrix(height, width);
        
        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();   
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        
        // Generate door randomly in the left door
        // lockeds.put(new Location(13, 1), new Safe(1, null, new Size(1, 1)));
        lockeds.put(new Location(0, 2), new Door(1, null, new Size(1, 2), false));
        unlocks.put(new Location(13, 9), new Key(1, -1, null));
        others.put(new Location(3, 1), new Alarm(1, new Size(9, 9)));
        
        // In order to put a potential alarm, get the maximum useful area
        final int maxW = width - 4;
        final int maxH = height - 4;
        int curX = 0;
        
        int index = SizeUtils.getBiggestFittingSize(MapAlarm.getMinigameDimensions(), new Size(maxW, maxH), true);
        Dimension minigameDim = null;
        if (index != -1)
            minigameDim = MapAlarm.getMinigameDimensions().get(index);

        if (minigameDim != null) {
            MapAlarmFactory factory = new MapAlarmFactory();
            factory.createRandomMapAlarm(minigameDim);
            others.put(new Location(1, 2), new Alarm(-1, minigameDim.getDimSize()));
        }
        
        matrix[doorOffset][0] = 2;
        matrix[doorOffset + 1][0] = 2;
        
        LevelMap lm = new LevelMap(-1, matrix, lockeds, unlocks, others, null);
        
        debugMatrix(matrix);
        debugPoints(lockeds);
        debugPoints(unlocks);
        debugPoints(others);
        
        return lm;
    }*/
    
    // Minigame rooms with alarm
    private static LevelMap generateEasyRoom(int width, int height, int doorOffset, int unlockOffset) {
        
        Integer [][] matrix = generateMatrix(height, width);
        
        HashMap<Location, PointOfInterest> lockeds = new HashMap<>();   
        HashMap<Location, PointOfInterest> unlocks = new HashMap<>();
        HashMap<Location, PointOfInterest> others = new HashMap<>();
        
        // Generate door randomly in the left door
        // lockeds.put(new Location(doorOffset, 0), new Door(-1, new int[]{}, new Size(1, 2), false));
        lockeds.put(new Location(0, doorOffset), new Door(-1, new ArrayList<>(), new Size(1, 2), false));
        // unlocks.put(new Location(unlockOffset, width - 2), new Key(-1, new int[]{-1}));
        // unlocks.put(new Location(width - 2, unlockOffset), new Key(-1, new int[]{-1}));
        unlocks.put(new Location(width - 2, 1), new Key(-1, -1, new ArrayList<>()));
        
        // In order to put a potential alarm, get the maximum useful area
        final int maxW = width - 4;
        final int maxH = height - 4;
        int curX = 0;
        
        int index = SizeUtils.getBiggestFittingSize(MapAlarm.getMinigameDimensions(), new Size(maxW, maxH), true);
        Dimension minigameDim = null;
        if (index != -1)
            minigameDim = MapAlarm.getMinigameDimensions().get(index);

        if (minigameDim != null) {
            MapAlarmFactory factory = new MapAlarmFactory();
            factory.createRandomMapAlarm(minigameDim);
            others.put(new Location(1, 2), new Alarm(-1, minigameDim.getDimSize()));
        }
        
        matrix[doorOffset][0] = 2;
        matrix[doorOffset + 1][0] = 2;
        
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
            rooms.put(new Location(0, curYOffset), new Room(curID++, new Size(leftRoomsWidth, maxRoomHeight), Direction.RIGHT));
            System.out.println(String.format("Generated room at: %d %d of size %d %d", 0, curYOffset, leftRoomsWidth, maxRoomHeight));
// Right rooms
            rooms.put(new Location(rightRoomsOffset, curYOffset), new Room(curID++, new Size(rightRoomsWidth, maxRoomHeight), Direction.LEFT));
            System.out.println(String.format("Generated room at: %d %d of size %d %d", rightRoomsOffset, curYOffset, rightRoomsWidth, maxRoomHeight));
            curYOffset += maxRoomHeight;
        } while (curYOffset + maxRoomHeight < MAX_MATRIX_HEIGHT);
        
        
        
        others.put(new Location(leftRoomsOffset + width / 2, 2), new EntryPoint(0));
        
        // Put escape point
        if (RandomUtils.genRandomInt(0, 1) == 0){
            // Generate escape point on top
            matrix[0][leftRoomsOffset + width / 2 - 1] = 2;
            matrix[0][leftRoomsOffset + width / 2] = 2;
            others.put(new Location(leftRoomsOffset + width / 2, 1), new EscapePoint(0, new ArrayList<>()));
        } else {
            // Generate escape point on bottom
            matrix[MAX_MATRIX_HEIGHT - 1][leftRoomsOffset + width / 2 - 1] = 2;
            matrix[MAX_MATRIX_HEIGHT - 1][leftRoomsOffset + width / 2] = 2;
            others.put(new Location(leftRoomsOffset + width / 2,MAX_MATRIX_HEIGHT - 2), new EscapePoint(0, new ArrayList<>()));
        }
        
        LevelMap lm = new LevelMap(0, matrix, null, null, others, rooms);
        
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
            rooms.put(new Location(curXOffset, 0), new Room(curID++, new Size(maxRoomWidth, upperRoomsHeight), Direction.DOWN));
            System.out.println(String.format("Generated room at: %d %d of size %d %d", curXOffset, 0, maxRoomWidth, upperRoomsHeight));
            // Bottom rooms
            rooms.put(new Location(curXOffset, bottomRoomsOffset), new Room(curID++, new Size(maxRoomWidth, bottomRoomsHeight), Direction.UP));
            System.out.println(String.format("Generated room at: %d %d of size %d %d", curXOffset, bottomRoomsOffset, maxRoomWidth, bottomRoomsHeight));
            curXOffset += maxRoomWidth;
        } while (curXOffset + maxRoomWidth < MAX_MATRIX_WIDTH);
        

        others.put(new Location(2, upperRoomsOffset + width / 2), new EntryPoint(0));
        
        // Put escape point in the corridor
        if (RandomUtils.genRandomInt(0, 1) == 0) {
            // Put escape point to the left
            matrix[upperRoomsOffset + width / 2 - 1][0] = 2;
            matrix[upperRoomsOffset + width / 2][0] = 2;
            others.put(new Location(0, upperRoomsOffset + width / 2 -1), new EscapePoint(0, new ArrayList<>()));
        } else {
            // Put escape point to the right
            matrix[upperRoomsOffset + width / 2 - 1][MAX_MATRIX_WIDTH - 1] = 2;
            matrix[upperRoomsOffset + width / 2][MAX_MATRIX_WIDTH - 1] = 2;
            others.put(new Location(0, upperRoomsOffset + width / 2 -1), new EscapePoint(0, new ArrayList<>()));
        }
        
        LevelMap lm = new LevelMap(0, matrix, null, null, others, rooms);
        
        debugMatrix(matrix);
        debugPoints(others);
        debugRooms(rooms);
        
        return lm;
    }
    
    
}
