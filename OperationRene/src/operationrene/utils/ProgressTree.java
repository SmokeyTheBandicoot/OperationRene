package operationrene.utils;

public class ProgressTree<T> {
    
    private Node<T> root = null;
    
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
    
    public boolean insert(Node<T> node, Node<T> parent) {
        if(parent != null) {
            if(root == null) {
                root = parent;
                root.left = node;
                root.right = null;
                root.parent = null;
                node.parent = root;
            }
            else if(isInTree(parent)) {
                parent.left = node;
                node.parent = parent;
            }
            else
                return false;
        }
        else {
            return false;
        }
        return true;
    }
    
    public boolean isInTree(Node<T> node) {
        if(root == null)
            return false;
        else if(root.left == null && root.right == null)
            return root == node;
        else
            return isInTree(root.left) || isInTree(root.right);
    }
}
