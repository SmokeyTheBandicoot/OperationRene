package operationrene.utils;

public class ProgressTree {
    
    Node root;
    
    public ProgressTree() {
        super();
    }
   
    /*public void insert(int key, Node parent) {
        if (root == null)
            
            
        
    }*/
    
    
    
    public static class Node {
        int key;
        Node[] children;
        Node parent;
        
        Node(int key){ 
            this.key = key; 
            this.children = null;
            this.parent = null;
        }
        
        Node(int key, Node parent) {
            this.key = key;
            this.children = null;
            this.parent = parent;
        }
    }
    
    
}
