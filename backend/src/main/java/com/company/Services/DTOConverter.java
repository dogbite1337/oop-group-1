package com.company.Services;

import com.company.DTOs.UserWithoutPassword;
import com.company.DTOs.VideoWithUsername;
import com.company.Entities.User;
import com.company.Entities.Video;

public class DTOConverter {
    public UserWithoutPassword turnUserIntoDto(User UserToTransform) {
        return new UserWithoutPassword(UserToTransform.getUserId(), UserToTransform.getUsername(), UserToTransform.getDescription(), UserToTransform.getUserVideos(), UserToTransform.getProfileURL());
    }
}
