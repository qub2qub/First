package ru.mars.HashEquals;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple example of overriding clone() method in Java to understand How Cloning of
 * Object works in Java.
 *
 * @author
*/
public class JavaCloneTest {
 
   public static void main(String args[]) {

       Rectangle rec = new JavaCloneTest().new Rectangle(30, 60);
       rec.addStr("A");
       System.out.println(rec);
     
       Rectangle copy = null;
       try {
           System.out.println("Creating Copy of this object using Clone method");
           copy = rec.clone();
           System.out.println("Copy " + copy);
         
       } catch (CloneNotSupportedException ex) {
           System.out.println("Cloning is not supported for this object");
       }
     
       //testing properties of object returned by clone method in Java
       System.out.println("copy != rec : " + (copy != rec));
       System.out.println("copy.getClass() == rec.getClass() : " + (copy.getClass() == rec.getClass()));
       System.out.println("copy.equals(rec) : " + copy.equals(rec));
     
       //Updating fields in original object
       rec.setHeight(100);
       rec.setWidth(45);
       rec.addStr("B");
     
       System.out.println("Original object :" + rec);
       System.out.println("Clonned object  :" + copy);
   }

   public class Rectangle implements Cloneable{
	   private int width;
	   private int height;
	   private Integer wid,hei;
	   List<String> str = new ArrayList<>();
	 
	   public Rectangle(int w, int h){
	       width = w;
	       height = h;
	       wid=w;
	       hei=h;
	   }
	   
	   public List<String> addStr(String s) {
		   str.add(s);
		   return str;
	   }
	   
	   public void setHeight(int height) {
	       this.height = height;
	       hei=height;
	   }

	   public void setWidth(int width) {
	       this.width = width;
	       wid=width;
	   }
	 
	   public int area(){
	       return width*height;
	   }
	   public Integer areaObj(){
		   return wid*hei;
	   }
	 
	   @Override
	   public String toString(){
	       return String.format("Rectangle [width: %d, height: %d, area: %d] --- [wid: %d, hei: %d, areaObj: %d] ----- %s", 
	    		   width, height, area(), wid, hei, areaObj(), str);
	   }

	   @Override
	   protected Rectangle clone() throws CloneNotSupportedException {
	       return (Rectangle) super.clone();
	   }

	   @Override
	   public boolean equals(Object obj) {
	       if (obj == null) {
	           return false;
	       }
	       if (getClass() != obj.getClass()) {
	           return false;
	       }
	       final Rectangle other = (Rectangle) obj;
	       if (this.width != other.width) {
	           return false;
	       }
	       if (this.height != other.height) {
	           return false;
	       }
	       return true;
	   }

	   @Override
	   public int hashCode() {
	       int hash = 7;
	       hash = 47 * hash + this.width;
	       hash = 47 * hash + this.height;
	       return hash;
	   }
	}
   
}

