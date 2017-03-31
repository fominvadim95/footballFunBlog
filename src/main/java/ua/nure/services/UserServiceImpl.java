package ua.nure.services;


import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.servercode.IBackendlessService;

import java.util.List;

public class UserServiceImpl implements IBackendlessService, UserService {

    public List<BackendlessUser> getUsers() {
        return Backendless.Data.find(BackendlessUser.class, new BackendlessDataQuery()).getCurrentPage();
    }

    @Override
    public int getUsersCount() {
        return getUsers().size();
    }

    @Override
    public void addUser(BackendlessUser user) {
        Backendless.UserService.register(user);

    }

    @Override
    public void setUser(BackendlessUser user) {
        Backendless.UserService.update(user);

    }

    @Override
    public void deleteUser(BackendlessUser user) {
        Backendless.Persistence.of(BackendlessUser.class).remove(user);

    }

    @Override
    public List<BackendlessUser> getUsersByCountry(String country) {
        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setWhereClause("country = '" + country + "'");
        return Backendless.Data.find(BackendlessUser.class, query).getCurrentPage();
    }


}
