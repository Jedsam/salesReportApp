package com.backend.security.user;

import java.util.List;

import com.backend.common.enums.Role;

public record AuthUser(String userId, List<Role> roles) {
}
