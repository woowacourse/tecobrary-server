package com.woowacourse.tecobrary.service;

import com.woowacourse.tecobrary.domain.User;
import com.woowacourse.tecobrary.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return Collections.unmodifiableList(userRepository.findAll());
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
    }

    public void saveCurrentGithubUser() {
        User user = User.registerGithubUser();
        if (userRepository.findByGithubId(user.getGithubId()).isPresent()) {
            throw new IllegalArgumentException("이미 등록된 사용자입니다.");
        }
        userRepository.save(user);
        log.debug("User saved : {}", user);
    }

    @Transactional
    public void updateUserName(Long id, String newName) {
        User user = findById(id);
        user.updateName(newName);
        log.debug("User updated : {}", user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}