package com.JsfJPA.controllers;

import com.JsfJPA.entities.Quality;
import com.JsfJPA.entities.User;
import com.JsfJPA.services.DataService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

@RequestScoped
@Named
public class HomeController {
    @Inject
    DataService dataService;

    @Inject
    SecurityContext securityContext;

    @Inject
    FacesContext facesContext;

    private Optional<User> currentUser;
    private List<Quality> currentQualities;

    @PostConstruct
    public void initialize() {
        String username = securityContext.getCallerPrincipal().getName();
        this.currentUser = dataService.getUser(username);
        this.currentUser.ifPresent(user -> {
            this.currentQualities = dataService.getQualities(user);
        });
    }

    public User getCurrentUser() {
        return this.currentUser.orElse(null);
    }

    public List<Quality> getCurrentQualities() {
        return currentQualities;
    }

    public String logout() throws ServletException {
        ExternalContext ec = facesContext.getExternalContext();
        ((HttpServletRequest)ec.getRequest()).logout();
        return "/login.xhtml?faces-redirect=true";
    }
}