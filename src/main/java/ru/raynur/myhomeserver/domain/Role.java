package ru.raynur.myhomeserver.domain;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.raynur.myhomeserver.domain.Permission;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    CLIENT(Set.of(Permission.CLIENT_SCOPE)),
    ADMIN(Set.of(Permission.CLIENT_SCOPE, Permission.MODERATOR_SCOPE));

    @Getter
    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}