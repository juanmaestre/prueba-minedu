/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.mineducacion.app.aplication.bussineslayer;

import co.edu.mineducacion.app.aplication.datalayer.ActividadDAL;
import co.edu.mineducacion.app.aplication.modellayer.Actividad;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author maest
 */
public class ActividadBO  implements Serializable {
    
    public boolean CreateActividad(Actividad obj) throws IOException, SQLException {
        try {
            return ActividadDAL.getInstance().CreateActividad(obj);
        } catch (IOException | SQLException ex) {
            throw new SQLException("ERROR:" + ex.getMessage());
        }
    }

    public boolean UpdateActividad(Actividad obj) throws IOException, SQLException {
        try {
            return ActividadDAL.getInstance().UpdateActividad(obj);
        } catch (Exception ex) {
            throw new SQLException("ERROR:" + ex.getMessage());
        }
    }
    
    public boolean DeleteActividad(Actividad obj) throws IOException, SQLException {
        try {
            return ActividadDAL.getInstance().DeleteActividad(obj);
        } catch (Exception ex) {
            throw new SQLException("ERROR:" + ex.getMessage());
        }
    }
    
    public List<Actividad> ListActividad(Actividad obj) throws IOException, SQLException {
        try {
            return ActividadDAL.getInstance().ListActividad(obj);
        } catch (Exception ex) {
            throw new SQLException("ERROR:" + ex.getMessage());
        }
    }
}
