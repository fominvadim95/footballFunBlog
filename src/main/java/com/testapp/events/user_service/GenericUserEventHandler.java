package com.testapp.events.user_service;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.commons.util.SocialType;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.property.UserProperty;
import com.backendless.servercode.ExecutionResult;
import com.backendless.servercode.RunnerContext;
import com.backendless.servercode.annotation.Async;
import com.testapp.models.Statistic;

import java.util.HashMap;
import java.util.Map;
        
/**
* GenericUserEventHandler handles the User Service events.
* The event handlers are the individual methods implemented in the class.
* The "before" and "after" prefix determines if the handler is executed before
* or after the default handling logic provided by Backendless.
* The part after the prefix identifies the actual event.
* For example, the "beforeLogin" method is the "Login" event handler and will
* be called before Backendless applies the default login logic. The event
* handling pipeline looks like this:

* Client Request ---> Before Handler ---> Default Logic ---> After Handler --->
* Return Response
*/
public class GenericUserEventHandler extends com.backendless.servercode.extension.UserExtender
{
    
  @Override
  public void afterLogin( RunnerContext context, String login, String password, ExecutionResult<HashMap> result ) throws Exception
  {
    Statistic statistic =  Backendless.Data.find(Statistic.class, new BackendlessDataQuery()).getCurrentPage().get(0);
    int usersCount = statistic.getUsersCount() + 1;
    statistic.setUsersCount(usersCount);
    Backendless.Data.save(statistic);

  }
    
}
        