public class LinkedList<T>
{
	
	Node firstNode;	
	Node lastNode;
		
	private class Node
	{
		T element;
		Node next;		
		
		Node(T element){
			
			this.element = element;
		}
	}
	
	void insert(T element)
	{
		Node newNode = new Node(element);
		
		if(firstNode == null)
		{
			firstNode = newNode; 
			lastNode  = newNode;
		}	
		else
		{
			lastNode.next = newNode; 
			lastNode = newNode;
		}
	
	}
	

}

