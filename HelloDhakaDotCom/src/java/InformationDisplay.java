
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class InformationDisplay extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String infoCat = request.getParameter("r1");

        MyDB db = new MyDB();
        Connection conn = db.getCon();

        

        String str1 = "", str2 = "", str3 = "", str4 = "";
        Statement stmt1 = conn.createStatement();
        ResultSet rs = null;
        
        String st0 = null;

        if (infoCat.equalsIgnoreCase("policestations")) {
            st0 = "Police Station";
            rs = stmt1.executeQuery("Select * from PST");
            
        } else if (infoCat.equalsIgnoreCase("hospital")) {
            st0 = "Hospital";
            rs = stmt1.executeQuery("Select * from UHO");
        }
        else if(infoCat.equalsIgnoreCase("hotel"))
        {
            st0 = "Hotel";
            rs = stmt1.executeQuery("Select * from UH");
        }

     
        out.print("<center>");

        out.println("<h2> Information Display </h2>");
        out.print("<table width = 80% border = 1>");

        out.print("<tr>");
        out.print("<td>");
        out.print(st0);
        out.print("</td>");

        out.print("<td>");
        out.print("Address");
        out.print("</td>");
        out.print("</tr>");

        while (rs.next()) {
            out.print("<tr>");
            out.print("<td>");
            str1 = rs.getString(1);
            out.print(str1);
            out.print("</td>");

            out.print("<td>");
            str2 = rs.getString(2);
            out.print(str2);
            out.print("</td>");
            out.print("</tr>");
        }
        out.print("</table> <br> <br>");

        out.println("<form action='http://localhost:41157/HelloDhakaDotCom/index.html'/>");
        out.println("<input type='submit' value='Back' style='bottom'/> </form>");
        out.print("</center>");
            
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(InformationDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(InformationDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
