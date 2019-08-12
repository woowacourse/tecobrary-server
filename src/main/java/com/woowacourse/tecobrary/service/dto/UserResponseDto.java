package com.woowacourse.tecobrary.service.dto;

import com.woowacourse.tecobrary.domain.Role;
import com.woowacourse.tecobrary.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private String avatar;
    private List<Role> roles;

    @Builder
    public UserResponseDto(Long id, String email, String name, String avatar, List<Role> roles) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.avatar = avatar;
        this.roles = roles;
    }

    public static UserResponseDto from  (User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .avatar(user.getAvatar())
                .roles(user.getRoles())
                .build();
    }
}
