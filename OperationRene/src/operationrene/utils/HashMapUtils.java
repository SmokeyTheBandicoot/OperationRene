package operationrene.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import operationrene.mapframework.matrixprops.*;
import operationrene.mapframework.pointsofinterest.PointOfInterest;

public class HashMapUtils {
    
    /**
     * Rotates the Locations inside an hashmap(Location, PointOfInterest)
     * @param hash
     * @param rot
     * @param s
     * @return 
     */
    public static HashMap<Location, PointOfInterest> rotate(HashMap<Location, PointOfInterest> hash, Rotation rot, Size s) {
        if (hash == null) return null;
        if (rot == Rotation.NONE) return hash;
        
        int n = s.getHeight();
        int m = s.getWidth();
        Size POISize;
        
        Set<Location> set = new HashSet<>(hash.keySet());
        
        for (Location oldLocation : set) {
            
            PointOfInterest POIR = hash.remove(oldLocation);
            POISize = POIR.getSize();
            
            if (null != rot) switch (rot) {
                case LEFT:
                    // output [j][n-1-i] = mat[i][j];
                    // Location newLocR = new Location(oldLocation.getY(), n-1-oldLocation.getX());
                    Location newLocR = new Location(oldLocation.getY(), m-1-oldLocation.getX());
                    // Adjust for size
                    newLocR = new Location(newLocR.getX(), newLocR.getY() - (POISize.getWidth() - 1));
                    POIR.setSize(new Size(POISize.getHeight(), POISize.getWidth()));
                    hash.put(newLocR, POIR);
                    break;
                case RIGHT:
                    // output [j][n-1-i] = mat[n-1-i][m-1-j];
                    // Location newLocL = new Location(m-1-oldLocation.getY(), oldLocation.getX());
                    Location newLocL = new Location(n-1-oldLocation.getY(), oldLocation.getX());
                    // Adjust for size
                    newLocL = new Location(newLocL.getX() - (POISize.getHeight() - 1), newLocL.getY());
                    POIR.setSize(new Size(POISize.getHeight(), POISize.getWidth()));
                    hash.put(newLocL, POIR);
                    break;
                case DEG180:
                    // output [i][j] = mat[n-1-i][m-1-j];
                    // Location newLocD = new Location(n-1-oldLocation.getX(), m-1-oldLocation.getY());
                    Location newLocD = new Location(n-1-oldLocation.getX(), m-1-oldLocation.getY());
                    // Adjust for size
                    newLocD = new Location(newLocD.getX() - (POISize.getWidth() - 1), newLocD.getY() - (POISize.getHeight() - 1));
                    // NOT NEEDED POIR.setSize(new Size(POISize.getWidth(), POISize.getWidth()));
                    hash.put(newLocD, POIR);
                    break;
                default:
                    break;
            }
        }
        return new HashMap<>(hash);
    }
    
    /**
     * Flips the Locations inside the hashmap(Location, PointOfInterest)
     * @param hash
     * @param fl
     * @param s
     * @return 
     */
    public static HashMap<Location, PointOfInterest> flip(HashMap<Location, PointOfInterest> hash, Flipping fl, Size s) {
        if (hash == null) return null;
        if (fl == Flipping.NONE) return hash;
        
        int n = s.getHeight();
        int m = s.getWidth();
        Size POISize;
        
        Set<Location> set = new HashSet<>(hash.keySet());
        
        for (Location oldLocation : set) {
            
            PointOfInterest POIF = hash.remove(oldLocation);
            POISize = POIF.getSize();
            
            if (null != fl) switch (fl) {
                case HORIZONTAL:{
                    // Location newLocH = new Location(n-1-oldLocation.getX(), oldLocation.getY());
                    Location newLocH = new Location(m-1-oldLocation.getX(), oldLocation.getY());
                    // Adjust for size
                    newLocH = new Location(newLocH.getX() - (POISize.getWidth() - 1), newLocH.getY());
                    hash.put(newLocH, POIF);
                        break;
                    }
                case VERTICAL:
                    // Location newLocV = new Location(oldLocation.getX(), m-1-oldLocation.getY());
                    Location newLocV = new Location(oldLocation.getX(), n-1-oldLocation.getY());
                    // Adjust for size
                    newLocV = new Location(newLocV.getX(), newLocV.getY() - (POISize.getHeight() - 1));
                    hash.put(newLocV, POIF);
                    break;
                default:
                    Location newLocB = new Location(m-1-oldLocation.getX(), n-1-oldLocation.getY());
                    // Adjust for size
                    newLocB = new Location(newLocB.getX() - (POISize.getWidth() - 1), newLocB.getY() - (POISize.getHeight() - 1));
                    hash.put(newLocB, POIF);
                    break;
                    
            }
        }
        return new HashMap<>(hash);
    }

    /**
     * Translates all the PointOfInterest with a specific offset
     * @param hash
     * @param offset
     * @return 
     */
    public static HashMap<Location, PointOfInterest> traslate(HashMap<Location, PointOfInterest> hash, Location offset) {
        if (hash == null) return null;
        Set<Location> set = new HashSet<>(hash.keySet());
        for (Location oldLoc : set) {
            PointOfInterest POI = hash.remove(oldLoc);
            Location newLoc = new Location(oldLoc.getX() + offset.getX(), oldLoc.getY() + offset.getY());
            System.out.println("Tralsated point: " + POI.toString() + 
                    "\nfrom location" + oldLoc.toString() + 
                    "\ninto new location " + newLoc.toString() + 
                    "\ntraslating of " + offset.toString());
            hash.put(newLoc, POI);
        }
        return new HashMap<>(hash);
    }
}
