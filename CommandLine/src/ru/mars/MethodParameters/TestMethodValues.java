package ru.mars.MethodParameters;

import java.util.Collection;
import java.util.Collections;

public class TestMethodValues {

    // change parameter in passMethod()
    public static void passMethod(int p) {
        p = 10;
    }


    public static void changeKeyValue(PairString ps, String key, String value) {
        ps.setKey(key);
        ps.setValue(value);
        // но если заасанить ps новый объет - по выходу из метода он пропадёт.
        ps = new PairString("k3", "v3");
        System.out.println("ps in method = " + ps);
        Collection c;

    }

    public static void main(String[] args) {

        PairString ps = new PairString("k1", "v1");
        System.out.println("ps1 = " + ps);
        changeKeyValue(ps, "k2", "v2");
        System.out.println("ps2 = " + ps);

        // ****************************************
        int x = 3;
        // invoke passMethod() with x as argument
        passMethod(x);
        // print x to see if its value has changed
        System.out.println("After invoking passMethod, x = " + x);

        PairString ps2 = new PairString("k1888", "v1777") {
            public  void m3() {
                System.out.println("M3 in Anonymous");
            }
        };
//    ps2.m3();
    }
}
