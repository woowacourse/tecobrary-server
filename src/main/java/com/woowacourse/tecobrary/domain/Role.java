package com.woowacourse.tecobrary.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor
public class Role {
    private static final int PREFIX_LENGTH = 5;

    public enum Type {
        ROLE_NONE(Collections.emptyList()),
        ROLE_MEMBER(Arrays.asList(Privilege.Type.READ_PRIVILEGE)),
        ROLE_MASTER(Arrays.asList(Privilege.Type.READ_PRIVILEGE, Privilege.Type.WRITE_PRIVILEGE));

        List<Privilege.Type> privileges;

        Type(List<Privilege.Type> privileges) {
            this.privileges = privileges;
        }

        public String getTypeWithOutPrefix() {
            return this.name().substring(PREFIX_LENGTH);
        }

        public List<Privilege.Type> getPrivileges() {
            return privileges;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    @Builder
    public Role(String name, Collection<User> users, Collection<Privilege> privileges) {
        this.name = name;
        this.users = users;
        this.privileges = new ArrayList<>(privileges);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}