package com.JsfJPA.services;

import com.JsfJPA.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class DataInitializer {

    @Inject
    DataService dataService;

    public void execute(@Observes @Initialized(ApplicationScoped.class) Object event){
        if(dataService.getAllUsers().isEmpty()){
            User sally = dataService.createUser("sakib", "test123", "sakib", "123456", "test123", "admin");
            User tom = dataService.createUser("litton", "Matthews", "litton", "123456", "test123",  "user");

            dataService.createQuality("Wonderful", sally);
            dataService.createQuality("Team Player", sally);
            dataService.createQuality("Good Judgement", sally);
            dataService.createQuality("Good Leader", sally);

            dataService.createQuality("Dilligent", tom);

            dataService.createQuality("Responsible", tom);
            dataService.createQuality("Cares for his teammates", tom);
        }
    }
}
