package com.project.sales.model;

import lombok.Getter;

@Getter
public enum UserRoles {
    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRoles(String user) {
        this.role = user;
    }
}
