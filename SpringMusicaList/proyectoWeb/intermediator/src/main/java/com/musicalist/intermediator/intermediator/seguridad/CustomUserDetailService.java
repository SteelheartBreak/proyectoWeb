package com.musicalist.intermediator.intermediator.seguridad;

import java.util.Collection;
import java.util.stream.Collectors;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.musicalist.intermediator.intermediator.DTO.UsuarioDTO;
import com.musicalist.intermediator.intermediator.Servicios.UsuarioService;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final  UsuarioService usuarioService;
    @Autowired
    public CustomUserDetailService(UsuarioService usuarioService)
    {
        this.usuarioService=usuarioService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioDTO userDB = usuarioService.BuscarCorreo(username);
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
