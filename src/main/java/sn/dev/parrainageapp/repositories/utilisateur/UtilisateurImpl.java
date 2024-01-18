package sn.dev.parrainageapp.repositories.utilisateur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sn.dev.parrainageapp.DBConnection;
import sn.dev.parrainageapp.entities.Role;
import sn.dev.parrainageapp.entities.Utilisateur;
import sn.dev.parrainageapp.repositories.role.IRole;
import sn.dev.parrainageapp.repositories.role.RoleImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurImpl implements IUtilisateur{
    private DBConnection db = new DBConnection();
    private ResultSet rs;
    @Override
    public Utilisateur seConnecter(String login, String password) {
        String sql = "SELECT * FROM user WHERE login = ? AND password = ?";
        Utilisateur user = null;
        try{
            //Préparation ou initialisation de la requete
            db.initPrepar(sql);
            //Passage de valeurs
            db.getPstm().setString(1, login);
            db.getPstm().setString(2, password);
            //Execution de la requete
            rs = db.executeSelect();
            if(rs.next()){
                user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString(3));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString(5));
                user.setActived(rs.getInt("actived"));
                IRole iRole = new RoleImpl();
                Role profil = iRole.getRoleById(rs.getInt("profil"));
                user.setProfil(profil);
            }
            db.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public ObservableList<Utilisateur> getAllUsers(){
            ObservableList<Utilisateur> users = FXCollections.observableArrayList();
            String sql = "SELECT * FROM user ORDER BY nom ASC";
            try{
                //Préparation ou initialisation de la request
                db.initPrepar(sql);
                //Execution de la request
                rs = db.executeSelect();
                while(rs.next()){
                    users.add(setUser(rs));
                }
                db.closeConnection();
            }catch (Exception e){
                e.printStackTrace();
            }
            return users;
    }

    private Utilisateur setUser(ResultSet rs) throws SQLException {
        Utilisateur user = new Utilisateur();
        user.setId(rs.getInt("id"));
        user.setNom(rs.getString("nom"));
        user.setPrenom(rs.getString("prenom"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setActived(rs.getInt("actived"));
        IRole iRole = new RoleImpl();
        Role profil = iRole.getRoleById(rs.getInt("profil"));
        user.setProfil(profil);

        return user;
    }

    public int storeUser(Utilisateur user){
        String sql = "INSERT INTO user values(null, ?, ?, ?, ?, ?, ?)";

        int ok ;
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, user.getNom());
            db.getPstm().setString(2, user.getPrenom());
            db.getPstm().setString(3, user.getLogin());
            db.getPstm().setString(4, user.getPassword());
            db.getPstm().setInt(5, user.getActived());
            db.getPstm().setInt(6, user.getProfil().getId());

            ok = db.executeMaj();
            db.closeConnection();
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return ok;
    }

}
