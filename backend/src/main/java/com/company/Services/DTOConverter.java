package com.company.Services;

import com.company.DTOs.UserWithoutPassword;
import com.company.Entities.User;

public class DTOConverter {
    public UserWithoutPassword turnUserIntoDto(User UserToTransform) {
        return new UserWithoutPassword(UserToTransform.getUserId(), UserToTransform.getUsername(), UserToTransform.getDescription(), UserToTransform.getUserVideos());
    }
}
