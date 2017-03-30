package ua.nure.fomin.services.impl;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.persistence.BackendlessDataQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.fomin.entities.Place;
import ua.nure.fomin.entities.User;
import ua.nure.fomin.services.FileService;
import ua.nure.fomin.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private FileService fileService;

    public UserServiceImpl() {
    }

    @Override
    public boolean signUp(User user) {
        try {
            BackendlessUser backendlessUser = new BackendlessUser();
            backendlessUser.setProperty("name", user.getName());
            backendlessUser.setProperty("password", user.getPassword());
            backendlessUser.setProperty("email", user.getEmail());
            backendlessUser.setProperty("age", user.getAge());
            backendlessUser.setProperty("sex", user.getSex());
            backendlessUser.setProperty("country", user.getCountry());
            Backendless.UserService.register(backendlessUser);
            fileService.createWorkDirectory(user.getName());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean signIn(User user) {
        try {
            Backendless.UserService.login(user.getName(), user.getPassword());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public BackendlessUser find(String name) {
        BackendlessDataQuery query = new BackendlessDataQuery();
        query.setWhereClause("name = '" + name + "'");
        BackendlessUser user = new BackendlessUser();
        try {
            user = Backendless.Persistence.of(BackendlessUser.class).find(query).getData().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean restorePassword(String name) {
        BackendlessUser user = find(name);
        if (name != null && !user.getProperties().isEmpty()) {
            try {
                Backendless.UserService.restorePassword(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> getAll() {
        return Backendless.Data.find(BackendlessUser.class,new BackendlessDataQuery()).getCurrentPage().stream().map(user->(String)user.getProperties().get("name")).collect(Collectors.toList());
    }
}
