package ru.mars.MethodParameters;

import ru.mars.Inheritance.Interface1;
import ru.mars.Inheritance.Interface2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Class2Int implements Interface1, Interface2 {

//    abstract void m2();
    // заново задеклаировать default метод, но уже как абстрактный метод.
    public abstract void default1();

    public static void main(String[] args) {
//        Class2Int c = new Class2Int();
//        c.m1();
        ArrayList<Integer> arr = new ArrayList<>(10);
        LinkedList<Integer> ll = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            arr.add(i);
            ll.add(i);
        }
        arr.remove(5);
//        arr.remove
//        ll.remove
        for (Integer integer : arr) {
            System.out.println(integer);
        }
        System.out.println("-------------------");
        System.out.println(arr.get(4));
        System.out.println(arr.get(5));
        System.out.println(arr.get(6));
    }
}
