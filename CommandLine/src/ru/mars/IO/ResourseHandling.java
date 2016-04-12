package ru.mars.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResourseHandling {

	public static void main(String[] args) {
		 try (
				 FileInputStream stockQuoteReader = new FileInputStream("StockQuotes1.txt");
				 FileOutputStream stockQuoteWriter = new FileOutputStream("StockQuotes2.txt")
				 ) {
				       int var;
				       while((var= stockQuoteReader.read()) != -1 )
				             stockQuoteWriter.write(new byte[12]);
				   } catch (/*FileNotFoundException |*/ IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

}
