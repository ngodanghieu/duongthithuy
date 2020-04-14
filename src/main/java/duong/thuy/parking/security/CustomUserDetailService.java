package duong.thuy.parking.security;

import duong.thuy.parking.entities.Credentials;
import duong.thuy.parking.repository.CredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
       Credentials user = credentialsRepository.findAllByUserName(phone).get(0);
        if (user != null) {
            Set<GrantedAuthority> roles = new HashSet<>();
            roles.add(new SimpleGrantedAuthority(user.getRole()));
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(roles);
            return new org.springframework.security.core.userdetails.User(phone, user.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException("user get email " + phone + " does not exist.");
        }
    }
}
