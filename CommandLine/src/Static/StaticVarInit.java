package Static;

public class StaticVarInit {

	static String stat = "A";
	static String ms = initMS("M1");
	
	public static String initMS(String str){
		return "Parent "+str;
	}
	public static void initStat(String str){
		stat = "Parent "+str;
	}
	
	public static void main(String[] args) {
		System.out.println("1) "+StaticVarInit.stat+", "+StaticVarInit.ms);
		StaticVarInit.initStat("B");
		StaticVarInit.initMS("M2");
		System.out.println("2) "+StaticVarInit.stat+", "+StaticVarInit.ms);
		System.out.println("3) "+StaticVarInit.StaticChild.stat+", "+StaticVarInit.StaticChild.ms);
		StaticVarInit.initStat("C");
		StaticVarInit.initMS("M3");
		System.out.println("4) "+StaticVarInit.StaticChild.stat+", "+StaticVarInit.StaticChild.ms);
		
	}
	
	public static class StaticChild extends StaticVarInit {
		public static String initMS(String str){
			return "Child "+str;
		}
		public static void initStat(String str){
			stat = "Child "+str;
		}
	}
}
