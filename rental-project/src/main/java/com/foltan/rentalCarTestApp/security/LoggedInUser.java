package com.foltan.rentalCarTestApp.security;

import com.foltan.rentalCarTestApp.domain.User;
import com.foltan.rentalCarTestApp.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LoggedInUser {

        private final UserRepository userRepository;

        public LoggedInUser(UserRepository userRepository) {
                this.userRepository = userRepository;
        }

        public User getUser() {

                Authentication principal = SecurityContextHolder.getContext().getAuthentication();
                String username = principal.getName();
                return userRepository.findByUsername(username).orElseThrow();

        }

}
