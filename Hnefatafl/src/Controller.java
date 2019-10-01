import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
	
	/**
	 * false = black, true = white
	 */
	protected boolean turn = false;
	
	/**
	 * Current round.
	 */
	protected int round = 0;
	
	protected int size;
	
	protected GameBoard board; 
	protected FigureLayout.t[][] boardPlan;
	
	/**
	 * Gernerate game board (size x size).
	 * 
	 * @param size
	 */

	public void generateBoard(FigureLayout.t[][] boardPlan){
		this.boardPlan = boardPlan;
		size = this.boardPlan.length;
		board = new GameBoard(size);
		setFigures();
	}
	
	protected void setFigures(){
		int size = boardPlan.length;
		for(int i=0;i<size;++i){
			for(int j=0;j<size;++j){
				switch(boardPlan[i][j]){
					case NONE: board.getField(i, j).setFigure(null); break;
					case WHITE: board.getField(i, j).setFigure(new Figure(true));break;
					case BLACK: board.getField(i, j).setFigure(new Figure(false));break;
					case KING: board.getField(i, j).setFigure(new King());break;
				}
			}
		}
	}
	
	
	
	public void printGameBoard() {
		System.out.println("Ausgabe:");
		System.out.print("\t");
		for (int c=0; c<size; c++)
		{
			System.out.print(c+"\t");
		}
		System.out.println("");
		for(int i=0;i<size;++i){
			System.out.print(i+"\t");
			for(int j=0;j<size;++j){
				if ( board.getField(i, j).getFigure()!=null )
				{
					if ( board.getField(i, j).getFigure() instanceof King )
					{
						System.out.print("K\t");
					}
					if ( board.getField(i, j).getFigure().isWhite==true
						&& !(board.getField(i, j).getFigure() instanceof King) )
					{
						System.out.print("W\t");
					}
					if ( board.getField(i, j).getFigure().isWhite==false )
					{
						System.out.print("B\t");
					}
				}
				else {
					System.out.print("\t");
				}
			}
			System.out.print("\n");
		}
		System.out.println("");
	}
	
	public void movement(){
		Scanner in = new Scanner(System.in);
		Field selectedField = this.selectFigure(in);
		List<Field> possibleFields = this.possibleMovement(selectedField);
		
		if(selectedField.isSet() && !possibleFields.isEmpty()){
			Field destinationField = this.selectFigure(in);
			
			if(possibleFields.contains(destinationField)){
				destinationField.setFigure(selectedField.getFigure());
				selectedField.setFigure(null);
			}
		}else{
			this.movement();
		}

    	in.close();
	}
	
	protected Field selectFigure(Scanner in){
		
		Field field = null;
		if(in.hasNextLine()){
			System.out.println("X: ");
	        int x = Integer.parseInt(in.nextLine());
	        System.out.println("Y: ");
	    	int y = Integer.parseInt(in.nextLine());
	    	field = board.getField(x, y);
		}
    	
    	return field;
	}
	
	/**
	 * @param positionX
	 */
	public List<Field> possibleMovement(Field field){
		Field possibleField;
		List<Field> possibleFields = new ArrayList<>();
		
		
		// X Values
		// 0 is here Field x variable
		for(int i = field.x; i >= 0; i--){
			possibleField = board.getField(i, field.y);
			if(checkField(field, possibleField)){
				possibleFields.add(possibleField);				
			}
		}
		for(int i = 0; i < size; i++){
			possibleField = board.getField(i, field.y);
			if(checkField(field, possibleField)){
				possibleFields.add(possibleField);				
			}
		}
		
		// Y Values
		// 0 is here Field y variable
		for(int i = /* 0 to change */0; i >= 0; i--){
			possibleField = board.getField(field.x, i);
			if(checkField(field, possibleField)){
				possibleFields.add(possibleField);				
			}
		}
		for(int i = 0; i < size; i++){
			possibleField = board.getField(field.x, i);
			if(checkField(field, possibleField)){
				possibleFields.add(possibleField);				
			}
		}
		return possibleFields;
	}
	
	protected boolean checkField(Field field,Field possibleField) {
		return !(field == possibleField || possibleField.type == Field.Types.SPEZIAL || possibleField.isSet());
	}
}
