import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Protocollo {

	public static final String addSymbol = "+";
	public static final String deleteSymbol = "-";
	public static final String dividerSymbol = "|";	 
	public static final String concatSymbol = ";";
	public static final String errSymbol = "ERR";

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

	public static List<String> Pedro(String input) {
		List<String> processed = new ArrayList<String>();

		if (input.isEmpty() | input == null) {
			processed.add("ERR");
			processed.add("input vuoto");
		}else {	
			switch(input.substring(0,1)) {
			case(addSymbol) : {
				processed.add(addSymbol);
				break;
			}
			case(deleteSymbol) : {	
				processed.add(deleteSymbol);
				break;
			}
			default:{}
			}
			
			Matcher nome = Pattern.compile("[a-zA-Z]+").matcher(input);
			while (nome.find()) {
				processed.add(nome.group());
			}
			
		}
		return processed;
	}
}
