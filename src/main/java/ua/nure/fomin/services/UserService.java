package ua.nure.fomin.services;

import com.backendless.BackendlessUser;
import ua.nure.fomin.entities.User;

import java.util.List;


public interface UserService {

    boolean signUp(User user);

    boolean signIn(User user);

    BackendlessUser find(String name);

    boolean restorePassword(String name);

    List<String> getAll();

}
