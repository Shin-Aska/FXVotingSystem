/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Richard
 */
public class MySQL extends Core {
    
    public MySQL(String url, String db, String uname, String pass) throws SQLException {
        String connString = "jdbc:mysql://" + url + "/" + db;
        conn = DriverManager.getConnection(connString, uname, pass);
    }
}
