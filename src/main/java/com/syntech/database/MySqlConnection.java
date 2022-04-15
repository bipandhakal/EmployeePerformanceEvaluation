package com.syntech.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author bipan
 */
public class MySqlConnection {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/EPE", "root", "toor");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("INSERT INTO Employee(ID,fName,lName,joined_date) VALUES(1,'Bipan','Dhakal','2022-02-03')");
            while (rs.next()) {
                System.out.println(rs);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
