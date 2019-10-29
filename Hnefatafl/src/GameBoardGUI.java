import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameBoardGUI extends JFrame{
	static int size;
	JButton button;
	JPanel header;
	JPanel board;
	JPanel fotter;
	JFrame jframe;
	static Dimension dim;
	public GameBoardGUI(int size) 
	{ 		
		Container cp = getContentPane();
		setTitle("Hnefatafl");
		dim = new Dimension(50 * size,50 * size);
		Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
		super.setLocation(sd.width/2 - dim.width/2,sd.height/2 - (dim.height+100)/2);
		cp.setLayout(new BoxLayout(cp,BoxLayout.Y_AXIS)); 
		
		header = new JPanel(); 
		JLabel weiﬂ = new JLabel("Weiﬂ");
		weiﬂ.setName("weiﬂ");
		header.add(weiﬂ);
		JLabel schwarz = new JLabel("Schwarz");
		schwarz.setName("schwarz");
		schwarz.setForeground(Color.DARK_GRAY);
		header.add(schwarz);
		
		cp.add(header);
		
		board = new JPanel();
		board.setMinimumSize(dim);
		board.setPreferredSize(dim);
		board.setLayout(new GridLayout(size , size));
		for (int i = 0;i<size;i++)
		{
			for (int j = 0;j<size;j++)
			{
				button = new JButton();
				button.setName(""+i+","+j);
				button.addActionListener(new ActionListener() 
				{					
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						System.out.println(((JButton) e.getSource()).getName());						
					}
				});
				board.add(button);
			}
		}
		cp.add(board);
		
		fotter = new JPanel();
		button = new JButton("Neu");
		button.addActionListener(new ActionListener() 
		{					
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("neues Spiel");						
			}
		});
		fotter.add(button);
		button = new JButton("Beenden");
		button.addActionListener(new ActionListener() 
		{					
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);										
			}
		});
		fotter.add(button);
		cp.add(fotter);
		
	}
	public static void main (String [] args) 
	{ 
		size = 11;
		GameBoardGUI gameboard = new GameBoardGUI(size); 
		gameboard.pack(); 
		gameboard.setVisible(true);
	}	
}

