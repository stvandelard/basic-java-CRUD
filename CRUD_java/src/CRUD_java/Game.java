/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD_java;

/**
 *
 * @author Stevan Del Arisandi 1301184365
 */
public class Game {
    private String idGame;
    private String judul;
    private String genre;

    public Game(String idGame, String judul,String genre) {
        this.idGame = idGame;
        this.judul = judul;
        this.genre = genre;  
    }

    public String getIdGame() {
        return idGame;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String toString() {
        String data = "ID Game : " + idGame + "\n"
                + "Judul Game : " + judul + "\n"
                + "Genre : " + genre  ;
                
        return data;
    }
}
