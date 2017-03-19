package Inheritance;


public interface Interface1 {

    String S1="Constant1";
    int m1();

    static int sm2() {   return 1;  };

    default void default1() {
        System.out.println("int1");
    }

}
