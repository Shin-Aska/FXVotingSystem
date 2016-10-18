/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingsystem;

import Database.MySQL;
import Database.SQLite;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Richard
 */
public class ConnectionController {
    
    private static MySQL network;
    private static SQLite local;
    
    public static void connect() throws SQLException {
                
        try {
            network = new MySQL(Configuration.ipaddress, "votingsystem-um", Configuration.username, Configuration.password);
        }
        catch (SQLException ex) {
            throw ex;
        }
    }
    
    public static boolean validateUser(String username, String password) throws SQLException {
        
        ResultSet set = network.executeStatement("SELECT * FROM User WHERE username = ? AND password = ?", "'" + username + "'", "'" + password + "'");
        while (set.next()) {
            return true;
        }
        return false;
    }
    
    public static ResultSet fetchPosters () throws SQLException {
        return network.executeStatement("SELECT id, name FROM candidate");
    }
    
    public static ResultSet fetchTickets () throws SQLException {
        return network.executeStatement("SELECT id, value FROM ticket");
    }
    
    public static void addPoster(String id, String name) throws SQLException {
        network.executeNonStatement("INSERT INTO candidate(id, name) VALUES(?, ?)", id, "'" + name + "'");
    }
    
    public static void addTicket(String v) throws SQLException {
        network.executeNonStatement("INSERT INTO ticket(value) VALUES(?)", "'" + v + "'");
    }
    
    public static void removeTicket(String v) throws SQLException {
        network.executeNonStatement("DELETE FROM ticket WHERE id = ?", "'" + v + "'");
    }
    
    public static Integer getNextPosterID() throws SQLException {
        int result = 1;
        ResultSet rS = network.executeStatement("SELECT MAX(id) FROM candidate");
        while (rS.next()) {
            result = rS.getInt(1);
        }
        
        result += 1;
        return result;
    }
    
    public static boolean verifyVoterID (String id) throws SQLException {
        
        boolean found = false;
        ResultSet rS = network.executeStatement("SELECT * FROM ticket WHERE value = ?", "'" + id + "'");
        while (rS.next()) {
            found = true;
        }
        
        if (found) {
            network.executeNonStatement("DELETE FROM ticket WHERE value = ?", "'" + id + "'");
        }
        return found;
    }
    
    public static void addVote(String candid) throws SQLException {
        network.executeNonStatement("UPDATE candidate SET votecount = votecount + 1 WHERE id = ?", candid);
    }
}
