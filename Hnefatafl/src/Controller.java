import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
	
	/**
	 * false = black, true = white
	 */
	protected boolean isWhitesTurn = true;
	
	/**
	 * Current round.
	 */
	protected int round = 0;
	
	protected int size;
	
	protected GameBoard board; 
	protected FigureLayout.t[][] boardPlan;
	protected Scanner input = new Scanner(System.in);
	
	/**
	 * Gernerate game board (from FigureLayout).
	 * 
	 * @param size
	 */

	public void generateBoard(FigureLayout.t[][] boardPlan){
		this.boardPlan = boardPlan;
		size = this.boardPlan.length;
		board = new GameBoard(size);
		board.setFigures(boardPlan);
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
				if ( board.getField(i, j).getType()==Field.Types.NORMAL)
				{
					if ( board.getField(i, j).getFigure()!=null )
					{
						if ( board.getField(i, j).getFigure().isWhite==true
							&& !(board.getField(i, j).getFigure() instanceof King) )
						{
							System.out.print("W\t");
						}
						if ( board.getField(i, j).getFigure().isWhite==false )
						{
							System.out.print("B\t");
						}
						if ( board.getField(i, j).getFigure() instanceof King )
						{
							System.out.print("K\t");
						}
					}
					else {
						System.out.print("\t");
					}
				}
				else	//getType()==Field.Types.SPEZIAL
				{
					if ( board.getField(i, j).getFigure() instanceof King )
					{
						System.out.print("K\t");
					}
					else {
						System.out.print("S\t");
					}
				}
			}
			System.out.print("\n");
		}
		System.out.println("");
		GameBoardGUI game = new GameBoardGUI(size);
		game.show();
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

		    	beatFigures(destinationField);
			}
		}else{
			this.movement();
		}

    	in.close();
	}
	
	public void movement2()
	{
		System.out.println("Eingabeschema: Zahl/Zahl");
		System.out.println("W�hle eine Figur aus: ");
		Point point;
		Point point2;
		Field origin=null;
		Field destination=null;
		boolean isPointValid;
		do
		{
			point = extractPoint(getInput());
			isPointValid = isPointValid(point);
			if(!isPointValid){
				System.out.print("Der Punkt ist nicht g�ltig, bitte nochmal eingeben:");
			}
		}
		while ( isPointValid==false);
		
		System.out.println("Gebe ein Feld ein wo die Figur hinziehen soll.");
		do
		{
			point2 = extractPoint(getInput());
			isPointValid = isFieldValid(point2);
			if ( isPointValid )
			{
				origin = board.getField(point.y, point.x);
				destination = board.getField(point2.y, point2.x);
				isPointValid = possibleMovement(origin).contains(destination);
			}
			if( !isPointValid ){
				System.out.print("Der Punkt ist nicht g�ltig, bitte nochmal eingeben:");
			}
		}
		while ( !isPointValid );
		
		//Bewege Figur
		destination.setFigure(origin.getFigure());
		origin.setFigure(null);
		
		this.printGameBoard();
	}
	
	public String getInput()
	{
		//macht was mit input
		//formatiert input-Werte in String "(Zahl/Zahl) der zur�ckgegeben wird
		
		return input.nextLine();
	}
	
	public Point extractPoint(String input) {
		int positionSlash = input.indexOf("/");
		Point point;
		try {
		String Zeile = input.substring(0, positionSlash);
		String Spalte = input.substring(positionSlash+1, input.length());
		
		point = new Point(Integer.parseInt(Zeile), Integer.parseInt(Spalte));
		}
		catch (Exception e){
			return null;
		}
		
		return point;
	}
	
	public boolean isPointValid(Point point)
	{
		if (point==null)
		{
			return false;
		}
		//point liegt im Gameboard
		int x = point.x, y=point.y;
		if(x < 0 || y < 0){
			return false;
		}
		if(x >= size || y >= size)
		{
			return false;
		}
		//is da Figur vom Spieler
		Figure figure = board.getField(x, y).getFigure();
		if(figure!=null){
			if(figure.isWhite == isWhitesTurn){
				return true;
			}
		}
		return false;
	}
	
	public boolean isFieldValid(Point point)
	{
		//point liegt im Gameboard
		int x = point.x, y=point.y;
		
		if (point==null)
		{
			return false;
		}
		if(x < 0 || y < 0){
			return false;
		}
		if(x >= size || y >= size)
		{
			return false;
		}
		return true;
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
			else {
				break;
			}
		}
		for(int i = 0; i < size; i++){
			possibleField = board.getField(i, field.y);
			if(checkField(field, possibleField)){
				possibleFields.add(possibleField);				
			}
			else {
				break;
			}
		}
		
		// Y Values
		// 0 is here Field y variable
		for(int i = /* 0 to change */0; i >= 0; i--){
			possibleField = board.getField(field.x, i);
			if(checkField(field, possibleField)){
				possibleFields.add(possibleField);				
			}
			else {
				break;
			}
		}
		for(int i = 0; i < size; i++){
			possibleField = board.getField(field.x, i);
			if(checkField(field, possibleField)){
				possibleFields.add(possibleField);				
			}
			else {
				break;
			}
		}
		return possibleFields;
	}
	
	protected boolean checkField(Field field,Field possibleField) {
		return !(field == possibleField || possibleField.type == Field.Types.SPEZIAL || possibleField.isSet());
	}
	
	protected void beatFigures(Field field){
		beatFigure(field, board.getField(field.x + 1, field.y));
		beatFigure(field, board.getField(field.x - 1, field.y));
		beatFigure(field, board.getField(field.x, field.y - 1));
		beatFigure(field, board.getField(field.x, field.y + 1));
	}
	
	protected void beatFigure(Field field, Field beatableField){
		if(beatableField == null || beatableField.getFigure() == null){
			return;
		}
		
		if(field.getFigure().isWhite == beatableField.getFigure().isWhite){
			return;
		}
		
		if(beatableField.getFigure() instanceof King){
			boolean firstField = checkBeatable(beatableField.x + 1, beatableField.y, beatableField);
			boolean secoundField = checkBeatable(beatableField.x -1, beatableField.y, beatableField);
			boolean thirdField = checkBeatable(beatableField.x, beatableField.y + 1, beatableField);
			boolean fouthField = checkBeatable(beatableField.x, beatableField.y - 1, beatableField);
			if(firstField && secoundField && thirdField && fouthField){
				beatableField.setFigure(null);
			}
		}else{	
			int x = 2 * beatableField.x - field.x;
			int y = 2 * beatableField.y - field.y;
			
			if(checkBeatable(x,y, beatableField)){
				beatableField.setFigure(null);
			}
		}
	}
	
	protected boolean checkBeatable(int x, int y, Field beatableField) {
		if(board.getField(x, y) == null){
			return false;
		}
		
		if(board.getField(x, y).getType() == Field.Types.SPEZIAL){
			return true;
		}
		
		if(board.getField(x, y).getFigure() == null){
			return false;	
		}
		
		if(board.getField(x, y).getFigure().isWhite != beatableField.getFigure().isWhite){
			return true;
		}
		
		return false;
	}
}
