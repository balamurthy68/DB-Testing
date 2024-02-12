package utils;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;
   public class jdbcValidateTableStructure
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
            
//            String sql = "select city_id,city from city where city='Chennai'";
            String sql = "describe city";
            java.sql.Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            boolean recs=false;
            int size=0;
            // loop through the result set
           //here we can validate the column type and definition
            while (rs.next()) {
			   //System.out.println(rs.getInt("city_id")+" " + rs.getString("city") );
            	
            	//Validate if city_id is primary key
            	if (size ==0)
            	{
            		if (rs.getObject(4).equals("PRI"))
            		{
            			System.out.println("City_id is the primary key. Table Structure Test Passed");
            		}
            		else
            		{
            			System.out.println("City_id is NOT the primary key. Table Structure Test Failed");
                			
            		}
            	}
			  System.out.println(rs.getObject(1) + " " + rs.getObject(2) + " " + rs.getObject(3) + " " + rs.getObject(4) );
			   
			  
			  size++;
			      
           }
            //System.out.println("Number of records :"+ size);
            System.out.println("columns :"+ size);
            
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
