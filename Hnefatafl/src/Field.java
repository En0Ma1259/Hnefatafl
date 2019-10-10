
public class Field {
	public enum Types
			{ 
			    NORMAL, SPEZIAL; 
			} 
	
	protected Types type;
	
	protected Figure object;
	
	// need to be protected
	public int x;
	
	// need to be protected
	public int y;
	
	public Field(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setType(Types type){
		this.type = type; 
	}
	
	public Types getType(){
		return type; 
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
}
