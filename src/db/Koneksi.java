/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author whois
 */
public class Koneksi {
    
        // just for simple app univ, no need to write credential in env
        private final String URL = "jdbc:mysql://localhost:3306/rental_mobil";
        private final String USER = "root";
        private final String PASS = "password";
        
        public Connection getConnection(){
            Connection con;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(URL, USER, PASS);
            } catch(ClassNotFoundException | SQLException ex) {
                System.out.println("Koneksi Ke Database Gagal " + ex.getMessage());
                con=null;
            }
            return con;
        }
        
        public static void main(String[] args){
            Koneksi koneksi = new Koneksi();
            koneksi.getConnection();
        }
}
