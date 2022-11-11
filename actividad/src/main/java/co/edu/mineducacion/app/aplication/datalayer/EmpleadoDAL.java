/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.mineducacion.app.aplication.datalayer;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import co.edu.mineducacion.app.aplication.modellayer.Empleado;
import co.edu.mineducacion.app.dataresource.ConexionDB;
/**
 *
 * @author maest
 */
public class EmpleadoDAL implements Serializable{
    
    static private EmpleadoDAL Instance = null;
    private List<Empleado> LstEmpleado = new ArrayList<Empleado>();
    
    private EmpleadoDAL(){
    }
    
    static synchronized public EmpleadoDAL getInstance() {
        if (Instance == null) {
            Instance = new EmpleadoDAL();
        }
        return Instance;
    }
    
     public boolean CreateEmpleado(Empleado obj) throws IOException, SQLException {
         
        ConexionDB conexion = new ConexionDB();
        boolean Insertado = false;
        try{
            conexion.connectMySql();
            conexion.SQLClear();
            conexion.SQL.append("INSERT INTO empleado(nombre,email,telefono)");
            conexion.SQL.append("VALUES(?,?,?)");
                        
            //Validamos Atributo
            if(((obj.getNombre()!=null?obj.getNombre():"").length()>0)){
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "STRING|"+ obj.getNombre().toUpperCase() +"");
            }else{
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "NULO");
            }
            //Validamos Atributo
            if(((obj.getEmail()!=null?obj.getEmail():"").length()>0)){
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "STRING|"+ obj.getEmail().toLowerCase() +"");
            }else{
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "NULO");
            }            
            //Validamos Atributo
            if(((obj.getTelefono()!=null?obj.getTelefono():"").length()>0)){
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "STRING|"+ obj.getTelefono().toLowerCase() +"");
            }else{
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "NULO");
            }
            
            //ACA EJECUTAMOS LA SENTENCIA
            if(conexion.executeSQLUpdate(conexion.Parametros) > 0){
                Insertado = true;
            }
            
        }catch (Exception ex) {
            //log.error(ex.getMessage(),ex);
            throw new SQLException ("ERROR:" + ex.getMessage());
        }finally{
            conexion.desconectar();  
        }
        return Insertado;
         
     }
    
     public boolean UpdateEmpleado(Empleado obj) throws IOException, SQLException, Exception {
        
        ConexionDB conexion = new ConexionDB();
        boolean Actualizado = false;          
        try{
            
            conexion.connectMySql();
            conexion.SQLClear();
            conexion.SQL.append("UPDATE empleado SET nombre=?, email=?, telefono=? WHERE idempleado=? ");
            
            if(((obj.getNombre()!=null?obj.getNombre():"").length()>0)){
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "STRING|"+ obj.getNombre().toUpperCase() +"");
            }else{
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "NULO");
            }
            //Validamos Atributo
            if(((obj.getEmail()!=null?obj.getEmail():"").length()>0)){
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "STRING|"+ obj.getEmail().toLowerCase() +"");
            }else{
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "NULO");
            }
            
             //Validamos Atributo
            if(((obj.getTelefono()!=null?obj.getTelefono():"").length()>0)){
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "STRING|"+ obj.getTelefono().toLowerCase() +"");
            }else{
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "NULO");
            }
            
            if((obj.getIdempleado()>0)){
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "INT|"+ obj.getIdempleado() +"");
            }else{
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "INT|-1");
            }
            
            //ACA EJECUTAMOS LA SENTENCIA
            if(conexion.executeSQLUpdate(conexion.Parametros) > 0){
                Actualizado = true;
            }
            
          }catch (Exception ex) {
            //log.error(ex.getMessage(),ex);
            throw new SQLException ("ERROR:" + ex.getMessage());
        }finally{
            conexion.desconectar();  
        }
        return Actualizado;
         
     }
     
     public boolean DeleteEmpleado(Empleado obj) throws IOException, SQLException, Exception {
        
        ConexionDB conexion = new ConexionDB();
        boolean Eliminado = false;         
        try{
            
            conexion.connectMySql();
            conexion.SQLClear();
            conexion.SQL.append("DELETE empleado WHERE idempleado=? ");
            
            if((obj.getIdempleado()>0)){
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "INT|"+ obj.getIdempleado() +"");
            }else{
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "INT|-1");
            }
            
            //ACA EJECUTAMOS LA SENTENCIA
            if(conexion.executeSQLUpdate(conexion.Parametros) > 0){
                Eliminado = true;
            }
            
          }catch (Exception ex) {
            //log.error(ex.getMessage(),ex);
            throw new SQLException ("ERROR:" + ex.getMessage());
        }finally{
            conexion.desconectar();  
        }
        return Eliminado;
         
     }
     
     public List<Empleado> ListEmpleado(Empleado obj) throws IOException, SQLException, Exception {
        
        ConexionDB conexion = new ConexionDB();
        List<Empleado> LstItems = new ArrayList<Empleado>();
        try{
            
            conexion.connectMySql();
            conexion.SQLClear();
            conexion.SQL.append("SELECT idempleado, nombre, email, telefono FROM empleado WHERE 1=1 ");
            
            if((obj.getIdempleado()>0)){
                conexion.ContPrepareStatement ++;
                conexion.SQL.append("AND idempleado=? ");
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "INT|"+ obj.getIdempleado() +"");
            }
            
            if(obj.getNombre()!=null){
                conexion.ContPrepareStatement ++;
                conexion.SQL.append("AND nombre =? ");
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "STRING|"+ obj.getNombre() +"");
            }
                        
            if(obj.getEmail()!=null){
                conexion.ContPrepareStatement ++;
                conexion.SQL.append("AND email=? ");
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "STRING|"+ obj.getEmail() +"");
            }

            if(obj.getTelefono()!=null){
                conexion.ContPrepareStatement ++;
                conexion.SQL.append("AND telefono=? ");
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "STRING|"+ obj.getTelefono() +"");
            }
            
            
            //ACA EJECUTAMOS LA SENTENCIA
            if(conexion.executeSQLQuery(conexion.Parametros) > 0){
                 while(conexion.RS.next()){
                    Empleado empleado = new Empleado();
                    empleado.setIdempleado((conexion.RS.getObject(1)!=null && !conexion.RS.wasNull())?conexion.RS.getInt(1):-1);
                    empleado.setNombre((conexion.RS.getObject(2)!=null && !conexion.RS.wasNull())?conexion.RS.getString(2):"");
                    empleado.setEmail((conexion.RS.getObject(3)!=null && !conexion.RS.wasNull())?conexion.RS.getString(3):"");
                    empleado.setTelefono((conexion.RS.getObject(4)!=null && !conexion.RS.wasNull())?conexion.RS.getString(4):"");
                    LstItems.add(obj);
                }
            }
            
          }catch (Exception ex) {
            //log.error(ex.getMessage(),ex);
            throw new SQLException ("ERROR:" + ex.getMessage());
        }finally{
            conexion.desconectar();  
        }         
        return LstItems;
     }
}
