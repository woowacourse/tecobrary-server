package com.woowacourse.tecobrary.service;

import com.woowacourse.tecobrary.controller.dto.NewUserNameRequestDto;
import com.woowacourse.tecobrary.domain.*;
import com.woowacourse.tecobrary.service.dto.UserResponseDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::from)
                .collect(Collectors.toList());
    }

    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        return UserResponseDto.from(user);
    }

    public User saveCurrentGithubUser() {
        Role role = roleRepository.findByName(Role.Type.ROLE_NONE.name())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 역할입니다."));
        User user = UserFactory.makeGithubMember(role);
        if (userRepository.findByGithubId(user.getGithubId()).isPresent()) {
            throw new IllegalArgumentException("이미 등록된 사용자입니다.");
        }
        User savedUser = userRepository.save(user);
        changeAuthentication(savedUser);
        return savedUser;
    }

    private void changeAuthentication(User user) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        user.getName(), null, user.getAuthorities()));
    }

    @Transactional
    public User updateUserName(Long id, NewUserNameRequestDto newUserName) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        user.updateName(newUserName.getNewUserName());
        return user;
    }

    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
