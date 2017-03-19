package HashEquals;

public class CovariantReturn {

	public static void main(String[] args) {
		CovariantReturn cr = new CovariantReturn();
		Alpha a = cr.new Alpha('A');
		Alpha a2 = a.doStuff('W');
		System.out.println("a2="+a2);
		a.print();
		
		Beta b = cr.new Beta();
		Beta b2 = b.doStuff('B');
		System.out.println("b2="+b2);
		b.print();
	}
	
	public class Alpha {
		char name = '-';
		
	    public Alpha() {
			super();
		}

		public Alpha(char name) {
			this.name = name;
		}

		Alpha doStuff(char c) {
	        return new Alpha(c);
	    }
		
		protected void print() {
			System.out.println("protected Print in Alfa");
		}
	}

	public class Beta extends Alpha {
		@Override
		public void print() {
			System.out.println("public Print in Alfa");
		}
		@Override
	    Beta doStuff(char c) { // legal override in Java 1.5
	    	System.out.println("DO - " + c);
	        return new Beta();
	    }
	}
	
}
