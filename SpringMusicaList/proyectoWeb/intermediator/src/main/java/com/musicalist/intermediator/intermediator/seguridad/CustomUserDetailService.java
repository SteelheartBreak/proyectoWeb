package com.musicalist.intermediator.intermediator.seguridad;

import java.util.Collection;
import java.util.stream.Collectors;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.musicalist.intermediator.intermediator.Modelo.Usuario;
import com.musicalist.intermediator.intermediator.Repositorio.UsuarioRepositorio;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario userDB = userRepository.findByCorreo(username).orElseThrow(
            () -> new UsernameNotFoundException("User not found")
        );

        List<String> roles = List.of(userDB.getRol());
        UserDetails userDetails = new User (userDB.getCorreo(),
            userDB.getContrasenia(),
            getAuthorities(roles));
        
        return userDetails;
    }

    private Collection <GrantedAuthority> getAuthorities(List<String> roles) {
        Collection <GrantedAuthority> role = roles
            .stream()
            .map(r -> new SimpleGrantedAuthority(r))
            .collect(Collectors.toList());
        return role;
    }
    
}
