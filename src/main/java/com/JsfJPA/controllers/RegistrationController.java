package com.JsfJPA.controllers;

import com.JsfJPA.services.DataService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotEmpty;
import com.JsfJPA.entities.User;
import jakarta.validation.constraints.Null;
import org.primefaces.event.FlowEvent;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

@ViewScoped
@Named
public class RegistrationController implements Serializable {
//    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class.getName());
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

    @NotEmpty
    private String phoneNumber;
    private String userOtp;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserOtp() {
        return userOtp;
    }

    public void setUserOtp(String userOtp) {
        this.userOtp = userOtp;
    }

    public void sendOtp() throws IOException {
        externalContext.getSessionMap().put("OTP", "123456");
    }

    public boolean verifyOtp() throws IOException {
        return externalContext.getSessionMap().get("OTP").equals(userOtp);
    }

    public void registerUser() throws IOException {
        User newUser = dataService.createUser(firstName, lastName, username, phoneNumber, password, "user");
        if(newUser != null)   {
            externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
        }
        else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "User creation failed!", null));
        }
    }

    public String onFlowProcess(FlowEvent event) throws IOException {
        String nextStep = event.getNewStep();
        if(nextStep.equals("otp")) {
            sendOtp();
        }
        else if (nextStep.equals("personal"))  {
            if (!verifyOtp()) {
                nextStep = "otp";
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incorrect Otp!", null));
            }
        }
        return nextStep;
    }
}
