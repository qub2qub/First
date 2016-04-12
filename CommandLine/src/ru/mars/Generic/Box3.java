package ru.mars.Generic;
class Box3<E extends Number> {
    private E t;
    public void set(E t) { this.t = t; }
    public E get() { return t; }
    
}