package com.JsfJPA.controllers;

import com.JsfJPA.entities.User;
import com.JsfJPA.services.DataService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

@RequestScoped
@Named
public class LoginController{

    @NotEmpty
    private String username;

//    @NotEmpty
    private String password;

    @Inject
    FacesContext facesContext;

    @Inject
    ExternalContext externalContext;

    @Inject
    SecurityContext securityContext;

    @Inject
    private DataService dataService;

    private String userOtpCode;


    public String getUserOtpCode() {
        return userOtpCode;
    }

    public void setUserOtpCode(String userOtpCode) {
        this.userOtpCode = userOtpCode;
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

    public void verifyCredentials() throws IOException {
        Optional<User> optionalUser = dataService.findByUsernameAndPassword(username, password);

        if (optionalUser.isPresent()) {
            externalContext.getSessionMap().put("username", username);
            externalContext.getSessionMap().put("group", optionalUser.get().getGroup());
//            externalContext.getSessionMap().put("OTP", "123456");
//            externalContext.redirect(externalContext.getRequestContextPath() + "/otpPage.xhtml");
            execute();
        }
        else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Credentials", null));
        }
    }

//    public void verifyOtp() throws IOException {
//        if(externalContext.getSessionMap().get("OTP").equals(userOtpCode)) {
//            username = (String) externalContext.getSessionMap().get("username");
//            execute();
//        }
//    }

    public void execute() throws IOException {
        switch(processAuthentication()) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Credentials", null));
                break;
            case SUCCESS:
                if (externalContext.getSessionMap().get("group").equals("admin"))
                    externalContext.redirect(externalContext.getRequestContextPath() + "/admins");
                else if (externalContext.getSessionMap().get("group").equals("user"))
                    externalContext.redirect(externalContext.getRequestContextPath() + "/app");
                else
                    externalContext.redirect(externalContext.getRequestContextPath() + "index.xhtml");
        }
    }

    private AuthenticationStatus processAuthentication () throws IOException {
        return securityContext.authenticate((HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(username, "guru")));
    }
}




