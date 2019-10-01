
public class GameBoard {
	
	protected Field[][] board; 
	
	public GameBoard(int size){
		board = new Field[size][size];
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				board[i][j] = new Field(j,i);
				 
				if((i == 0 && j == 0) || (i == 0 && j == size-1) || (i == size-1 && j == 0) || (i == size-1 && j == size-1) || (i == (size/2) && j == (size/2))){
					board[i][j].setType(Field.Types.SPEZIAL);
				}else{
					board[i][j].setType(Field.Types.NORMAL);	
				}
			}
		}
		setFigures(size);
	}
	
	public Field getField(int x, int y){
		if(y < 0 || board.length < y){
			return null;
		}
		if(x < 0 || board[y].length < x){
			return null;
		}
		
		return board[y][x];
	}
	
	protected void setFigures(int size){
		FigureLayout.t[][] field =FigureLayout.field5x5;
		for(int i=0;i<size;++i){
			for(int j=0;j<size;++j){
				switch(field[i][j]){
					case NONE: board[i][j].setFigure(null); break;
					case WHITE: board[i][j].setFigure(new Figure(true));break;
					case BLACK: board[i][j].setFigure(new Figure(false));break;
				}
			}
		}
		board[size/2][size/2].setFigure(new King());
	}
	
	protected void setFigures(FigureLayout.t[][] boardPlan){
		int size = boardPlan.length;
		for(int i=0;i<size;++i){
			for(int j=0;j<size;++j){
				switch(boardPlan[i][j]){
					case NONE: board[i][j].setFigure(null); break;
					case WHITE: board[i][j].setFigure(new Figure(true));break;
					case BLACK: board[i][j].setFigure(new Figure(false));break;
					//board[size/2][size/2].setFigure(new King());
				}
			}
		}
	}
	
	
}
