import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
	
	/**
	 * false = black, true = white
	 */
	protected boolean isWhitesTurn = false;
	
	/**
	 * Current round.
	 */
	protected int round = 0;
	
	protected int size;
	
	protected boolean end;
	
	protected GameBoard board; 
	protected FigureLayout.t[][] boardPlan;
	protected Scanner input = new Scanner(System.in);
	
	protected Field currentFieldOne;
	protected Field currentFieldTwo;
	protected List<Field> possibleMovement;
	
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
		board.getField(1, 0).setFigure(new King());
	}

	public void start(){
		String winner = " hat gewonnen.";
		
		do {
			this.movement();			
		}while (this.end != true);
		
		
		if(this.isWhitesTurn){
			winner = "Wei�" + winner;
		}else{
			winner = "Schwarz" + winner;
		}
		System.out.println(winner);
	}
	
	public void printGameBoard() {
		String turn = "";
		if(this.isWhitesTurn){
			turn = "Wei�";
		}else{
			turn = "Schwarz";
		}
		System.out.println(turn + " am Zug");
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
	}

	public void movement()
	{
		System.out.println("Eingabeschema: Zahl/Zahl");
		System.out.println("W�hle eine Figur aus: ");
		Point point;
		Point point2;
		Field origin=null;
		Field destination=null;
		List<Field> possibleMovement;
		
		boolean isPointValid;
		do
		{
			point = extractPoint(getInput());
			isPointValid = isPointValid(point);
			if(!isPointValid){
				System.out.print("Der Punkt ist nicht gültig, bitte nochmal eingeben:");
			}
			origin = board.getField(point.x, point.y);
			possibleMovement = possibleMovement(origin);
			if(possibleMovement.isEmpty()){
				System.out.print("Die Figur kann nicht bewegt werden. Andere Figur auswählen");
				isPointValid = false;
			}
		}
		while (isPointValid == false);
		
		System.out.println("Gebe ein Feld ein wo die Figur hinziehen soll.");
		do
		{
			point2 = extractPoint(getInput());
			isPointValid = isFieldValid(point2);
			if ( isPointValid )
			{
				destination = board.getField(point2.x, point2.y);
				isPointValid = possibleMovement.contains(destination);
			}
			if( !isPointValid ){
				System.out.print("Der Punkt ist nicht g�ltig, bitte nochmal eingeben:");
			}
		}
		while ( !isPointValid );
		
		//Bewege Figur
		destination.setFigure(origin.getFigure());
		origin.setFigure(null);
		
		if(destination.getFigure() instanceof King && destination.isConer()){
			this.end = true;
		}else {
			beatFigures(destination);
			this.isWhitesTurn = !this.isWhitesTurn;
		}
		
		this.printGameBoard();
	}
	
	public String getInput()
	{
		//macht was mit input
		//formatiert input-Werte in String "(Zahl/Zahl) der zurückgegeben wird
		
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
		Field field = board.getField(point.x, point.y);
		if(field == null){
			return false;
		}
		
		Figure figure = field.getFigure();
		if(figure != null){
			if(figure.isWhite == isWhitesTurn){
				return true;
			}
		}
		return false;
	}
	
	public boolean isFieldValid(Point point)
	{
		Field field = this.board.getField(point.x, point.y);
		if(field == null){
			return false;
		}
		
		Figure figure = field.getFigure();
		if(figure != null){
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
	public List<Field> possibleMovement(Field origin){
		Field possibleField;
		List<Field> possibleFields = new ArrayList<>();
		
		
		// X Values
		// 0 is here Field x variable
		for(int i = origin.x-1; i >= 0; i--){
			possibleField = board.getField(i, origin.y);
			if(checkField(origin, possibleField)){
				possibleFields.add(possibleField);				
			}else{
				break;
			}

		}
		for(int i = origin.x+1; i < size; i++){
			possibleField = board.getField(i, origin.y);
			if(checkField(origin, possibleField)){
				possibleFields.add(possibleField);				
			}else{
				break;
			}

		}
		
		// Y Values
		// 0 is here Field y variable
		for(int i = origin.y-1; i >= 0; i--){
			possibleField = board.getField(origin.x, i);
			if(checkField(origin, possibleField)){
				possibleFields.add(possibleField);				
			}else{
				break;
			}
		}
		for(int i = origin.y+1; i < size; i++){
			possibleField = board.getField(origin.x, i);
			if(checkField(origin, possibleField)){
				possibleFields.add(possibleField);				
			}else{
				break;
			}

		}
		return possibleFields;
	}
	
	protected boolean checkField(Field field,Field possibleField) {
		if(field.getFigure() instanceof King){
			return !possibleField.isSet();
		}
		return !(possibleField.type == Field.Types.SPEZIAL || possibleField.isSet());
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
				this.end = true;
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
	
	public Field getMovementFieldOne(){
		return this.currentFieldOne;
	}
	
	public Field getMovementFieldTwo(){
		return this.currentFieldTwo;
	}
	public List<Field> getPossibleMovement(){
		return this.possibleMovement;
	}
	
	public void setMovementFieldOne(String input){
		Point point = extractPoint(input);
		boolean isPointValid = isPointValid(point);
		if(isPointValid){
		    this.currentFieldOne = board.getField(point.x, point.y);
			this.possibleMovement = possibleMovement(this.currentFieldOne);
		}
	}
	
	public void setMovementFieldTwo(String input){
		Point point = extractPoint(getInput());
		boolean isPointValid = isFieldValid(point);
		if ( isPointValid )
		{
			Field destination = board.getField(point.x, point.y);
			if(this.possibleMovement.contains(destination)){
				this.currentFieldTwo  = destination;
			}
		}
	}
	
	public void movementGUI() {
		if(this.currentFieldOne == null || this.currentFieldTwo == null){
			return;
		}
		
		//Bewege Figur
		this.currentFieldTwo.setFigure(this.currentFieldOne.getFigure());
		this.currentFieldOne.setFigure(null);
		
		if(this.currentFieldTwo.getFigure() instanceof King && this.currentFieldTwo.isConer()){
			this.end = true;
		}else {
			beatFigures(this.currentFieldTwo);
			this.isWhitesTurn = !this.isWhitesTurn;
		}
		
		this.currentFieldOne = this.currentFieldTwo = null;
	}
}
