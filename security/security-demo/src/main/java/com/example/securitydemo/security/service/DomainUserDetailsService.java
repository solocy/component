package com.example.securitydemo.security.service;

import com.example.securitydemo.domain.Menu;
import com.example.securitydemo.domain.User;
import com.example.securitydemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;


    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        return userRepository.findOneByAccount(lowercaseLogin)
            .map(user -> createSpringSecurityUser(user))
            .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {
        String roles = user.getRoles().stream()
            .flatMap(authority -> {
                    authority.getMenus().size();
                    return authority.getMenus().stream();
                }
            ).distinct()
            .map(Menu::getPermission)
            .collect(Collectors.joining(","));
//        if (Objects.isNull(user.getLastTryTime())
//            || user.getLastTryTime().plusSeconds(validationProperties.getLogin().getTime()).compareTo(Instant.now())< 0 ) {
//                user.setLock(false);
//                user.setLastTryTime(Instant.now());
//                user.setAttempts(0);
//                userRepository.save(user);
//        }
        return new org.springframework.security.core.userdetails.User(user.getAccount(), user.getPassword(),
            true, true, true,
            true, AuthorityUtils.commaSeparatedStringToAuthorityList(roles));
    }
}
