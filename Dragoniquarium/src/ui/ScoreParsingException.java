package ui;

public class ScoreParsingException extends Exception {

	private static final long serialVersionUID = -4940073990280696865L;
	private int errorType;
	
	public ScoreParsingException(int errorType){
		this.errorType = errorType;
	}
	
	@Override
	public String getMessage(){
		String txt="";
		if (errorType == 0) txt = "No record score";
		else if (errorType == 1) txt = "Wrong record format";
		return txt;
	}
}
