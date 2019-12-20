package operationrene.datastructures;

import java.util.ArrayList;
import java.util.List;



public class ProgressTree<T> {
    
    private Node<T> root = null;
    
    public ProgressTree(T root) {
        this.root = new Node<>(root);
    }
    
    public Node<T> getRoot() {
        return root;
    }
    
    public boolean insert(T nodeKey, T parentKey) {
        Node<T> nodeNode = new Node<>(nodeKey);
        Node<T> nodeParent = new Node<>(parentKey);
        boolean test;
        if(parentKey != null) {
            if(root == null) {
                root = nodeParent;
                root.left = nodeNode;
                root.right = null;
                root.parent = null;
                nodeNode.parent = root;
            }
            else if(test = contains(nodeParent.key, root)) {
                /*if (nodeParent.left == null) {
                    nodeParent.left = nodeNode;
                    nodeNode.parent = nodeParent;
                } else {
                    nodeParent.right = nodeNode;
                    nodeNode.parent = nodeParent;
                }*/
                Node<T> treeNode = getNode(nodeParent.key, root);
                if (treeNode.left == null) {
                    treeNode.left = nodeNode;
                    nodeNode.parent = treeNode;
                    return true;
                } else if (treeNode.right == null) {
                    treeNode.right = nodeNode;
                    nodeNode.parent = treeNode;
                    return true;
                } else {
                    return false;
                }
            }
            else
                return false;
        }
        else {
            return false;
        }
        return true;
    }
    
    public boolean contains(T nodeKey, Node<T> subtreeRoot) {
        Node<T> nodeNode = new Node<>(nodeKey);
        if (subtreeRoot == null) return false;
        if (subtreeRoot.key == nodeKey) return true;
        return contains(nodeKey, subtreeRoot.left) || contains(nodeKey, subtreeRoot.right);
    }
    
    public Node<T> getNode(T key, Node<T> subtreeRoot) {
        if (key == null) return null;
        if (subtreeRoot == null) return null;
        if (subtreeRoot.key == key) return subtreeRoot;
        boolean t1 = subtreeRoot.left != null;
        boolean t2 = subtreeRoot.left.key == key;
        if (t1 && t2) return subtreeRoot.left;
        if (subtreeRoot.right != null && subtreeRoot.right.key == key) return subtreeRoot.right;
        Node<T> resultLeft = getNode(key, subtreeRoot.left);
        if (resultLeft != null) return resultLeft;
        else return getNode(key, subtreeRoot.right);             
    }
    
    public int height(T key) {
        if (key == null) return 0;
        if (root == null) return 0;
        Node<T> node = getNode(key, root);
        if (node.left == null && node.right == null) return 1;
        if (node.right == null) return 1 + height(node.left.key);
        int lheight = height(node.left.key);
        int rheight = height(node.right.key);
        return 1 + Math.max(lheight, rheight);
    }
    
    public String printTree(String separator) {
        return recurseTree(root, "", "", separator);
    }
    
    private String recurseTree(Node<T> node, String indent, String result, String separator) {
        if (node == null) return indent + "~null";
        else {
            result += indent + "(" + node.key.toString() + ")"
                    + "\n" + recurseTree(node.left, indent + separator, result, separator) 
                    + "\n" + recurseTree(node.right, indent + separator, result, separator);
            return result;
        }
    }

    public List<T> toListBreadthFirst(Node<T> root) {
        List<T> list = new ArrayList<>();
        if (root == null) return new ArrayList<>();
        list.add(root.key);
        list.addAll(toListBreadthFirst(root.left));
        list.addAll(toListBreadthFirst(root.right));
        return list;
    }
    
    
    
    /*public String printTree() {
        StringBuilder s = new StringBuilder();
        int h = height(root.key);
        for (int i = 0; i < h; i++)
            s.append(printLevel(root, i, ".   "));
        return s.toString();
    }
    
    private String printLevel(Node<T> node, int level, String separator) {
        StringBuilder s = new StringBuilder();
        if (separator == null) separator = ".   ";
        if (node == null) return "~null";
        if (node.key == null) return "~null";
        if (level == 0) return "(" + node.key.toString() + ")\n";
        
        s.append(separator);
        s.append(printLevel(node.left, level - 1, separator));
        
        s.append("\n").append(separator);
        s.append(printLevel(node.right, level - 1, separator));
    }*/
    
    // Node class
    public static class Node<T> {
        
        public T key;
        public Node<T> left;
        public Node<T> right;
        public Node<T> parent;
        
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
