/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.mineducacion.app.dataresource;

import java.io.*;
import java.sql.*;
import java.util.*;
/**
 *
 * @author maest
 */
public class ConexionManager {
    
    static private ConexionManager instance = null;       // Instancia sencilla
    static private int clients;
    private Vector drivers = new Vector();
    private PrintWriter log;
    private Hashtable pools = new Hashtable();
    private String motorDBName = "POSTGRESQL";
    private String driverDBName = "org.postgresql.Driver";
    private String urlDBName = "jdbc:postgresql://127.0.0.1:5432/db-minedu";
    private String usuarioDBName = "jmmaestrej";
    private String passwordDBName = "12345678";
    
    private ConexionManager() {
        init();
    }
    
    /**
     * Retorna una instancia, creando una si el metodo es llamado
     * por primera vez.
     *
     * @return ConexionManager la instancia simple.
     */
    static synchronized public ConexionManager getInstance() {
        if (instance == null) {
            instance = new ConexionManager();
        }
        clients++;
        return instance;
    }
    
    /**
     * Retorna una conexion de la base de datos.
     *
     * recibe como paremetro el nombre pool, tal como este definido
     * parametro con de conexion
     */
    public void freeConnection(String name, Connection con) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        try{
            if (pool != null) {
                pool.freeConnection(con);
            }
        }catch(Exception e){
            log(e.getMessage());
            //logger.error(e.getMessage());
            //System.out.println("Ha fallado la desconexion "+e.getMessage());
        }finally{
            try{
                if(con!=null){
                    con.close();
                }
            }catch(Exception e){
                log(e.getMessage());
                //logger.error(e.getMessage());
                //System.out.println("Ha fallado la desconexion "+e.getMessage());
            }
        }
    }
    
    /**
     * Retorna una conexion abierta. Si ninguna este disponible, y el maximo
     * numero de conexiones no han sido rechazadas, entonces una nueva conexion
     * es creada.
     *
     */
    public Connection getConnection(String name) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null) {
            return pool.getConnection();
        }
        return null;
    }
    
    /**
    /**
     * Retorna una conexion abierta. Si ninguna este disponible, y el maximo
     * numero de conexiones no han sido rechazadas, entonces una nueva conexion
     * es creada. Si el maximo numero de conexiones es rechazado, entonces espera
     * hasta que haya disponibles
     *
     */
    public Connection getConnection(String name, long time) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null) {
            return pool.getConnection(time);
        }
        return null;
    }
    
    /**
     * Cierra todas las conexiones y desconecta los drivers.
     */
    public synchronized void release() {
        // Wait until called by the last client
        if (--clients != 0) {
            return;
        }

        Enumeration allPools = pools.elements();
        while (allPools.hasMoreElements()) {
            DBConnectionPool pool = (DBConnectionPool) allPools.nextElement();
            pool.release();
        }
        Enumeration allDrivers = drivers.elements();
        while (allDrivers.hasMoreElements()) {
            Driver driver = (Driver) allDrivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                log("Se desconectara el driver JDBC " + driver.getClass().getName());
            }
            catch (SQLException e) {
                log(e.getMessage());
                //logger.error(e.getMessage());
                //log(e, "No se puede desconectar el driver JDBC: " + driver.getClass().getName());
            }
        }
    }
    
    /**
     * Crea instancias DBConnectionPool basada en las propiedades.
     * A DBConnectionPool que pueden ser definidas con las siguientes propiedades:
     * <PRE>
     * &lt;poolname&gt;.url         El JDBC URL para la database
     * &lt;poolname&gt;.user        Una base de datos de usuario (opcional)
     * &lt;poolname&gt;.password    una base de datos de contraseñas (si estas son especificadas)
     * &lt;poolname&gt;.maxconn     El numero maximo de conexiones
     * </PRE>
     *
     * @param props
     */
    private void createPools(Properties props) {
        Enumeration propNames = props.propertyNames();
        while (propNames.hasMoreElements()) {
            String name = (String) propNames.nextElement();
            //if (name.endsWith(".url")) {
            if (name.endsWith(".Url")) {
                String poolName = name.substring(0, name.lastIndexOf("."));
                //String poolName = "PRUEBA";
                String url = props.getProperty(poolName + ".Url");
                this.urlDBName = url;
                
                if (url == null) {
                    log("No se ha especificado la URL para " + poolName);
                    continue;
                }
                String user = props.getProperty(poolName + ".UserName");
                this.usuarioDBName = user;
                String password = props.getProperty(poolName + ".Password");
                this.passwordDBName = password;
                String maxconn = props.getProperty(poolName + ".MaxConn", "0");
                int max;
                try {
                    max = Integer.valueOf(maxconn).intValue();
                }
                catch (NumberFormatException e) {
                    log("Invalido valor de maxconn " + maxconn + " para " + poolName);
                    max = 0;
                }
                // se despliegan los datos del pool.
//                System.out.print("Pool: " + poolName + "\n");
//                System.out.print("URL : " + url + "\n");
//                System.out.print("User: " + user + "\n");
//                System.out.print("Pwd : " + password + "\n");
//                System.out.print("MaxC: " + max + "\n");
                DBConnectionPool pool = new DBConnectionPool(poolName, url, user, password, max);
                pools.put(poolName, pool);
                log("Pool inicializado " + poolName);
            }
        }
    }
    
    /**
     * Carga las propiedasdes e inicializa la instancias con estos valores.
     */
    private void init() {
        InputStream is = getClass().getResourceAsStream("dbparameters.properties");
        Properties dbProps = new Properties();
        try {
            dbProps.load(is);
            //System.out.println("Se cargaron las propiedades del archivo db.properties");
        }
        catch (Exception e) {
            log(e.getMessage());
            //System.err.println("No se pueden leer las propiedades de archivo. ");
            //System.err.println("Asegurese de que db.properties se encuentre en el CLASSPATH.");
            return;
        }
        String logFile = dbProps.getProperty("logfile", "log.txt");
        try {
            System.out.println("Log: " + logFile);
            log = new PrintWriter(new FileWriter(logFile, true), true);
        }
        catch (IOException e) {
            //System.err.println("No se puede abrir el archivo logico: " + logFile);
            //logger.error(e.getMessage());
            log = new PrintWriter(System.err);
        }
        loadDrivers(dbProps);
        setMotorDB(dbProps);
        createPools(dbProps);
    }
    
    /**
     * Carga y registra todos los drivers JDBC. Estos estaran listos para
     * ConexionManager, como opuesto the DBConnectionPool,
     * varios pool , pueden compartir el mismo driver.
     *
     */
    private void loadDrivers(Properties props) {
        String driverClasses = props.getProperty("PRUEBA.Drivers");
        StringTokenizer st = new StringTokenizer(driverClasses);
        while (st.hasMoreElements()) {
            String driverClassName = st.nextToken().trim();
            try {
                System.out.println("Driver: " + driverClassName);
                Driver driver = (Driver)Class.forName(driverClassName).newInstance();
                DriverManager.registerDriver(driver);
                drivers.addElement(driver);
                this.driverDBName = driverClassName;
                log("Se registro el driver JDBC " + driverClassName);
                //System.out.println("Se registro el driver " + driverClassName);
            } catch (Exception e) {
                //logger.error(e.getMessage());
                log("No se puede registrar el driver JDBC: " + driverClassName + ", Exception: " + e);
                //System.out.println("No se pudo registrar el driver " + driverClassName);
            }
        }
    }
    
    public String getMotorDB() {
        return this.motorDBName;
    }
    
    public String getDriverDB() {
        return this.driverDBName;
    }
    
    public String getUrlDB() {
        return this.urlDBName;
    }
    
    public String getUsuarioDB() {
        return this.usuarioDBName;
    }
    
    public String getPasswordDB() {
        return this.passwordDBName;
    }

    private void setMotorDB(Properties props) {
        String motorDB = props.getProperty("PRUEBA.MotorDB");
        StringTokenizer st = new StringTokenizer(motorDB);
        //String motorDBName = null;
        while (st.hasMoreElements()) {
            this.motorDBName = st.nextToken().trim();
        }
    }

    /**
     * Escribe un mensaje en un archivo log.
     */
    private void log(String msg) {
        log.println(new java.util.Date() + ": " + msg);
    }

    /**
     * Escribe un mensaje con una excepcion en un archivo.
     */
    private void log(Throwable e, String msg) {
        log.println(new java.util.Date() + ": " + msg);
        e.printStackTrace(log);
    }
    
    /**
     * Esta clase representa un pool de conexiones. Estas se crean nuevas por demanda
     */
    class DBConnectionPool {
        private int checkedOut;
        private Vector freeConnections = new Vector();
        private int maxConn;
        private String name;
        private String password;
        private String URL;
        private String user;

        /**
         * Crea un nuevo pool de conexiones.
         *
         * @param nombre, el pool nombre
         * @param URL el URL JDBC para la base de datos
         * @param usuario, la base de datos o nulo.
         * @paramcontraseña o nulo.
         * @param Conn para el maximo numero de coexiones
         *
         */
        public DBConnectionPool(String name, String URL, String user, String password, int maxConn) {
            this.name = name;
            this.URL = URL;
            this.user = user;
            this.password = password;
            this.maxConn = maxConn;
        }

        /**
         * Notifica si se puede esperar por conexion,uso de hilos
         *
         * @param con, es chequeado
         */
        public synchronized void freeConnection(Connection con) {
            // Put the connection at the end of the Vector
            freeConnections.addElement(con);
            checkedOut--;
            notifyAll();
        }

        /**
         * Una nueva conexion es creadasi al momento de chequear estas estan disponibles
         */
        public synchronized Connection getConnection() {
            Connection con = null;
            if (freeConnections.size() > 0) {
                // Selecciona primero la conexion del vector
                // para que get round-robin se utilizado.
                con = (Connection) freeConnections.firstElement();
                freeConnections.removeElementAt(0);
                try {
                    if (con.isClosed()) {
                        log("Se removio una mala conexion de " + name);
                        // Intenta de nuevo. Recursividad
                        con = getConnection();
                    }
                }
                catch (SQLException e) {
                    log("Se removio una mala conexion de " + name);
                    // Intenta de nuevo. Recursividad
                    con = getConnection();
                }
            }
            else if (maxConn == 0 || checkedOut < maxConn) {
                con = newConnection();
            }
            if (con != null) {
                checkedOut++;
            }
            return con;
        }

     /**
     * Chequea las conexiones desde el pool. Si no hay conexiones libres.
	 * Una nueva conexion es creada si hay disponibilidad.
	 * Si la conexion libre ha sido cerrada por la base de datos, esta es removida del pool.
	 * El metodo espera a que hayas conexiones disponibles.
         *
         * @param tiempo de espera en milisegundos
         */
        public synchronized Connection getConnection(long timeout) {
            long startTime = new java.util.Date().getTime();
            Connection con;
            while ((con = getConnection()) == null) {
                try {
                    wait(timeout);
                }
                catch (InterruptedException e) {}
                if ((new java.util.Date().getTime() - startTime) >= timeout) {
                    // Tiempo de espera ha terminado
                    return null;
                }
            }
            return con;
        }

        /**
         * Cierra todas las conexiones disponibles.
         */
        public synchronized void release() {
            Enumeration allConnections = freeConnections.elements();
            while (allConnections.hasMoreElements()) {
                Connection con = (Connection) allConnections.nextElement();
                try {
                    con.close();
                    log("Conexion cerrada para el pool " + name);
                    //System.out.println("Conexion cerrada para el pool " + name);
                }
                catch (SQLException e) {
                    //logger.error(e.getMessage());
                    log(e, "No se puede cerrar la conexion para el pool " + name);
                    //System.out.println("No se puede cerrar la conexion para el pool " + name);
                }
            }
            freeConnections.removeAllElements();
        }

        /**
         * Crea una nueva conexion, utilizando el usuario y la contraseña
         * si estas fueron especificadas.
         */
        private Connection newConnection() {
            Connection con = null;
            try {
                if (user == null) {
                    con = DriverManager.getConnection(URL);
                }
                else {
                    con = DriverManager.getConnection(URL, user, password);
                }
                log("Creada una nueva conexion para el pool " + name);
                //System.out.println("Creada una nueva conexion para el pool " + name);
            }
            catch (SQLException e) {
                //logger.error(e.getMessage());
                log(e, "No se puede crear una nueva conexion para " + URL);
                //System.out.println("No se puede crear una nueva conexion para " + URL);
                return null;
            }
            return con;
        }
    }
    
}