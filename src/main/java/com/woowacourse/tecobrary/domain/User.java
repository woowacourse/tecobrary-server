package com.woowacourse.tecobrary.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class User {
    private static final int EMAIL_LENGTH = 100;
    private static final int NAME_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int githubId;

    @Email
    @Column(length = EMAIL_LENGTH)
    private String email;

    @Column(length = NAME_LENGTH)
    private String name;

    private String avatar;

    private Enum authorization;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    @Builder
    public User(int githubId, String email, String name, String avatar) {
        this.githubId = githubId;
        this.email = email;
        this.name = name;
        this.avatar = avatar;
    }

    public static User registerGithubUser() {
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> map = (HashMap<String, String>) authentication.getUserAuthentication().getDetails();
        Object githubId = map.get("id");
        int id = Integer.parseInt(githubId.toString());

        return User.builder()
                .githubId(id)
                .email(map.getOrDefault("email", null))
                .name(map.getOrDefault("name", null))
                .avatar(map.getOrDefault("avatar_url", null))
                .build();
    }

    public void updateName(String newName) {
        this.name = newName;
    }
}
