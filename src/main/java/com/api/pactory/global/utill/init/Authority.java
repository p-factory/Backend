package com.api.pactory.global.utill.init;

public enum Authority {
    ROLE_ADMIN(1L), ROLE_GUEST(2L), ROLE_USER(3L);

    private final Long id;

    Authority(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}