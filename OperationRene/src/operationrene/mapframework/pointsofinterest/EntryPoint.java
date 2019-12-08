/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operationrene.mapframework.pointsofinterest;

/**
 *
 * @author Miky Gargiulo
 */
public class EntryPoint extends PointOfInterest {
    
    public EntryPoint(int roomID) {
        super(PointType.EntryPoint, roomID, new int[]{-1}, 1, 1);
    }
    
}
