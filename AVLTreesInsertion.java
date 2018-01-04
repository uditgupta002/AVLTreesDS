

class Node{
	
	int height;
        int data;
        Node left,right;
	Node(int data)
        {
		this.data = data;
		this.height = 0;
		this.left = null;
		this.right = null;
	}
}


class AVLTreesInsertion {
    
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
    
    public Node leftRotate(Node node){
        Node y = node.right;
        Node T3 = y.left;
        //Performing left Rotation
        node.right = T3;
        y.left = node;
        
        node.height = height(node.left) > height(node.right) ? height(node.left) + 1 : height(node.right) + 1;
        y.height = height(y.left) > height(y.right) ? height(y.left) + 1 : height(y.right) + 1;
        
        return y;
    }
    
    public Node rightRotate(Node node){
        Node y = node.left;
        Node T3 = y.right;
        //Performing left Rotation
        node.left = T3;
        y.right = node;
        
        node.height = height(node.left) > height(node.right) ? height(node.left) + 1 : height(node.right) + 1;
        y.height = height(y.left) > height(y.right) ? height(y.left) + 1 : height(y.right) + 1;
        
        return y;
    }
    
    public Node insertToAVL(Node node,int data)
    {
         if(node == null){
             return new Node(data);
         }
         
         if(node.data < data){
             node.left = insertToAVL(node.left,data);
         } else if(node.data > data) {
             node.right = insertToAVL(node.right,data);
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
}
