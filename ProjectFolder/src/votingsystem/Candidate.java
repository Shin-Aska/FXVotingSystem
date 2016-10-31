/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingsystem;

import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public class Candidate {
    
    public static ArrayList<Candidate> list = new ArrayList<Candidate>();
    public String id, postID, name;
    
    public Candidate(String i, String p, String n) {
        id     = i;
        postID = p;
        name   = n;
    }
}
