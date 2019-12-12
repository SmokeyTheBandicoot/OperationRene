package operationrene.datastructures;



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
        System.out.println("###");
        if (subtreeRoot == null) return null;
        if (subtreeRoot.key == key) return subtreeRoot;
        if (subtreeRoot.left != null && subtreeRoot.left.key == key) return root.left;
        if (subtreeRoot.right != null && subtreeRoot.right.key == key) return root.right;
        Node<T> resultLeft = getNode(key, subtreeRoot.left);
        if (resultLeft != null) return resultLeft;
        else return getNode(key, subtreeRoot.right);             
    }
    
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
