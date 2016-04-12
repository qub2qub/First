public class AddDigits {
	
	public static int addDigits(int num) {
		String str = ""+num;
		if (str.length() == 1) {
			return num;
		}
		int res = 0;
		for (int i = 0; i < str.length(); i++) {
			res+=Integer.parseInt(String.valueOf(str.charAt(i)));
		}
		System.out.println("res="+res);
		return addDigits(res);
	}
	
	public static void main(String[] args) {
		System.out.println(" FIN = " + addDigits(1234567991));
	}
}
