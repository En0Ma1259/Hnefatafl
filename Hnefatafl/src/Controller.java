
public class Controller {
	
	/**
	 * false = black, true = white
	 */
	protected boolean turn = false;
	
	/**
	 * Current round.
	 */
	protected int round = 0;
	
	/**
	 * Gernerate game board (size x size).
	 * 
	 * @param size
	 */
	public void generateBoard(int size){
		
	}
	
	public void movement(){
		Field selectedField = this.selectFigure();
		// possibleFields = 
		this.destination();
		
	}
	
	protected Field selectFigure(){
		// 2 User inputs for x and y
		// Wert X:
		// Wert Y:
		return new Field(0,0);
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
	public void possibleMovement(Field field){
		// Object ist auf 3
		// field[0][X] horizontal
		// field[0][0] = 1
		// field[0][1] = 2
		// field[0][2] = 4
		// field[0][2] = 5
		// field[1][X] vertical
	}
}
