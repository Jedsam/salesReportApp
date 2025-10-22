package com.backend.security.user;

import java.util.List;
import java.util.UUID;

import com.backend.common.enums.Role;

public record AuthUser(UUID userId, List<Role> roles) {
}
