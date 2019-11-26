
public class GameBoard {
	
	protected Field[][] board; 
	
	protected int size;
	
	public GameBoard(int size){
		this.size = size;
		board = new Field[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				
				board[i][j] = new Field(j,i, isCorner(j, i));
				 
				if((i == 0 && j == 0) || (i == 0 && j == size-1) || (i == size-1 && j == 0) || (i == size-1 && j == size-1) || (i == (size/2) && j == (size/2))){
					board[i][j].setType(Field.Types.SPEZIAL);
				}else{
					board[i][j].setType(Field.Types.NORMAL);	
				}
			}
		}
	}
	
	protected void setFigures(FigureLayout.t[][] boardPlan){
		int size = boardPlan.length;
		for(int i=0;i<size;++i){
			for(int j=0;j<size;++j){
				switch(boardPlan[i][j]){
					case NONE: board[i][j].setFigure(null); break;
					case WHITE: board[i][j].setFigure(new Figure(true));break;
					case BLACK: board[i][j].setFigure(new Figure(false));break;
					case KING: board[i][j].setFigure(new King());break;
				}
			}
		}
	}
	
	public Field getField(int x, int y){
		if(y < 0 || board.length <= y){
			return null;
		}
		if(x < 0 || board[y].length <= x){
			return null;
		}
		return board[y][x];
	}
	
	protected boolean isCorner(int x, int y) {
		if(x == 0 && y == 0 || x == 0 && y == this.size - 1 || x == this.size - 1 && y == 0 || x == this.size - 1 && y == this.size - 1){
			return true;
		}
		
		return false;
	}
}
