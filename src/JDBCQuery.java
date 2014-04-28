import java.sql.*;

public class JDBCQuery{
	//define the driver to use
	public static String driver = Login.DRIVER_CLASS;
	//the database info
    public static String dbName =Login.DB;  
    public static String user = Login.USER;
    public static String pw = Login.PWD;
    // define the DB connection URL to use
    public static String connectionURL = Login.DRIVER+dbName; 
    public static Connection conn = null ;
    public static Statement stmt;
    public static ResultSet results;
	
    public static void main ( String [] args ){
    	
    	// For testing Method Calls
    	ResultSet result = getCDAIforstudent("Megan", "King");
           
    	try {
				while ( result.next ()) {
					
					// For #8
//					String emphasis = result.getString("emphasis");
//				    String average = result.getString("average");
//				    String maxscore = result.getString("maxscore");
//				    String minscore = result.getString("minscore");
//				    System.out.println(emphasis+" : "+average+" : "+maxscore+" : "+minscore);
					
					// For #9 
					String fname = result.getString("fname");
				    String lname = result.getString("lname");
				    String CDAI = result.getString("CDAI");
				    String criteria = result.getString("criteria");
				    String score = result.getString("score");
				    System.out.println(fname+" "+lname+" : "+CDAI+" : "+criteria+" : "+score);
				}
				stmt.close ();
	            conn.close ();
			} catch (SQLException e) {
				e.printStackTrace();
			}
           
    }
    // Phase 2 Query 1
    // A query to display the average score for a specific criterion (identified by id such as
    // A1F12C1) in a specific CDAI in a specific semester.
    public static ResultSet getAvgCriteriaScore(String AssessCriteriaID) {
    	try {
    		Class.forName (driver); // register the JDBC driver
	    	conn = DriverManager.getConnection (connectionURL,user,pw );
	        stmt = conn.createStatement ();
	        results = stmt.executeQuery
	        		("SELECT avg(score) AS 'Avg' FROM grades WHERE criteria = 'AssessCriteriaID';");
            return results;
    	} catch ( Exception e) { 
            System .out.println (" Error " + e );
        }
    	return results;
    }
    
 // Phase 2 Query 2
    // A query to display the average scores for all criteria in a specific CDAI in a specific
    // semester (CDAI identified by id such as A1F12)
    public static ResultSet getAvgCDAIScore(String AssessID) {
    	try {
    		Class.forName (driver); // register the JDBC driver
	    	conn = DriverManager.getConnection (connectionURL,user,pw );
	        stmt = conn.createStatement ();
	        results = stmt.executeQuery
	        		("SELECT avg(score) AS 'Avg for specific CDAI', criteria AS 'Criteria Name' FROM grades WHERE criteria IN (SELECT name FROM `criteria` WHERE unique_id = 'AssessID') ;");
            return results;
    	} catch ( Exception e) { 
            System .out.println (" Error " + e );
        }
    	return results;
    }
    
    // Phase 2 Query 3
    // A query to display the average scores for all criteria in a specific CDAI (identified by id such as A1)
    public static ResultSet getAvgEntireCDAI(String AssessID) {
    	try {
    		Class.forName (driver); // register the JDBC driver
	    	conn = DriverManager.getConnection (connectionURL,user,pw );
	        stmt = conn.createStatement ();
	        results = stmt.executeQuery
	        		("SELECT avg(score) AS 'average', criteria AS 'Criteria Name' FROM grades WHERE criteria IN (SELECT name FROM criteria WHERE CDAI = 'AssessID') GROUP BY criteria ORDER BY criteria;");
            return results;
    	} catch ( Exception e) { 
            System .out.println (" Error " + e );
        }
    	return results;
    }
    
    // Phase 2 Query 4
    // A query to display the average score over all criteria in a specific CDAI in a specific semester (CDAI identified by id such as A1F12)
    public static ResultSet getAvgOfAllCDAI(String AssessID) {
    	try {
    		Class.forName (driver); // register the JDBC driver
	    	conn = DriverManager.getConnection (connectionURL,user,pw );
	        stmt = conn.createStatement ();
	        results = stmt.executeQuery
	        		("SELECT unique_id, AVG(score) AS 'average' FROM(SELECT uni_id, unique_id, score FROM grades g JOIN criteria c WHERE g.criteria = c.name) scores WHERE unique_id = 'A1F10' GROUP BY unique_id ORDER BY uni_id;");
            return results;
    	} catch ( Exception e) { 
            System .out.println (" Error " + e );
        }
    	return results;
    }
    
    // Phase 2 Query 5
    // A query to display the average score over all criteria for a specific CDAI (identified by id such as A1) 
    public static ResultSet getAvgOfAllCDAICriteria(String AssessID) {
    	try {
    		Class.forName (driver); // register the JDBC driver
	    	conn = DriverManager.getConnection (connectionURL,user,pw );
	        stmt = conn.createStatement ();
	        results = stmt.executeQuery
	        		("SELECT avg(score) AS 'average' FROM grades WHERE criteria IN (SELECT name AS 'criteria' FROM criteria WHERE CDAI = 'AssessID');");
            return results;
    	} catch ( Exception e) { 
            System .out.println (" Error " + e );
        }
    	return results;
    }
    
    // Phase 2 Query 6
    // A query to identify the CDAI with the highest average score across all CDAIs.
    public static ResultSet getHighestAvgOfAllCDAI() {
    	try {
    		Class.forName (driver); // register the JDBC driver
	    	conn = DriverManager.getConnection (connectionURL,user,pw );
	        stmt = conn.createStatement ();
	        results = stmt.executeQuery
	        		("SELECT avg( score ) AS 'average', grades.CDAI AS 'CDAI' FROM grades JOIN criteria ON criteria.name = grades.criteria GROUP BY grades.CDAI ORDER BY avg( score ) DESC LIMIT 1;");
            return results;
    	} catch ( Exception e) { 
            System .out.println (" Error " + e );
        }
    	return results;
    }
    
    // Phase 2 Query 7
    // A query to identify the CDAI with the lowest average score across all CDAIs.
    public static ResultSet getLowestAvgOfAllCDAI() {
    	try {
    		Class.forName (driver); // register the JDBC driver
	    	conn = DriverManager.getConnection (connectionURL,user,pw );
	        stmt = conn.createStatement ();
	        results = stmt.executeQuery
	        		("SELECT avg( score ) AS 'average', grades.CDAI AS 'CDAI' FROM grades JOIN criteria ON criteria.name = grades.criteria GROUP BY grades.CDAI ORDER BY avg( score ) ASC LIMIT 1;");
            return results;
    	} catch ( Exception e) { 
            System .out.println (" Error " + e );
        }
    	return results;
    }
    
    // Phase 2 Query 8
    // A query to provide the average, lowest and highest CDAI scores, over all CDAI scores in 
    // each emphasis, grouped by emphasis. Include “undeclared” as its own category.
    public static ResultSet getAllAvgsOfAllCDAI() {
    	try {
    		Class.forName (driver); // register the JDBC driver
	    	conn = DriverManager.getConnection (connectionURL,user,pw );
	        stmt = conn.createStatement ();
	        results = stmt.executeQuery
	        		("SELECT emphasis, avg( score ) AS 'average', max( score ) AS 'maxscore', min( score ) AS 'minscore' FROM grades JOIN student ON student.uni_id = grades.uni_id GROUP BY emphasis ORDER BY emphasis;");
            return results;
    	} catch ( Exception e) { 
            System .out.println (" Error " + e );
        }
    	return results;
    }
  
    // Phase 2 Query 9 - Doesn't work right now.
    // A query to display all the CDAI data (every criteria score earned by that student) for 
    // a student, identified by first name and last name.
    public static ResultSet getCDAIforstudent(String fname, String lname) {
    	try {
    		Class.forName (driver); // register the JDBC driver
	    	conn = DriverManager.getConnection (connectionURL,user,pw );
	        stmt = conn.createStatement ();
	        results = stmt.executeQuery
	        		("SELECT fname, lname, g.CDAI AS 'CDAI', criteria, score FROM grades g JOIN criteria c JOIN student s WHERE g.criteria = c.name AND s.uni_id = g.uni_id AND g.uni_id = (SELECT uni_id FROM student WHERE `fname` LIKE 'fname' AND `lname` LIKE '%lname') ORDER BY g.uni_id;");
            return results;
    	} catch ( Exception e) { 
            System .out.println (" Error " + e );
        }
    	return results;
    }
    
    
    
}