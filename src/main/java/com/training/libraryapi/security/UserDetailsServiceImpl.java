package com.training.libraryapi.security;

import com.training.libraryapi.domain.Usuario;
import com.training.libraryapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Usuario> user = userRepository.findByUsuario(userName);
        user.orElseThrow(() -> new UsernameNotFoundException(userName + "not found."));
        return user.map(UserDetailsImpl::new).get();
    }

}
