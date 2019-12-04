package operationrene.utils;

public class ProgressTree<T> {
    
    private Node<T> root;
    
    public ProgressTree(Node<T> root) {
        this.root = root;
    }
    
    public static class Node<T> {
        T key;
        Node<T> left;
        Node<T> right;
        Node<T> parent;
        
        public Node(T key){ 
            this.key = key; 
            this.left = null;
            this.right = null;
            this.parent = null;
        }
        
        public Node(T key, Node<T> parent) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.parent = parent;
        }
    }
    
    
}
