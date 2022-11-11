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
import co.edu.mineducacion.app.aplication.modellayer.Actividad;
import co.edu.mineducacion.app.aplication.modellayer.Empleado;
import co.edu.mineducacion.app.dataresource.ConexionDB;
/**

/**
 *
 * @author maest
 */
public class ActividadDAL  implements Serializable{
    
    static private ActividadDAL Instance = null;
    private List<Actividad> LstActividad = new ArrayList<Actividad>();

        
    private ActividadDAL(){
    }
    
    static synchronized public ActividadDAL getInstance() {
        if (Instance == null) {
            Instance = new ActividadDAL();
        }
        return Instance;
    }
   
    public boolean CreateActividad(Actividad obj) throws IOException, SQLException {
        
        ConexionDB conexion = new ConexionDB();
        boolean Insertado = false;
        try{
            conexion.connectMySql();
            conexion.SQLClear();
            conexion.SQL.append("INSERT INTO actividad(descripcion,fechapro,idempleado)");
            conexion.SQL.append("VALUES(?,?,?)");
                        
            //Validamos Atributo
            if(((obj.getDescripcion()!=null?obj.getDescripcion():"").length()>0)){
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "STRING|"+ obj.getDescripcion().toUpperCase() +"");
            }else{
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "NULO");
            }
            //Validamos Atributo
            if(((obj.getFechapro()!=null?obj.getFechapro():"").length()>0)){
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "TIMESTAMP|"+ obj.getFechapro() +"");
            }else{
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "NULO");
            }            

             //Validamos Atributo
            if((obj.getIdempleado()>0)){
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "INT|"+ obj.getIdempleado() +"");
            }else{
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "INT|-1");
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
    
    public boolean UpdateActividad(Actividad obj) throws IOException, SQLException, Exception {
        
        ConexionDB conexion = new ConexionDB();
        boolean Actualizado = false;          
        try{
            
            conexion.connectMySql();
            conexion.SQLClear();
            conexion.SQL.append("UPDATE actividad SET descripcion=?, finalizado=?, fechafin=?, idempleado=? WHERE idactividad=? ");
            
            if(((obj.getDescripcion()!=null?obj.getDescripcion():"").length()>0)){
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "STRING|"+ obj.getDescripcion().toUpperCase() +"");
            }else{
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "NULO");
            }
            //Validamos Atributo
            if(obj.getFinalizado()!=null){
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "BOOLEAN|"+ obj.getFinalizado() + null);
            }else{
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "NULO");
            }
           
             //incluir si finalizado fue marcado cmo verdadero asignar en la variable de fechafin la fecha actual
            if(obj.getFechafin()!=null){
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "TIMESTAMP|"+ obj.getFechafin() +"");
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
            
            if((obj.getIdactividad()>0)){
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "INT|"+ obj.getIdactividad() +"");
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
    
    public boolean DeleteActividad(Actividad obj) throws IOException, SQLException, Exception {
        
        ConexionDB conexion = new ConexionDB();
        boolean Eliminado = false;         
        try{
            
            conexion.connectMySql();
            conexion.SQLClear();
            conexion.SQL.append("DELETE actividad WHERE idactividad=? ");
            
            if((obj.getIdactividad()>0)){
                conexion.ContPrepareStatement ++;
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "INT|"+ obj.getIdactividad() +"");
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
    
    public List<Actividad> ListActividad(Actividad actividad) throws IOException, SQLException, Exception {
        
        ConexionDB conexion = new ConexionDB();
        List<Actividad> LstItems = new ArrayList<Actividad>();
        try{
            
            conexion.connectMySql();
            conexion.SQLClear();
            conexion.SQL.append("SELECT a.idactividad, a.descripcion, a.fechaini, a.fechapro, a.finalizado, a.fechafin, "
                    + " a.idempleado, e.nombre "
                    + " FROM actividad a inner join empleado e on a.idempleado = e.idempleado WHERE 1=1 ");
//hay que validar lo siguiente o simplemente listar?? lo pregunto por que es el listar
// el orden de los item en la validacion debe corresponder con la posicion de los signos de interrogacion??
            if((actividad.getIdactividad()>0)){
                conexion.ContPrepareStatement ++;
                conexion.SQL.append("AND idactividad=? ");
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "INT|"+ actividad.getIdactividad() +"");
            }
            
            if(actividad.getDescripcion()!=null){
                conexion.ContPrepareStatement ++;
                conexion.SQL.append("AND descripcion =? ");
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "STRING|"+ actividad.getDescripcion() +"");
            }
                        
            if(actividad.getFechaini()!=null){
                conexion.ContPrepareStatement ++;
                conexion.SQL.append("AND fechaini=? ");
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "TIMESTAMP|"+ actividad.getFechaini() +"");
            }

            if(actividad.getFinalizado()!=null){
                conexion.ContPrepareStatement ++;
                conexion.SQL.append("AND finalizado=? ");
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "BOOLEAN|"+ actividad.getFinalizado() +"");
            }
            
            if(actividad.getFechafin()!=null){
                conexion.ContPrepareStatement ++;
                conexion.SQL.append("AND fechafin=? ");
                conexion.Parametros.put(String.valueOf(conexion.ContPrepareStatement), "TIMESTAMP|"+ actividad.getFechafin() +"");
            }
            
            
            //ACA EJECUTAMOS LA SENTENCIA
            if(conexion.executeSQLQuery(conexion.Parametros) > 0){
                 while(conexion.RS.next()){
                    Actividad obj = new Actividad();
                    obj.setIdactividad((conexion.RS.getObject(1)!=null && !conexion.RS.wasNull())?conexion.RS.getInt(1):-1);                    
                    obj.setDescripcion((conexion.RS.getObject(2)!=null && !conexion.RS.wasNull())?conexion.RS.getString(2):"");
                    obj.setFechaini((conexion.RS.getObject(3)!=null && !conexion.RS.wasNull())?conexion.RS.getString(3):"");
                    obj.setFechapro((conexion.RS.getObject(4)!=null && !conexion.RS.wasNull())?conexion.RS.getString(4):"");                    
                    obj.setFinalizado((conexion.RS.getObject(5)!=null && !conexion.RS.wasNull())?conexion.RS.getString(5):"");
                    obj.setFechafin((conexion.RS.getObject(6)!=null && !conexion.RS.wasNull())?conexion.RS.getString(6):"");                                        
                    obj.setIdempleado((conexion.RS.getObject(7)!=null && !conexion.RS.wasNull())?conexion.RS.getInt(7):-1);                    
                    Empleado empleado = new Empleado();
                    empleado.setIdempleado(obj.getIdempleado());
                    empleado.setNombre((conexion.RS.getObject(8)!=null && !conexion.RS.wasNull())?conexion.RS.getString(8):"");
                    obj.setEmpleado(empleado);
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
