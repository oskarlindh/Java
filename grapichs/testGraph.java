import javax.swing.*;

public class testGraph{
	
	 public class test1{
	        
		JFrame frame = new JFrame("first way");
		JButton button = new JButton("let's see");
		button.setBounds(200, 150, 90, 50);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(button);
		frame.setSize(500, 600);
		frame.setLayout(null);
		frame.setVisible(true);
	}


	public static void main(String[] args){
	
		test1 hej = new test1();
	}
}
