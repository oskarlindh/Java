public class InternalNode extends Node{

	int count;
	Node leftChild;
	Node rightChild;

	InternalNode(int count,Node left, Node right)
	{
		super.count = count;
		leftChild = left;
		rightChild = right;
	}	

	
}
