package com.company.Services;

import com.company.DTOs.UserWithoutPassword;
import com.company.DTOs.VideoWithUsername;
import com.company.Entities.User;
import com.company.Entities.Video;

import java.util.HashMap;

public class DTOConverter {
    public UserWithoutPassword turnUserIntoDto(User UserToTransform) {
        return new UserWithoutPassword(UserToTransform.getUserId(), UserToTransform.getUsername(), UserToTransform.getDescription(), UserToTransform.getUserVideos(), UserToTransform.getProfileURL());
    }

    public VideoWithUsername turnVideoIntoDto(Video VideoToTransform, HashMap<Integer, String> mappedUsers) {
        return new VideoWithUsername(VideoToTransform.getVideoId(), VideoToTransform.getUserId(), VideoToTransform.getLink(), VideoToTransform.getTitle(), VideoToTransform.getDescription(), mappedUsers.get(VideoToTransform.getUserId()));
    }
}
