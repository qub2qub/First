package old;

import Inheritance.Interface1;
import Inheritance.Interface2;
import Inheritance.Interface3;

public interface GroupedInterface extends Interface1, Interface2, Interface3 {

    // constant declarations

    // base of natural logarithms
    double E = 2.718282;

    // method signatures
    void doSomething (int i, double x);
    int doSomethingElse(String s);
}