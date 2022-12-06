package com.training.libraryapi.service;

import com.training.libraryapi.domain.UserLogin;
import com.training.libraryapi.domain.Usuario;
import com.training.libraryapi.repository.UsuarioRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // public Usuario CadastrarUsuario(Usuario usuario) {

    public Optional<Usuario> CadastrarUsuario(Usuario usuario) {

        if (repository.findByUsuario(usuario.getUsuario()).isPresent())
            return null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String senhaEncoder = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaEncoder);

        return Optional.of(repository.save(usuario));
        //return repository.save(usuario);
    }

    public Optional<UserLogin> Logar(Optional<UserLogin> user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());

        if (usuario.isPresent()) {
            if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

                String auth = user.get().getUsuario() + ":" + user.get().getSenha();
                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);

                user.get().setToken(authHeader);
                user.get().setId(usuario.get().getId());
                user.get().setNome(usuario.get().getNome());
                user.get().setEmail(usuario.get().getEmail());
                //user.get().setFoto(usuario.get().getEmail());
                //user.get().setTipo(usuario.get().getTipo());
								
				/*
				user.get().setNome(usuario.get().getNome());
				user.get().setSenha(usuario.get().getSenha());*/
                return user;
            }
        }
        return null;

    }

}
