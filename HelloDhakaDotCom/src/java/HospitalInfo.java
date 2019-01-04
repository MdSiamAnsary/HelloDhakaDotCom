
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
import javax.servlet.http.HttpSession;


public class HospitalInfo extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String str1 = null, str2 = null, str3 = null, str4 = null;
        MyDB db = new MyDB();
        Connection conn = db.getCon();
        Statement stmt = conn.createStatement();
        Statement stmt1 = conn.createStatement();
        
        HttpSession session = request.getSession();
        String user = (String)session.getAttribute("username");

        String op = request.getParameter("rd2");

        String st1 = request.getParameter("h2");
        String st2 = request.getParameter("a2");

        if (op.equalsIgnoreCase("i2")) {

            stmt.execute("insert into UHO (NN,PS) values ('" + st1 + "','" + st2 + "')");

        } else if (op.equalsIgnoreCase("d2")) {
            stmt.execute("DELETE FROM UHO WHERE NN='" + st1 + "' AND PS = '" + st2 + "'");
        }

        ResultSet rs = stmt1.executeQuery("Select * from UHO");
        ResultSetMetaData rsmd = rs.getMetaData();
        out.print("<center>");
        
         out.println("<h2>Hello "+user+"</h2>");

        out.println("<h2> Information Display </h2>");
        out.print("<table width = 80% border = 1>");

        out.print("<tr>");
        out.print("<td>");
        out.print("Hospital");
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

        out.println("<form action='http://localhost:41157/HelloDhakaDotCom/welcome.html'/>");
        out.println("<input type='submit' value='Back' style='bottom'/> </form>");
        out.print("</center>");
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(HospitalInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(HospitalInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
