package operationrene.maingame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import operationrene.OperationRene;
import operationrene.ProceduralLevelPartsGenerator;
import operationrene.StateID;
import operationrene.alarm.PulsatingLasersAlarm;
import operationrene.element.DoorElement;
import operationrene.element.Element;
import operationrene.element.EscapePointElement;
import operationrene.element.MinigameElement;
import operationrene.element.SafeElement;
import operationrene.mapframework.LevelMap;
import operationrene.mapframework.levelbuilder.LevelBuilder;
import operationrene.mapframework.matrixprops.Location;
import operationrene.mapframework.matrixprops.Rotation;
import operationrene.mapframework.matrixprops.Size;
import operationrene.mapframework.pointsofinterest.AlarmIdentifier;
import operationrene.mapframework.pointsofinterest.AlarmZone;
import operationrene.mapframework.pointsofinterest.Door;
import operationrene.mapframework.pointsofinterest.EscapePoint;
import operationrene.mapframework.pointsofinterest.Key;
import operationrene.mapframework.pointsofinterest.Room;
import operationrene.mapframework.pointsofinterest.Safe;
import operationrene.utils.GameTimer;
import operationrene.utils.MatrixUtils;
import operationrene.utils.RoomUtils;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

public class GameMap {

    private TiledMap map;
    private int width;
    private int height;
    private int posX;
    private int posY;
    private ArrayList<Element> elements;
    private ArrayList<Rectangle> alarms;
    private HashMap<Rectangle, int[]> pulsatingAlarms;

    private Location playerStartPosition;
    Integer[][] matrix;

    public GameMap(int type) throws SlickException {

        this.elements = new ArrayList<Element>();
        this.alarms = null;

        switch (type) {

            case MapID.LEVEL_1:
                this.map = new TiledMap("assets/tilesets/level1/Livello1.tmx");
                this.width = OperationRene.WIDTH;
                this.height = OperationRene.HEIGHT;
                this.posX = 0;
                this.posY = 0;
                this.playerStartPosition = new Location(29, 7);
                this.elements.add(new DoorElement(new Door(0, this.getFilledArray(new int[]{1}), new Size(1, 2), true), 1, 24, 13));
                this.elements.add(new EscapePointElement(new EscapePoint(0, this.getFilledArray(new int[]{2})), 2, 31, 7));
                this.elements.add(new MinigameElement(new Key(0, StateID.WIRES_ID, this.getFilledArray(new int[]{1})), 3, 15, 9));
                this.elements.add(new SafeElement(new Safe(0, this.getFilledArray(new int[]{2}), new Size(2, 2)), 4, 29, 19));
                break;

            case MapID.LEVEL_2:
                this.map = new TiledMap("assets/tilesets/level2/Livello2.tmx");
                this.width = OperationRene.WIDTH;
                this.height = OperationRene.HEIGHT;
                this.posX = 0;
                this.posY = 0;
                this.playerStartPosition = new Location(10, 12);
                this.elements.add(new DoorElement(new Door(0, null, new Size(2, 1), true), 0, 16, 10));
                this.elements.add(new DoorElement(new Door(0, null, new Size(2, 1), true), 1, 23, 15));
                this.elements.add(new DoorElement(new Door(0, this.getFilledArray(new int[]{0, 1}), new Size(1, 2), true), 2, 29, 12));
                this.elements.add(new EscapePointElement(new EscapePoint(0, this.getFilledArray(new int[]{2})), 3, 8, 12));
                this.elements.add(new MinigameElement(new Key(0, StateID.SIMONSAYS_ID, this.getFilledArray(new int[]{0})), 4, 11, 5));
                this.elements.add(new MinigameElement(new Key(0, StateID.KEYPAD_ID, this.getFilledArray(new int[]{1})), 5, 27, 17));
                this.elements.add(new SafeElement(new Safe(0, this.getFilledArray(new int[]{2}), new Size(2, 2)), 6, 34, 4));
                break;

            case MapID.LEVEL_3:
                this.map = new TiledMap("assets/tilesets/level3/Livello3.tmx");
                this.width = OperationRene.WIDTH;
                this.height = OperationRene.HEIGHT;
                this.posX = 0;
                this.posY = 0;
                this.playerStartPosition = new Location(7, 16);
                this.elements.add(new DoorElement(new Door(0, null, new Size(2, 1), true), 1, 8, 14));
                this.elements.add(new DoorElement(new Door(0, null, new Size(2, 1), true), 2, 8, 19));
                this.elements.add(new DoorElement(new Door(0, this.getFilledArray(new int[]{1, 2}), new Size(1, 2), true), 3, 12, 16));
                this.elements.add(new DoorElement(new Door(0, null, new Size(2, 1), true), 4, 20, 19));
                this.elements.add(new DoorElement(new Door(0, null, new Size(1, 2), true), 5, 22, 15));
                this.elements.add(new DoorElement(new Door(0, this.getFilledArray(new int[]{4, 5}), new Size(2, 1), true), 6, 17, 13));
                this.elements.add(new EscapePointElement(new EscapePoint(0, this.getFilledArray(new int[]{6})), 7, 5, 16));

                this.elements.add(new MinigameElement(new Key(0, StateID.WIRES_ID, this.getFilledArray(new int[]{1})), 8, 14, 3));
                this.elements.add(new MinigameElement(new Key(0, StateID.WORDS_ID, this.getFilledArray(new int[]{2})), 9, 18, 21));
                this.elements.add(new MinigameElement(new Key(0, StateID.MEMORY_ID, this.getFilledArray(new int[]{4})), 10, 32, 22));
                this.elements.add(new MinigameElement(new Key(0, StateID.KEYPAD_ID, this.getFilledArray(new int[]{5})), 11, 41, 21));
                this.elements.add(new SafeElement(new Safe(0, this.getFilledArray(new int[]{6}), new Size(2, 2)), 12, 40, 7));

                this.alarms = new ArrayList<Rectangle>();

                //colonna 1
                this.alarms.add(new Rectangle((25 * 32), (4 * 32), 32, 32));
                this.alarms.add(new Rectangle((25 * 32), (5 * 32), 32, 32));
                this.alarms.add(new Rectangle((25 * 32), (6 * 32), 32, 32));
                this.alarms.add(new Rectangle((25 * 32), (7 * 32), 32, 32));
                this.alarms.add(new Rectangle((25 * 32), (8 * 32), 32, 32));
                this.alarms.add(new Rectangle((25 * 32), (9 * 32), 32, 32));
                this.alarms.add(new Rectangle((25 * 32), (12 * 32), 32, 32));

                //colonna 2
                this.alarms.add(new Rectangle((26 * 32), (9 * 32), 32, 32));
                this.alarms.add(new Rectangle((26 * 32), (12 * 32), 32, 32));

                //colonna 3
                this.alarms.add(new Rectangle((27 * 32), (9 * 32), 32, 32));
                this.alarms.add(new Rectangle((27 * 32), (12 * 32), 32, 32));

                //colonna 4
                this.alarms.add(new Rectangle((28 * 32), (6 * 32), 32, 32));
                this.alarms.add(new Rectangle((28 * 32), (9 * 32), 32, 32));
                this.alarms.add(new Rectangle((28 * 32), (12 * 32), 32, 32));

                //colonna 5
                this.alarms.add(new Rectangle((29 * 32), (6 * 32), 32, 32));
                this.alarms.add(new Rectangle((29 * 32), (9 * 32), 32, 32));
                this.alarms.add(new Rectangle((29 * 32), (12 * 32), 32, 32));

                //colonna 6
                this.alarms.add(new Rectangle((30 * 32), (6 * 32), 32, 32));
                this.alarms.add(new Rectangle((30 * 32), (9 * 32), 32, 32));
                this.alarms.add(new Rectangle((30 * 32), (12 * 32), 32, 32));

                //colonna 7
                this.alarms.add(new Rectangle((31 * 32), (6 * 32), 32, 32));
                this.alarms.add(new Rectangle((31 * 32), (9 * 32), 32, 32));
                this.alarms.add(new Rectangle((31 * 32), (12 * 32), 32, 32));

                //colonna 8
                this.alarms.add(new Rectangle((32 * 32), (6 * 32), 32, 32));
                this.alarms.add(new Rectangle((32 * 32), (12 * 32), 32, 32));

                //colonna 9
                this.alarms.add(new Rectangle((33 * 32), (6 * 32), 32, 32));
                this.alarms.add(new Rectangle((33 * 32), (12 * 32), 32, 32));

                //colonna 10
                this.alarms.add(new Rectangle((34 * 32), (6 * 32), 32, 32));
                this.alarms.add(new Rectangle((34 * 32), (7 * 32), 32, 32));
                this.alarms.add(new Rectangle((34 * 32), (8 * 32), 32, 32));
                this.alarms.add(new Rectangle((34 * 32), (9 * 32), 32, 32));
                this.alarms.add(new Rectangle((34 * 32), (10 * 32), 32, 32));
                this.alarms.add(new Rectangle((34 * 32), (11 * 32), 32, 32));
                this.alarms.add(new Rectangle((34 * 32), (12 * 32), 32, 32));

                //colonna 11
                this.alarms.add(new Rectangle((35 * 32), (6 * 32), 32, 32));
                this.alarms.add(new Rectangle((35 * 32), (7 * 32), 32, 32));
                this.alarms.add(new Rectangle((35 * 32), (8 * 32), 32, 32));
                this.alarms.add(new Rectangle((35 * 32), (9 * 32), 32, 32));
                this.alarms.add(new Rectangle((35 * 32), (10 * 32), 32, 32));
                this.alarms.add(new Rectangle((35 * 32), (11 * 32), 32, 32));
                this.alarms.add(new Rectangle((35 * 32), (12 * 32), 32, 32));

                break;

            case MapID.LEVEL_RANDOM:

                this.alarms = new ArrayList<>();
                this.pulsatingAlarms = new HashMap<>();
                LevelBuilder lb = new LevelBuilder();
                lb.buildLevel();
                LevelMap lm = lb.getBuildingLevel();
                matrix = lm.getMatrix();

                this.map = new TiledMap("assets/tilesets/levelprocedural/level.tmx");
                this.drawMap(map);
                for (Location l : lm.getOtherObjects().keySet()) {

                    switch (lm.getOtherObjects().get(l).getPointType()) {
                        case EntryPoint:
                            this.playerStartPosition = new Location(l.getX(), l.getY());
                            break;
                        case EscapePoint:
                            EscapePoint e = (EscapePoint) lm.getOtherObjects().get(l);
                            this.elements.add(new EscapePointElement(new EscapePoint(e.getRoomID(), e.getRequiredKeysID()), 3, l.getX(), l.getY()));
                            break;

                        case AlarmZone:
                            AlarmZone alarmZone = (AlarmZone) lm.getOtherObjects().get(l);

                            int roomID = alarmZone.getRoomID();
                            Room alarmRoom = null;

                            for (Location loc : lm.getRooms().keySet()) {
                                Room r = (Room) lm.getRooms().get(loc);
                                if (r.getRoomID() == roomID) {
                                    alarmRoom = r;
                                }
                            }

                            Rotation rot = RoomUtils.calculateRotation(alarmRoom.getDir());

                            int[][] AlarmMatrix = alarmZone.getMapAlarm().getMatrix();

                            Integer[][] newMat = MatrixUtils.rotateMatrix(AlarmMatrix, rot);

                            if (alarmZone.getAlarmType() == AlarmIdentifier.AlarmType.PULSATING_LASERS) {
                                for (int i = 0; i < newMat.length; i++) {
                                    for (int j = 0; j < newMat[0].length; j++) {
                                        if (newMat[i][j] != 0 && newMat[i][j] != 1) {
                                            this.pulsatingAlarms.put(new Rectangle((l.getX() + j) * 32, (l.getY() + i) * 32, 32, 32), ((PulsatingLasersAlarm) alarmZone.getMapAlarm()).getOffSecs());
                                        }
                                    }
                                }

                            } else {

                                for (int i = 0; i < newMat.length; i++) {
                                    for (int j = 0; j < newMat[0].length; j++) {
                                        if (newMat[i][j] != 0 && newMat[i][j] != 1) {
                                            this.alarms.add(new Rectangle((l.getX() + j) * 32, (l.getY() + i) * 32, 32, 32));
                                        }
                                    }
                                }

                            }

                            break;
                    };

                }
                for (Location l : lm.getLockedObjects().keySet()) {

                    switch (lm.getLockedObjects().get(l).getPointType()) {
                        case Door:
                            Door d = (Door) lm.getLockedObjects().get(l);
                            this.elements.add(new DoorElement(new Door(d.getRoomID(), d.getRequiredKeysID(), d.getSize(), true), 1, l.getX(), l.getY()));
                            break;
                        case Safe:
                            Safe s = (Safe) lm.getLockedObjects().get(l);
                            this.elements.add(new SafeElement(new Safe(s.getRoomID(), this.getFilledArray(new int[]{-7}), s.getSize()), 2, l.getX(), l.getY()));

                            map.setTileId(l.getX(), l.getY(), 2, 41);
                            map.setTileId(l.getX() + 1, l.getY(), 2, 42);
                            map.setTileId(l.getX(), l.getY() + 1, 2, 51);
                            map.setTileId(l.getX() + 1, l.getY() + 1, 2, 52);
                            break;

                    };

                }
                int x = 100;
                for (Location l : lm.getUnlockingObjects().keySet()) {

                    switch (lm.getUnlockingObjects().get(l).getPointType()) {
                        case Key:

                            Key k = (Key) lm.getUnlockingObjects().get(l);

                            this.elements.add(new MinigameElement(new Key(4, k.getGameType(), k.getRequiredKeysID()), x, l.getX(), l.getY()));
                            map.setTileId(l.getX(), l.getY(), 2, 40);
                            break;
                    };
                    x++;
                }

                this.width = OperationRene.WIDTH;
                this.height = OperationRene.HEIGHT;

                break;
        }

    }

    public void draw() {

        this.map.render(posX, posY);

    }

    public boolean checkAlarmCollision(Shape playerShape) {
        if (this.alarms == null) {
            return false;
        }
        for (Rectangle e : this.alarms) {
            if (e.intersects(playerShape)) {
                return true;
            }
        }

        for (Rectangle r : pulsatingAlarms.keySet()) {

            if (r.intersects(playerShape) && !arrayContains(pulsatingAlarms.get(r), GameTimer.getIstance().getTime() % 10)) {

                System.out.println(Arrays.toString(pulsatingAlarms.get(r)));
                return true;
            }

        }

        return false;
    }

    public boolean checkCollision(int posX, int posY, int width, int height) {

        int wallLayer = map.getLayerIndex("walls");
        int objectLayer = map.getLayerIndex("objects");

        if ((map.getTileId(posX / 32, posY / 32, objectLayer) != 0) || (map.getTileId(posX / 32, posY / 32, wallLayer) != 0)) {//ANGOLO ALTO-SINISTRA
            return true;
        } else if ((map.getTileId((posX + width) / 32, (posY + height) / 32, objectLayer) != 0) || (map.getTileId((posX + width) / 32, (posY + height) / 32, wallLayer) != 0)) {//ANGOLO BASSO-DESTRA
            return true;
        } else if ((map.getTileId((posX + width) / 32, posY / 32, objectLayer) != 0) || (map.getTileId((posX + width) / 32, posY / 32, wallLayer) != 0)) {//ANGOLO ALTO-DESTRA
            return true;
        } else if ((map.getTileId(posX / 32, (posY + height) / 32, objectLayer) != 0) || (map.getTileId(posX / 32, (posY + height) / 32, wallLayer) != 0)) {//ANGOLO BASSO-SINISTRA
            return true;
        } else if ((map.getTileId(posX / 32, (posY + (height / 2)) / 32, objectLayer) != 0) || (map.getTileId(posX / 32, (posY + (height / 2)) / 32, wallLayer) != 0)) {//CENTRO SINISTRA
            return true;
        } else if ((map.getTileId((posX + width) / 32, (posY + (height / 2)) / 32, objectLayer) != 0) | (map.getTileId((posX + width) / 32, (posY + (height / 2)) / 32, wallLayer) != 0)) {//CENTRO DESTRA
            return true;
        } else {
            return false;
        }

    }

    public static boolean arrayContains(int[] array, int x) {

        for (int i : array) {
            if (i == x) {
                return true;
            }

        }
        return false;
    }

    public ArrayList<Element> getElements() {

        return elements;

    }

    public ArrayList<Rectangle> getAlarms() {

        return this.alarms;

    }

    public HashMap<Rectangle, int[]> getPulsatingAlarms() {
        return pulsatingAlarms;
    }

    public void setTileId(int x, int y, int layer, int id) {

        map.setTileId(x, y, layer, id);

    }

    public void deleteTile(int x, int y, String layerName) {

        this.map.setTileId(x, y, this.map.getLayerIndex(layerName), 0);

    }

    public void unlockroom(int x, int y, int xdim, int ydim) {
        for (int i = x; i < x + xdim; i++) {
            for (int j = y; j < y + ydim; j++) {
                for (int z = 0; z <= 2; z++) {
                    map.setTileId(i, j, 3, map.getTileId(i, j, z));
                }
            }
        }
    }

    public void drawblackroom(int x, int y, int xdim, int ydim) {
        for (int i = x; i < x + xdim; i++) {
            for (int j = y; j < y + ydim; j++) {
                map.setTileId(i, j, 3, 69);
            }
        }
    }

    private ArrayList<Integer> getFilledArray(int[] data) {

        ArrayList<Integer> array = new ArrayList<Integer>();

        for (int d : data) {

            array.add(d);

        }

        return array;

    }

    public Location getPlayerStartPosition() {

        return this.playerStartPosition;

    }

    public void drawMap(TiledMap map) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                switch (matrix[i][j]) {
                    case 1:
                        map.setTileId(j, i, map.getLayerIndex("walls"), matrix[i][j] + 4);
                        break;
                    case 2:
                        map.setTileId(j, i, map.getLayerIndex("walls"), matrix[i][j] + 4);
                        map.setTileId(j, i, map.getLayerIndex("floor"), 4);
                        break;
                    default:
                        map.setTileId(j, i, map.getLayerIndex("floor"), matrix[i][j] + 4);
                        break;

                }
            }
        }
    }

}
