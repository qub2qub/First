package basics;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/context")
public class SrvContext extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        final PrintWriter wr = resp.getWriter();
        resp.setContentType("text/html");
//        wr.print("Servlet Context attributes");

        final ServletContext sc = getServletContext();

        wr.print("<table width=100% border='1px solid black'>");
        wr.print("<tr><td>getServerInfo</td><td>"+sc.getServerInfo()+"</td></tr>");
        /*final Enumeration<String> attributeNames = sc.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            Object name =  attributeNames.nextElement();
            wr.print("<tr><td>"+name+"</td><td>"+sc.getAttribute((String) name)+"</td></tr>");
        }*/
        wr.print("</table>");
    }
}
