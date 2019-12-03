package operationrene.utils;

import java.util.LinkedList;

public class ProgressTree<T> {
    
    private Node<T> root;
    
    public ProgressTree(Node<T> root) {
        this.root = root;
    }
    
    public boolean contains(Node<T> node) {
        return root.contains(node);
    }

    public void insert(int key, Node<T> node, Node<T> parent) throws Exception {
        if (root == null)
            this.root = node;
        else if (parent == root)
                root.children.add(node);
        else {
            if (this.contains(parent)) {
                parent.children.add(node);
            } else 
                throw new Exception("Parent not present in tree");
        
        }   
    }
    
    
    
    public static class Node<T> {
        T key;
        LinkedList<Node<T>> children;
        Node<T> parent;
        
        public Node(T key){ 
            this.key = key; 
            this.children = null;
            this.parent = null;
        }
        
        public Node(T key, Node<T> parent) {
            this.key = key;
            this.children = null;
            this.parent = parent;
        }
        
        public boolean contains(Node<T> node) {
            if (node == null) return false;
            if (this.equals(node)) return true;
            if (this.children == null) return false;
            return this.children.contains(node);
        }
    }
    
    
}
