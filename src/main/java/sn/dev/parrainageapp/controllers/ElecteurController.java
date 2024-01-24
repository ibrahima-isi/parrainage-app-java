package sn.dev.parrainageapp.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.dev.parrainageapp.entities.Utilisateur;
import sn.dev.parrainageapp.repositories.utilisateur.IUtilisateur;
import sn.dev.parrainageapp.repositories.utilisateur.UtilisateurImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class ElecteurController {
    IUtilisateur iUser = new UtilisateurImpl();


    @FXML
    private TableColumn<Utilisateur, String> monParrainage;

    @FXML
    private TableColumn<Utilisateur, String> nomCandidat;

    @FXML
    private TableColumn<Utilisateur, Integer> parrainagerCol;

    @FXML
    private TableColumn<Utilisateur, Boolean> prenomCandidat;
    @FXML
    private TableView<Utilisateur> tableView;
    public void loadTable() {
        ObservableList<Utilisateur> liste = iUser.getAllUsers();
        tableView.setItems(liste);
        nomCandidat.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCandidat.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//        COlLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
//        ColROle.setCellValueFactory(new PropertyValueFactory<>("profil"));
//        ColStatus.setCellValueFactory(new PropertyValueFactory<>("actived"));
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
//        loadCbb();
    }

}
