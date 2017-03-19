package Exceptions;

public class CatchFinally {
	
	int a = 1;
	int b = 2;
	
	public int sum(String str) {
		int c=3; 
		try {
			c = (Integer.valueOf(str));
		} catch (Exception e) {
			System.out.println("in catch");
			return (a+b);
		} finally {
			System.out.println("in fnally");
			return (a+b+c);
		}
//		return 333;
	}
	
	public static int proveIt()
    {
    	try {
    		System.out.println("in try set return 1");
            	return 1;  
    	}  
    	finally {  
    	    System.out.println("finally block is run before method returns.");
    	}
    }

	public static void main(String[] args) {
		CatchFinally cf = new CatchFinally();
//		String str = "a";
		System.out.println("SUM="+cf.sum("10"));
		System.out.println("SUM="+cf.sum("A"));
		System.out.println("Prove="+cf.proveIt());
	}

}
