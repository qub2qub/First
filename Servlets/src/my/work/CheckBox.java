package my.work;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckBox")
public class CheckBox extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Set response content type
	      response.setContentType("text/html");

	      PrintWriter out = response.getWriter();
		  String title = "Reading Checkbox Data";
	      out.println(print3checkboxes(request, title));
//	      out.println(printAllParams(request, title));
	  }

	private String printAllParams(HttpServletRequest request, String title) {
		StringBuffer sb =  new StringBuffer(
				"<!doctype html public \"-//w3c//dtd html 4.0 " +
				"transitional//en\">\n");
		sb.append(
		        "<html>\n" +
		        "<head><title>" + title + "</title></head>\n" +
		        "<body bgcolor=\"#f0f0f0\">\n" +
		        "<h1 align=\"center\">" + title + "</h1>\n" +
		        "<table width=\"100%\" border=\"1\" align=\"center\">\n" +
		        "<tr bgcolor=\"#949494\">\n" +
		        "<th>Param Name</th><th>Param Value(s)</th>\n"+
		        "</tr>\n");

		      Enumeration<String> paramNames = request.getParameterNames();
		      
		      while(paramNames.hasMoreElements()) {
		         String paramName = paramNames.nextElement();
		         sb.append("<tr><td>" + paramName + "</td>\n<td>");
		         String[] paramValues =
		                request.getParameterValues(paramName);
		         // Read single valued data
		         if (paramValues.length == 1) {
		           String paramValue = paramValues[0];
		           if (paramValue.length() == 0)
		             sb.append("<i>No Value</i>");
		           else
		             sb.append(paramValue);
		         } else {
		             // Read multiple valued data
		             sb.append("<ul>");
		             for(int i=0; i < paramValues.length; i++) {
		                sb.append("<li>" + paramValues[i]);
		             }
		             sb.append("</ul>");
		         }
		      }
		      sb.append("</tr>\n</table>\n</body></html>");
		return sb.toString();
	}
	private String print3checkboxes(HttpServletRequest request, String title) {
		return "<!doctype html public \"-//w3c//dtd html 4.0 " +
			      "transitional//en\">\n" +
	                "<html>\n" +
	                "<head><title>" + title + "</title></head>\n" +
	                "<body bgcolor=\"#f0f0f0\">\n" +
	                "<h1 align=\"center\">" + title + "</h1>\n" +
	                "<ul>\n" +
	                "  <li><b>Maths Flag : </b>: "
	                + request.getParameter("maths") + "\n" +
	                "  <li><b>Physics Flag: </b>: "
	                + request.getParameter("physics") + "\n" +
	                "  <li><b>Chemistry Flag: </b>: "
	                + request.getParameter("chemistry") + "\n" +
	                "</ul>\n" +
	                "</body></html>";
	}

	  // Method to handle POST method request.
	  public void doPost(HttpServletRequest request,
	                     HttpServletResponse response)
	      throws ServletException, IOException {
	     doGet(request, response);
	  }
}
