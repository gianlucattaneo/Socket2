import java.util.ArrayList;
import java.util.List;

public class Protocollo {
	
	public static final String  addSymbol = "+";
	public static final String deleteSymbol = "-";
	public static final String dividerSymbol = "|";	 
	public static final String concatSymbol = ";";
	
 	public static List<String> processInput(String input) {

		List<String> processed = new ArrayList<String>();
		
		int indexDiv = input.indexOf(dividerSymbol);
		
		
		
		switch(input.substring(0,1)) {
		case(addSymbol) : {
			
			processed.add(addSymbol);
			
			if(indexDiv != -1) {
				processed.add(input.substring(1, indexDiv));
				
				String arguments = input.substring(indexDiv+1);
				
				int indexCon = arguments.indexOf(concatSymbol);
				
				while(indexCon != -1) {
					processed.add(arguments.substring(0, indexCon));
					arguments = arguments.substring(indexCon+1);
					indexCon = arguments.indexOf(concatSymbol);
				}
				
				processed.add(arguments); 
				
			}else {
				processed.add(input.substring(1));
			}
			break;
		}
		case(deleteSymbol) : {	
			
			processed.add(deleteSymbol);
			
			if(indexDiv != -1) {
				processed.add(input.substring(1, indexDiv));
				
				String arguments = input.substring(indexDiv+1);
				
				int indexCon = arguments.indexOf(concatSymbol);
				
				while(indexCon != -1) {
					processed.add(arguments.substring(0, indexCon));
					arguments = arguments.substring(indexCon+1);
					indexCon = arguments.indexOf(concatSymbol);
				}
				
				processed.add(arguments);
				
			}else {
				processed.add(input.substring(1));
			}
			break;
		}
		default : {
			
			if(indexDiv != -1) {
				processed.add(input.substring(0, indexDiv));
				processed.add(input.substring(indexDiv+1));
			}else {
				processed.add(input);
			}
		}
		}
		
		return processed;
	}

}
