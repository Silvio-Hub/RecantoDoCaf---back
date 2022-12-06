package com.training.libraryapi.controller;

import com.training.libraryapi.domain.UserLogin;
import com.training.libraryapi.domain.Usuario;
import com.training.libraryapi.repository.UsuarioRepository;
import com.training.libraryapi.service.UsuarioService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Log4j2
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {

        ResponseEntity<List<Usuario>> response = ResponseEntity.ok(repository.findAll());
        log.info(response.getBody());
        return response;
    }


    @PostMapping("/logar")
    public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user) {
        return usuarioService.Logar(user).map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> post(@RequestBody Usuario usuario) {
        Optional<Usuario> user = usuarioService.CadastrarUsuario(usuario);
        try {
            return ResponseEntity.ok(user.get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        // return ResponseEntity.status(HttpStatus.CREATED)
        // .body(usuarioService.CadastrarUsuario(usuario));
    }

}
