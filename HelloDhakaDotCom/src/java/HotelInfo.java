

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HotelInfo extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        MyDB db = new MyDB();
        Connection conn = db.getCon();
        Statement stmt = conn.createStatement();
        Statement stmt1 = conn.createStatement();

        String op = request.getParameter("rd1");

        String st1 = request.getParameter("h1");
        String st2 = request.getParameter("a1");

        if (op.equalsIgnoreCase("i1")) {

            stmt.execute("insert into UH (NN,PS) values ('" + st1 + "','" + st2 + "')");

        } else if (op.equalsIgnoreCase("d1")) {
            stmt.execute("DELETE FROM UH WHERE NN='" + st1 + "' AND PS = '" + st2 + "'");
        }
        
        RequestDispatcher rd=request.getRequestDispatcher("HotelInfoDisplay");  
        rd.include(request, response); 
                
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(HotelInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(HotelInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
