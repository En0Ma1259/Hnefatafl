
public class GameBoard {
	
	protected Field[][] board; 
	
	public GameBoard(int size){
		board = new Field[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				board[i][j] = new Field(i,j);
				 
				if((i == 0 && j == 0) || (i == 0 && j == size-1) || (i == size-1 && j == 0) || (i == size-1 && j == size-1) || (i == (size/2) && j == (size/2))){
					board[i][j].setType(Field.Types.SPEZIAL);
				}else{
					board[i][j].setType(Field.Types.NORMAL);	
				}
			}
		}
		// fill board here
		// 0,0| 0,size | size,size | size, 0 | middle spezial Field
	}
}
