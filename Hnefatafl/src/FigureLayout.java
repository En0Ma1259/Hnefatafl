
public class FigureLayout {
	public enum type{
		NONE, WHITE, BLACK;
	}
	
	public static type[][] field5x5=new type[][]{
		{type.NONE,type.BLACK,type.BLACK,type.BLACK,type.NONE},
		{type.BLACK,type.NONE,type.WHITE,type.NONE,type.BLACK},
		{type.BLACK,type.WHITE,type.NONE,type.WHITE,type.BLACK},
		{type.BLACK,type.NONE,type.WHITE,type.NONE,type.BLACK},
		{type.NONE,type.BLACK,type.BLACK,type.BLACK,type.NONE}
	};
}
