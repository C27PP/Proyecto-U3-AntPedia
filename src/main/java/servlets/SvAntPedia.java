package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import models.hormiga;
import java.sql.*;


/**
 *
 * @author Cristian Jesús Cruz Mendívil ID:267652
 */

@WebServlet(name = "SvAntPedia", urlPatterns = {"/SvAntPedia"})
public class SvAntPedia extends HttpServlet {
    
    List<hormiga> listaHormiga = new ArrayList<hormiga>();
    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=AntPedia;encrypt=true;trustServerCertificate=true";
    private final String user = "JavaUser";
    private final String password = "password123";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvAntPedia</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvAntPedia at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException e){
            throw new ServletException("Driver JDBC no encontrado", e);
        }
        
        try(Connection con = (Connection) DriverManager.getConnection(url, user, password) ){
            String sql = "SELECT * FROM Hormigas";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            response.setContentType("text/html;charset=UTF-8");
            listaHormiga.clear();
            while(rs.next()){
                hormiga a = new hormiga(rs.getInt("id"), rs.getString("nombre"), rs.getString("photo"), rs.getString("descrip"));
                listaHormiga.add(a);
            }
            
            request.setAttribute("lista", listaHormiga);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }catch(SQLException e){
            throw new ServletException("Error al conectar con la base de datos");
        }
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException e){
            throw new ServletException("Driver JDBC no encontrado", e);
        }
        
        String nombre = request.getParameter("nombre");
        String photo = request.getParameter("photo");
        String descrip = request.getParameter("descrip");
        
        try(Connection con = (Connection) DriverManager.getConnection(url, user, password)){
            String sql ="INSERT INTO Hormigas(nombre, photo, descrip) Values( ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, photo);
            ps.setString(3, descrip);
            ps.executeUpdate();
            
            doGet(request, response);
            
        }catch(SQLException e){
            throw new ServletException("Error al conectar con la base de datos");
        }
        
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
