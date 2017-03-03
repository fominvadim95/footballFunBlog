package ua.nure.fomin.services;

import com.backendless.BackendlessUser;
import ua.nure.fomin.entities.User;


public interface UserService {

    boolean signUp(User user);

    boolean signIn(User user);

    BackendlessUser find(String name);

    boolean restorePassword(String name);

}
