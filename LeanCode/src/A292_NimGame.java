public class A292_NimGame {

    public static boolean calc3(int n, int m) {
        boolean win;
        ++m; // какой сейчас ход
        if (n>3) return calc3(n-1, m) || calc3(n-2, m) || calc3(n-3, m);
        win = ((n >= 1 && n <= 3) && (m % 2 == 1));
        return win;
    }

    public static boolean calc2(int n, int m, int t) {
        boolean win;
        ++m;
        String user = "On";
        if (m % 2 == 0) {
            user = "Ja";
        }
        System.out.println(" -- step="+m+", take from="+n+", "+user+" past took="+t);
        if (n>3) return calc2(n-1, m, 1) || calc2(n-2, m, 2) || calc2(n-3, m, 3);
        win = ((n >= 1 && n <= 3) && (m % 2 == 1));
        return win;
    }
//        System.out.println("1<= N-"+n+" <= 3 + M-Nechet="+(m % 2));

    public static boolean canWinNim(int n) {
        int all = n;
        boolean a1,a2,a3;
        int moves = 0;
        // должен быть мой ход и камней от 1 до 3.
        boolean win1 = (moves%2==0 && all >=1 && all <= 3);
        // брать так, чтобы в конце на его ход осталось 4 камня.
        boolean win2 = (moves%2==1 && all==4);

        return calc(n,0);

//        if (all > 0) {
//            a1 = calc(n - 1, 1);
//            a2 = calc(n - 2, 1);
//            a3 = calc(n - 3, 1);
//            moves++;
//            if (all==4) return true;
//        } else {
//            return false;
//        }
// надо чтобы кол-во ходов было нечётное, т.е. сейчас должен быть его ход и должно остаться 4 камня
// может ли в хоть каком либо 1 раскладе остаться 4 камня и нечётный ход.
    }

//        System.out.print("move = " + m);
//        System.out.println(", all = " + n);
//            System.out.println("<<---------- here ----------- M="+m+", all="+n);
    public static boolean calc(int n, int m) {
        boolean a1 = false, a2 = false, a3 = false;
        if ((n == 4 && m % 2 == 1) || (n>=1 && n<=3 && m % 2 == 0)){
            return true;
        }
        if (n > 0) {
            a1 = calc(n - 1, ++m);
            a2 = calc(n - 2, ++m);
            a3 = calc(n - 3, ++m);
        }
        return a1 || a2 || a3;
    }


    public static void main(String[] args) {
        System.out.println("STARTED");
        int nn = 8;
//        System.out.println(nn+"--> can win --> "+canWinNim(nn));
        System.out.println(nn+"--> can win --> "+calc2(nn,0,0));
        System.out.println("5%2="+(5%2));
        System.out.println("4%2="+(4%2));
//        5%2=1
//        4%2=0
    }
}
