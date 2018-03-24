import java.util.LinkedList;

public class BinomialHeap<T extends Comparable<T>>{
	

	LinkedList<BinoTree> treeList;
	
	
	
	
	BinomialHeap(T element)
	{
		treeList.add(new BinoTree(element));
	
	}


	private class BinoTree{
			
			BinoNode root;
			LinkedList<BinoTree> rootList;	
			int rank;

		BinoTree(T element){
			
				root = new BinoNode(element);
				rank = 0;

		
		}
			
		BinoNode getRoot(){
		
			return root;
		}	
		
		BinoTree merge(BinoTree tree)
		{	
			int comp = root.key.compareTo(tree.root.key);
			
			if(comp < 0)
			{
				 
				return insertSubTree(tree);
				
			}
			else
			{
				return tree.insertSubTree(this);
					
			}		
		
		}
		

		BinoTree insertSubTree(BinoTree tree)
		{
			rank += 1;
			
			if(rootList == null)
			{
				rootList = new LinkedList<BinoTree>();
				rootList.add(tree);	
				return this;
			}
		    if(tree.rootList.get(0).key < rootList.get(0).key){
				
					rootList.add(tree);	
			
			}	
			else{
			
				rootList.addFirst(tree);
			}
			return this;			
		}	
		
		}
		
		private class BinoNode{
			
			T key;

			BinoNode(T key){
				
				this.key = key;
					
			}

			T getKey(){
				return key;
			}
		}
		
		void insertElement(T element){
					
			merge(new BinomialHeap<T>(element));	
			
		}
		
		void insertTree(BinoTree<T> tree)
		{
				
		}

		void merge(BinomialHeap<T> heap){
			
			compTree = treeList.size();
			compThis = this.treeList.size();
			BinoTree currentTree;
			BinoTree currentThis;
			
			if(compTree < compThis)
			{	
					Iterator<T> it = compTree.iterator();
				
				while (it.hasNext()){
						
						
							
				}	
			}	
			if()
			{
				return;
			}
			else 
			{
				return;
			}
		

		}		

}
