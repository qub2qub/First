package Concurrency;

/**
 * Java Program to demonstrate how to use Thread in Java with Example
 * Here two threads are provided Runnable interface implementation using
 * anonymous class and when started they will print Thread's name.
 * @author
 */
public class ThreadExample {

    public static void main(String args[]){
       
        //two threads in Java which runs in Parallel
        Thread threadA = new Thread(new Runnable(){
            public void run(){
                for(int i =0; i<2; i++){
                    System.out.println("This is thread : " + Thread.currentThread().getName());
                }
            }
        }, "Thread A");
       
        //Runnable interface is implemented using Anonymous Class
        Thread threadB = new Thread(new Runnable(){
            public void run(){
                for(int i =0; i<3; i++){
                    System.out.println("This is thread : " + Thread.currentThread().getName());
                }
            }
        }, "Thread B");
       
        //starting both Thread in Java
        threadA.start(); //start will call run method in new thread
        threadB.start();
       
    }  

}