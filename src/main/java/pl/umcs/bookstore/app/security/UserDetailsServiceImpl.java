package pl.umcs.bookstore.app.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.umcs.bookstore.app.security.model.CustomUserDetails;
import pl.umcs.bookstore.app.user.domain.User;
import pl.umcs.bookstore.app.user.domain.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return new CustomUserDetails(user);
    }
}
