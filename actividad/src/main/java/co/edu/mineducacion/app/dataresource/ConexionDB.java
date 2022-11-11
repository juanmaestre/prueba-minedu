/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.mineducacion.app.dataresource;

import java.io.InputStream;
import java.sql.*;
import java.lang.*;
import java.util.*;

import java.math.BigDecimal;

/**
 *
 * @author maest
 */
public class ConexionDB extends Object{
    
    //Constantes de parametros de conexion
    public static String STR_MYSQL_DRV   = null;
    public static String STR_MYSQL_USR;//   = "root";
    public static String STR_MYSQL_PWD;//   = "12345678";
    public static String STR_MYSQL_URL;
    public String STR_MOTOR_DB = null;
    public static String STR_MYSQL_POL   = "PRUEBA";
    
    //De base de datos
    public Connection CONN                  = null;
    public Statement STMT                   = null;
    public PreparedStatement PSTMT          = null;
    public ResultSet RS                     = null;
    public ResultSetMetaData RSMD           = null;
    public StringBuffer SQL;
    public ConexionManager connMgr = null;
    public static long AffectedRows    = 0;
    public String mensaje_error = null;
    
    public Map Parametros = new HashMap();
    public Integer ContPrepareStatement = 0;
    public InputStream InputStreamFile  = null;
    
    /** Creates a new instance of ConexionDB */
    public ConexionDB() {
        try {
            SQL = new StringBuffer();
            connMgr = ConexionManager.getInstance();
        }catch(Exception e){
            //System.out.println(e.getMessage());
            e.printStackTrace();
            //logger.error(e.getMessage());
        }
    }
    
    /**
    * Genera una instancia de Statement para ejecutar los querys de consulta
    */
    public void connectMySql() throws SQLException, Exception {
        connectMySQL();
    }
    
    /**
    * Limpia el StringBuffer SQL.
    */
    public void SQLClear() {
        SQL.delete(0, SQL.length());
    }
    
    /**
    * Ejecuta la sentencia sql alimentada en el StringBuffer SQL.
    * La ejecuciÃƒÂ³n de la sentencia retorna el objeto RS de tipo ResultSet.
    * TambiÃƒÂ©n el miembro AffectedRows es alimentado con el nÃƒÂºmero de
    * registros extraidos de la bd.
    */
    public long executeSQLQuery() throws SQLException, Exception {
        long intResult = -1;
        if (CONN == null) {
            mensaje_error = "No se instanciado la conexion con la BD CONN es nulo!... No se puede ejecutar la sentencia SQL!.";
            System.out.println(mensaje_error);
                throw new Exception(mensaje_error);
        }
        STMT = CONN.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        RS = STMT.executeQuery(SQL.toString());
        RS.last();
        intResult = RS.getRow();
        RS.beforeFirst();
        RSMD = RS.getMetaData();
        AffectedRows = intResult;
        return intResult;
    }
    
    /**
    * Ejecuta la sentencia sql alimentada en el StringBuffer SQL.
    * La ejecuciÃƒÂ³n de la sentencia retorna el objeto RS de tipo ResultSet.
    * TambiÃƒÂ©n el miembro AffectedRows es alimentado con el nÃƒÂºmero de
    * registros extraidos de la bd.
    */
    public long executeSQLQuery(Map Parametros) throws SQLException, Exception {
        long intResult = -1;
        if (CONN == null) {
            mensaje_error = "No se instanciado la conexion con la BD CONN es nulo!... No se puede ejecutar la sentencia SQL!.";
            System.out.println(mensaje_error);
                throw new Exception(mensaje_error);
        }
        //ConexionDB.PSTMT = CONN.prepareStatement(SQL.toString(),ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        PSTMT = CONN.prepareStatement(SQL.toString(),ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        /*Agrega los parametros*/
        PSTMT = getPSTMT(PSTMT,Parametros);
        /*Fin*/
        RS = PSTMT.executeQuery();
        RS.last();
        intResult = RS.getRow();
        RS.beforeFirst();
        RSMD = RS.getMetaData();
        AffectedRows = intResult;
        return intResult;
    }
    
    
    private PreparedStatement getPSTMT(PreparedStatement PSTMT, Map Parametros) throws SQLException, Exception{
        
        if(!(Parametros.isEmpty())){
            if(Parametros.size()>0){
                Set set = Parametros.entrySet();
                //Set set = Parametros.keySet();
                Iterator i = set.iterator();
                while(i.hasNext()) {
                    Map.Entry me = (Map.Entry)i.next();
                    
                    if (me.getValue().toString().split("\\|")[0].equals("STRING"))
                        PSTMT.setObject(Integer.parseInt(me.getKey().toString()), me.getValue().toString().split("\\|")[1].toString(), Types.VARCHAR);
                    else if (me.getValue().toString().split("\\|")[0].equals("BIGDECIMAL"))
                        PSTMT.setObject(Integer.parseInt(me.getKey().toString()), BigDecimal.valueOf(Long.parseLong(me.getValue().toString().split("\\|")[1].toString())), Types.NUMERIC);
                    else if (me.getValue().toString().split("\\|")[0].equals("BOOLEAN"))
                        PSTMT.setObject(Integer.parseInt(me.getKey().toString()), Boolean.valueOf(me.getValue().toString().split("\\|")[1].toString()), Types.BIT);
                    else if (me.getValue().toString().split("\\|")[0].equals("SHORT"))
                        PSTMT.setObject(Integer.parseInt(me.getKey().toString()), Short.parseShort(me.getValue().toString().split("\\|")[1].toString()), Types.SMALLINT);
                    else if (me.getValue().toString().split("\\|")[0].equals("INT"))
                        PSTMT.setObject(Integer.parseInt(me.getKey().toString()), Integer.parseInt(me.getValue().toString().split("\\|")[1].toString()), Types.INTEGER);
                    else if (me.getValue().toString().split("\\|")[0].equals("LONG"))
                        PSTMT.setObject(Integer.parseInt(me.getKey().toString()), Long.parseLong(me.getValue().toString().split("\\|")[1].toString()), Types.BIGINT);
                    else if (me.getValue().toString().split("\\|")[0].equals("FLOAT"))
                        PSTMT.setObject(Integer.parseInt(me.getKey().toString()), Float.parseFloat(me.getValue().toString().split("\\|")[1].toString()), Types.REAL);
                    else if (me.getValue().toString().split("\\|")[0].equals("DOUBLE"))
                        PSTMT.setObject(Integer.parseInt(me.getKey().toString()), Double.parseDouble(me.getValue().toString().split("\\|")[1].toString()), Types.DOUBLE);
                    else if (me.getValue().toString().split("\\|")[0].equals("DATE"))
                        PSTMT.setObject(Integer.parseInt(me.getKey().toString()), me.getValue().toString().split("\\|")[1].toString(), Types.DATE);
                    else if (me.getValue().toString().split("\\|")[0].equals("TIME"))
                        PSTMT.setObject(Integer.parseInt(me.getKey().toString()), me.getValue().toString().split("\\|")[1].toString(), Types.TIME);
                    else if (me.getValue().toString().split("\\|")[0].equals("TIMESTAMP"))
                        PSTMT.setObject(Integer.parseInt(me.getKey().toString()), me.getValue().toString().split("\\|")[1].toString(), Types.TIMESTAMP);
                    else if (me.getValue().toString().split("\\|")[0].equals("FILE") && InputStreamFile!=null)
                        PSTMT.setBlob(Integer.parseInt(me.getKey().toString()), InputStreamFile);
                    else if (me.getValue().toString().equals("NULO"))
                        PSTMT.setNull(Integer.parseInt(me.getKey().toString()), Types.NULL);
                }
                //System.out.println("viene lleno...");
            }
        }
        return PSTMT;
    }
    /**
    * Ejecuta la sentencia sql alimentada en el StringBuffer SQL.
    * El miembro AffectedRows es alimentado con el nÃƒÂºmero de registros
    * afectados por la sentencia sql.
    */
    public long executeSQLUpdate() throws SQLException, Exception {
        long intResult = -1;
        if (CONN == null) {
            mensaje_error = "No se ha instanciado la conexion con la BD CONN es nulo!... No se puede ejecutar la sentencia SQL!.";
            System.out.println(mensaje_error);
            throw new Exception(mensaje_error);
        }else{
            PSTMT = CONN.prepareStatement(SQL.toString());
            intResult = PSTMT.executeUpdate();
            PSTMT=null;
            CONN.commit();
            AffectedRows = intResult;
        }
        return intResult;
    }
    
    public long executeSQLUpdate(Map Parametros) throws SQLException, Exception {
        long intResult = -1;
        if (CONN == null) {
            mensaje_error = "No se ha instanciado la conexion con la BD CONN es nulo!... No se puede ejecutar la sentencia SQL!.";
            //System.out.println(mensaje_error);
            throw new Exception(mensaje_error);
        }else{
            PSTMT = CONN.prepareStatement(SQL.toString());
            PSTMT = getPSTMT(PSTMT,Parametros);
            intResult = PSTMT.executeUpdate();
            PSTMT=null;
            //CONN.commit();
            AffectedRows = intResult;
        }
        return intResult;
    }

    /**
    * Establece u obtiene una conexiÃƒÂ³n con la bd postgresql administrada por
    * el Pool de conexiones.
    */
    protected void connectMySQL() throws SQLException, Exception {
        CONN = connMgr.getConnection(STR_MYSQL_POL);
        STR_MOTOR_DB = connMgr.getMotorDB();
        if (CONN == null) {
            
            STR_MYSQL_DRV = connMgr.getDriverDB();
            STR_MYSQL_URL = connMgr.getUrlDB();
            STR_MYSQL_USR = connMgr.getUsuarioDB();
            STR_MYSQL_PWD = connMgr.getPasswordDB();
            
            //CUANDO ES MYSQL
            if(connMgr.getMotorDB().equals("MYSQL")){
                Class.forName(STR_MYSQL_DRV);
                Driver driver = (Driver)Class.forName(STR_MYSQL_DRV).newInstance();
                DriverManager.registerDriver(driver);
                CONN = DriverManager.getConnection(STR_MYSQL_URL, STR_MYSQL_USR, STR_MYSQL_PWD);
                //System.out.println("No se pudo obtener una conexion del pool " + STR_MYSQL_POL);
            }
            //CUANDO ES ORACLE
            if(connMgr.getMotorDB().equals("ORACLE")){
                Class.forName(STR_MYSQL_DRV);
                Driver driver = (Driver)Class.forName(STR_MYSQL_DRV).newInstance();
                DriverManager.registerDriver(driver);
                CONN = DriverManager.getConnection(STR_MYSQL_URL, STR_MYSQL_USR, STR_MYSQL_PWD);
                //System.out.println("No se pudo obtener una conexion del pool " + STR_MYSQL_POL);
            }
            //CUANDO ES SQLSERVER
            if(connMgr.getMotorDB().equals("SQLSERVER")){
                Class.forName(STR_MYSQL_DRV);
                Driver driver = (Driver)Class.forName(STR_MYSQL_DRV).newInstance();
                DriverManager.registerDriver(driver);
                CONN = DriverManager.getConnection(STR_MYSQL_URL, STR_MYSQL_USR, STR_MYSQL_PWD);
                //System.out.println("No se pudo obtener una conexion del pool " + STR_MYSQL_POL);
            }
            //CUANDO ES POSTGRESQL
            if(connMgr.getMotorDB().equals("POSTGRESQL")){
                Class.forName(STR_MYSQL_DRV);
                Driver driver = (Driver)Class.forName(STR_MYSQL_DRV).newInstance();
                DriverManager.registerDriver(driver);
                CONN = DriverManager.getConnection(STR_MYSQL_URL, STR_MYSQL_USR, STR_MYSQL_PWD);
                //System.out.println("No se pudo obtener una conexion del pool " + STR_MYSQL_POL);
            }
            System.out.println("Fue necesario establecer una conexion sin el pool.");
        } else {
            //System.out.println("Se obtuvo una conexion del pool " + STR_MYSQL_POL);
        }
    }

    /**
    * Retorna el String del valor de una columna del objeto RS actual,
    * si el valor es nulo o vacÃƒÂ­o, retorna un espacio "&nbsp;".
    *
    * @param column_name Es el nombre de la columna de la cual se desea
    * obtener el valor.
    */
    public String getColumn(String column_name) throws SQLException, Exception {
        String strResult = null;
        if ((RS != null)) {
        if ( RS.getString(column_name).equals(null) || RS.getString(column_name).trim().equals("") )
            strResult = "&nbsp;";
        else
            strResult = RS.getString(column_name);
        if (strResult.length() == 0) { strResult = "&nbsp;"; }
        }
        return strResult;
    }
    
    /**
     * Termina la conexion con la base de datos
     */
    public void desconectar(){
        connMgr.freeConnection(STR_MYSQL_POL, CONN);
    }

    @Override
    public void finalize(){
        this.desconectar();
    }
}