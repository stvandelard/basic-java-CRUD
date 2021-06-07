/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD_java;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
/**
 *
 * @author Stevan Del Arisandi 1301184365
 */
public class Database {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ArrayList<Game> game = new ArrayList<>();
    
    public Database(){
        loadGame();
    }
    
    public void connect(){
        try{
            String url = "jdbc:mysql://localhost/game";
            String user = "root";
            String pass = "";
            conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
        } catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnect(){
        try{
            conn.close();
            stmt.close();
        } catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadGame(){
        connect();
        try{
            String query = "SELECT * FROM game";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                game.add(new Game(
                        rs.getString("idGame"),
                        rs.getString("judul"),
                        rs.getString("genre")));
            }
        } catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
    }
    
    public ArrayList<Game> getGame(){
        return game;
    }
    
    public boolean ValidateDuplicate(String idGame){
        boolean duplicate = false;
        connect();
        try{
            String query = "SELECT * FROM game where idgame='"+idGame+"'";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                duplicate = true;
            }
        } catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
        
        return duplicate;
    }
    
    public boolean manipulate(String query){
        boolean cek = false;
        try{
            int rows = stmt.executeUpdate(query);
            if (rows>0) cek = true;
        } catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            cek = false;
        }
        return cek;
    }
    
    public void addGame(Game g){
        connect();
        String query = "INSERT INTO game VALUES (";
        query += "'" + "0"+ "',";
        query += "'" + g.getIdGame()+ "',";
        query += "'" + g.getJudul()+ "',";
        query += "'" + g.getGenre()+ "'";
        query += ")";
        if (manipulate(query)) game.add(g);
        disconnect();
    }
    
    public void updateGame(Game g){
        connect();
        String query = "UPDATE game SET ";
        query += "idgame='" + g.getIdGame()+ "', ";
        query += "judul='" + g.getJudul()+ "', ";
        query += "genre='" + g.getGenre()+ "' ";
        query += "WHERE idgame='" + g.getIdGame() + "'";
        manipulate(query);
        disconnect();
    }
    
    public void delGame(Game g){
        connect();
        String query = "DELETE FROM game WHERE idgame='" + g.getIdGame() + "'";
        manipulate(query);
        disconnect();
    }    
}
