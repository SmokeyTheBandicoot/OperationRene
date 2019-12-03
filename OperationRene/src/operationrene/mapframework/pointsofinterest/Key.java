package operationrene.mapframework.pointsofinterest;

public class Key extends PointOfInterest {
    
    public Key( int ID) {
        super(PointType.Key, ID);
    }
    
    @Override
    public String toString(){
        return "Type: Key; ID: " + ID;
    }
    
}
