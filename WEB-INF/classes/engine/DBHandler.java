package engine;
import java.sql.*;
import java.util.Enumeration;

public class DBHandler {

    org.apache.log4j.Logger cat=org.apache.log4j.Logger.getLogger("Presentetion.class");
	PreparedStatement _pstmt = null;
	String _queryString = null;
    ConnectionPool connectionPool = null;
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    //Instatiation of the Database class opens a connection with the database
    public DBHandler() throws Exception {
        this.openConnection();
    }
	public DBHandler( org.apache.log4j.Logger logger) {
		String methodsig = "DBHandler.DBHandler()";
        logger=cat;
		//Verify that the driver class exists
		try {
				this.openConnection();
		}
		catch (Exception e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}

		//Log it
		cat.info(methodsig + " Connected to freelancing DB");
	}
	   //This method opens a connection with the database
	//todo use this for connection pooling
	private void openConnection() throws Exception {

	       connectionPool = ConnectionPool.getInstance();
	       connectionPool.initialize();
	       connection = connectionPool.getConnection();
	       statement = connection.createStatement();
	   }

//This method closes the connection with the database
//todo use this for connection pooling
    public void closeConnection() throws Exception {
        statement.close();
        //cat.info("closed statement putting connection in the pool=closing");
	    connectionPool.putConnection(connection);
        //connection.close();
    }
	public boolean setQueryString(String queryString) {
		String methodsig = "DBHandler.setQueryString()";
		if (connection == null) {
			cat.info(methodsig + "Error!!! - Couldn't set the query string because the Connection was null.  The DBHandler was not constructed correctly or the database does not exist.");
			return false;
		}

		try {
			_pstmt = connection.prepareStatement(queryString);
			_queryString = queryString;
		} catch (SQLException sqle) {
			cat.info(methodsig + "Couldn't create PreparedStatement. " + sqle);
			return false;
		}
		return true;
	}

	public void close() {
		try {
			this.closeConnection();
		} catch (Exception sqle) {
		}
	}


	/**
	 * Do a select query on a prepared statement with no arguments
	 */
	public ResultSet lookup() {
		String methodsig = "DBHandler.lookup()";
		cat.info(methodsig + "Looking up query: '" + _queryString + "'");

		try {
			if (!_pstmt.execute()) {
				cat.info(methodsig+ "The resultset was empty on the lookup..");
			}
         //   cat.info("return _pstmt.getResultSet()= "+_pstmt.getResultSet());  
			return _pstmt.getResultSet();
		} catch (SQLException sqle) {
		   cat.info(methodsig+ "Couldn't connect to database, or create PreparedStatement. " + sqle);
			return null;
		}
	}

	/**
	 * Do a select query on a prepared statement with single argument,
	 * where the argument is a String
	 */
	public ResultSet lookup(String arg) {
		String methodsig = "DBHandler.lookup(String)";
		cat.info(methodsig+ "Looking up: '" + arg + "' with query '" + _queryString + "'");

		try {
			_pstmt.setString(1,arg);
			if (!_pstmt.execute()) {
				cat.info(methodsig+ "The resultset was empty on the lookup..");
			}
			cat.info(methodsig+"The resultset returns:"+(_pstmt.getResultSet().getInt(1))+_pstmt.getWarnings().toString());
			return _pstmt.getResultSet();
		} catch (SQLException sqle) {
			cat.info(methodsig+ "Couldn't connect to database, or create PreparedStatement. " + sqle);
			return null;
		}
	}

	/**
	 * Do a select query on a prepared statement with single argument,
	 * where the argument is an integer
	 */
	public ResultSet lookup(int arg) {
		final String methodsig = "DBHandler.lookup(int)";
		cat.info("MethodStart:"+methodsig);
		ResultSet returnval = lookup(Integer.toString(arg));
		cat.info("MethodEnd:"+methodsig);
		return returnval;
	}

	/**
	 * Do a select query on a prepared statement with a vector of arguments,
	 */
	 public ResultSet lookup(java.util.Vector v) {
		final String methodsig = "DBHandler.lookup(Vector)";
			cat.info("MethodStart:"+methodsig);

		try {
			//i is the a counter that places each variable in the vector into the
			//proper place in the prepared statement
			int i = 1;
			for (Enumeration enume = v.elements(); enume.hasMoreElements(); ) {
				_pstmt.setString(i++,(String)enume.nextElement());
			}
			if (!_pstmt.execute()) {
				cat.info(methodsig+ "The resultset was empty on the lookup..");
			}
			cat.info("MethodEnd"+methodsig);
			return _pstmt.getResultSet();
		} catch (SQLException sqle) {
			cat.info(methodsig+ "Couldn't connect to database, or create PreparedStatement. " + sqle);
			cat.info("MethodEnd:"+methodsig);
			return null;
		}
	 }

	/**
	 * Insert a row of values (Vector of args) into a table
     * Could be integer or string values 
	 */
	public int insert(java.util.Vector args) {
		String methodsig = "DBHandler.insert()";
		cat.info("MethodStart:"+methodsig);

		//Create a string out of the args so we can pass it into the logfile message
		String argString = "(";
		for (Enumeration enume = args.elements(); enume.hasMoreElements();) {
			Object o = enume.nextElement();
			if (o instanceof Integer) {
				argString = argString.concat((Integer)o + ",");
			}
			else {
				argString = argString.concat((String)o + ",");
			}
		}
		argString = argString.substring(0,argString.length()-1);
		argString = argString.concat(")");
		cat.info(methodsig+ "Inserting: '" + argString + "' with query '" + _queryString + "'");

		//Create the prepared statement by passing in each argument of the vector
		try {
			for (int i = 0; i < args.size(); i++) {
				Object o = args.elementAt(i);
//				if (o == null) {
//				    _pstmt.setNull(i+1, 1);
//				}
				if (o instanceof Integer) {
					_pstmt.setInt(i+1, ((Integer)o).intValue());
				}
				else {
					_pstmt.setString(i+1, (String)o);
				}
			}
			int updateResult = _pstmt.executeUpdate();
			cat.info(methodsig+ "The insert updated " + updateResult + " rows");
		    cat.info("MethodEnd:"+methodsig);
			return updateResult;
		} catch (SQLException sqle) {
			cat.info(methodsig+ "Couldn't connect to database, or create PreparedStatement. " + sqle);
			//sqle.printStackTrace();
			cat.info("MethodEnd:"+methodsig);
			return 0;
		}
	}

	/**
	 * Execute a query with no args
	 */
	 public boolean execute() {
		String methodsig = "DBHandler.execute()";
		cat.info("MethodStart:"+methodsig);
		cat.info(methodsig+ "Executing query: '" + _queryString + "'");
		try {
			boolean returnval = _pstmt.execute();
			cat.info("MethodEnd:"+methodsig);
			return returnval;
		} catch (SQLException sqle) {
			cat.info(methodsig+ "Couldn't connect to database. " + sqle);
			cat.info("MethodEnd:"+methodsig);
			return false;
		}
	 }

	/**
	 * Execute an update query with a String as an arg
	 */
	 public int executeUpdate(String arg) {
		String methodsig = "DBHandler.executeUpdate(String)";
		cat.info("MethodStart:"+methodsig);
		cat.info(methodsig+ "Updating '" + arg + "' with query string '" + _queryString + "'");
		try {
			_pstmt.setString(1, arg);
			int updateResult = _pstmt.executeUpdate();
			cat.info(methodsig+ "The updated affected " + updateResult + " rows");
			cat.info("MethodEnd:"+methodsig);
			return updateResult;
		} catch (SQLException sqle) {
			cat.info(methodsig+ "Couldn't connect to database. " + sqle);
			cat.info("MethodEnd:"+methodsig);
			return 0;
		}
	 }
	/**
	 * Execute a Select query with a String as an arg to get  NUMBEROFIMPRESSIONS
	 */
	 public int executeSelect(String arg) {
		String methodsig = "DBHandler.executeSelect(String)";
		cat.info("MethodStart:"+methodsig);
		cat.info(methodsig+ "Selecting with where argument'" + arg + "' with query string '" + _queryString + "'");
		try {
			//_pstmt.setString(1, arg);
			ResultSet selectResult = this.lookup(arg);
			cat.info(methodsig+ "The Select affected " + selectResult.getString(1) + " rows");
			cat.info("MethodEnd:"+methodsig);
			return selectResult.getInt(1);
		} catch (SQLException sqle) {
			cat.info(methodsig+ "Couldn't connect to database. " + sqle);
			cat.info("MethodEnd:"+methodsig);
			return 0;
		}
	 }

	/**
	 * Execute an update query with a Vector of Strings as args
	 */
	public int executeUpdate(java.util.Vector args) {
		String methodsig = "DBHandler.executeUpdate(Vector)";
		cat.info("MethodStart:"+methodsig);

		//Create a string out of the args so we can pass it into the logfile message
		String argString = new String("(");
		for (Enumeration enume = args.elements(); enume.hasMoreElements();) {
			Object o = enume.nextElement();
			if (o instanceof Integer) {
				argString = argString.concat((Integer)o + ",");
			}
			else {
				argString = argString.concat((String)o + ",");
			}
		}
		argString = argString.substring(0,argString.length()-1);
		argString = argString.concat(")");
		cat.info(methodsig+ "Executing: '" + argString + "' with query '" + _queryString + "'");

		//Create the prepared statement by passing in each argument of the vector
		try {
			for (int i = 0; i < args.size(); i++) {
				Object o = args.elementAt(i);
				if (o instanceof Integer) {
					_pstmt.setInt(i+1, ((Integer)o).intValue());
				}
				else {
					_pstmt.setString(i+1, (String)o);
				}
			}
			int executeResult = _pstmt.executeUpdate();
			cat.info(methodsig+ "The execute updated returned '" + executeResult + "'");
			cat.info("MethodEnd:"+methodsig);
			return executeResult;
		} catch (SQLException sqle) {
			cat.info(methodsig+ "Couldn't connect to database, or create PreparedStatement. " + sqle);
			//sqle.printStackTrace();
			cat.info("MethodEnd:"+methodsig);
			return 0;
		}
	}



	public String getQueryString() {
		return _queryString;
	}
}