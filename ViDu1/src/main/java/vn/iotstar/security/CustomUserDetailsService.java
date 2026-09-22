package vn.iotstar.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import vn.iotstar.repository.UserRepository;

@Service @RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository users;
    @Override public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var u = users.findByEmailWithRole(email).orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản"));
        return User.withUsername(u.getEmail()).password(u.getPassword()).roles(u.getRole().getName()).disabled(!u.isEnabled()).build();
    }
}
