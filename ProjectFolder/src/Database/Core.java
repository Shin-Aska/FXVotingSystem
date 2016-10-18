/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Richard
 */
public class Core {
    
    public Connection conn;
    
    public void executeNonStatement (String statement, String ... params) throws SQLException {
        
        PreparedStatement stmt = conn.prepareStatement(statement);
        int pCounter   = 1;
        
        for (String p : params) {
            if (p.charAt(0) == '\'' && p.charAt(p.length() - 1) == '\'') {
                p = p.substring(1, p.length() - 1);
                stmt.setString(pCounter, p);
            }
            else {
                
                try {
                    double val = Double.parseDouble(p);
                    stmt.setDouble(pCounter, val);
                }
                catch (NumberFormatException ex) {
                    
                    try {
                        
                        int val = Integer.parseInt(p);
                        stmt.setInt(pCounter, val);
                    }
                    catch (NumberFormatException ex2) {
                        
                    }
                }
            }
            
            pCounter += 1;
        }
        
        stmt.execute();
    }
    
    public ResultSet executeStatement (String statement, String ... params) throws SQLException {
        
        PreparedStatement stmt = conn.prepareStatement(statement);
        int pCounter   = 1;
        
        for (String p : params) {
            if (p.charAt(0) == '\'' && p.charAt(p.length() - 1) == '\'') {
                p = p.substring(1, p.length() - 1);
                stmt.setString(pCounter, p);
            }
            else {
                
                try {
                    double val = Double.parseDouble(p);
                    stmt.setDouble(pCounter, val);
                }
                catch (NumberFormatException ex) {
                    
                    try {
                        
                        int val = Integer.parseInt(p);
                        stmt.setInt(pCounter, val);
                    }
                    catch (NumberFormatException ex2) {
                        
                    }
                }
            }
            
            pCounter += 1;
        }
        
        return stmt.executeQuery();
    }
}
