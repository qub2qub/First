package Inheritance;

public interface Interface2 extends Interface1, Interface3{

    default int m1() {
        System.out.println("int2");
        return 2;
    }

    default void m2() {

    }
}
