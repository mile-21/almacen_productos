
package com.emrgentes;

   import java.sql.Connection;
   import java.sql.DriverManager;
   import java.sql.SQLException;
   import java.util.logging.Level;
   import java.util.logging.Logger;

public class conexionDB {
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/db_almacen";
    static String usuario = "root";
     static String password = "";

 Connection conn = null;
    
     public conexionDB(){
      try{
 //Especificacion del Driver
           Class.forName(driver);
 //Estable la conexion a la base de datos
          conn = DriverManager.getConnection(url, usuario, password);
 //Verificar si la conexion fue exitosa
        if (conn != null) {
        System.out.println("Conexion OK" + conn);
    }
 } catch (SQLException ex){
   System.out.println("Error de SQL "+ ex.getMessage());

 } catch (ClassNotFoundException ex){
   Logger.getLogger(conexionDB.class.getName()).log(Level.SEVERE, null, ex);
   }
 } 
     
    public Connection conectar(){
    return conn;
 }
     public void desconectar(){
       try{
   conn.close();
     }catch (SQLException ex){
     Logger.getLogger(conexionDB.class.getName()).log(Level.SEVERE, null, ex);
  }
 }

}
