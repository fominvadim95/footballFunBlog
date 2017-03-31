package ua.nure.fomin.listeners;


import com.backendless.Backendless;
import com.backendless.persistence.BackendlessDataQuery;
import ua.nure.fomin.entities.Statistic;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        Statistic statistic = Backendless.Data.find(Statistic.class, new BackendlessDataQuery()).getCurrentPage().get(0);
        int usersCount = statistic.getUsersCount() - 1;
        statistic.setUsersCount(usersCount);
        Backendless.Data.save(statistic);

    }
}
