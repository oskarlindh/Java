import java.util.Random; 
import java.util.ArrayList;

public class Tree<T extends Comparable<T>>{

	private Node root; 
	int elementCount = 0; 
	
	Tree(){
		root = new Node(null);
		root.parent = null;
	}
	
	private class Node{
		
		Node parent =  null;
		Node right  =  null;
		Node left   =  null; 
		T element;	

		Node(T elem){
		
		element = elem;
		
		}
	
		boolean isEmpty()
		{
			return element == null;
		}
	}
	
	boolean isEmpty()
	{
		return root.isEmpty();
	}

	private Node getRoot()
	{
		return root;
	}
	
	
	private void insertTree(Node node ,T elem){
			
			
		int comp = elem.compareTo(node.element);	
		
		if(comp == 0){
			return;	
		}	

		else if(comp < 0)			
		{	
		
			if(node.left ==  null){
				node.left = new Node(elem);	
				elementCount += 1;
				node.left.parent = node;
			}else{
				insertTree(node.left, elem);
							
			}
		
		}	
		
		else if(comp > 0){
		
		if(node.right == null){
		
			node.right = new Node(elem);
			node.right.parent = node;
			elementCount += 1;
		}
		else{
					
			insertTree(node.right, elem);
		}	
			
		}
	}
	
	
	int treeDepth(int depth){
		
		return 1;
		
	}
	
	boolean treeContains(T lookUpElem){
	
		Node currentNode = root;
		int comp = lookUpElem.compareTo(currentNode.element);

		while(comp != 0){

			if(currentNode == null){
				return false; 
			}	
			
			comp = lookUpElem.compareTo(currentNode.element);		
			if(comp < 0){
				currentNode = currentNode.left;
				
			}	
			
			if(comp > 0){
				currentNode = currentNode.right;
				
			}	
		
		}
		return true;
	}

	void insert(T elem){
		

		if (isEmpty()){
			System.out.print("root \n");
			root = new Node(elem);
			elementCount += 1;
		}else{	
		
		insertTree(root, elem);	
	
		}
	}

				
	
	int treeDepth(Node node){
	
		if(node == null){
		
			return 0;
		}
		else{
		return 1 + maximum(treeDepth(node.left), treeDepth(node.right));
		}
	}
	
	int depth(){
	
		return treeDepth(root);
	}

	int maximum(int a, int b){
		if(a > b){
			return a;
		}
		else return b;
	}
		

	//TODO Finnish	
/*	
	void preorder(Arraylist<T> list,Node node){
	
	
	}
*/	
	void printInorder(ArrayList<T> list, Node node)
	{
		if(node == null){
			return;
		}
			
		printInorder(list, node.left);
		list.add(node.element);
		printInorder(list, node.right);
	}

	ArrayList<T> inOrder(){
		
		ArrayList<T> list = new ArrayList<T>();
		printInorder(list,root);
		return list;	
	}	

	public static void main(String[] args){
		
		int q = 1;	
		Tree<Integer> t = new Tree<Integer>();
		System.out.print(t.isEmpty());
		System.out.print("\n");
			
		t.insert(q);	
		System.out.print(t.isEmpty());
		System.out.print("\n");
		System.out.print(t.root.element + "\n");

		t.insert(2);	
		t.insert(-1);	
		
		System.out.print("djupet är " + t.depth() +" \n");	
		System.out.print(t.isEmpty());
		System.out.print("\n");
		Random rand = new Random();
		int hej = 0;
		int o = 0;
		for(int i = 1; i < 100; i++)
		{
			hej = rand.nextInt(200);	
			t.insert(hej);
			o += 1;
		}
	
		System.out.print("djupet är " + t.depth() +" \n");	
		t.insert(5);
		System.out.print(t.elementCount + "  Hej \n");
		//t.inOrder();
		ArrayList<Integer> list =  t.inOrder();
		for(int x : list){
			System.out.print(x +" ");		
			
		}
		System.out.print("\n");	
	}


}
