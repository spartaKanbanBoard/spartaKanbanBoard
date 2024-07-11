package com.sparta.spartakanbanboard.domain.user.entity;

public enum UserRole {
    USER(Authority.USER),  // 사용자 권한
    ADMIN(Authority.MANAGER);  // 관리자 권한

    private final String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String MANAGER = "ROLE_MANAGER";
    }
}