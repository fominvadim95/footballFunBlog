package ua.nure.services;


import com.backendless.BackendlessUser;

import java.util.List;

public interface UserService {

    List<BackendlessUser> getUsers();

    int getUsersCount();

    void addUser(BackendlessUser user);

    void setUser(BackendlessUser user);

    void deleteUser(BackendlessUser user);

    List<BackendlessUser> getUsersByCountry(String country);
}
