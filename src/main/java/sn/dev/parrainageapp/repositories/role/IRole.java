package sn.dev.parrainageapp.repositories.role;

import javafx.collections.ObservableList;
import sn.dev.parrainageapp.entities.Role;

public interface IRole {
    public Role getRoleById(int id);
    ObservableList<Role> getAllRoles();
}
