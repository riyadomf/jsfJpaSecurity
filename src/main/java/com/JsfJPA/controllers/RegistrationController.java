package com.JsfJPA.controllers;

import com.JsfJPA.services.DataService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotEmpty;
import com.JsfJPA.entities.User;
import jakarta.validation.constraints.Null;

import java.io.IOException;

@RequestScoped
@Named
public class RegistrationController {
    @Inject
    DataService dataService;
    @Inject
    ExternalContext externalContext;

    @Inject
    FacesContext facesContext;
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String group;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public  void registerUser() throws IOException {
        User newUser = dataService.createUser(firstName, lastName, username, password, group);
        if(newUser != null)   {
            externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
        }
        else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User creation failed!", null));
        }
    }
}
