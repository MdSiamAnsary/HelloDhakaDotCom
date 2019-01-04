
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


public class AdminInfo extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String str1 = null, str2 = null, str3 = null, str4 = null;
        MyDB db = new MyDB();
        Connection conn = db.getCon();
        Statement stmt = conn.createStatement();
        Statement stmt1 = conn.createStatement();

        String op = request.getParameter("rd0");

        String st1 = request.getParameter("h0");
        String st2 = request.getParameter("a0");
        
        System.out.println(st1 + "    "+ st2);

        if (op.equalsIgnoreCase("i0")) {

            stmt.execute("insert into UU (NN,PS) values ('" + st1 + "','" + st2 + "')");

        } else if (op.equalsIgnoreCase("d0")) {
            stmt.execute("DELETE FROM UU WHERE NN='" + st1 + "' AND PS = '" + st2 + "'");
        }
        
        RequestDispatcher rd=request.getRequestDispatcher("AdminInfoDisplay");  
        rd.forward(request, response); 

        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
