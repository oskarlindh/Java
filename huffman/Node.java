public abstract class Node implements Comparable<Node> 
{

	int count;
	
	Node()
	{
	
	}

	public int compareTo(Node comp)
	{
		if(count < comp.count)	
		{
			return -1; 
		}
		if(count > comp.count)
		{
			return 1;
		}
		else return 0;
	}
	


}
