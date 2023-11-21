package com.musicalist.intermediator.intermediator.Controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.musicalist.intermediator.intermediator.DTO.UsuarioDTO;
import com.musicalist.intermediator.intermediator.Servicios.UsuarioService;
import com.musicalist.intermediator.intermediator.seguridad.JWTGenerator;
import org.springframework.security.core.AuthenticationException;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    Canciones_likeController voto;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTGenerator jwtGenerator;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UsuarioService usuarioService;

    @CrossOrigin
    @GetMapping("/find/correo/{correo}")
    @Operation(summary = "Obtener un usuario por correo")
    public ResponseEntity<UsuarioDTO> findByCorreo(@PathVariable String correo) {
        return ResponseEntity.ok(usuarioService.BuscarCorreo(correo));
    }

    @Operation(summary = "Agregar un Usuario")
    @PostMapping("/add")
    public ResponseEntity<UsuarioDTO> agregar(@RequestBody UsuarioDTO user) {
        if (user == null) {
            ResponseEntity<UsuarioDTO> response = new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
            return response;
        }
        user.setContrasenia(passwordEncoder.encode(user.getContrasenia()));
        usuarioService.GuardarUsuario(user);
        ResponseEntity<UsuarioDTO> response = new ResponseEntity<>(user, HttpStatus.CREATED);
        return response;
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los usuarios")
    public ResponseEntity<List<UsuarioDTO>> getAll() {
        List<UsuarioDTO> usuarios = usuarioService.Todos();
        ResponseEntity<List<UsuarioDTO>> response = new ResponseEntity<>(usuarios, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> borrar(@PathVariable Integer id) {
        if (id != null) {
            UsuarioDTO usuario = usuarioService.BuscarID(id);
            voto.deleteByUsuarioId(usuario.getId());
            usuarioService.borrar(usuario);
            return new ResponseEntity<>("Usuario eliminado", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("ID de usuario nulo", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find/id/{id}")
    @Operation(summary = "Obtener un usuario por id")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id) {
        UsuarioDTO usuario = usuarioService.BuscarID(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/modificar")
    public ResponseEntity<UsuarioDTO> modificar(@RequestBody UsuarioDTO user) {
        UsuarioDTO Final = usuarioService.BuscarID(user.getId());
        Final.setNombre(user.getNombre());
        Final.setCorreo(user.getCorreo());
        Final.setRol(user.getRol());
        if(user.getContrasenia()!=null)
        {
            if(user.getContrasenia().trim()!="")
            {
                Final.setContrasenia(passwordEncoder.encode(user.getContrasenia()));
            }
        }
        usuarioService.GuardarUsuario(Final);
        return ResponseEntity.ok(Final);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UsuarioDTO user) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getCorreo(), user.getContrasenia()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            UsuarioDTO BuscarRol = usuarioService.BuscarCorreo(user.getCorreo());
            if (BuscarRol != null) {
                String token = jwtGenerator.generateToken(auth, BuscarRol.getRol());
                return new ResponseEntity<>(token, HttpStatus.OK);
            }
            return new ResponseEntity<>("Credenciales erroneas", HttpStatus.OK);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Credenciales erroneas", HttpStatus.OK);
        }
    }

    @GetMapping("/details")
    public ResponseEntity<UsuarioDTO> buscarUser() {
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        UsuarioDTO user = usuarioService.BuscarCorreo(correo);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}