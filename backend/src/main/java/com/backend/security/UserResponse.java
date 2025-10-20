package com.backend.security;

import java.util.List;
import com.backend.common.enums.Role;

public record UserResponse(String id, List<Role> roles) {

}
