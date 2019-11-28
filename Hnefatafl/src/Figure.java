import java.util.ArrayList;
import java.util.List;

public class Figure {
	
	public boolean isWhite;
	
	protected List<Field> possibleFields = new ArrayList<>();
	
	protected boolean beaten(){
		return false;
	}
	
	public Figure (boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	public void setPossibleFields(List<Field> possibleFields){
		this.possibleFields = possibleFields;
	}
	
	public List<Field> getPossibleFields(){
		return this.possibleFields;
	}
}
