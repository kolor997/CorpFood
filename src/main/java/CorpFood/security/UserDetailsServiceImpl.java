package CorpFood.security;

import CorpFood.model.entity.User;
import CorpFood.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       User findByLogin = userRepository.findOneByLogin(s);
       if(findByLogin == null){
           throw new UsernameNotFoundException(s);
       }
        return new UserDetailsImpl(findByLogin.getLogin(),findByLogin.getPassword());
    }
}
