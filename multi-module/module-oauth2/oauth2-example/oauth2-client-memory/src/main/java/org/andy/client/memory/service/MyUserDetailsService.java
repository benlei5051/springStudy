package org.andy.client.memory.service;

import org.andy.client.memory.domain.Role;
import org.andy.client.memory.domain.User;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

/**
 * Created by lxg
 * on 2017/2/20.
 */
public class MyUserDetailsService implements UserDetailsService {

    private final static Set<User> users = new HashSet<>();

//    必须配置大写ROLE_,接口限制，例如   @PreAuthorize("hasRole('ADMIN')")，不需要写成 @PreAuthorize("hasRole('ROLE_ADMIN')")
    private final String role = "ROLE_";

    static {
        Role role1 = new Role(1, "ADMIN");
        Role role2 = new Role(1, "EMPLOY");
        Role role3 = new Role(1, "MANAGER");
        String password = new Md5PasswordEncoder().encodePassword("123456", null);
        users.add(new User(1, "test-user1", password, Arrays.asList(role1)));
        users.add(new User(2, "test-user2", password, Arrays.asList(role2)));
        users.add(new User(3, "test-user3", password, Arrays.asList(role3)));
        users.add(new User(4, "test-user4", password, Arrays.asList(role1, role2)));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = users.stream()
                .filter((u) -> u.getUsername().equals(s))
                .findFirst();
        if (!user.isPresent())
            throw new UsernameNotFoundException("there's no user founded!");
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        Iterator<Role> iterator = user.get().getList().iterator();
        while (iterator.hasNext()) {
            collection.add(new SimpleGrantedAuthority(role + iterator.next().getRole_name()));
        }
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), collection);
    }
}
