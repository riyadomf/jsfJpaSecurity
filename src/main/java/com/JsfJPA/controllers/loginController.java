package com.JsfJPA.controllers;

import com.JsfJPA.entities.User;
import com.JsfJPA.services.DataService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

@RequestScoped
@Named
public class loginController implements Serializable{
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

    private String generatedOtpCode;
    private String userOtpCode;
    private Integer secret = 30303030;

    public String getGeneratedOtpCode() {
        return generatedOtpCode;
    }

    public void setGeneratedOtpCode(String generatedOtpCode) {
        this.generatedOtpCode = generatedOtpCode;
    }

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
            externalContext.getSessionMap().put("OTP", "123456");
            externalContext.redirect(externalContext.getRequestContextPath() + "/otpPage.xhtml");
        }
        else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Credentials", null));
        }
    }

    public void verifyOtp() throws IOException {
        if(externalContext.getSessionMap().get("OTP").equals(userOtpCode)) {
            username = (String) externalContext.getSessionMap().get("username");
            execute();
        }
    }


    public void execute() throws IOException {
        switch(processAuthentication()) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Credentials", null));
                break;
            case SUCCESS:
                externalContext.redirect(externalContext.getRequestContextPath() + "/admins/index.xhtml");
        }
    }

    private AuthenticationStatus processAuthentication () throws IOException {
        return securityContext.authenticate((HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(username, "guru")));
    }
}




