package operationrene.mapframework.levelbuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import operationrene.mapframework.LevelMap;
import operationrene.mapframework.matrixprops.Location;
import operationrene.mapframework.pointsofinterest.PointOfInterest;
import operationrene.mapframework.pointsofinterest.PointOfInterest.PointType;
import operationrene.datastructures.ProgressTree;
import operationrene.utils.RandomUtils;

public class ProgressionRandomizer {
    
    protected float firstLevelRamificationChance = 0.3f;
    protected float secondLevelRamificationChance = 0.2f;
    protected float thirdLevelRamificationChance = 0.1f;

    // Getters and Setters
    public float getFirstLevelRamificationChance() {
        return firstLevelRamificationChance;
    }

    public void setFirstLevelRamificationChance(float firstLevelRamificationChance) {
        this.firstLevelRamificationChance = firstLevelRamificationChance;
    }

    public float getSecondLevelRamificationChance() {
        return secondLevelRamificationChance;
    }

    public void setSecondLevelRamificationChance(float secondLevelRamificationChance) {
        this.secondLevelRamificationChance = secondLevelRamificationChance;
    }

    public float getThirdLevelRamificationChance() {
        return thirdLevelRamificationChance;
    }

    public void setThirdLevelRamificationChance(float thirdLevelRamificationChance) {
        this.thirdLevelRamificationChance = thirdLevelRamificationChance;
    }
    
    

    // Used to dynamically produce a tree which represents the sequence of keys-doors of the level
    private ProgressTree<Location> pt = null;

    // Level to randomize
    private LevelMap lm = null;

    // Cache Hashmaps
    private final HashMap<Location, PointOfInterest> locks;
    private final HashMap<Location, PointOfInterest> unlocks;

    public ProgressionRandomizer(LevelMap lm) {
        // Creates a SHALLOW COPY of the hashmaps of the LevelMap
        this.locks = new HashMap<>(lm.getLockedObjects());
        this.unlocks = new HashMap<>(lm.getUnlockingObjects());
        this.lm = lm;
    }

    /**
     * Function responsible for the complete randomization of the level, and
     * sets the correct parameters for all the objects in the hashmap
     *
     * @return the progression tree
     * @throws java.lang.Exception
     */
    public ProgressTree<Location> randomize() throws Exception {
        
        // Generate random IDs for the keys
        int [] randomIDs = generateRandomIDs(unlocks.size());
        int k = 0;
        
        for (Location l : unlocks.keySet()) {
            ArrayList<Integer> req = new ArrayList<>();
            req.clear();
            req.add(randomIDs[k++]);
            unlocks.get(l).setRequiredKeysID(req);
            req.clear();
        
        }
        
        /* MatrixUtils.debugMatrix(lm.getMatrix());
        System.out.println("****************************************");
        ProceduralLevelPartsGenerator.debugPoints(locks);
        ProceduralLevelPartsGenerator.debugPoints(unlocks);
        ProceduralLevelPartsGenerator.debugPoints(lm.getOtherObjects());
        ProceduralLevelPartsGenerator.debugRooms(lm.getRooms()); */

        // First it finds the "safe" (the final goal) and sets it as the root of the tree
        Location safeLocation = findSafe();

        // If the safe was not added in the locked objects:
        if (safeLocation == null) {
            throw new Exception("Cannot find the final goal. Failed to randomize progression");
        }

        // Instantiate progression tree and start working
        // Set the safe as the root
        pt = new ProgressTree<>(safeLocation);

        // Immediately add the minigame before the safe
        Location safeUnlock = findByRoomID(locks.get(safeLocation).getRoomID(), unlocks);
        boolean inserted = pt.insert(safeUnlock, safeLocation);

        // Immediately add the door that locks the minigame
        Location doorMinigame = findByRoomID(unlocks.get(safeUnlock).getRoomID(), locks);
        pt.insert(doorMinigame, safeUnlock);

        // Remove from the hashmaps the used objects
        locks.remove(safeLocation);
        unlocks.remove(safeUnlock);
        locks.remove(doorMinigame);

        // Transform the keyset in the map into an array list for accessing by random ID
        List<Location> keyArray = new ArrayList<>(unlocks.keySet());
        
        // keep reference to parent
        Location curParent = doorMinigame;

        // Now loop all the elements in pairs (lock - unlock) to add random progression
        while (!keyArray.isEmpty()) {

            // Declare and generate new random
            Random r = new Random();
            int randomIndex = RandomUtils.genRandomInt(0, keyArray.size() - 1);

            // Get the corresponding random location from the LOCKED objects
            Location randomLocation = keyArray.get(randomIndex);

            // Insert the location in the progression tree
            pt.insert(randomLocation, curParent);

            // Update current parent
            curParent = randomLocation;

            // Attach corresponding child for the roomID
            Location correspondingUnlock = findByRoomID(unlocks.get(randomLocation).getRoomID(), locks);
            // Insert the corresponding Unlocking object
            pt.insert(correspondingUnlock, curParent);
            // Update the current parent after the insertion
            curParent = correspondingUnlock;
            
            // Removes the used unlocking object from the unlocks
            unlocks.remove(randomLocation);
            // Removes the locked object added into progression
            locks.remove(correspondingUnlock);
            keyArray.remove(randomLocation);

            // 30% of probability to have a ramified progression in the current node
            // TODO: Re-implement random branching (Start by removing the "-" in front of "firstLevelRamificationChance"
            // WARNING: there are ERRORS in the following code (inside this IF statement)
            // The code has to be updated with the code outside the IF
            if (!unlocks.isEmpty() && Math.random() < -firstLevelRamificationChance) {

                // Declare and generate new random
                r = new Random();
                randomIndex = r.nextInt(keyArray.size());

                // Get the corresponding random location from the LOCKED objects
                randomLocation = keyArray.get(randomIndex);

                // Insert the location in the progression tree
                pt.insert(randomLocation, curParent);

                // Update current parent
                curParent = randomLocation;

                // Attach corresponding child for the roomID
                pt.insert(findByRoomID(unlocks.get(randomLocation).getRoomID(), locks), curParent);

                // Removes the used unlocking object from the unlocks
                unlocks.remove(randomLocation);

                // 20% of probability to have a ramified progression in the current node
                if (!unlocks.isEmpty() && Math.random() < secondLevelRamificationChance) {

                    // Declare and generate new random
                    r = new Random();
                    randomIndex = r.nextInt(keyArray.size());

                    // Get the corresponding random location from the LOCKED objects
                    randomLocation = keyArray.get(randomIndex);

                    // Insert the location in the progression tree
                    pt.insert(randomLocation, curParent);

                    // Update current parent
                    curParent = randomLocation;

                    // Attach corresponding child for the roomID
                    pt.insert(findByRoomID(unlocks.get(randomLocation).getRoomID(), locks), curParent);

                    // Removes the used unlocking object from the unlocks
                    unlocks.remove(randomLocation);

                    // 10% of probability to have a ramified progression in the current node
                    if (unlocks.isEmpty() && Math.random() < thirdLevelRamificationChance) {

                        // Declare and generate new random
                        r = new Random();
                        randomIndex = r.nextInt(keyArray.size());

                        // Get the corresponding random location from the LOCKED objects
                        randomLocation = keyArray.get(randomIndex);

                        // Insert the location in the progression tree
                        pt.insert(randomLocation, curParent);

                        // Update current parent
                        curParent = randomLocation;

                        // Attach corresponding child for the roomID
                        pt.insert(findByRoomID(unlocks.get(randomLocation).getRoomID(), locks), curParent);

                        // Removes the used unlocking object from the unlocks
                        unlocks.remove(randomLocation);
                    }
                }
            }
        }
        
        /* System.out.println(pt.toString());
        System.out.println("################################");
        pt.printTree(".  ");
        System.out.println("___");
        System.out.println(locks.keySet());
        System.out.println(unlocks.keySet()); */

        // Once progression tree is built, then proceed to update the hashmaps accordingly
        // First randomize KEY IDs
        setRandomKeysIDtoUnlockings();
        // Then, using progression tree, pair them accordingly to each corresponding door which opens to that key
        setIDs(pt.getRoot());

        return pt;
    }

    private void setIDs(ProgressTree.Node<Location> locked) {

        // Variable for caching
        HashMap<Location, PointOfInterest> unlocksCache = lm.getUnlockingObjects();

        // Get the children IDs and store them
        ArrayList<Integer> ids = new ArrayList<>();

        // System.out.println(locked.key);
        // Inspects left and right children of the locked object, 
        // which are unlocking object by construction of the ProgressTree
        if (locked.left == null && locked.right == null) {
            // Otherwise it is empty, so it is unlocked
            // System.out.println(lm.getLockedObjects().get(locked.key));
            lm.getLockedObjects().get(locked.key).setRequiredKeysID(ids);
            return;
        }

        // Unlock objects only have 1 requiredKeyID
        PointOfInterest poi = lm.getUnlockingObjects().get(locked.left.key);
        Integer i = poi.getRequiredKeysID().get(0); 
        ids.add(i);
        if (locked.right != null) // Unlock objects only have 1 requiredKeyID
        {
            Integer ii = lm.getUnlockingObjects().get(locked.right.key).getRequiredKeysID().get(0);
            ids.add(ii);
        }

        // Set the req keys ID for the locked object
        lm.getLockedObjects().get(locked.key).setRequiredKeysID(ids);

        // Then do this recursively
        setIDs(locked.left.left);

        // Since by construction left is always preferred, and there is a little chance to have right
        if (locked.right != null) {
            setIDs(locked.right.left);
        }

    }

    /**
     * By construction, there is only one "safe" object in the locked objects,
     * which is the final goal This function will find that object
     *
     * @return
     */
    private Location findSafe() {
        if (locks != null) {
            for (Location l : locks.keySet()) {
                if (locks.get(l).getPointType() == PointType.Safe) {
                    return l;
                }
            }
        }
        return null;
    }
    
    /**
     * Finds another element in the inspectedMap with room id roomID
     * @param roomID roomID of the searched element
     * @param inspectedMap map where to search for the element
     * @return reference to the found element, null otherwise
     */
    private Location findByRoomID(int roomID, HashMap<Location, PointOfInterest> inspectedMap) {
        if (inspectedMap == null) {
            return null;
        }
        for (Location l : inspectedMap.keySet()) {
            if ((inspectedMap.get(l).getRoomID() == roomID) && (inspectedMap.get(l).getPointType() != PointType.Safe)) {
                return l;
            }
        }
        return null;
    }

    /**
     * Generates a random array of IDs for the keys
     * @param num number of random elements to generate
     * @return array of num length of random integers
     */
    private static int[] generateRandomIDs(int num) {
        int[] arr = new int[num];
        int cur = 10;
        for (int x = 0; x < num; x++) {
            int ran = RandomUtils.genRandomInt(cur, cur + 4);
            arr[x] = ran;
            cur += 5;
        }
        return arr;
    }

    /**
     * Sets random key IDs to the keys in the level map (unlocking hashmap)
     */
    private void setRandomKeysIDtoUnlockings() {
        
        // At the beginning, start to assign random numbers for KeyIDs
        int [] arr = generateRandomIDs(lm.getUnlockingObjects().size());
        ArrayList<Integer> randomIDs = new ArrayList<>();
        for (int i : arr)
            randomIDs.add(i);

        int index = 0;
        for (Location loc : lm.getUnlockingObjects().keySet()) {
            ArrayList<Integer> tempArr = new ArrayList<>();
            tempArr.add(randomIDs.get(index));
            lm.getUnlockingObjects().get(loc).setRequiredKeysID(tempArr);
            index++;
        }
    }
}
