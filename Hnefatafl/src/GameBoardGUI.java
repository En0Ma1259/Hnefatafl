import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class GameBoardGUI extends JFrame{
	static int size;
	JButton button;
	JPanel header;
	JPanel board;
	JPanel fotter;
	JFrame jframe;
	String img;
	public String position;
	static Dimension dim;
	protected Controller controller;
	protected List<Field> possibleMovement;
	
	public GameBoardGUI() 
	{ 		
		
	}
	
	public void setNewBoard(Controller controller, int size, GameBoard gboard, boolean isWhitesTurn)
	{
		this.controller = controller;
		Container cp = getContentPane();
		cp.removeAll();
		Field test = controller.getMovementFieldOne();
		List<Field> list = controller.getPossibleMovement();
		String string = "";
		if(test != null){ 
			string = "" + test.x + test.y;
			if(!list.isEmpty()){
				string += " nicht leer";
			}
		}
		setTitle("Hnefatafl" + string);
		
		dim = new Dimension(50 * size,50 * size);
		Dimension sd = Toolkit.getDefaultToolkit().getScreenSize();
		super.setLocation(sd.width/2 - dim.width/2,sd.height/2 - (dim.height+100)/2);
		cp.setLayout(new BoxLayout(cp,BoxLayout.Y_AXIS)); 
		
		header = new JPanel(); 
		JLabel turn = new JLabel();
		if(isWhitesTurn == true)
		{
			turn.setText("Weiﬂ");
		}
		else 
		{
			turn.setText("Schwarz");
		}
		if (controller.isEnd())
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
							if(controller.getMovementFieldOne()==controller.getMovementFieldTwo()){
								controller.resetSelection();
								controller.printGameBoardGUI();
								
							}
							controller.movementGUI();
						}
						controller.printGameBoardGUI();
					}
				});
				button.setEnabled(false);
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
							
						button.setText("K");
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
							button.setText("W");
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
							button.setText("S");
						}
					}
				}
					
				if(possibleMovement != null && possibleMovement.isEmpty() == false)
				{
					if(possibleMovement.contains(gboard.getField(i, j)))
					{
						button.setEnabled(true);
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
}