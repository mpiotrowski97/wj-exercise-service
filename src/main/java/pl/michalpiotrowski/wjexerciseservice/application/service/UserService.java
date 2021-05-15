package pl.michalpiotrowski.wjexerciseservice.application.service;

import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getCurrentUserName() {
        val authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }
}
