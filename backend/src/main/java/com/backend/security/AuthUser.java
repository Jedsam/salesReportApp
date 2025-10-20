package com.backend.security;

import java.util.List;

import com.backend.common.enums.Role;

public record AuthUser(String userId, List<Role> roles) {
}
