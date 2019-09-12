
public class Field {
	public enum Types
			{ 
			    NORMAL, SPEZIAL; 
			} 
	
	protected Types type;
	
	protected Figure object;
	
	protected int x;
	
	protected int y;
	
	public Field(int x, int y){
		this.x = y;
		this.y = y;
	}
	
	public void setType(Types type){
		this.type = type; 
	}
}
