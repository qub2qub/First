package my.work;
// Import required java libraries
import java.io.*;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.util.*;

// Implements Filter class
//@WebFilter("/*")
public class LogFilter implements Filter  {
	
	static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
   public void  init(FilterConfig config) 
                         throws ServletException{
      // Get init parameter 
      String testParam = config.getInitParameter("test-param"); 

      //Print the init parameter 
      System.out.println("Test Param: " + testParam); 
   }
   public void  doFilter(ServletRequest request, 
                 ServletResponse response,
                 FilterChain chain) 
                 throws java.io.IOException, ServletException {

      // Get the IP address of client machine.   
      String ipAddress = request.getRemoteAddr();

      // Log the IP address and current timestamp.
      System.out.println("IP "+ ipAddress + ", Time " + SDF.format(new Date()));

      // Pass request back down the filter chain
      chain.doFilter(request,response);
   }
   public void destroy( ){
      /* Called before the Filter instance is removed 
      from service by the web container*/
   }
}