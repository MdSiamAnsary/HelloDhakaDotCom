

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class login extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
          
            MyDB db = new MyDB();
            Connection conn = db.getCon();

            String str1 = "", str2 = "";
            
            Statement stmt1 = conn.createStatement();
            ResultSet rs = stmt1.executeQuery("Select * from UU where NN='" + username + "'");
            while (rs.next()) {
                str1 = rs.getString(1);
                str2 = rs.getString(2);
            }
            
          

            if ((username.length() !=0) && (password.length() != 0)) {
                if (username.equalsIgnoreCase(str1) && password.equalsIgnoreCase(str2)) {
                    response.sendRedirect("welcome.html");
                } else {
                    out.println("Incorrect user name or password");
                    response.setHeader("Refresh", "3; URL=http://localhost:41157/HelloDhakaDotCom/index.html");

                }

            } else {
                out.println("Empty username or password");
                response.setHeader("Refresh", "3; URL=http://localhost:41157/HelloDhakaDotCom/index.html");
            }
            
            
           
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
