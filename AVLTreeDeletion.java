import java.util.*;

class Node{
	
	int height;
        int data;
        Node left,right;
	
	Node(int data) {
		this.data = data;
		this.height = 0;
		this.left = null;
		this.right = null;
	}
}


public class AVLTreeDeletion {
    
    Node root;
    
    int height(Node N) {
        if (N == null)
            return 0;
 
        return N.height;
    }
    
    int getBalance(Node N) {
        if (N == null)
            return 0;
 
        return height(N.left) - height(N.right);
    }
    
    public static Node minValueNode(Node root) {
        Node current = root;
        while(current != null && current.left != null)
            current = current.left;
        return current;
    }
    
    public Node leftRotate(Node node){
        Node y = node.right;
        Node T3 = y.left;
        
        node.right = T3;
        y.left = node;
        
        node.height = height(node.left) > height(node.right) ? height(node.left) + 1 : height(node.right) + 1;
        y.height = height(y.left) > height(y.right) ? height(y.left) + 1 : height(y.right) + 1;
        
        return y;
    }
    
    public Node rightRotate(Node node){
        Node y = node.left;
        Node T3 = y.right;
        
        node.left = T3;
        y.right = node;
        
        node.height = height(node.left) > height(node.right) ? height(node.left) + 1 : height(node.right) + 1;
        y.height = height(y.left) > height(y.right) ? height(y.left) + 1 : height(y.right) + 1;
        
        return y;
    }
    
    
     Node deleteNode(Node root, int key)
    {
        if (root == null)
            return root;
 
        if (key < root.data)
            root.left = deleteNode(root.left, key);
        else if (key > root.data)
            root.right = deleteNode(root.right, key);
        else
        {
            if ((root.left == null) || (root.right == null))
            {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
 

                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else
                    root = temp;
            }
            else {
                Node temp = minValueNode(root.right);
                root.data = temp.data;
                root.right = deleteNode(root.right, temp.data);
            }
        }
 
        if (root == null)
            return root;
 
        root.height = height(root.left) > height(root.right) ? height(root.left) + 1 : height(root.right) + 1;
 
        int balance = getBalance(root);
 
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);
 
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
 
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);
 
        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
 
        return root;
    }
    
    public Node insert(Node node,int data)
    {
         if(node == null){
             return new Node(data);
         }
         
         if(node.data > data){
             node.left = insert(node.left,data);
         } else if(node.data < data) {
             node.right = insert(node.right,data);
         } else {
             return node;
         }
         
         node.height = height(node.left) > height(node.right) ? height(node.left) + 1 : height(node.right) + 1;
         
         int balanceFactor = getBalance(node);
         if(balanceFactor > 1){
             if(data < node.left.data){
                 return rightRotate(node);
             } else {
                 node.left = leftRotate(node.left);
                 return rightRotate(node);
             }
         } else if(balanceFactor < -1) {
             if(data > node.right.data){
                 return leftRotate(node);
             } else {
                 node.right = rightRotate(node.right);
                 return leftRotate(node);
             }
         }
         return node;
}
    
    public static void inorder(Node node) {
        
        if(node == null)
            return;

        Stack<Node> stack = new Stack<Node>();
        Node currentNode = node;
        while(currentNode != null){
            stack.push(currentNode);
            currentNode = currentNode.left;
        }
        
        while(!stack.isEmpty()){
            Node current = stack.pop();
            System.out.print(current.data+" ");
            if(current.right != null){
                current = current.right;
                while(current != null){
                    stack.push(current);
                    current = current.left;
                }
            }
            
        }
        /*
        inorder(node.left);
        System.out.print(node.data+" ");
        inorder(node.right);
        */
    }
    
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
 
        tree.root = tree.insert(tree.root, 9);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 0);
        tree.root = tree.insert(tree.root, 6);
        tree.root = tree.insert(tree.root, 11);
        tree.root = tree.insert(tree.root, -1);
        tree.root = tree.insert(tree.root, 1);
        tree.root = tree.insert(tree.root, 2);
 
        tree.root = tree.deleteNode(tree.root, 10);
        tree.root = tree.deleteNode(tree.root, 9);
        inorder(tree.root);
    }
}
