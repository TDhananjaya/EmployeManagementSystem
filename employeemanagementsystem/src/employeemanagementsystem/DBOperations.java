/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagementsystem;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author Dhana
 */
public class DBOperations {

    String url = "jdbc:mysql://localhost:3306/employee";
    String username = "root";
    String password = "";
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs= null;

    boolean addEmployee(EmployeeDetails em) {
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);//get the connection
            String query = "INSERT INTO employeedetails VALUES(?,?,?,?,?,?,?,?)";

            pst = (PreparedStatement) con.prepareStatement(query);

            pst.setInt(1, 0); // add values to the sql query
            pst.setString(2, em.getFirstName()); // add values to the sql query
            pst.setString(3, em.getLastName()); // add values to the sql query
            pst.setInt(4, em.getAge()); // add values to the sql query
            pst.setString(5, em.getCountry()); // add values to the sql query
            pst.setString(6, em.getEmail()); // add values to the sql query
            pst.setString(7, em.getUsername()); // add values to the sql query
            pst.setString(8, em.getPassword()); // add values to the sql query

            pst.executeUpdate(); // execute the sql query and insert the values to the db tables

            return true;

        } catch (Exception e) {
            System.out.print(e);
            return false;

        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
            }

        }
    }

    int checkUsername(String username) {
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
              String query = "SELECT username FROM employeedetails ";
              
              pst = (PreparedStatement)con.prepareStatement(query);
             rs =  pst.executeQuery();
             
             while(rs.next()){
                    if(username.equals(rs.getString(1))){
                        return 0;//user name provied already in db
                    }
             }
             return 1;//  user name dpes NOT exists db
             
             
        } catch (Exception e) {
            System.out.print(e);
            return 2;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
            }
        }
    }
}
