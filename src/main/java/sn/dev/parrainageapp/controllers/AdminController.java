package sn.dev.parrainageapp.controllers;

import javafx.scene.control.*;
import sn.dev.parrainageapp.entities.Utilisateur;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import sn.dev.parrainageapp.entities.Role;
import sn.dev.parrainageapp.repositories.role.IRole;
import sn.dev.parrainageapp.repositories.role.RoleImpl;
import sn.dev.parrainageapp.repositories.utilisateur.IUtilisateur;
import sn.dev.parrainageapp.repositories.utilisateur.UtilisateurImpl;
import sn.dev.parrainageapp.tools.Notification;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    IUtilisateur iUser = new UtilisateurImpl();

    @FXML
    private ComboBox<Role> RoleCbb;

    @FXML
    private TableView<Utilisateur> tableView;

    @FXML
    private TableColumn<Utilisateur, String> COlLogin;

    @FXML
    private TableColumn<Utilisateur, Integer> ColID;

    @FXML
    private TableColumn<Utilisateur, String> ColNom;

    @FXML
    private TableColumn<Utilisateur, String> ColPrenom;

    @FXML
    private TableColumn<Utilisateur, Role> ColROle;

    @FXML
    private TableColumn<Utilisateur, Integer> ColStatus;

    @FXML
    private PasswordField mdpPwf;

    @FXML
    private TextField nomTfd;

    @FXML
    private TextField prenomTfd1;

    @FXML
    void creationBtn(ActionEvent event) {
        Utilisateur user = new Utilisateur();
        user.setActived(1);
        user.setNom(nomTfd.getText());
        user.setPrenom(prenomTfd1.getText());
        user.setPassword(mdpPwf.getText());
        IRole role = new RoleImpl();
        Role profil = role.getRoleById(RoleCbb.getValue().getId());
        user.setProfil(profil);
        user.setLogin(String.format("%s%d", nomTfd.getText(), prenomTfd1.getText().length()));
        int ok = iUser.storeUser(user);
        if(ok == 1){
            Notification.NotifSuccess("succes", "Utilisateur creer");
            loadTable();
            clearField();
        }
        else Notification.NotifError("error", "Echec de creation");
    }

    public void loadTable() {
        ObservableList<Utilisateur> liste = iUser.getAllUsers();
        tableView.setItems(liste);
        ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ColPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        COlLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        ColROle.setCellValueFactory(new PropertyValueFactory<>("profil"));
        ColStatus.setCellValueFactory(new PropertyValueFactory<>("actived"));
    }

    public void loadCbb() {
        IRole iRole = new RoleImpl();
        ObservableList<Role> roles = iRole.getAllRoles();
        RoleCbb.getItems().addAll(roles);
    }

    public void clearField() {
        nomTfd.setText("");
        prenomTfd1.setText("");
        mdpPwf.setText("");
        RoleCbb.getItems().clear();
        loadCbb();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
        loadCbb();
    }


}

