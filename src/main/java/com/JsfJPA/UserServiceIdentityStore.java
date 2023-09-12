package com.JsfJPA;

import com.JsfJPA.services.DataService;
import com.JsfJPA.entities.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;


import java.util.Optional;

@ApplicationScoped
public class UserServiceIdentityStore implements IdentityStore {
    @Inject
    private DataService dataService;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;
        String username = login.getCaller();

        Optional<User> optionalUser = dataService.getUser(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            return new CredentialValidationResult(user.getUsername(), user.getGroups());
        }
        else {
            return CredentialValidationResult.INVALID_RESULT;
        }

    }
}
