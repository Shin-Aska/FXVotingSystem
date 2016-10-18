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
public class SQLite extends Core {
    
    public SQLite(String database) throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:" + database + ".db");
    }
}
