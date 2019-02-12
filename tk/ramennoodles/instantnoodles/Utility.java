package tk.ramennoodles.instantnoodles;

public class Utility {
	public static String argsToString(String[] args) {
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < args.length; i++){
		sb.append(args[i]).append(" ");
		}
		 
		String allArgs = sb.toString().trim();
		
		return allArgs;
	}
}
