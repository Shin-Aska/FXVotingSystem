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
    
    public static ResultSet fetchPosters (String category) throws SQLException {
        if (category.equals("All"))
            return network.executeStatement("SELECT id, id AS 'Poster ID', name FROM candidate ORDER BY id");
        else
            return network.executeStatement("SELECT id, posterID AS 'Poster ID', name FROM candidate WHERE votecategory = ? ORDER BY posterID", "'" + category + "'");
    }
    
    public static ResultSet fetchPosterVotes () throws SQLException {
        return network.executeStatement("SELECT id, name, votecount FROM candidate ORDER BY votecount DESC");
    }
    
    public static ResultSet fetchTickets () throws SQLException {
        return network.executeStatement("SELECT id, value, COUNT(value) AS 'Remaining' FROM ticket GROUP BY value ORDER BY id");
    }
    
    public static void addPoster(String id, String name, String category) throws SQLException {
        network.executeNonStatement("INSERT INTO candidate(posterID, name, votecategory) VALUES(?, ?, ?)", id, "'" + name + "'", "'" + category + "'");
    }
    
    public static void addTicket(String v) throws SQLException {
        //network.executeNonStatement("INSERT INTO ticket(value, category) VALUES(?, 'Qualitative'), (?, 'Quantitative')", "'" + v + "'", "'" + v + "'");
        network.executeNonStatement("INSERT INTO ticket(value, category) VALUES(?, 'Qualitative')", "'" + v + "'");
    }
    
    public static void removeTicket(String v) throws SQLException {
        network.executeNonStatement("DELETE FROM ticket WHERE id = ?", "'" + v + "'");
    }
    
    public static Integer getNextPosterID(String category) throws SQLException {
        int result = 1;
        ResultSet rS = network.executeStatement("SELECT MAX(posterID) FROM candidate WHERE votecategory = ?", "'" + category + "'");
        while (rS.next()) {
            result = rS.getInt(1);
        }
        
        result += 1;
        return result;
    }
    
    public static boolean verifyVoterID (String id, String cat) throws SQLException {
        
        boolean found = false;
        //ResultSet rS = network.executeStatement("SELECT * FROM ticket WHERE value = ? AND category = ?", "'" + id + "'", "'" + cat + "'");
        ResultSet rS = network.executeStatement("SELECT * FROM ticket WHERE value = ?", "'" + id + "'");
        while (rS.next()) {
            found = true;
        }
        
        if (found) {
            //network.executeNonStatement("DELETE FROM ticket WHERE value = ? AND category = ?", "'" + id + "'", "'" + cat + "'");
            network.executeNonStatement("DELETE FROM ticket WHERE value = ?", "'" + id + "'");
        }
        
        return found;
    }
    
    public static void addVote(String candid) throws SQLException {
        network.executeNonStatement("UPDATE candidate SET votecount = votecount + 1 WHERE id = ?", candid);
    }
    
    public static String getCategory (String candid) throws SQLException {
        
        String category = "";
        ResultSet rS = network.executeStatement("SELECT votecategory FROM candidate WHERE id = ?", candid);
        while (rS.next()) {
            category = rS.getString(1);
        }
        
        return category;
    }

    static void disconnect() {
        network = null;
    }
}
