package my.work;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloSevlet extends HttpServlet {

	private static final long serialVersionUID = -401939853433898014L;

	private String message;
	private AtomicInteger atomCount = new AtomicInteger(0);
	private static AtomicInteger atomStaticCount = new AtomicInteger(0);
	private int intCount = 0;
	
	private static Thread th = null; 
	
	
	@Override
	public void init() throws ServletException {
		printCounters("HelloSevlet INIT() method");
		th=Thread.currentThread();
		System.out.println("INIT th="+th.getName());
		// Do required initialization
	      message = "Hello World";
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		message = "<br /> atomCount="+atomCount.incrementAndGet()+
				"<br /> atomStaticCount="+atomStaticCount.incrementAndGet()+
				"<br /> intCount="+(++intCount)+
				"<br /> THREAD="+Thread.currentThread().getName()+
				"<br /> th EQUALS="+th.equals(Thread.currentThread()+
				"<br /> 111 HTTP METHOD="+request.getMethod());

		System.out.println("GET th EQUALS="+th.equals(Thread.currentThread()));
		System.out.println(" 222 HTTP METHOD="+request.getMethod());

		printCounters("HelloSevlet DO_GET() method");
		
		// Set response content type
	      response.setContentType("text/html");

	      // Actual logic goes here.
	      PrintWriter out = response.getWriter();
	      String title = "TestWebServlet";
	      String docType ="<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
	      out.println(docType +
	                "<html>\n<head><title>" + title + "</title></head>\n" +
	                "<body bgcolor=\"#f0f0f0\">\n" +
	                body(request, response) +
	                "</body></html>");
	}
	
	private String body(HttpServletRequest request, HttpServletResponse response) {
		return "<h2>" + message + "</h2>"+
                "<h1 align=\"center\">TestWebServlet</h1>\n" +
                "<ul>\n" +
                "  <li><b>First Name</b>: "
                + request.getParameter("first_name") + "\n" +
                "  <li><b>Last Name</b>: "
                + request.getParameter("last_name") + "\n" +
                "</ul>\n";
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.err.println("HelloSevlet DESTROY() method");
	}

	private void printCounters(String begin) {
		System.err.println(begin+
				"\n atomCount="+atomCount+
				"\n atomStaticCount="+atomStaticCount+
				"\n intCount="+intCount+
				"\nTHREAD="+Thread.currentThread().getName()+
				"\nth EQUALS="+(th==null?"NULL":th.equals(Thread.currentThread()))+
				"\n---------------------------------------------------------");
	}
	
}
