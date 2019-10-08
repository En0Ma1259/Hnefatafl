import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameBoardGUI extends JFrame
{
	public static int size;
	Dimension dim = new Dimension(50,50);
	JButton button; 
	JPanel buttons;
	JPanel header;
	JPanel footer;
	public GameBoardGUI(int size)
	{ 
		Container cp = getContentPane(); 
		
		cp.setPreferredSize(new Dimension((size * 50),(size * 50) + 100));
		
		cp.setLayout(new GridLayout(size,size));
		for (int i = 0; i < size; i++)
		{ 
			for (int j = 0; j < size; j++)
			{ 
				button = new JButton();
				button.setName((i+1) + "-" + (j+1));
				button.addActionListener(new ActionListener()
				{ 
					public void actionPerformed(ActionEvent e) 
					{ 
						JButton button = (JButton)e.getSource();
						System.out.println(button.getName()); 
					} 
				});
				button.setMinimumSize(dim);
				button.setMaximumSize(dim);
				cp.add(button);
			}
		} 
	}
	public static void main (String [] args) { 
		size = 11;
		GameBoardGUI test = new GameBoardGUI(size); 
		test.pack(); 
		test.setVisible(true);; 
		}
}
