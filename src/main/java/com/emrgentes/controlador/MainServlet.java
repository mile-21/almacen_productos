package com.emrgentes.controlador;
import com.emrgentes.conexionDB;
import com.emrgentes.producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
        
        try{ 
            String op;
           op = (request.getParameter("op") != null) ? request.getParameter("op"):"list";
                 ArrayList<producto> lista = new ArrayList<producto>(); 
           conexionDB canal = new conexionDB();
           Connection conn = canal.conectar();
           PreparedStatement ps;
      
       ResultSet rs;
            if (op.equals("list")) {
 //Para listar los datos
             String sql = "select * from productos";
 //Consulta de seleccion y almacenarlo en una coleccion
                  ps = conn.prepareStatement(sql);
                  rs = ps.executeQuery();
                  
      while(rs.next()){
            producto pro = new producto();
            pro.setId(rs.getInt("id"));
            pro.setProducto(rs.getString("productos"));
            pro.setPrecio(rs.getFloat("precio"));
            pro.setCantidad(rs.getInt("cantidad"));
            lista.add(pro); 
 }
       request.setAttribute("lista", lista);
 //Enviar al index.jsp para mostrar la informacion
       request.getRequestDispatcher("index.jsp").forward(request, response);
 }
       if (op.equals("nuevo")) {
 //Instanciar un objeto de la clase Libro
        producto pr = new producto();

        System.out.println(pr.toString());

 //El objetivo se pone como atributo de request
          request.setAttribute("pro", pr);
 //Redireccionar a editar.jsp
         request.getRequestDispatcher("editar.jsp").forward(request, response);
 }
         if (op.equals("eliminar")) {
 //Obtener el id
         int id = Integer.parseInt(request.getParameter("id"));
 //Realizar la eliminacion en la base de datos
         String sql = "delete from productos where id = ?";
             ps = conn.prepareStatement(sql);
             ps.setInt(1, id);
             ps.executeUpdate();
 //Redireccionar a MainController
         response.sendRedirect("MainServlet");
 }
         if (op.equals("editar")) {
 //Obtener el id
         int id = Integer.parseInt(request.getParameter("id"));
  try{
         producto pro1 = new producto();

         ps = conn.prepareStatement("select * from productos where id = ?");
         ps.setInt(1, id);
         rs = ps.executeQuery();
 if (rs.next()) {
     
         pro1.setId(rs.getInt("id"));
         pro1.setProducto(rs.getString("productos"));
         pro1.setPrecio(rs.getFloat("precio"));
         pro1.setCantidad(rs.getInt("cantidad"));
 }
     request.setAttribute("pro", pro1);
     request.getRequestDispatcher("editar.jsp").forward(request, response);
     }catch(SQLException ex){
     Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null,
     ex);
   }
 }
     } catch(SQLException ex){
       System.out.println("ERROR al ConeCtar" +ex.getMessage());
   }
 }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
           int id = Integer.parseInt(request.getParameter("id"));
           System.out.println("Valor de ID" + id);
           String producto = request.getParameter("producto");
           Float precio = Float.parseFloat(request.getParameter("precio"));
           int cantidad = Integer.parseInt(request.getParameter("cantidad"));

          producto pro = new producto();
          pro.setId(id);
          pro.setProducto(producto);
          pro.setPrecio(precio);
          pro.setCantidad(cantidad);

   conexionDB canal = new conexionDB();
   Connection conn = canal.conectar();
   PreparedStatement ps;
   ResultSet rs;

         if (id == 0) {
 //Nuevo Resgistro
 String sql = "insert into productos (productos, precio, cantidad) values (?,?,?)";
          ps = conn.prepareStatement(sql);
          ps.setString(1, pro.getProducto());
          ps.setFloat(2, pro.getPrecio());
          ps.setInt(3, pro.getCantidad());
          ps.executeUpdate();
 }else{
 //Edicion de Registro
      String sql1 = "update productos set productos=?, precio=?, cantidad=? where id=?";
 try {

        ps = conn.prepareStatement(sql1);
        ps.setString(1, pro.getProducto());
        ps.setFloat(2, pro.getPrecio());
        ps.setInt(3, pro.getCantidad());
        ps.setInt(4, pro.getId());

        ps.executeUpdate();

     }catch(SQLException ex){
     Logger.getLogger(MainServlet.class.getName()).log(Level.SEVERE, null,
     ex);
  }
 }
      response.sendRedirect("MainServlet");
      } catch (SQLException ex) {
      System.out.println("Error en SQL " + ex.getMessage());
      
    }
 }
}


