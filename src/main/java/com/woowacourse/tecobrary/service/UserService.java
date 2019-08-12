package com.woowacourse.tecobrary.service;

import com.woowacourse.tecobrary.controller.dto.NewUserNameRequestDto;
import com.woowacourse.tecobrary.domain.RoleRepository;
import com.woowacourse.tecobrary.domain.User;
import com.woowacourse.tecobrary.domain.UserRepository;
import com.woowacourse.tecobrary.service.dto.UserResponseDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::from)
                .collect(Collectors.toList());
    }

    public Optional<User> findByGithubId(int githubId) {
        return userRepository.findByGithubId(githubId);
    }

    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        return UserResponseDto.from(user);
    }

    public User save(User user) {
        return userRepository.save(user);
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
