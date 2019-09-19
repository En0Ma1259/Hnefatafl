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
	
	/**
	 * Gernerate game board (size x size).
	 * 
	 * @param size
	 */
	public void generateBoard(int size){
		this.size = size;
		board = new GameBoard(size);
	}
	
	public void printGameBoard() {

		System.out.println("");
		for(int i=0;i<size;++i){
			for(int j=0;j<size;++j){
				if( board.getField(i,j).getFigure()!=null ) {
					if(board.getField(i,j).getFigure() instanceof King) {
						System.out.println("K\t");
					}
					else { //instanceof != King
						if( board.getField(i,j).getFigure().isWhite==true ) {
							System.out.print("W\t");	
						}
						else //isWhite==false
						{
							System.out.print("S\t");	
						}
					}
				}
				else {
					System.out.print("0\t");	
				}
			}
			System.out.print("\n");
		}
		System.out.println("");
	}
	
	public void movement(){
		Field selectedField = this.selectFigure();
		List<Field> possibleFields = this.possibleMovement(selectedField);
		
		if(possibleFields.isEmpty()){
		}
		
		this.destination();
		
	}
	
	protected Field selectFigure(){
		Scanner in = new Scanner(System.in);
		
		System.out.println("Turn: " + turn);
		System.out.println("X: ");
        int x = Integer.parseInt(in.nextLine());
        System.out.println("Y: ");
    	int y = Integer.parseInt(in.nextLine());
    	in.close();
    	
    	return board.getField(x, y);
	}
	
	protected void destination() {
		// 2 User inputs for x and y
		// Wert X:
		// Wert Y:
		// return Field;
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
		
		// Y Valeus
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
