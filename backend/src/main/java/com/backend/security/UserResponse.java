package com.backend.security;

import java.util.List;
import com.backend.common.enums.Role;

public record UserResponse(Long id, List<Role> roles) {

}
