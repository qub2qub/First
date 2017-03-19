public class A258_AddDigits {

    /**
     * КАКАЯ СЛОЖНОСТЬ У ЭТОГО АЛГОРИТМА?
     */
    public static int addDigits(int num) {
        String s = "" + num;
        if (s.length() == 1) {
            return num;
        }
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
//            res += s.charAt(i) - 48;
            res += s.charAt(i) - '0';
//            999 = 100*9 + 10*9 + 1*9...

        }
        return addDigits(res);
    }

    /*
    https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html
    '5' has the int value 53
    if we write '5'-'0' it evaluates to 53-48, or the int 5
    if we write char c = 'B'+32; then c stores 'b'
     */

    public static int[] addDigits2(int num, int m) {
        ++m;
        String s = "" + num;
        if (s.length() == 1) {
            return new int[] {num, m};
        }
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += s.charAt(i) - '0';
        }
        return addDigits2(res, m);
    }

    public static void main(String[] args) {
//        int base = 999999999; //9
        int base = 987659879; //9
        int[] res = addDigits2(base, 0);
        System.out.println("moves="+res[1]+", res="+res[0]);
    }
}
