package sn.dev.parrainageapp.repositories.role;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sn.dev.parrainageapp.DBConnection;
import sn.dev.parrainageapp.entities.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleImpl implements IRole{
    private DBConnection db = new DBConnection();
    private ResultSet rs;
    @Override
    public Role getRoleById(int id) {
        String sql = "SELECT * FROM role WHERE id = ?";
        Role role = null;
        try{
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            rs = db.executeSelect();
            if(rs.next()){
                role = new Role(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt("etat")
                );
            }
            db.closeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return role;
    }
    public ObservableList<Role> getAllRoles() {
        ObservableList<Role> roles = FXCollections.observableArrayList();
        String sql = "SELECT * FROM role where id > 1";
        try{
            db.initPrepar(sql);
            rs = db.executeSelect();
            while (rs.next()){
                roles.add(new Role(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt("etat"))
                );
            }
            db.closeConnection();
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return roles;
    }
}
