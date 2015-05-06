package engine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool{
    org.apache.log4j.Logger cat = org.apache.log4j.Logger.getLogger("ConnectionPool.class");
    static final int MAX_CONNECTIONS = 20;     //I should increase this for a busier website
    static Vector connections = null;
    //String URL = "jdbc:odbc:Tialios";
    static ConnectionPool instance = null;
    public static synchronized ConnectionPool getInstance(){
        if(instance == null)
            instance  = new ConnectionPool();
        return instance;
    }

    public synchronized void initialize(){
        if(connections == null){
            try{

              //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

              Class.forName("com.mysql.jdbc.Driver").newInstance();
              //Class.forName("org.gjt.mm.mysql.Driver").newInstance();
             // log.log("got the driver");
            }
            catch(Exception e){

                 cat.error("Driver class NF Exception: " + e.getMessage());
              //  log.log("Driver class NF Exception: " + e.getMessage());
            }
            try{
                connections = new Vector();
                int count = 0;
                while (count < MAX_CONNECTIONS){
                   // Connection connection = DriverManager.getConnection(URL,"","");
                   //this is for local deployment for bookaroom:jdbc:mysql:/localhost/bookaroom_gr_-_main?user=bookaroom&password=zarty256
                    // rimu
                    //Connection connection =DriverManager.getConnection("jdbc:mysql:/localhost/panorama?user=admin&password=zg00h0z&useUnicode=true&characterEncoding=UTF-8");    // VPS
                  // Connection connection =DriverManager.getConnection("jdbc:mysql:/localhost/freelancing?user=test&password=test&useUnicode=true"); //    linuxserver                                      &useUnicode=true&characterEncoding=UTF-8
                  // Connection connection =DriverManager.getConnection("jdbc:mysql:/localhost/freelancing?user=admin&password=dm@rket&useUnicode=true&characterEncoding=UTF-8"); //    Dedicated                                      &useUnicode=true&characterEncoding=UTF-8
                 Connection connection =DriverManager.getConnection("jdbc:mysql:/localhost/inoxergo?user=root&password=zarty256&useUnicode=true&characterEncoding=UTF-8");       //localhost
                 //Connection connection =DriverManager.getConnection("jdbc:mysql:/localhost/inoxergo?user=admin&password=dm@rket&useUnicode=true&characterEncoding=UTF-8");       //server

                  //  Connection connection =DriverManager.getConnection("jdbc:mysql:/localhost/freelancing?user=root&password=zarty&useUnicode=true&characterEncoding=UTF-8");       //localhost       //linuxserver
                 //  Connection connection =DriverManager.getConnection("jdbc:mysql:/localhost/freelancing?user=root&password=d4n3l4545&useUnicode=true&characterEncoding=UTF-8");       //localhost
                  //  Connection connection =DriverManager.getConnection("jdbc:mysql:/localhost/panorama?user=admin&password=dm@rket&useUnicode=true&characterEncoding=UTF-8");//&characterEncoding=UTF-8");

                    // Connection connection =DriverManager.getConnection("jdbc:mysql://mysql.otenet.gr/tialios?user=tialios&password=t1A!o$0&useUnicode=true&characterEncoding=UTF-8"); //otenet tialios.gr
                  // Connection connection =DriverManager.getConnection("jdbc:mysql:/localhost/freelancing?user=admin&password=zg00h0z&useUnicode=true"); //  VPS
                    //connections.addElement(connection);
                     connections.addElement(new ConnectioCachable(connection));
                    count++;
                }
            }
            catch(SQLException e)
            {
           cat.error("SQLException: " + e.getMessage());
           cat.error("SQLState: " + e.getSQLState());
           cat.error("VendorError: " + e.getErrorCode());
}
        }
        //todo good place for checking validity
    }

    public synchronized Connection getConnection(){
        Connection connection = null;
        ConnectioCachable connectioCachable= null;
        connections.trimToSize();
        boolean validity =false;
        if(connections == null)
            return null;
        if(connections.size() > 0){
         while(!validity) {
             connectioCachable = (ConnectioCachable) connections.firstElement();
             connections.removeElement(connectioCachable); //.removeElementAt(connections.size());
             validity=connectioCachable.isValid();
             if(validity){connection=((ConnectioCachable)connectioCachable).getCached();}
             else{this.removeAllConnections();
                 this.initialize();}
         }
        }
           return connection;
    }



    public synchronized void putConnection(Connection connection){
        connections.addElement(new ConnectioCachable(connection));
        notifyAll();
    }

    public synchronized void removeAllConnections(){

        try{
            if(connections == null)
                return;
            int size = connections.size();
            for(int i=0; i<size; i++){
                ConnectioCachable connectioCachable = (ConnectioCachable)connections.elementAt(i);
                (((ConnectioCachable)connectioCachable).getCached()).close();
            }
            connections.removeAllElements();
            connections = null;

          /*  engine.UserManagement um=new engine.UserManagement();
            try {
                um.updateEndSession();
            } catch (Exception e) {
                e.printStackTrace(); 
            }*/

        }
        catch(SQLException e){cat.error("error in removeAllConnections()");}
    }

 class CachableItem {
      long cacheStart;
      long cacheEnd;
      final long EXPIRETIME = 1000*60*3; // 1 hour cache
      protected Object cached;
      public CachableItem() {
         cacheStart = System.currentTimeMillis();
         cacheEnd = cacheStart + EXPIRETIME;
      }
      public boolean isValid() {
         return (System.currentTimeMillis() - cacheEnd) <= 0;
      }
   }
      class ConnectioCachable extends CachableItem {
      public ConnectioCachable( Connection connection ) {
         super();
         cached=connection;
      }
           public Connection getCached() {
         return (Connection)cached;
      }
      }


    





}