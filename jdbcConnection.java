package utils;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;
   public class jdbcConnection
   {
       @SuppressWarnings("deprecation")
	public static void main (String[] args) 
       {
           System.out.println("\n\n***** MySQL JDBC Connection Testing *****");
		   Connection conn = null;
           try
           {
        	   
		      java.sql.Driver d=new com.mysql.cj.jdbc.Driver();
        	   d.connect("com.mysql.cj.jdbc.Driver",null);
        	   String userName = "root";
               String password = "root@1234";
               String url = "jdbc:MySQL://localhost:3306/sakila";    
               conn = DriverManager.getConnection (url, userName, password);
			  
            System.out.println ("\nDatabase Connection Established...");
            
            String sql = "select city_id,city from city";
            java.sql.Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            boolean recs=false;
            int size=0;
            // loop through the result set
           
            while (rs.next()) {
			  size++;
			   System.out.println(rs.getInt("city_id")+" " + rs.getString("city") );
			      
           }
            System.out.println("Number of records :"+ size);
            
            if (size >0 )
			
			   {recs = true;}
            else
			   {recs=false;}
            
           }
           
          catch (Exception ex)
           {
		       System.err.println ("Cannot connect to database server");
			   ex.printStackTrace();
           }      
		   
		   finally
           {
               if (conn != null)
               {
                   try
                   {
                       System.out.println("\n***** Let us terminate the Connection *****");
					   conn.close ();					   
                       System.out.println ("\nDatabase connection terminated...");
                   }
                   catch (Exception ex)
				   {
				   System.out.println ("Error in connection termination!");
				   }
               }
           }
           
       }
   }
