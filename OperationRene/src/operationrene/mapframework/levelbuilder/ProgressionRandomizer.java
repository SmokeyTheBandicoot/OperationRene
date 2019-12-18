package operationrene.mapframework.levelbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import operationrene.mapframework.LevelMap;
import operationrene.mapframework.matrixprops.Location;
import operationrene.mapframework.pointsofinterest.PointOfInterest;
import operationrene.mapframework.pointsofinterest.PointOfInterest.PointType;
import operationrene.datastructures.ProgressTree;

public class ProgressionRandomizer {
    
    // Used to dynamically produce a tree which represents the sequence of keys-doors of the level
    private ProgressTree<Location> pt = null;
    
    // Level to randomize
    private LevelMap lm = null;
    
    // Cache Hashmaps
    private HashMap<Location, PointOfInterest> locks;
    private HashMap<Location, PointOfInterest> unlocks;

    public ProgressionRandomizer(LevelMap lm) {
        // Creates a SHALLOW COPY of the hashmaps of the LevelMap
        this.locks = new HashMap<>(lm.getLockedObjects());
        this.unlocks = new HashMap<>(lm.getUnlockingObjects());
        this.lm = lm;
    }
    
    /**
     * Function responsible for the complete randomization of the level,
     * and sets the correct parameters for all the objects in the hashmap
     * @return the progression tree
     * @throws java.lang.Exception
     */
    public ProgressTree<Location> randomize() throws Exception {
        
        // First it finds the "safe" (the final goal) and sets it as the root of the tree
        Location safeLocation = findSafe();
        
        // If the safe was not added in the locked objects:
        if (safeLocation == null)
            throw new Exception("Cannot find the final goal. Failed to randomize progression");
        
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
            int randomIndex = r.nextInt(keyArray.size());
            
            // Get the corresponding random location from the LOCKED objects
            Location randomLocation = keyArray.get(randomIndex);
            
            // Insert the location in the progression tree
            pt.insert(randomLocation, curParent);
            
            // Update current parent
            curParent = randomLocation;
            
            // Attach corresponding child for the roomID
            pt.insert(findByRoomID(unlocks.get(randomLocation).getRoomID(), locks), curParent);
            
            // Removes the used unlocking object from the unlocks
            unlocks.remove(randomLocation);
            
            // 30% of probability to have a ramified progression in the current node
            if (!unlocks.isEmpty() && Math.random() < 0.3) {
                
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
                if (!unlocks.isEmpty() && Math.random() < 0.2) {
                
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
                    if (unlocks.isEmpty() && Math.random() < 0.1) {
                
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
        
        // Once progression tree is built, then proceed to update the hashmaps accordingly
        setIDs(pt.getRoot());    
        
        return pt;
    }
    
    private void setIDs(ProgressTree.Node<Location> locked) {
        
        // Get the children IDs and store them
        List<Integer> ids = new ArrayList<>();
        
        // Inspects left and right children of the locked object, 
        // which are unlocking object by construction of the ProgressTree
        if (locked.left == null && locked.right == null) {
            // Otherwise it is empty, so it is unlocked
            //lm.getLockedObjects().get(locked.key).setRequiredKeysID(new int[]{});------------------------------------------------------------------------------------da int[] ad arraylist
            return;
        }
        
        // Unlock objects only have 1 requiredKeyID
        //ids.add(lm.getUnlockingObjects().get(locked.left.key).getRequiredKeysID()[0]);------------------------------------------------------------------------------------da int[] ad arraylist
        if (locked.right != null)
            // Unlock objects only have 1 requiredKeyID
            //ids.add(lm.getUnlockingObjects().get(locked.right.key).getRequiredKeysID()[0]);------------------------------------------------------------------------------------da int[] ad arraylist
            System.out.println("CANCELLA QUESTA STAMPA SERVE SOLO PER NON GENERARE ERRORE");
        // Transform ArrayList<Integer> into int[]
        int[] reqIds = new int[ids.size()];
        for (int x = 0; x < ids.size(); x++){
            reqIds[x] = ids.get(x);
        }
        
        // Set the req keys ID for the locked object
        //lm.getLockedObjects().get(locked.key).setRequiredKeysID(reqIds);------------------------------------------------------------------------------------da int[] ad arraylist
        
        // Then do this recursively
        setIDs(locked.left.left);
        
        // Since by construction left is always preferred, and there is a little chance to have right
        if (locked.right != null) setIDs(locked.right.left);
        
    }
    
    /**
     * By construction, there is only one "safe" object in the locked objects, which is the final goal
     * This function will find that object
     * @return 
     */
    private Location findSafe(){
        if (locks != null)
            for (Location l : locks.keySet())
                if (locks.get(l).getPointType() == PointType.Safe)
                    return l;
        return null;
    }
    
    private Location findByRoomID(int roomID, HashMap<Location, PointOfInterest> inspectedMap) {
        if (inspectedMap == null)
            return null;
        for (Location l : inspectedMap.keySet())
            if ((inspectedMap.get(l).getRoomID() == roomID) && (inspectedMap.get(l).getPointType() != PointType.Safe))
                return l;
        return null;
    }
        
}
