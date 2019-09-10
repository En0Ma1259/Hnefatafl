
public class GameBoard {
	
	protected Field[][] board; 
	
	public GameBoard(int size){
		board = new Field[size][size];
		// fill board here
		// 0,0| 0,size | size,size | size, 0 | middle spezial Field
	}
}
