/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD_java;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 *
 * @author Stevan Del Arisandi 1301184365
 */
public class Handler extends MouseAdapter implements ActionListener{
    private FormInput form;
    private Database db;

    public Handler() {
        form = new FormInput();
        db = new Database();
        form.addActionListener(this);
        form.addMouseAdapter(this);
        form.setVisible(true);
        form.setListGame(getListGame());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(form.getBtnAdd())) {
            btnAddActionPerformed();
        }
        else if (source.equals(form.getBtnDelete())){
            btnDeleteActionPerformed();
        }
        else if (source.equals(form.getBtnEdit())){
            btnEditActionPerformed();
        }
    }
    
    public void btnAddActionPerformed(){
        String idGame = form.getIdGame();
        String judul = form.getJudul();
        String genre = form.getGenre();
        Game g = new Game(idGame, judul, genre);
        if (idGame.isEmpty() || judul.isEmpty() || genre.isEmpty()){
            form.showMessage("Data Kosong", "Error", 0);
        }else{
            if (db.ValidateDuplicate(idGame)){
                form.showMessage("ID Sudah Ada", "Error", 0);
            } else {
                db.addGame(g);
                form.resetView();
                form.setListGame(getListGame());
                form.showMessage("Data Berhasil Ditambah", "Success", 1);
            }
            
            
        }
    }
    
    public void  btnDeleteActionPerformed(){   
        int i = form.getSelectedGame();
        db.delGame(db.getGame().get(i));
        System.out.println();
        db.getGame().remove(i);
        form.resetView();
        form.setListGame(getListGame());
        form.showMessage("Data Berhasil Dihapus", "Success", 1);     
    }
    
    public void btnEditActionPerformed(){
        int i = form.getSelectedGame();
        String idGame = form.getIdGame();
        String judul = form.getJudul();
        String genre = form.getGenre();
        Game g = new Game(idGame, judul, genre);
        db.updateGame(g);
        if (idGame.isEmpty() || judul.isEmpty() || genre.isEmpty()){
            form.showMessage("Data Kosong", "Error", 0);
        }else{
            db.getGame().set(i,g);
            form.resetView();
            form.setListGame(getListGame());
            form.showMessage("Data Berhasil Diedit", "Success", 1);   
        }
    }
    
    private String[] getListGame(){
        String[] ListID = new String[db.getGame().size()];
        for (int i = 0; i < ListID.length; i++) {
            ListID[i] = db.getGame().get(i).getIdGame();
        }
        return ListID;
    }

    public void mousePressed(MouseEvent me){
        Object source = me.getSource();
        if (source.equals(form.getListGame())) {
            int i = form.getSelectedGame();
            Game g = db.getGame().get(i);
            form.setTextGame(g.toString());
            form.setIdGame(g.getIdGame());
            form.setJudul(g.getJudul());
            form.setGenre(g.getGenre());
        }
    }    
}
