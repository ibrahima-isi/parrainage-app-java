package sn.dev.parrainageapp.repositories.utilisateur;

import javafx.collections.ObservableList;
import sn.dev.parrainageapp.entities.Utilisateur;

public interface IUtilisateur {
    public Utilisateur seConnecter(String login, String password);
    public ObservableList<Utilisateur> getAllUsers();
    public int storeUser(Utilisateur user);
}
