package com.bogdan.courierapp.security;

import com.bogdan.courierapp.entity.AppRole;
import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.security.core.userdetails.User.withUsername;


//@Service
//@RequiredArgsConstructor
public class UserDetailsServiceImpl{

//    public final CourierRepository courierRepository;
//
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Courier courier = courierRepository.findByUsername(username);
//        if (courier == null) {
//            throw new UsernameNotFoundException("User with username '" + username + "' not found");
//        }
//
//        return withUsername(username)
//                .username(courier.getCourierName())
//                .password(courier.getPassword())
//                .authorities(courier.getAppRole().getName())
//                .build();
//    }


    private Collection<? extends GrantedAuthority> getAuthorities(Collection<AppRole> collection) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (AppRole role : collection) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));

        }

        return authorities;
    }
}
