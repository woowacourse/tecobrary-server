package com.woowacourse.tecobrary.service;

import com.woowacourse.tecobrary.domain.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> findAll() {
        return Collections.unmodifiableList(userRepository.findAll());
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }

    public void saveCurrentGithubUser() {
        Role role = roleRepository.findByName(Role.Type.ROLE_NONE.name())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 역할입니다."));
        User user = UserFactory.makeGithubMember(role);
        if (userRepository.findByGithubId(user.getGithubId()).isPresent()) {
            throw new IllegalArgumentException("이미 등록된 사용자입니다.");
        }
        User savedUser = userRepository.save(user);
        changeAuthentication(savedUser);
    }

    private void changeAuthentication(User user) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        user.getName(), null, user.getAuthorities()));
    }

    @Transactional
    public void updateUserName(Long id, String newName) {
        User user = findById(id);
        user.updateName(newName);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
