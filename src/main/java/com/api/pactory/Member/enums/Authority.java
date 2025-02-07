package com.api.pactory.Member.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    ROLE_ADMIN(1L), ROLE_GUEST(2L), ROLE_USER(3L);

    private final Long id;

    Authority(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}