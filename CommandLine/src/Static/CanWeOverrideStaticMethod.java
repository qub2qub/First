package Static;

/**
*
* Java program which demonstrate that we can not override static method in Java.
* Had Static method can be overridden, with Super class type and sub class object
* static method from sub class would be called in our example, which is not the case.
* @author
*/
public class CanWeOverrideStaticMethod {

   public static void main(String args[]) {
    
       Screen scrn = new ColorScreen();
       ColorScreen scrn2 = new ColorScreen();
    
       //if we can  override static , this should call method from Child class
     //IDE will show warning, static method should be called from classname
       scrn.show();
       scrn2.show(); 
    
   } 

}

class Screen{

   /*
    * public static method which can not be overridden in Java
    */
   public static void show(){
       System.out.println("Static method from parent class");
   }
}

class ColorScreen extends Screen{
   /*
    * static method of same name and method signature as existed in super
    * class, this is not method overriding instead this is called
    * method hiding in Java
    */
   public static void show(){
       System.out.println("Overridden static method in Child Class in Java");
   }
}