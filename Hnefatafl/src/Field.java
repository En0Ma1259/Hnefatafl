
public class Field {
	public enum Types
			{ 
			    NORMAL, SPEZIAL; 
			} 
	
	protected Types type;
	
	protected Figure object;
	
	protected boolean corner = false;
	
	// need to be protected
	public int x;
	
	// need to be protected
	public int y;
	
	public Field(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Field(int x, int y, boolean corner){
		this.x = x;
		this.y = y;
		this.corner = corner;
	}
	
	public void setType(Types type){
		this.type = type; 
	}
	
	public boolean isSet(){
		return this.object != null;
	}
	
	public void setFigure(Figure figure){
		this.object = figure;
	}
	
	public Types getType(){
		return this.type;
	}
	
	public Figure getFigure(){
		return this.object;
	}
	
	public boolean isConer(){
		return this.corner;
	}
}
