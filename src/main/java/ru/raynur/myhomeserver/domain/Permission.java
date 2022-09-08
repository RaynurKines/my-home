package ru.raynur.myhomeserver.domain;

public enum Permission {
    CLIENT_SCOPE("scope:client"),
    MODERATOR_SCOPE("scope:moderator");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
