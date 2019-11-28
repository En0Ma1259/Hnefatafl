import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

import javax.imageio.*;
import javax.swing.*;

public class GameBoardGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int size;
	JButton button;
	JPanel header;
	JPanel board;
	JPanel fotter;
	JFrame jframe;
	public String position;
	static Dimension dim;
	protected Controller controller;
	protected List<Field> possibleMovement;
	Image img = null;
	
	public GameBoardGUI() 
	{ 		
		
	}
	
	public void setNewBoard(Controller controller, int size, GameBoard gboard, boolean isWhitesTurn)
	{
		this.controller = controller;
		this.controller.calculatePossibleFields();
		boolean end = controller.isEnd();
		if(!this.controller.hasPossibleMovement()) {
			end = true;
			isWhitesTurn = !isWhitesTurn; 
		}
		
		Container cp = getContentPane();
		cp.removeAll();
		setTitle("Hnefatafl");
		
		dim = new Dimension(50 * size,50 * size);
		Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
		if (controller.first)
		{
			controller.first = false;
			super.setLocation(sd.width/2 - dim.width/2,sd.height/2 - (dim.height+100)/2);
		}
		cp.setLayout(new BoxLayout(cp,BoxLayout.Y_AXIS)); 
		
		header = new JPanel(); 
		JLabel turn = new JLabel();
		if(isWhitesTurn)
		{
			turn.setText("Wei�");
		}
		else 
		{
			turn.setText("Schwarz");
		}
		
		if (end)
		{
			turn.setText(turn.getText() + " hat gewonnen");
		}
		else
		{
			turn.setText(turn.getText() + " ist am Zug");
		}
		turn.setName("Zug");
		header.add(turn);
		
		cp.add(header);
		
		board = new JPanel();
		board.setMinimumSize(dim);
		board.setMaximumSize(dim);
		board.setPreferredSize(dim);
		
		board.setLayout(new GridLayout(size , size));
		for (int j = 0;j<size;j++)
		{
			for (int i = 0;i<size;i++)
			{
				button = new JButton();
				button.setName(""+j+"/"+i);
				possibleMovement = controller.getPossibleMovement();
				button.addActionListener(new ActionListener() 
				{					
					@Override
					public void actionPerformed(ActionEvent e) 
					{		
						possibleMovement = controller.getPossibleMovement();
						position = ((JButton) e.getSource()).getName();	
						if (possibleMovement == null || possibleMovement.isEmpty())
						{
							position = ((JButton) e.getSource()).getName();	
							controller.extractPoint(position);
							controller.setMovementFieldOne(position);
						}
						else
						{
							controller.setMovementFieldTwo(position);
							controller.movementGUI();
						}
						controller.printGameBoardGUI();
					}
				});
				button.setEnabled(false);
				if(gboard.getField(i, j).getType() == Field.Types.SPEZIAL){
					String path = "";
					if(gboard.getField(i, j).isConer()){
						path = "Icons/feld_markiert.jpg";
					}
					else {
						path = "Icons/feld_markiert2.jpg";
					}
					try {
						img = ImageIO.read(getClass().getResource(path));
						button.setIcon(new ImageIcon(img));
					} catch (IOException e) {
						e.printStackTrace();
					}  
				}
				if( gboard.getField(i,j).getFigure()!=null ) 
				{
					if(gboard.getField(i,j).getFigure() instanceof King) 
					{
						if(isWhitesTurn == true)
						{
							button.setEnabled(true);
						}
						else 
						{
							button.setEnabled(false);
						}
							
						button.setText("");
						try {
							img = ImageIO.read(getClass().getResource("Icons/w_koenig.jfif"));
							button.setIcon(new ImageIcon(img));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else 
					{ //instanceof != King
						if( gboard.getField(i,j).getFigure().isWhite==true ) 
						{
							if(isWhitesTurn == true)
							{
								button.setEnabled(true);
							}
							else 
							{
								button.setEnabled(false);
							}						
							button.setText("");
							try {
								img = ImageIO.read(getClass().getResource("Icons/w_bauer.png"));
								button.setIcon(new ImageIcon(img));
							} catch (IOException e) {
								e.printStackTrace();
							}							
						}
						else //isWhite==false
						{
							if(isWhitesTurn == false)
							{
								button.setEnabled(true);
							}
							else 
							{
								button.setEnabled(false);
							}
							button.setText("");
							try {
								img = ImageIO.read(getClass().getResource("Icons/s_bauer.png"));
								button.setIcon(new ImageIcon(img));
							} catch (IOException e) {
								e.printStackTrace();
							}   
						}
					}
				}
					
				if(possibleMovement != null && possibleMovement.isEmpty() == false)
				{
					if(possibleMovement.contains(gboard.getField(i, j)))
					{
						button.setEnabled(true);
						try {
							img = ImageIO.read(getClass().getResource("Icons/feld_markiert.jpg"));
							button.setIcon(new ImageIcon(img));
						} catch (IOException e) {
							e.printStackTrace();
						}    				        
					}
					else
					{
						button.setEnabled(false);
					}
				}
				if (controller.isEnd())
				{
					button.setEnabled(false);
				}
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
				controller.generateBoard(FigureLayout.fieldNormal);	
				controller.printGameBoardGUI();
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
}