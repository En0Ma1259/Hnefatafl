
public class FigureLayout {
	public enum t{
		NONE, WHITE, BLACK, KING;
	}
	
	public static t[][] field5x5=new t[][]{
		{t.NONE,t.BLACK,t.BLACK,t.BLACK,t.NONE},
		{t.BLACK,t.NONE,t.WHITE,t.NONE,t.BLACK},
		{t.BLACK,t.WHITE,t.KING,t.WHITE,t.BLACK},
		{t.BLACK,t.NONE,t.WHITE,t.NONE,t.BLACK},
		{t.NONE,t.BLACK,t.BLACK,t.BLACK,t.NONE}
	};
	
	public static t[][] fieldNormal=new t[][]{
		{t.NONE, t.NONE,t.NONE, t.BLACK,t.BLACK,t.BLACK,t.BLACK,t.BLACK,t.NONE,t.NONE,t.NONE},
		{t.NONE,t.NONE,t.NONE,t.NONE,t.NONE,t.BLACK,t.NONE,t.NONE,t.NONE,t.NONE,t.NONE},
		{t.NONE,t.NONE,t.NONE,t.NONE,t.NONE,t.NONE,t.NONE,t.NONE,t.NONE,t.NONE,t.NONE},
		{t.BLACK,t.NONE,t.NONE,t.NONE,t.NONE,t.WHITE,t.NONE,t.NONE,t.NONE,t.NONE,t.BLACK},
		{t.BLACK,t.NONE,t.NONE,t.NONE,t.WHITE,t.WHITE,t.WHITE,t.NONE,t.NONE,t.NONE,t.BLACK},
		{t.BLACK,t.BLACK,t.NONE,t.WHITE,t.WHITE,t.KING,t.WHITE,t.WHITE,t.NONE,t.BLACK,t.BLACK},
		{t.BLACK,t.NONE,t.NONE,t.NONE,t.WHITE,t.WHITE,t.WHITE,t.NONE,t.NONE,t.NONE,t.BLACK},
		{t.BLACK,t.NONE,t.NONE,t.NONE,t.NONE,t.WHITE,t.NONE,t.NONE,t.NONE,t.NONE,t.BLACK},
		{t.NONE,t.NONE,t.NONE,t.NONE,t.NONE,t.NONE,t.NONE,t.NONE,t.NONE,t.NONE,t.NONE},
		{t.NONE,t.NONE,t.NONE,t.NONE,t.NONE,t.BLACK,t.NONE,t.NONE,t.NONE,t.NONE,t.NONE},
		{t.NONE, t.NONE,t.NONE, t.BLACK,t.BLACK,t.BLACK,t.BLACK,t.BLACK,t.NONE,t.NONE,t.NONE}
		
	};
}
