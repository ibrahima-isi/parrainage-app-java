package sn.dev.parrainageapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import sn.dev.parrainageapp.DBConnection;
import sn.dev.parrainageapp.entities.Utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CandidatController {

    @FXML
    private ListView<Utilisateur> candidatView;

    @FXML
    private ListView<Utilisateur> electeurView;

    Utilisateur user = new Utilisateur();
    DBConnection db = new DBConnection();
    public ResultSet getCandidat(){
        ResultSet rs = null;
        String sql = "SELECT * FROM user where profil = 2";
            db.initPrepar(sql);
            rs = db.executeSelect();
            db.closeConnection();

        return rs;
    }

}

