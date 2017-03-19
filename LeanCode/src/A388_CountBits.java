public class A388_CountBits {

    // надо подсчитать количестово единиц в двоичном
    // представлении всех цифр, которые 0 ? i ? num
    public int[] countBits1(int num) {
        int[] res = new int[num+1];
        System.out.println("res.length = " + res.length);
        for (int i = 0 ; i<=num; i++) {
            res[i]=Integer.bitCount(i);
            System.out.println(i+" = " + res[i]);
        }
        return res;
    }
    public void hex2bin() {
        int a5 = 0x55555555;
        int a3 = 0x33333333;
        int a0f = 0x0f0f0f0f;
        int a3f = 0x3f;
        System.out.println("0x55555555 = \t" + Integer.toBinaryString(a5));
        System.out.println("0x33333333 = \t" + Integer.toBinaryString(a3));
        System.out.println("0x0f0f0f0f = \t\t" + Integer.toBinaryString(a0f));
        System.out.println("0x3f = \t\t\t\t" + Integer.toBinaryString(a3f));
    }

    public void shiftBits(int num) {
        System.out.println("i >>> 1" + Integer.toBinaryString(num >>> 1) );
        System.out.println("i >>> 2" + Integer.toBinaryString(num >>> 2) );
        System.out.println("i >>> 4" + Integer.toBinaryString(num >>> 4) );
        System.out.println("i >>> 8" + Integer.toBinaryString(num >>> 8) );
        System.out.println("i >>> 16" + Integer.toBinaryString(num >>> 16) );
    }

    public int[] countBits3(int num) {
        int[] res = new int[num+1];

        return res;
    }


    public static void main(String[] args) {
        A388_CountBits c = new A388_CountBits();
//        c.countBits1(5);
//        c.hex2bin();
        for (int i = 0 ; i<=5; i++) {
            System.out.println("_______"+i+"_________");
            c.shiftBits(i);
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^");
        }
    }

}
