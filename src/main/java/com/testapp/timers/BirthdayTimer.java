package com.testapp.timers;


import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.servercode.annotation.BackendlessTimer;
import com.backendless.servercode.extension.TimerExtender;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@BackendlessTimer("{'startDate':1491066000000,'frequency':{'schedule':'daily','repeat':{'every':1}},'timername':'BirthdayTimer'}")
public class BirthdayTimer extends TimerExtender {

    private static final String DEVELOPER_EMAIL = "vadym.fomin@nure.ua";

    @Override
    public void execute(String s) throws Exception {
        LocalDate today = LocalDate.now();
        List<BackendlessUser> users = Backendless.Data.find(BackendlessUser.class,new BackendlessDataQuery()).getCurrentPage();
        for(BackendlessUser user : users ){
           String birthday = (String) user.getProperties().get("created");
           List<Integer> numbers = Arrays.asList(birthday.split("/")).stream().map(number->Integer.parseInt(number)).collect(Collectors.toList());
            if(today.getDayOfMonth() == numbers.get(0) && today.getMonth().getValue() == numbers.get(1) && today.getYear() == numbers.get(2)){
                Backendless.Messaging.sendTextEmail("Birthday","Happy birthday", Arrays.asList(DEVELOPER_EMAIL));
            }
        }




    }
}
