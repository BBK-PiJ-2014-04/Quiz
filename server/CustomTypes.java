package server;

public class CustomTypes {
	/**
	 * 
	 * 
	 * @author SalvatoreCardali
	 *
	 */
	
	public enum Status {
		Opened, Completed
	}
	
	/**
	 * Tells if a string is an Integer
	 * 
	 * @param s
	 * @param radix
	 * @return
	 */
	//Thanks StackOverflow
	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
}
