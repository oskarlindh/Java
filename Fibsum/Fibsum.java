import java.util.*; 

public class Fibsum{
	
		static long sum = 0;

	public void fib(int limit)
	{		
		int temp = 0;
		int temp2 = 1;
		int temp3 = 1;
		List numbers = new ArrayList(); 		
		
		for(int i = 1; i <= limit; i++)
		{ 
		    	temp = temp2;
			temp2 =  temp3;
			temp3 = temp2 + temp;
			if((temp % 2) == 0){
				numbers.add(temp3);	
			}
		}	
		
		Iterator<Integer> it = numbers.iterator();
	
		while (it.hasNext()){
			
			sum += it.next();	
						}

		
	}
		
	
	public static void main(String[] args)
	{
		Fibsum o = new Fibsum();
		o.fib(1);			
		System.out.print(sum);
		System.out.print("\n");
	}

}
